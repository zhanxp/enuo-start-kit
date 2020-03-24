package com.enuocms.core.dao;


import com.enuocms.core.model.BaseEntity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public abstract class BaseIdEntityDao<T extends BaseEntity> extends BaseDao<T> {

    public T get(Object key) {
    	return mapper.selectByPrimaryKey(key);
    }

    public int save(T entity){
//    	if(entity.getId()==null){
//    		entity.setId(EntityUtil.getAutoId());
//    	}
    	initEntityPersistenceProperties(entity);
        return super.save(entity);
    }

	private void initEntityPersistenceProperties(T entity) {
		Timestamp now = getNow();
		entity.setCreateDate(now);
    	entity.setUpdateDate(now);
    	entity.setEntStatus(1);
	}

	private Timestamp getNow() {
		return new Timestamp(new Date().getTime());
	}
    
    public int saveUseGeneratedKeys(T entity){
    	initEntityPersistenceProperties(entity);
        return super.saveUseGeneratedKeys(entity);
    }
    
    public int update(T entity) {
    	entity.setUpdateDate(getNow());
    	return super.update(entity);
    }
    
    public int updateSelective(T entity) {
    	entity.setUpdateDate(getNow());
    	return super.updateSelective(entity);
    }

    public int delete(T entity){
        return super.delete(entity);
    }
    
}
