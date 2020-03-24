package com.enuocms.core.service;

import com.enuocms.core.model.PageResult;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public interface BaseService<T> {

    public T get(Object key) ;

    public int save(T entity);
    
    public int saveUseGeneratedKeys(T entity);
    
    public int update(T entity);
    
    public int updateSelective(T entity) ;
    
    public int updateSelectiveByCondition(T entity, Condition condition);

    public int delete(T entity);
    
    public int deleteByKey(T entity);
    
    public int deleteByCondition(Condition condition);
    
    public List<T> select(T entity);

    public T selectOne(T entity);
    
    public List<T> selectByCondition(Condition condition);
    
    public int count(T entity);
    
    public int countByCondition(Condition condition);

    /**
     * 单表分页查询
     * 
     * @param condition		查询条件
     * @param pageNum		页码数
     * @param pageSize		每页条数
     * @return
     */
//    public List<T> selectPage(Condition condition, int pageNum,int pageSize);
//
//    public List<T> selectPage(T entity, int pageNum,int pageSize);

    public PageResult<T> selectPage(Condition condition, int pageNum,int pageSize);

    public PageResult<T> selectPage(T entity, int pageNum, int pageSize);
}
