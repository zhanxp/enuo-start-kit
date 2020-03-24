package com.enuocms.core.service;

import com.enuocms.core.dao.BaseDao;
import com.enuocms.core.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Component
public abstract class BaseServiceImpl<T> {

    @Autowired
    protected BaseDao<T> dao;

    public T get(Object key) {
        return dao.get(key);
    }

    public int save(T entity){
        return dao.save(entity);
    }

    public int saveUseGeneratedKeys(T entity){
        return dao.saveUseGeneratedKeys(entity);
    }

    public int update(T entity) {
        return dao.update(entity);
    }

    public int updateSelective(T entity) {
        return dao.updateSelective(entity);
    }

    public int updateSelectiveByCondition(T entity, Condition condition) {
        return dao.updateByConditionSelective(entity, condition);
    }

    public int delete(T entity){
        return dao.delete(entity);
    }

    public int deleteByKey(T entity){
        return dao.deleteByKey(entity);
    }

    public int deleteByCondition(Condition condition){
        return dao.deleteByCondition(condition);
    }

    public List<T> select(T entity){
        return dao.select(entity);
    }

    public T selectOne(T entity){
        List<T> list = dao.select(entity);
        if(list.size()>0){
            return list.get(0);
        }
        return  null;
    }

    public List<T> selectByCondition(Condition condition){
        return dao.selectByCondition(condition);
    }

    public int count(T entity) {
        return dao.count(entity);
    }

    public int countByCondition(Condition condition) {
        return dao.countByCondition(condition);
    }

    /**
     * 单表分页查询
     *
     * @param condition		查询条件
     * @param pageNum		页码数
     * @param pageSize		每页条数
     * @return
     */
    //    public List<T> selectPage(Condition condition, int pageNum,int pageSize){
//        return dao.selectPage(condition, pageNum, pageSize);
//    }

    public PageResult<T> selectPage(Condition condition, int pageNum, int pageSize){
        List<T> list = dao.selectPage(condition, pageNum, pageSize);
        Integer total = dao.countByCondition(condition);
        return new PageResult<>(pageNum,pageSize,total,list);
    }

    public PageResult<T> selectPage(T entity, int pageNum,int pageSize){
        List<T> list = dao.selectPage(entity, pageNum, pageSize);
        Integer total = dao.count(entity);
        return new PageResult<>(pageNum,pageSize,total,list);
    }
}
