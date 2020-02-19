package com.enuocms.android.service.entity;

import com.enuocms.android.app.APIConfigs;
import com.enuocms.android.utils.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */

public class AjaxPageData<T> {
    public int total;		//数据总数
    public int pageIndex;		//分页页号
    public int pageSize;		//分页尺寸
    public int pageCount;		//分页总数
    public List<T> items;

    private AjaxPageData() {
    }

    /*
    * @function         解析AjaxPage，分页数据
    * @object           服务端返回的JSONObject对象
    * @clazz            数据类型.class
    * */
    public static <T> AjaxPageData<T> parseAjaxPage(final JSONObject object, final Class<T> clazz) {
        AjaxPageData<T> ajaxPageData = new AjaxPageData<T>();

        JSONArray jsonArray = null;
        if (object != null){
            JSONObject jsonObject = object.optJSONObject(APIConfigs.API_RESPONSE_KEY_DATA);
            if (jsonObject != null){
                ajaxPageData.pageIndex = jsonObject.optInt(APIConfigs.API_RESPONSE_KEY_PAGE_INDEX);
                ajaxPageData.pageSize = jsonObject.optInt(APIConfigs.API_RESPONSE_KEY_PAGE_SIZE);
                ajaxPageData.total = jsonObject.optInt(APIConfigs.API_RESPONSE_KEY_PAGE_DATA_SIZE);
                ajaxPageData.pageCount = jsonObject.optInt(APIConfigs.API_RESPONSE_KEY_PAGE_COUNT);
                jsonArray = jsonObject.optJSONArray(APIConfigs.API_RESPONSE_KEY_DATA_RESULT);
            }
        }

        ajaxPageData.items = parseAjaxData(jsonArray,clazz);

        return ajaxPageData;
    }

    /*
   * @function         解析AjaxData，列表数据
   * @object           服务端返回的JSONObject对象
   * @clazz            数据类型.class
   * */
    public static <T> List<T> parseAjaxData(final JSONArray jsonArray, final Class<T> clazz) {
        List<T> ajaxListData = new ArrayList<T>();
        if (jsonArray != null) {
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject tempJsonObject = jsonArray.getJSONObject(i);

                    T itemData = JSONHelper.toObject(tempJsonObject, clazz);
                    ajaxListData.add(itemData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ajaxListData;
    }
}
