package com.enuocms.android.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.enuocms.android.R;
import com.enuocms.android.adapter.CommonAdapter;
import com.enuocms.android.entity.ITableData;
import com.enuocms.android.service.entity.AjaxPageData;
import com.enuocms.android.service.entity.AjaxResult;
import com.enuocms.android.tablecell.ITableItem;
import com.enuocms.android.tablecell.SectionTitleItem;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class U_BaseListActivity extends U_BaseActivity{

    protected RefreshLayout refreshListView;   //列表View

    protected List<String> groupList;                  //分组列表

    protected List<ITableItem> itemList;               //列表项数据
    protected CommonAdapter commonAdapter;         //列表项数据适配器

    protected int currentPageIndex = 0;                //当前页号
    protected int pageCount = 0;                       //分页总数
    protected int dataSize = 0;                        //数据总数
    protected int pageSize = 0;                        //分页尺寸

    protected static final int FIRST_PAGE_INDEX = 0;   //首页页号

    /*
     * @function    初始化内部变量
     * @date        2015-7-9
     */
    {
        this.groupList = new ArrayList<String>();
        this.itemList =  new ArrayList<ITableItem>();
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        //0、重置数据
        onClearPageData();
        //1、加入适配器中
        this.commonAdapter = new CommonAdapter(this);
        this.commonAdapter.setData(this.itemList);
    }


    /*
     * @function    初始化View
     * @date        2015-7-9
     */
    @Override
    protected void onInitView() {
        super.onInitView();

        //初始化列表视图对象
        this.refreshListView = (RefreshLayout)this.aq.id(R.id.refreshListView).getView();
        refreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
               onReloadPage();
            }
        });
        refreshListView.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                onGoNextPage();
            }
        });

        //list view
        ListView listView = (ListView)this.aq.id(R.id.listView).getView();
        listView.setAdapter(this.commonAdapter);

        LayoutInflater mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View emptyListView = mInflater.inflate(R.layout.u_view_empty_list_view, null);
        listView.setEmptyView(emptyListView);

        showNoneView();
    }

    /*
     * @function    关闭刷新功能
     * @date        2015-9-1
     */
    protected void disableView() {
        if (this.refreshListView != null){
            this.refreshListView.setEnableRefresh(false);
            this.refreshListView.setEnableLoadmore(false);
        }
    }

    /*
     * @function    开启下拉刷新功能，关闭上拉加载功能
     * @date        2015-9-1
     */
    protected void enableReloadView() {
        if (this.refreshListView != null){
            this.refreshListView.setEnableRefresh(true);
            this.refreshListView.setEnableLoadmore(false);
        }
    }

    /*
    * @function     切换编辑状态
    * */
    protected void enable(boolean clickable){
        for (ITableItem item:this.itemList) {
            item.setClickable(clickable);
        }
        this.commonAdapter.notifyDataSetChanged();
    }

    /*
     * @function    追加分组（界面）
     * @group       组名
     * @date        2015-10-14
     */
    protected void addGroupView(String group){
        if (!containGroup(group)){
            addGroup(group);
            this.itemList.add(new SectionTitleItem(group));
        }
    }

    /*
     * @function    追加分组
     * @group       组名
     * @date        2015-10-14
     */
    protected void addGroup(String group){
        this.groupList.add(group);
    }

    /*
     * @function    包含分组
     * @group       组名
     * @date        2015-10-14
     */
    protected boolean containGroup(String group){
        return this.groupList.contains(group);
    }

    /*
     * @function    初始化Model时，回调方法
     * @date        2015-7-9
     */
    protected void onInitModel() {
        this.itemList.clear();
    }

    /*
     * @function    追加Model时，回调方法
     * @date        2015-7-15
     */
    protected void onAppendModel(ITableData data) {
        this.commonAdapter.notifyDataSetChanged();
    }

    /*
     * @function    追加Model时，回调方法
     * @date        2015-7-15
     */
    protected void onAppendModel(List<? extends ITableData> datas) {
        this.commonAdapter.notifyDataSetChanged();
    }

    /*
     * @function    更新Model时，回调方法
     * @date        2015-7-15
     */
    protected void onUpdateModel(ITableData data, int position) {
        this.commonAdapter.notifyDataSetChanged();
    }

    /*
     * @function    更新Model时，回调方法
     * @date        2015-7-15
     */
    protected void onUpdateModel() {
        this.commonAdapter.notifyDataSetChanged();
    }

    /*
     * @function    插入Model时，回调方法
     * @date        2015-7-15
     */
    protected void onInsertModel(ITableData data) {
        this.commonAdapter.notifyDataSetChanged();
    }

    /*
     * @function    插入Model时，回调方法
     * @date        2015-7-15
     */
    protected void onInsertModel(List<? extends ITableData> datas) {
        this.commonAdapter.notifyDataSetChanged();
    }

    /*
     * @function    移除Model时，回调方法
     * @date        2015-9-8
     */
    protected void onRemoveModel(int position) {
        //1、获取Model的分组
        String group = onGetGroup(position);
        //2、移除Model
        this.itemList.remove(position);
        //3、移除Model分组
        boolean needGroup = false;
        int groupPosition = -1;
        for (int i = 0; i < this.itemList.size(); i++) {
            ITableItem item = this.itemList.get(i);
            if (item instanceof SectionTitleItem){
                if (onGetGroup(i).equals(group)){
                    groupPosition = i;
                }
            }else {
                if (onGetGroup(i).equals(group)){
                    needGroup = true;
                    break;
                }
            }
        }
        if (!needGroup){
            if (groupPosition != -1){
                this.itemList.remove(groupPosition);
                removeGroup(group);
            }
        }

        this.commonAdapter.notifyDataSetChanged();
    }

    /*
     * @function    获取Model分组时，回调函数
     * @date        2015-12-15
     */
    protected String onGetGroup(int position) {
        String group = "";

        ITableItem item = this.itemList.get(position);
        if (item instanceof SectionTitleItem){
            SectionTitleItem derivedItem = (SectionTitleItem)item;
            group = derivedItem.getTitle();
        }

        return group;
    }

    /*
     * @function    移除分组
     * @group       组名
     * @date        2015-10-14
     */
    protected void removeGroup(String group){
        if (containGroup(group)){
            this.groupList.remove(group);
        }
    }

    /*
     * @function    启动抽取Model，Model来源可以从网络、数据库、其他
     * @date        2015-7-15
     */
    @Override
    protected void onStartGettingModel() {
        super.onStartGettingModel();
        //异步请求列表
        onGoNextPage();
    }

    /*
    * @function     判断能否加载下一页数据时，回调方法
    * */
    protected boolean onCanGoNextPage(){
        if (this.currentPageIndex + 1 <= this.pageCount){
            return true;
        }else{
            return false;
        }
    }

    /*
    * @function     重新加载页面时，回调方法
    * */
    protected void onReloadPage() {
        if (this.refreshListView != null){
            onClearPageData();
            onGoNextPage();
        }
    }

    /*
    * @function     加载下一页数据时，回调方法
    * */
    protected void onGoNextPage() {
        if (this.currentPageIndex == FIRST_PAGE_INDEX){
            showNoneView();
            showLoading();
        }

        this.currentPageIndex++;
    }

    /*
    * @function     下一页数据，加载完成后的公共操作
    * */
    protected void goNextPageDone() {
        //无论何时，全部关闭
        {
            hideLoading();

            this.refreshListView.finishRefresh(500);
            this.refreshListView.finishLoadmore(500);
        }

        disableView();
        showResultSuccessView();
    }

    /*
    * @function     下一页数据，加载完成后的公共操作
    * */
    protected void goNextPageDone(AjaxResult result,AjaxPageData data) {
        //无论何时，全部关闭
        {
            hideLoading();
            this.refreshListView.finishRefresh(500);
            this.refreshListView.finishLoadmore(500);
        }

        if (result.connected) {
            if (result.success) {
                onAppendModel(data.items);
                onUpdatePageData(data);
                showResultSuccessView();
            } else {
                this.currentPageIndex--;
            }
        } else {
            this.currentPageIndex--;
            showConnectedErrorView();
        }

        result.debug(false);
    }

    /*
    * @function     下一页数据，加载完成后的公共操作
    * */
    protected void goNextPageDone(AjaxResult result, List<? extends ITableData> data) {
        //无论何时，全部关闭
        {
            hideLoading();
            this.refreshListView.finishRefresh(500);
            this.refreshListView.finishLoadmore(500);
        }

        if (result.connected) {
            if (result.success) {
                onAppendModel(data);
                disableView();
                showResultSuccessView();
            } else {
                this.currentPageIndex--;
            }
        } else {
            this.currentPageIndex--;
            showConnectedErrorView();
        }

        result.debug(false);
    }

    /*
   * @function     下一页数据，加载完成后的公共操作
   * */
    protected void goNextPageDone(AjaxResult result,ITableData data) {
        //无论何时，全部关闭
        {
            hideLoading();
            this.refreshListView.finishRefresh(500);
            this.refreshListView.finishLoadmore(500);
        }

        if (result.connected) {
            if (result.success) {
                onAppendModel(data);
                enableReloadView();
                showResultSuccessView();
            }else {
                this.currentPageIndex--;
            }
        }else{
            this.currentPageIndex--;
            showConnectedErrorView();
        }

        result.debug(false);
    }

    /*
    * @function     清零本地分页数据时，回调方法
    * */
    protected void onClearPageData(){
        this.dataSize = 0;
        this.pageCount = 0;
        this.currentPageIndex = 0;
        this.pageSize = 0;
        //清空数据
        this.onInitModel();
        //清空分组
        this.groupList.clear();
    }

    /*
    * @function     更新本地分页数据时，回调方法
    * */
    public void onUpdatePageData(AjaxPageData data){
        this.currentPageIndex = data.pageIndex;
        this.pageCount = data.pageCount;
        this.dataSize = data.total;
        this.pageSize = data.pageSize;

        if (!onCanGoNextPage()) {                     //加载数据完毕
            this.refreshListView.setEnableLoadmore(true);
        }
    }

    /*
    * @function    隐藏所有View
    * @date        2015-9-1
    */
    protected void showNoneView() {
        this.aq.id(R.id.refreshListView).invisible();
        this.aq.id(R.id.network_exception).invisible();
    }

    /*
    * @function    显示网络异常View
    * @date        2015-9-1
    */
    protected void showConnectedErrorView() {
        this.aq.id(R.id.refreshListView).invisible();
        this.aq.id(R.id.network_exception).visible();

        this.aq.id(R.id.network_exception).getView().bringToFront();
    }

    /*
    * @function    显示结果正常View
    * @date        2015-9-1
    */
    protected void showResultSuccessView() {
        this.aq.id(R.id.refreshListView).visible();
        this.aq.id(R.id.network_exception).invisible();

        this.aq.id(R.id.refreshListView).getView().bringToFront();
    }

    /*
    * @function    回调宿主
    * @date        2016-1-26
    */
    public void call(int position) {

    }
}

