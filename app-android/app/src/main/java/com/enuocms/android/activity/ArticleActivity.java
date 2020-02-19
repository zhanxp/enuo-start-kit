package com.enuocms.android.activity;

import android.os.Bundle;

import com.enuocms.android.R;
import com.enuocms.android.app.APIConfigs;
import com.enuocms.android.entity.Article;
import com.enuocms.android.entity.ITableData;
import com.enuocms.android.service.ArticleService;
import com.enuocms.android.service.entity.AjaxPageData;
import com.enuocms.android.service.entity.AjaxResult;
import com.enuocms.android.service.handler.AjaxPageDataHandler;
import com.enuocms.android.tablecell.TitleItem;
import com.enuocms.android.widget.U_BaseListActivity;

import java.util.List;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class ArticleActivity extends U_BaseListActivity {

    protected ArticleService articleService = new ArticleService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article);
        super.onCreate(savedInstanceState);
        onInitView();
    }

    @Override
    protected void onInitView() {
        super.onInitView();
    }

    @Override
    protected void onGoNextPage() {
        super.onGoNextPage();

        this.articleService.getPageList(this.currentPageIndex, APIConfigs.API_DATA_PAGE_SIZE,
                new AjaxPageDataHandler<Article>() {
                    @Override
                    public void callback(AjaxResult result, AjaxPageData<Article> data) {
                        goNextPageDone(result, data);
                    }
                }
        );
    }



    @Override
    protected void onAppendModel(List<? extends ITableData> datas) {
        List<Article> list = (List<Article>)datas;
        for (Article ent:list) {
            TitleItem item = new TitleItem(ent.title);
            this.itemList.add(item);
        }
        super.onAppendModel(datas);
    }

    @Override
    protected void onInsertModel(List<? extends ITableData> datas) {
        List<Article> list = (List<Article>)datas;
        for (Article ent: list) {
            this.itemList.add(0,new TitleItem(ent.title));
        }
        super.onInsertModel(datas);
    }
}
