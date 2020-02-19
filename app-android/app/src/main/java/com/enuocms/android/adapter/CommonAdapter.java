package com.enuocms.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.enuocms.android.tablecell.BaseTableCell;
import com.enuocms.android.tablecell.ITableItem;
import com.enuocms.android.tablecell.TitleItem;
import com.enuocms.android.tablecell.TitleItemCell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 占小平
 * @function
 * @date 2017/9/19
 */
public class CommonAdapter extends BaseAdapter {
    protected Context mContext;
    protected List<ITableItem> list = new ArrayList<ITableItem>();

    public CommonAdapter(Context _context) {
        this.mContext = _context;
    }

    public void setData(List<ITableItem> _list) {
        list = _list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int pos, View v, ViewGroup p) {
        final ITableItem tableItem = list.get(pos);
        BaseTableCell tableCell = getTableCell(tableItem, v);
        try {
            tableCell.setPosition(pos);
            tableCell.setData(tableItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableCell;
    }

    /*
    * @function     用于建立列表项Data和列表项Cell之间的映射关系
    * */
    protected BaseTableCell getTableCell(ITableItem object, View v) {
        BaseTableCell holder = null;
        try {

            if (object instanceof TitleItem) {
                if (v != null && v instanceof TitleItemCell) {
                    holder = (TitleItemCell) v;
                } else {
                    holder = new TitleItemCell(this.mContext);
                }
            }

//            if (object instanceof SubTitleItem) {
//                if (v != null && v instanceof SubTitleItemCell) {
//                    holder = (SubTitleItemCell) v;
//                } else {
//                    holder = new SubTitleItemCell(this.mContext);
//                }
//            }
//
//            if (object instanceof SectionTitleItem) {
//                if (v != null && v instanceof SectionTitleItemCell) {
//                    holder = (SectionTitleItemCell) v;
//                } else {
//                    holder = new SectionTitleItemCell(this.mContext);
//                }
//            }
//
//            if (object instanceof ActionSeparateItem) {
//                if (v != null && v instanceof ActionSeparateItemCell) {
//                    holder = (ActionSeparateItemCell) v;
//                } else {
//                    holder = new ActionSeparateItemCell(this.mContext);
//                }
//            }
//
//            if (object instanceof RemarkTitleItem) {
//                if (v != null && v instanceof RemarkTitleItemCell) {
//                    holder = (RemarkTitleItemCell) v;
//                } else {
//                    holder = new RemarkTitleItemCell(this.mContext);
//                }
//            }
//
//            if (object instanceof SectionCheckedItem) {
//                if (v != null && v instanceof SectionCheckedItemCell) {
//                    holder = (SectionCheckedItemCell) v;
//                } else {
//                    holder = new SectionCheckedItemCell(this.mContext);
//                }
//            }
//
//            if (object instanceof TitleInfoItem) {
//                if (v != null && v instanceof TitleInfoItemCell) {
//                    holder = (TitleInfoItemCell) v;
//                } else {
//                    holder = new TitleInfoItemCell(this.mContext);
//                }
//            }
//
//            if (object instanceof TitleActionItem) {
//                if (v != null && v instanceof TitleActionItemCell) {
//                    holder = (TitleActionItemCell) v;
//                } else {
//                    holder = new TitleActionItemCell(this.mContext);
//                }
//            }
//
//            if (object instanceof ActionItem) {
//                if (v != null && v instanceof ActionItemCell) {
//                    holder = (ActionItemCell) v;
//                } else {
//                    holder = new ActionItemCell(this.mContext);
//                }
//            }
//
//            if (object instanceof TitleEditItem) {
//                if (v != null && v instanceof TitleEditCell) {
//                    holder = (TitleEditCell) v;
//                } else {
//                    holder = new TitleEditCell(this.mContext);
//                }
//            }
//
//            if (object instanceof ViewItem) {
//                if (v != null && v instanceof ViewItemCell) {
//                    holder = (ViewItemCell) v;
//                } else {
//                    holder = new ViewItemCell(this.mContext);
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return holder;
    }
}
