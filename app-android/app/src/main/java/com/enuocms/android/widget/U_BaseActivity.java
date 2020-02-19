package com.enuocms.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;

import com.androidquery.AQuery;
import com.enuocms.android.app.MainApplication;
import com.enuocms.android.entity.ITableData;

import java.util.List;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class U_BaseActivity extends AppCompatActivity {

    protected AppCompatActivity hostActivity;
    protected AQuery aq;                               	//绑定Activity的AQuery对象（已经初始化，请务必在加载View之后调用）

    //private ProgressHUD laodingDialog;					//加载对话框
    private GestureDetector gestureDetector;			//左滑手势检测器

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        //0、初始化内部变量
        hostActivity = this;
        this.aq = new AQuery(this);
        //1、开始抽取数据
        onStartGettingModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        MainApplication.getMainApplication().setStackTopActivity(this);
    }

    public void onPause() {
        super.onPause();
    }

    /*
    * @function     显示加载对话框
    * */
    protected void showLoading() {
//        if (laodingDialog == null || !laodingDialog.isShowing()) {
//            laodingDialog = ProgressHUD.show(hostActivity, hostActivity.getResources()
//                    .getString(R.string.loading), true, true, null);
//        }
    }

    /*
    * @function     隐藏加载对话框
    * */
    protected void hideLoading() {
//        if (laodingDialog != null && laodingDialog.isShowing()) {
//            laodingDialog.dismiss();
//        }
    }

//    /*
//    * @function     按下返回键，触发返回事件
//    * */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

//    /*
//    * @function     从最左侧向右滑动，触发返回事件
//    * */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //转发触摸事件
//        if (this.gestureDetector == null){
//            this.gestureDetector = new GestureDetector(hostActivity,new GestureDetector.SimpleOnGestureListener(){
//                @Override
//                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                    if (e1 != null && e2 != null){
//                        if (e2.getX() - e1.getX() > 100){	//滑动距离
//                            if (e1.getX() < 100){			//从最左侧开始滑动
//                                goBack();
//                            }
//                        }
//                    }
//                    return super.onFling(e1, e2, velocityX, velocityY);
//                }
//            });
//        }
//
//        this.gestureDetector.onTouchEvent(event);
//
//        return true;
//    }


    /*
    * @function     刷新界面
    * */
    public void refreshView(){

    }

    /*
     * @function    初始化View时，回调函数
     * @date        2015-7-9
     */
    protected void onInitView() {
//        //按下返回按钮，触发返回事件
//        this.aq.id(R.id.btn_back).clicked(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goBack();
//            }
//        });
//        //按下Home按钮，跳转到Home界面
//        this.aq.id(R.id.btn_home).clicked(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EnumActivityTag.TagForWebPlusActivity.jumpToActivity(hostActivity);
//            }
//        });
    }

//    /*
//    * @function     响应返回事件
//    * */
//    protected  void goBack(){
//        hostActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//        hostActivity.finish();
//    }

    /*
     * @function    启动抽取Model，Model来源可以从网络、数据库、其他，回调函数
     * @date        2015-7-15
     */
    protected void onStartGettingModel() {

    }

//    /*
//     * @function    启动抽取Model，完成之后的公共操作
//     * @flag		附加标示
//     * @date        2015-11-5
//     */
//    protected <T> T startGettingModelDone(AjaxResult success,boolean flag,ITableData items) {
//        //无论何时，全部关闭
//        {
//            hideLoading();
//        }
//
//        T itemData = null;
//
//        if (success.connected) {
//            if (success.success) {
//                onStartGettingModelSuccess(items);
//                itemData = (T)items;
//            }else{
//                onStartGettingModelFailure();
//            }
//        }else{
//            onStartGettingModelError();
//        }
//
//        success.debug(flag);
//
//        return itemData;
//    }

//    /*
//     * @function    启动抽取Model，完成之后的公共操作
//     * @flag		附加标示
//     * @requestCode	请求码
//     * @date        2015-11-5
//     */
//    protected <T> T startGettingModelDone(AjaxResult success,boolean flag,ITableData items,Integer requestCode) {
//        //无论何时，全部关闭
//        {
//            hideLoading();
//        }
//
//        T itemData = null;
//
//        if (success.connected) {
//            if (success.success) {
//                onStartGettingModelSuccess(items,requestCode);
//                itemData = (T)items;
//            }else{
//                onStartGettingModelFailure();
//            }
//        }else{
//            onStartGettingModelError();
//        }
//
//        success.debug(flag);
//
//        return itemData;
//    }

//    /*
//     * @function    启动抽取Model，完成之后的公共操作
//     * @flag		附加标示
//     * @date        2015-11-5
//     */
//    protected void startGettingModelDone(AjaxResult success,boolean flag) {
//        //无论何时，全部关闭
//        {
//            hideLoading();
//        }
//
//        if (success.connected) {
//            if (success.success) {
//                onStartGettingModelSuccess();
//            }else{
//                onStartGettingModelFailure();
//            }
//        }else{
//            onStartGettingModelError();
//        }
//
//        success.debug(flag);
//    }

//    /*
//     * @function    启动抽取Model，完成之后的公共操作
//     * @flag		附加标示
//     * @date        2015-11-5
//     */
//    protected void startGettingModelDone(AjaxResult success,boolean flag,List<? extends ITableData> datas) {
//        //无论何时，全部关闭
//        {
//            hideLoading();
//        }
//
//        if (success.connected) {
//            if (success.success) {
//                onStartGettingModelSuccess(datas);
//            }else{
//                onStartGettingModelFailure();
//            }
//        }else{
//            onStartGettingModelError();
//        }
//
//        success.debug(flag);
//    }

//    /*
//     * @function    启动抽取Model，完成之后的公共操作
//     * @flag		附加标示
//     * @date        2015-11-5
//     */
//    protected void startGettingModelDone(AjaxResult success,boolean flag,List<? extends ITableData> datas,Integer requestCode) {
//        //无论何时，全部关闭
//        {
//            hideLoading();
//        }
//
//        if (success.connected) {
//            if (success.success) {
//                onStartGettingModelSuccess(datas,requestCode);
//            }else{
//                onStartGettingModelFailure();
//            }
//        }else{
//            onStartGettingModelError();
//        }
//
//        success.debug(flag);
//    }

    /*
     * @function    抽取Model，成功之后的回调函数
     * @date        2015-7-15
     */
    protected void onStartGettingModelSuccess() {

    }

    /*
     * @function    抽取Model，成功之后的回调函数
     * @date        2015-7-15
     */
    protected void onStartGettingModelSuccess(ITableData data) {

    }

    /*
     * @function    抽取Model，成功之后的回调函数
     * @date        2015-7-15
     */
    protected void onStartGettingModelSuccess(ITableData data, Integer requestCode) {

    }

    /*
     * @function    抽取Model，成功之后的回调函数
     * @date        2015-7-15
     */
    protected void onStartGettingModelSuccess(List<? extends ITableData> datas) {

    }

    /*
     * @function    抽取Model，成功之后的回调函数
     * @date        2015-7-15
     */
    protected void onStartGettingModelSuccess(List<? extends ITableData> datas,Integer requestCode) {

    }

    /*
     * @function    抽取Model，失败之后的回调函数
     * @date        2015-7-15
     */
    protected void onStartGettingModelFailure() {

    }

    /*
     * @function    抽取Model，错误之后的回调函数
     * @date        2015-7-15
     */
    protected void onStartGettingModelError() {

    }

}
