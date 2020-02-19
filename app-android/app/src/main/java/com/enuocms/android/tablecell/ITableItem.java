package com.enuocms.android.tablecell;

/**
 * @author 占小平
 * @function
 * @date 2017/9/18
 */
public interface ITableItem {
    boolean isClickable();

    void setClickable(boolean clickable);

    Object getTag();

    void setTag(Object tag);
}
