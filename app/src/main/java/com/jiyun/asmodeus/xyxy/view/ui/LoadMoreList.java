package com.jiyun.asmodeus.xyxy.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.jiyun.asmodeus.xyxy.R;


public class LoadMoreList extends ListView implements AbsListView.OnScrollListener {
    private View footer;

    private int totalItem;
    private int lastItem;

    private boolean isLoading;

    private OnLoadMore onLoadMore;

    private LayoutInflater inflater;

    public LoadMoreList(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @SuppressLint("InflateParams")
    private void init(Context context) {
        inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.load_more_footer, null, false);
        footer.setVisibility(View.GONE);
        this.addFooterView(footer,null,false);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastItem = firstVisibleItem+visibleItemCount;
        this.totalItem = totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(this.totalItem == lastItem&&scrollState == SCROLL_STATE_IDLE){
            if(!isLoading){
                isLoading=true;
                footer.setVisibility(View.VISIBLE);
                this.addFooterView(footer,null,false);
                this.setSelection(this.getBottom());
                if(onLoadMore!=null){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(onLoadMore!=null){
                                onLoadMore.loadMore();
                            }
                        }
                    }, 500);

                }
            }
        }
    }
    public void setLoadMoreListen(OnLoadMore onLoadMore){
        this.onLoadMore = onLoadMore;
    }
    /**
     * 加载完成调用此方法
     */
    public void onLoadComplete(){
        footer.setVisibility(View.GONE);
        this.removeFooterView(footer);
        isLoading = false;

    }


    /**
     * 最后一条数据调用此方法
     */
    public void onLoadEnd(){
        isLoading = true;
    }

    public void removeFooter() {
        this.removeFooterView(footer);
    }

    public interface OnLoadMore{
        void loadMore();
    }

//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//				MeasureSpec.AT_MOST);
//		super.onMeasure(widthMeasureSpec, expandSpec);
//	}
}
