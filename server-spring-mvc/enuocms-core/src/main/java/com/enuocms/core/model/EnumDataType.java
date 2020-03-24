package com.enuocms.core.model;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public interface EnumDataType {

	/**
	 * 获取代码
	 * @return
	 */
	public int getCode();
	
	/**
	 * 获取名称
	 * @return
	 */
	public String getDisplay();
	
	public String getValue();

}
