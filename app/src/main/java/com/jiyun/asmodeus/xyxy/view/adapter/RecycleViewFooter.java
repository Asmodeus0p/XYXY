package com.jiyun.asmodeus.xyxy.view.adapter;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.jiyun.asmodeus.xyxy.R;

public class RecycleViewFooter extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.load_more_footer;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_load_xyxy_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_moref_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
