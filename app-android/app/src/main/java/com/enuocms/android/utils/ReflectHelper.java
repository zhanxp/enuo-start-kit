package com.enuocms.android.utils;

import java.util.Collection;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class ReflectHelper {

    /*
    * @function     根据类.class，创建对象
    * @attention    指定对象包含无参构造方法
    * */
    public static <T> T createInstance(Class<T> clazz) {
        if (clazz == null)
            return null;
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /*
    * @function     判断类.class，为Object
    * */
    public static boolean isObjectType(Class<?> clazz) {
        return clazz != null && !isSingleType(clazz) && !isArrayType(clazz)
                && !isCollectionType(clazz);
    }

    /*
    * @function     判断类.class，为简单数据类型
    * */
    public static boolean isSingleType(Class<?> clazz) {
        return isBooleanType(clazz) || isNumberType(clazz) || isStringType(clazz);
    }

    /*
    * @function     判断类.class，为boolean
    * */
    public static boolean isBooleanType(Class<?> clazz) {
        return (clazz != null)
                && ((Boolean.TYPE.isAssignableFrom(clazz)) || (Boolean.class
                .isAssignableFrom(clazz)));
    }

    /*
    * @function     判断类.class，为number
    * */
    public static boolean isNumberType(Class<?> clazz) {
        return (clazz != null)
                && ((Byte.TYPE.isAssignableFrom(clazz))
                || (Short.TYPE.isAssignableFrom(clazz))
                || (Integer.TYPE.isAssignableFrom(clazz))
                || (Long.TYPE.isAssignableFrom(clazz))
                || (Float.TYPE.isAssignableFrom(clazz))
                || (Double.TYPE.isAssignableFrom(clazz)) || (Number.class
                .isAssignableFrom(clazz)));
    }

    /*
    * @function     判断类.class，为String
    * */
    public static boolean isStringType(Class<?> clazz) {
        return (clazz != null)
                && ((String.class.isAssignableFrom(clazz))
                || (Character.TYPE.isAssignableFrom(clazz)) || (Character.class
                .isAssignableFrom(clazz)));
    }

    /*
    * @function     判断类.class，为Array
    * */
    public static boolean isArrayType(Class<?> clazz) {
        return clazz != null && clazz.isArray();
    }

    /*
    * @function     判断类.class，为Collection
    * */
    public static boolean isCollectionType(Class<?> clazz) {
        return clazz != null && Collection.class.isAssignableFrom(clazz);
    }
}
