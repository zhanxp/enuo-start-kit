/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.enuocms.core.mapper;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Mybatis - 通用分页拦截器
 *
 * @author liuzh/abel533/isea533
 * @version 3.3.0
 *          项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PageHelper implements Interceptor {
    
    //分页的id后缀
    String SUFFIX_PAGE = "_PageHelper";
    //count查询的id后缀
    String SUFFIX_COUNT = SUFFIX_PAGE + "_Count";
    //第一个分页参数
    String PAGEPARAMETER_FIRST = "First" + SUFFIX_PAGE;
    //第二个分页参数
    String PAGEPARAMETER_SECOND = "Second" + SUFFIX_PAGE;
    
    String PROVIDER_OBJECT = "_provider_object";
    //存储原始的参数
    String ORIGINAL_PARAMETER_OBJECT = "_ORIGINAL_PARAMETER_OBJECT";
    
    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(0);

     /**
     * Mybatis拦截器方法
     *
     * @param invocation 拦截器入参
     * @return 返回执行结果
     * @throws Throwable 抛出异常
     */
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        RowBounds rowBounds = (RowBounds) args[2];
        if (rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        } else {
            //忽略RowBounds-否则会进行Mybatis自带的内存分页
            args[2] = RowBounds.DEFAULT;
            
            //获取原始的ms
            MappedStatement ms = (MappedStatement) args[0];
            SqlSource sqlSource = ms.getSqlSource();
            
            //pageSize>0的时候执行分页查询，pageSize<=0的时候不执行相当于可能只返回了一个count
            //将参数中的MappedStatement替换为新的qs
            processPageMappedStatement(ms, sqlSource, args, rowBounds);
            //返回结果
            return invocation.proceed();
        }
    }
    
    /**
     * 处理分页查询的MappedStatement
     *
     * @param ms
     * @param sqlSource
     * @param page
     * @param args
     */
    public void processPageMappedStatement(MappedStatement ms, SqlSource sqlSource, Object[] args, RowBounds rowBounds) {
        args[0] = getMappedStatement(ms, sqlSource, args[1], SUFFIX_PAGE);
        //处理入参
        args[1] = setPageParameter((MappedStatement) args[0], args[1], rowBounds);
    }
    
    /**
     * 获取ms - 在这里对新建的ms做了缓存，第一次新增，后面都会使用缓存值
     *
     * @param ms
     * @param sqlSource
     * @param suffix
     * @return
     */
    public MappedStatement getMappedStatement(MappedStatement ms, SqlSource sqlSource, Object parameterObject, String suffix) {
        MappedStatement qs = null;
        if (ms.getId().endsWith(SUFFIX_PAGE) || ms.getId().endsWith(SUFFIX_COUNT)) {
            throw new RuntimeException("分页插件配置错误:请不要在系统中配置多个分页插件(使用Spring时,mybatis-config.xml和Spring<bean>配置方式，请选择其中一种，不要同时配置多个分页插件)！");
        }
        try {
            qs = ms.getConfiguration().getMappedStatement(ms.getId() + suffix);
        } catch (Exception e) {
            //ignore
        }
        if (qs == null) {
            //创建一个新的MappedStatement
            qs = newMappedStatement(ms, getsqlSource(ms, sqlSource, parameterObject, suffix == SUFFIX_COUNT), suffix);
            try {
                ms.getConfiguration().addMappedStatement(qs);
            } catch (Exception e) {
                //ignore
            }
        }
        return qs;
    }
    
    /**
     * 新建count查询和分页查询的MappedStatement
     *
     * @param ms
     * @param sqlSource
     * @param suffix
     * @return
     */
    public MappedStatement newMappedStatement(MappedStatement ms, SqlSource sqlSource, String suffix) {
        String id = ms.getId() + suffix;
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), id, sqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        if (suffix == SUFFIX_PAGE) {
            builder.resultMaps(ms.getResultMaps());
        } else {
            //count查询返回值int
            List<ResultMap> resultMaps = new ArrayList<ResultMap>();
            ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), id, int.class, EMPTY_RESULTMAPPING).build();
            resultMaps.add(resultMap);
            builder.resultMaps(resultMaps);
        }
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }
    
    /**
     * 获取新的sqlSource
     *
     * @param ms
     * @param sqlSource
     * @param parameterObject
     * @param count
     * @return
     */
    public SqlSource getsqlSource(final MappedStatement ms, final SqlSource sqlSource, Object parameterObject, boolean count) {
        if (sqlSource instanceof DynamicSqlSource) {//动态sql
            MetaObject msObject = SystemMetaObject.forObject(ms);
            SqlNode sqlNode = (SqlNode) msObject.getValue("sqlSource.rootSqlNode");
            final MixedSqlNode mixedSqlNode;
            if (sqlNode instanceof MixedSqlNode) {
                mixedSqlNode = (MixedSqlNode) sqlNode;
            } else {
                List<SqlNode> contents = new ArrayList<SqlNode>(1);
                contents.add(sqlNode);
                mixedSqlNode = new MixedSqlNode(contents);
            }
            return new SqlSource(){
                
                public BoundSql getBoundSql(Object parameterObject) {
                    Configuration configuration = ms.getConfiguration();
                    SqlNode rootSqlNode = mixedSqlNode;
                    
                    DynamicContext context;
                    //由于增加分页参数后会修改parameterObject的值，因此在前面处理时备份该值
                    //如果发现参数是Map并且包含该KEY，就使用备份的该值
                    if (parameterObject != null
                            && parameterObject instanceof Map
                            && ((Map) parameterObject).containsKey(ORIGINAL_PARAMETER_OBJECT)) {
                        context = new DynamicContext(configuration, ((Map) parameterObject).get(ORIGINAL_PARAMETER_OBJECT));
                    } else {
                        context = new DynamicContext(configuration, parameterObject);
                    }
                    rootSqlNode.apply(context);
                    SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
                    Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
                    SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
                    sqlSource = getStaticPageSqlSource(configuration, sqlSource, parameterObject);

                    BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
                    //设置条件参数
                    for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
                        boundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
                    }
                    return boundSql;
                }
            };
        } else if (sqlSource instanceof ProviderSqlSource) {//注解式sql
            return new SqlSource(){

                @Override
                public BoundSql getBoundSql(Object parameterObject) {
                    Configuration configuration = ms.getConfiguration();
                    BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
                    return new BoundSql(
                            configuration,
                            getPageSql(boundSql.getSql()),
                            getPageParameterMapping(configuration, boundSql),
                            parameterObject);
                }
                
            };
        } else {
            return getStaticPageSqlSource(ms.getConfiguration(), sqlSource, parameterObject);
        }
    }
    
    /**
     * 获取静态的分页SqlSource
     * <br/>
     * ParameterMappings需要增加分页参数的映射
     *
     * @param configuration
     * @param sqlSource
     * @param parameterObject
     * @return
     */
    public SqlSource getStaticPageSqlSource(Configuration configuration, SqlSource sqlSource, Object parameterObject) {
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        return new StaticSqlSource(configuration, getPageSql(boundSql.getSql()), getPageParameterMapping(configuration, boundSql));
    }
    
    public List<ParameterMapping> getPageParameterMapping(Configuration configuration, BoundSql boundSql) {
        List<ParameterMapping> newParameterMappings = new ArrayList<ParameterMapping>();
        if (boundSql.getParameterMappings() != null) {
            newParameterMappings.addAll(boundSql.getParameterMappings());
        }
        newParameterMappings.add(new ParameterMapping.Builder(configuration, PAGEPARAMETER_FIRST, Integer.class).build());
        newParameterMappings.add(new ParameterMapping.Builder(configuration, PAGEPARAMETER_SECOND, Integer.class).build());
        return newParameterMappings;
    }
    
    /**
     * 设置分页参数
     *
     * @param parameterObject
     * @param page
     * @return
     */
    public Map setPageParameter(MappedStatement ms, Object parameterObject, RowBounds rowBounds) {
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        return setPageParameter(ms, parameterObject, boundSql, rowBounds);
    }
    
    public String getPageSql(String sql) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        sqlBuilder.append(sql);
        sqlBuilder.append(" limit ?,?");
        return sqlBuilder.toString();
    }
    
    public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, RowBounds rowBounds) {
        Map paramMap = null;
        if (parameterObject == null) {
            paramMap = new HashMap();
        } else if (parameterObject instanceof Map) {
            //解决不可变Map的情况
            paramMap = new HashMap();
            paramMap.putAll((Map) parameterObject);
        } else {
            paramMap = new HashMap();
            //动态sql时的判断条件不会出现在ParameterMapping中，但是必须有，所以这里需要收集所有的getter属性
            //TypeHandlerRegistry可以直接处理的会作为一个直接使用的对象进行处理
            boolean hasTypeHandler = ms.getConfiguration().getTypeHandlerRegistry().hasTypeHandler(parameterObject.getClass());
            MetaObject metaObject = SystemMetaObject.forObject(parameterObject);
            if (!hasTypeHandler) {
                for (String name : metaObject.getGetterNames()) {
                    paramMap.put(name, metaObject.getValue(name));
                }
            }
            //下面这段方法，主要解决一个常见类型的参数时的问题
            if (boundSql.getParameterMappings() != null && boundSql.getParameterMappings().size() > 0) {
                for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
                    String name = parameterMapping.getProperty();
                    if (!name.equals(PAGEPARAMETER_FIRST)
                            && !name.equals(PAGEPARAMETER_SECOND)
                            && paramMap.get(name) == null) {
                        if (hasTypeHandler
                                || parameterMapping.getJavaType().equals(parameterObject.getClass())) {
                            paramMap.put(name, parameterObject);
                            break;
                        }
                    }
                }
            }
        }
        //备份原始参数对象
        paramMap.put(ORIGINAL_PARAMETER_OBJECT, parameterObject);
        paramMap.put(PAGEPARAMETER_FIRST, rowBounds.getOffset());
        paramMap.put(PAGEPARAMETER_SECOND, rowBounds.getLimit());
        return paramMap;
    }

    /**
     * 只拦截Executor
     *
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties p) {
         
    }

}
