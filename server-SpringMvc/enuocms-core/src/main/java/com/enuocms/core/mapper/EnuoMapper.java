package com.enuocms.core.mapper;

import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.RowBoundsMapper;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public interface EnuoMapper<T> extends
		tk.mybatis.mapper.common.BaseMapper<T>,
	ExampleMapper<T>,
	RowBoundsMapper<T>,
	MySqlMapper<T> {

}
