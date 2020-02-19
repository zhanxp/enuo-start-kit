package com.enuocms.android.tablecell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.androidquery.AQuery;
import com.enuocms.android.R;
import com.enuocms.android.app.Configs;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public class TitleItemCell extends BaseTableCell {
    public TitleItemCell(Context context) {
        super(context);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = mInflater.inflate(R.layout.tablecell_title,itemContainer);

        aq = new AQuery(rootView);
    }

    @Override
    public void setData(ITableItem data) {
        TitleItem item = (TitleItem) data;

        this.aq.id(R.id.txtTitle).text(item.getTitle());
        if (item.getDrawable() != Configs.INVALID_VAL) {
            this.aq.id(R.id.imgTitle).image(item.getDrawable());
            this.aq.id(R.id.imgTitle).visible();
        } else {
            this.aq.id(R.id.imgTitle).gone();
        }
    }
}
