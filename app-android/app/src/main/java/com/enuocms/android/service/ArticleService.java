package com.enuocms.android.service;

import android.app.Activity;

import com.enuocms.android.app.APIConfigs;
import com.enuocms.android.entity.Article;
import com.enuocms.android.service.handler.AjaxPageDataHandler;

import java.util.Map;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class ArticleService  extends ABaseNetworkService {

    public ArticleService(Activity activity) {
        super(activity);
    }

    public void getPageList( int pageIndex, int pageSize,  final AjaxPageDataHandler<Article> callbackHandler){
        //1、解析url
        String url = APIConfigs.API_URL_ROOT + "article/list";
        //2、填充params
        Map<String,Object> params = getAjaxPageParams(pageIndex, pageSize);
        //3、提交网络请求
        submitAjaxPageDataGetRequest(url, params, callbackHandler, Article.class);
    }
}
