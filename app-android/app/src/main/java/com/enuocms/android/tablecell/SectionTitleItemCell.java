package com.enuocms.android.tablecell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.androidquery.AQuery;
import com.enuocms.android.R;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class SectionTitleItemCell extends BaseTableCell {

    public SectionTitleItemCell(Context context) {
        super(context);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = mInflater.inflate(R.layout.tablecell_sectiontitle,itemContainer);

        this.aq = new AQuery(rootView);
    }

    @Override
    public void setData(ITableItem data) {
        SectionTitleItem item = (SectionTitleItem) data;
        this.aq.id(R.id.txtTitle).text(item.getTitle());
    }

}
