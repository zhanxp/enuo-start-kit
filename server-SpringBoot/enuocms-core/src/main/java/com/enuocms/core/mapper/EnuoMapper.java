package com.enuocms.core.mapper;

import tk.mybatis.mapper.common.*;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public interface EnuoMapper<T>extends BaseMapper<T>,
        ExampleMapper<T>,
        RowBoundsMapper<T>,
        MySqlMapper<T>{
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}