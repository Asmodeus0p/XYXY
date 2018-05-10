package com.jiyun.asmodeus.xyxy.view.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.asmodeus.xyxy.view.fragment.MyselfFragment;

public abstract class BaseFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        loadDatas();
    }

    protected abstract int getLayoutId();
    protected abstract void init();
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden)
            onHidden();
        else
            onShow();

    }

    protected void onHidden(){};

    protected void onShow(){};

    protected abstract void loadDatas();
    protected void emptyList(View emptyhintView , View loadMoreList){
        emptyhintView.setVisibility(View.VISIBLE);
        loadMoreList.setVisibility(View.GONE);
    }

}
