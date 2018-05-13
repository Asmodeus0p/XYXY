package com.jiyun.asmodeus.xyxy.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.IMyselfFavoriteZhiBoContract;
import com.jiyun.asmodeus.xyxy.model.entity.FavoriteListBean;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.presenter.FavoriteListImp;
import com.jiyun.asmodeus.xyxy.view.adapter.FavoriteListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements IMyselfFavoriteZhiBoContract.loadFavoriteListView {


    @BindView(R.id.my_collect_fragment_list_recycler)
    RecyclerView myCollectFragmentListRecycler;
    @BindView(R.id.my_collect_fragment_list_empty)
    RelativeLayout myCollectFragmentListEmpty;
    @BindView(R.id.my_collect_fragment_list_fault)
    RelativeLayout myCollectFragmentListFault;
    Unbinder unbinder;
    List<FavoriteListBean.DataBean.ListBean> list;
    private FavoriteListImp favoriteListImp;
    private FavoriteListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_favorites, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        initView();

        return inflate;
    }

    private void initView() {
        //list = new ArrayList<>();
        favoriteListImp = new FavoriteListImp(this);
        int userId = SharedPreferencesUtils.getUserId(getContext());
        favoriteListImp.loadDatas(userId, 2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCollectFragmentListRecycler.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void laodListDatas(FavoriteListBean favoriteListBean) {
        List<FavoriteListBean.DataBean.ListBean> list = favoriteListBean.getData().getList();
        adapter = new FavoriteListAdapter(list);
        myCollectFragmentListRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
         Log.e("===", "list.size():" + list.size());

    }
}
