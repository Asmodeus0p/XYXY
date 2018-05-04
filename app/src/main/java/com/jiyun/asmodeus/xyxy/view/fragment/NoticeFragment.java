package com.jiyun.asmodeus.xyxy.view.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.NoticeContract;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;
import com.jiyun.asmodeus.xyxy.presenter.NoticePresenterImp;
import com.jiyun.asmodeus.xyxy.view.adapter.NoticeAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends BaseFragment implements NoticeContract.NoticaView{
    private NoticePresenterImp presenterImp;
    private RecyclerView mRecy;
    private List<NoticeBean.DataBean.ListBean> list;
    private NoticeAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void init() {
        list=new ArrayList<>();
        mRecy=getView().findViewById(R.id.yugaoRecy);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecy.setLayoutManager(linearLayoutManager);
        presenterImp=new NoticePresenterImp(this);
        presenterImp.laodNoticeDatas();
        adapter=new NoticeAdapter(list);
        mRecy.setAdapter(adapter);
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    public void laodNoticeDatas(NoticeBean noticeBean) {
    list.addAll(noticeBean.getData().getList());
    adapter.notifyDataSetChanged();
       // Log.d("NoticeFragment", list.get(0).getAddress());
    }
}
