package com.enuocms.android.tablecell;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.androidquery.AQuery;
import com.enuocms.android.R;
import com.enuocms.android.widget.U_BaseListActivity;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class BaseTableCell extends LinearLayout implements ITableCell {
    public View itemStrokeLineTop;
    public View itemLineTop;
    public View itemLineBottom;
    public LinearLayout itemContainer;

    protected Context context;		//上下文（使用前，请初始化）
    protected AQuery aqRoot;		//父容器
    protected AQuery aq;			//子容器（使用前，请初始化）

    protected int position;			//当前Cell位置

    public BaseTableCell(Context context) {
        super(context);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = mInflater.inflate(R.layout.tablecell_container,this);

        aqRoot = new AQuery(rootView);

        this.context = context;

        itemStrokeLineTop = this.aqRoot.id(R.id.strokeLine).getView();
        this.aqRoot.id(R.id.strokeLine).gone();

        itemLineTop = this.aqRoot.id(R.id.itemLineTop).getView();
        itemLineBottom = this.aqRoot.id(R.id.itemLineBottom).getView();
        itemContainer = (LinearLayout)this.aqRoot.id(R.id.itemContainer).getView();
    }

    @Override
    public void setData(ITableItem data) {
    }

    public void setPosition(int position) {
        this.position = position;
        if (this.position == 0) {
            this.aqRoot.id(R.id.itemLineTop).visible();
        } else {
            this.aqRoot.id(R.id.itemLineTop).gone();
        }
    }

    /*
    * @function		请求宿主
    * */
    protected U_BaseListActivity getHostActivity(){
        if (this.context != null){
            if (this.context instanceof Activity){
                return (U_BaseListActivity)this.context;
            }
        }
        return null;
    }

    /*
    * @function		回调宿主
    * */
    protected void call(){
        getHostActivity().call(this.position);
    }
}
