package com.jiyun.asmodeus.xyxy.view.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

public class BasicFragment extends Fragment {
    /**
     * 列表空内容
     */
    protected void emptyList(View emptyhintView , View loadMoreList){
        emptyhintView.setVisibility(View.VISIBLE);
        loadMoreList.setVisibility(View.GONE);
    }

    /**
     * 列表内容刷新
     */
    protected void datafillList(View emptyhintView , View loadMoreList){
        emptyhintView.setVisibility(View.GONE);
        loadMoreList.setVisibility(View.VISIBLE);
    }

    /**
     * 无网络 数据错误
     */
    protected void faultList(View faulthintView, View faultrefresh, View loadMoreList, final View.OnClickListener onClickListener){
        faulthintView.setVisibility(View.VISIBLE);
        loadMoreList.setVisibility(View.GONE);
        faultrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null){
                    onClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 无网络情况下 列表内容刷新
     */
    protected void faultDataFillList(View faulthintView, View loadMoreList){
        faulthintView.setVisibility(View.GONE);
        loadMoreList.setVisibility(View.VISIBLE);
    }
}
