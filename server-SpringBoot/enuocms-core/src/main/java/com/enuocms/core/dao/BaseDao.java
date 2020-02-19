package com.enuocms.core.dao;

import com.enuocms.core.mapper.EnuoMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public abstract class BaseDao<T> {

    @Autowired
    protected EnuoMapper<T> mapper;
    
    public T get(Object key) {
    	return mapper.selectByPrimaryKey(key);
    }

    public int save(T entity){
        return mapper.insert(entity);
    }
    
    public int saveUseGeneratedKeys(T entity){
        return mapper.insertUseGeneratedKeys(entity);
    }
    
    /**
     * 批量插入，必须主键字段是id
     * @param list
     * @return
     */
    public int saveList(List<T> list) {
    	return mapper.insertList(list);
    }
    
    public int update(T entity) {
    	return mapper.updateByPrimaryKey(entity);
    }
    
    public int updateSelective(T entity) {
    	return mapper.updateByPrimaryKeySelective(entity);
    }
    
    public int updateByConditionSelective(T entity, Condition condition) {
    	return mapper.updateByExampleSelective(entity, condition);
    }

    public int delete(T entity){
        return mapper.delete(entity);
    }
    
    public int deleteByKey(T entity){
        return mapper.deleteByPrimaryKey(entity);
    }
    
    public int deleteByCondition(Condition condition){
        return mapper.deleteByExample(condition);
    }
    
    public List<T> select(T entity){
    	return mapper.select(entity);
    }
    
    public List<T> selectByCondition(Condition condition){
    	return mapper.selectByExample(condition);
    }
    
    public int count(T entity) {
    	return mapper.selectCount(entity);
    }
    
    public int countByCondition(Condition condition) {
    	return mapper.selectCountByExample(condition);
    }

    /**
     * 单表分页查询
     * 
     * @param condition		查询条件
     * @param pageNum		页码数
     * @param pageSize		每页条数
     * @return
     */
    public List<T> selectPage(Condition condition, int pageNum,int pageSize){
    	if(pageNum<=0 || pageSize<=0) {
    		return new ArrayList<T>();
    	}
		return mapper.selectByExampleAndRowBounds(condition, 
				new RowBounds((pageNum - 1) * pageSize, pageSize));
    }
    
    /**
     * 单表分页查询
     * 
     * @param condition		查询条件
     * @param pageNum		页码数
     * @param pageSize		每页条数
     * @return
     */
    public List<T> selectPage(T entity, int pageNum,int pageSize){
    	if(pageNum<=0 || pageSize<0) {
    		return new ArrayList<T>();
    	}
    	if(pageSize>0) {
			return mapper.selectByRowBounds(entity, 
					new RowBounds((pageNum - 1) * pageSize, pageSize));
    	} else {
    		return mapper.select(entity);
    	}
    }
}
