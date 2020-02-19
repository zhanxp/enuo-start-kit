package com.enuocms.android.tablecell;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class SectionTitleItem implements ITableItem {

    private boolean mClickable = true;
    private int mDrawable = -1;
    private String mTitle;
    private Object mTag = -1;

    public SectionTitleItem(String _title) {
        this.mTitle = _title;
        this.setClickable(false);
    }

    public SectionTitleItem(String _title, int _tag) {
        this.mTitle = _title;
        this.mTag = _tag;
    }

    public SectionTitleItem(String _title, boolean _clickable) {
        this.mTitle = _title;
        this.mClickable = _clickable;
    }

    public SectionTitleItem(int _drawable, String _title) {
        this.mDrawable = _drawable;
        this.mTitle = _title;
    }

    public SectionTitleItem(int _drawable, String _title, int _tag) {
        this.mDrawable = _drawable;
        this.mTitle = _title;
        this.mTag = _tag;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public void setDrawable(int drawable) {
        this.mDrawable = drawable;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public Object getTag() {
        return mTag;
    }

    public void setTag(Object mTag) {
        this.mTag = mTag;
    }

    @Override
    public boolean isClickable() {
        return mClickable;
    }

    @Override
    public void setClickable(boolean clickable) {
        mClickable = clickable;
    }
}
