package com.enuocms.android.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class JSONHelper {

    /*
    * @funtion		JSONObject转换为Object
    * @support		基本数据类型、字符串、数组、集合
    * */
    public static <T> T toObject(JSONObject jsonObject, Class<T> objectClass) {
        if (objectClass == null || isNull(jsonObject)) {
            return null;
        }

        T obj = ReflectHelper.createInstance(objectClass);
        if (obj == null) {
            return null;
        }

        for (Field f : objectClass.getFields()) {
            setField(obj, f, jsonObject);
        }

        return obj;
    }

    /*
    * @funtion		JSONObject字符串转换为Object
    * */
    public static <T> T toObject(String jsonString, Class<T> objectClass) {
        if (objectClass == null || jsonString == null || jsonString.length() == 0) {
            return null;
        }
        JSONObject jo = null;
        try {
            jo = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (isNull(jo)) {
            return null;
        }

        return toObject(jo, objectClass);
    }

    /*
    * @funtion		Object转换为json字符串
    * */
    public static String fromObject(Object object) {
        JSONStringer js = new JSONStringer();
        from(js, object);
        return js.toString();
    }

    /*
    * @funtion		JSONArray转换为JSONObject集合
    * */
    public static List<JSONObject> toJSONObjectList(JSONArray jsonArray) {
        List<JSONObject> list = null;
        try {
            JSONObject jsonObject;
            list = new ArrayList<JSONObject>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                list.add(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
    * @funtion		将Object对象，转化为json串，底层实现
    * */
    private static void from(JSONStringer js, Object object) {
        if (isNull(object)) {
            try {
                js.value(null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }

        Class<?> clazz = object.getClass();
        if (ReflectHelper.isObjectType(clazz)) { //
            fromObject(js, object);
        } else if (ReflectHelper.isArrayType(clazz)) { //
            fromArray(js, object);
        } else if (ReflectHelper.isCollectionType(clazz)) { //
            Collection<?> collection = (Collection<?>) object;
            fromCollection(js, collection);
        } else { //
            try {
                js.value(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * @funtion		将array数组，转化为json串
    * */
    private static void fromArray(JSONStringer js, Object array) {
        try {
            js.array();
            for (int i = 0; i < Array.getLength(array); ++i) {
                Object o = Array.get(array, i);
                from(js, o);
            }
            js.endArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * @funtion		将Collection集合，转化为json串
    * */
    private static void fromCollection(JSONStringer js,
                                       Collection<?> collection) {
        try {
            js.array();
            for (Object o : collection) {
                from(js, o);
            }
            js.endArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * @funtion		将Object对象，转化为json串
    * */
    private static void fromObject(JSONStringer js, Object obj) {
        try {
            js.object();
            for (Field f : obj.getClass().getFields()) {
                Object o = f.get(obj);
                js.key(f.getName());
                from(js, o);
            }
            js.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * @funtion		根据类中字段类型和字段名称，从JSONObject中解析字段内容
    * */
    private static void setField(Object object, Field field, JSONObject jsonObject) {
        String fieldName = field.getName();
        Class<?> fieldClass = field.getType();

        try {
            if (ReflectHelper.isArrayType(fieldClass)) { //
                Class<?> fieldComponentClass = fieldClass.getComponentType();
                JSONArray fieldValue = jsonObject.optJSONArray(fieldName);
                if (!isNull(fieldValue)) {
                    Object fieldArray = toArray(fieldValue, fieldComponentClass);
                    field.set(object, fieldArray);
                }
            } else if (ReflectHelper.isCollectionType(fieldClass)) { //集合类型
                //解析ComponentClass
                Class<?> fieldComponentClass = null;
                Type fieldGenericType = field.getGenericType();
                if (fieldGenericType instanceof ParameterizedType) {
                    ParameterizedType ptype = (ParameterizedType) fieldGenericType;
                    Type[] targs = ptype.getActualTypeArguments();
                    if (targs != null && targs.length > 0) {
                        Type t = targs[0];
                        fieldComponentClass = (Class<?>) t;
                    }
                }
                //解析集合
                JSONArray fieldValue = jsonObject.optJSONArray(fieldName);
                if (!isNull(fieldValue)) {
                    Object fieldCollection = toCollection(fieldValue, fieldClass, fieldComponentClass);
                    field.set(object, fieldCollection);
                }
            } else if (ReflectHelper.isSingleType(fieldClass)) { //简单数据类型
                Object fieldValue = jsonObject.opt(fieldName);
                if (fieldValue != null && !isNull(fieldValue) && !jsonObject.isNull(fieldName)) {
                    field.set(object, fieldValue);
                }
            } else if (ReflectHelper.isObjectType(fieldClass)) { //对象数据类型
                JSONObject fieldValue = jsonObject.optJSONObject(fieldName);
                if (!isNull(fieldValue)) {
                    Object fieldObject = toObject(fieldValue, fieldClass);	//递归调用
                    field.set(object, fieldObject);
                }
            } else {
                throw new Exception("unknow type!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * @funtion		从JSONArray对象中，根据指定类.class，解析出数组
    * */
    private static <T> T[] toArray(JSONArray jsonArray, Class<T> clazz) {
        if (clazz == null || isNull(jsonArray)) {
            return null;
        }

        int len = jsonArray.length();

        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, len);

        for (int i = 0; i < len; ++i) {
            try {
                JSONObject jo = jsonArray.getJSONObject(i);
                T o = toObject(jo, clazz);
                array[i] = o;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return array;
    }

    /*
    * @funtion		从JSONArray字符串中，根据指定类.class，解析出数组
    * */
    private static <T> T[] toArray(String jsonArrayString, Class<T> clazz) {
        if (clazz == null || jsonArrayString == null || jsonArrayString.length() == 0) {
            return null;
        }
        JSONArray jo = null;
        try {
            jo = new JSONArray(jsonArrayString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (isNull(jo)) {
            return null;
        }

        return toArray(jo, clazz);
    }

    /*
    * @funtion		从JSONArray对象中，根据指定集合类.class、集合元素.class，解析出集合
    * */
    @SuppressWarnings("unchecked")
    private static <T> Collection<T> toCollection(JSONArray jsonArray,
                                                  Class<?> collectionClazz, Class<T> genericType) {

        if (collectionClazz == null || genericType == null || isNull(jsonArray)) {
            return null;
        }

        Collection<T> collection = (Collection<T>) ReflectHelper.createInstance(collectionClazz);

        for (int i = 0; i < jsonArray.length(); ++i) {
            try {
                JSONObject jo = jsonArray.getJSONObject(i);
                T o = toObject(jo, genericType);
                collection.add(o);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return collection;
    }

    /*
    * @funtion		从JSONArray字符串中，根据指定集合类.class、集合元素.class，解析出集合
    * */
    private static <T> Collection<T> toCollection(String jsonArrayString,
                                                  Class<?> collectionClazz, Class<T> genericType) {
        if (collectionClazz == null || genericType == null
                || jsonArrayString == null || jsonArrayString.length() == 0) {
            return null;
        }
        JSONArray jo = null;
        try {
            jo = new JSONArray(jsonArrayString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (isNull(jo)) {
            return null;
        }

        return toCollection(jo, collectionClazz, genericType);
    }

    private static boolean isNull(Object obj) {
        if (obj instanceof JSONObject) {
            return JSONObject.NULL.equals(obj);
        }
        return obj == null;
    }
}