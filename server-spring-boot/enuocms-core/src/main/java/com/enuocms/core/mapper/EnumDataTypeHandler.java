package com.enuocms.core.mapper;

import com.enuocms.core.model.EnumDataType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public class EnumDataTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> type;

    private final E[] enums;

    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     * @param type 配置文件中设置的转换类
     */
    public EnumDataTypeHandler(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.enums = type.getEnumConstants();
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位State子类
            return locateState(rs.getInt(columnName));
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位State子类
            return locateState(rs.getInt(columnIndex));
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位State子类
            return locateState(cs.getInt(columnIndex));
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
            throws SQLException {
        // baseTypeHandler已经帮我们做了parameter的null判断
    	if(parameter instanceof EnumDataType) {
    		ps.setInt(i, ((EnumDataType)parameter).getCode());
    	} else {	
    		ps.setInt(i, parameter.ordinal());
    	}
    }
    
    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷
     * @param code 数据库中存储的自定义code属性
     * @return code对应的枚举类
     */
    private E locateState(int code) {
    	if(!EnumDataType.class.isAssignableFrom(type)) {
			try {
				return enums[code];
			} catch (Exception ex) {
				throw new IllegalArgumentException("Cannot convert " + code
						+ " to " + type.getSimpleName() + " by ordinal value.",	ex);
			}
    	}
    	
        for(E status : enums) {
            if((status instanceof EnumDataType) && ((EnumDataType)status).getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + code + ",请核对" + type.getSimpleName());
    }

}
