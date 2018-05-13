package com.jiyun.asmodeus.xyxy.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by vicoltree on 17/11/7.
 */

public class ScrollLoadMoreList extends ListView implements View.OnTouchListener {
    private View footer;

    private int totalItem;
    private int lastItem;

    private boolean isLoading;

    private View loadView;

    private OnLoadMore onLoadMore;

    private MyScrollView myScrollView;

    public ScrollLoadMoreList(Context context) {
        super(context);
        init(context);
    }

    public ScrollLoadMoreList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollLoadMoreList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @SuppressLint("InflateParams")
    private void init(Context context) {
//        inflater = LayoutInflater.from(context);
//        footer = inflater.inflate(R.layout.load_more_footer, null, false);
//        footer.setVisibility(View.GONE);
//        this.addFooterView(footer,null,false);
//        this.setOnScrollListener(this);
    }

//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        this.lastItem = firstVisibleItem+visibleItemCount;
//        this.totalItem = totalItemCount;
//    }
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//        Log.d("TAG", "onScrollStateChanged: ");
//        if(this.totalItem == lastItem&&scrollState == SCROLL_STATE_IDLE){
//            if(!isLoading){
//                isLoading=true;
//                if(loadView!=null){
//                    loadView.setVisibility(View.VISIBLE);
//                }
//                if(onLoadMore!=null){
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if(onLoadMore!=null){
//                                onLoadMore.loadMore();
//                            }
//                        }
//                    }, 500);
//
//                }
//            }
//        }
//    }
    public void setLoadMoreListen(OnLoadMore onLoadMore){
        this.onLoadMore = onLoadMore;
    }

    public void setLoadView(MyScrollView myScrollView,View loadView) {
        this.loadView = loadView;
        this.myScrollView = myScrollView;
        loadView.setVisibility(GONE);
        this.myScrollView.setOnTouchListener(this);
    }

    /**
     * 加载完成调用此方法
     */
    public void onLoadComplete(){

        if(loadView!=null){
            loadView.setVisibility(View.GONE);
        }
        isLoading = false;
    }


    /**
     * 最后一条数据调用此方法
     */
    public void onLoadEnd(){
        isLoading = true;
        if(loadView!=null){
            loadView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
                int scrollY = v.getScrollY();
                int height = v.getHeight();
                int scrollViewMeasuredHeight = myScrollView.getChildAt(0).getMeasuredHeight();
                if ((scrollY + height) == scrollViewMeasuredHeight) {

                    if(!isLoading){
                        isLoading=true;
                        if(loadView!=null){
                            loadView.setVisibility(View.VISIBLE);
                        }
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
        return false;
    }

    public interface OnLoadMore{
        void loadMore();
    }

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
