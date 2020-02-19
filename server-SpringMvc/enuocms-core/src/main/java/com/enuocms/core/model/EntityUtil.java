package com.enuocms.core.model;

import java.util.Random;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public class EntityUtil {

	private static final long baseTime = 1420041600000L;
	private static final Random random = new Random();
	
	public static long getAutoId(){
		return (System.currentTimeMillis() - baseTime) * 1000  + random.nextInt(1000);
	}
}
