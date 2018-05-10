package com.jiyun.asmodeus.xyxy.view.fragment;


import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.ValiableContract;
import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;
import com.jiyun.asmodeus.xyxy.presenter.ValiablePresenterImp;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeValuableListViewAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.RecyclerValuableTitleAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;
import com.jiyun.asmodeus.xyxy.view.ui.LoadMoreListView;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Typeface.NORMAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValuableFragment extends BaseFragment implements ValiableContract.ValiableView {
    private LinearLayout home_valuable_fragment_layout;
    private NestedScrollView home_valuable_fragment_nestscroll;
    private FlyBanner advViewpager;
    private RecyclerView homne_valuable_sort_recyclerview;
    private LoadMoreListView home_valuable_fragment_listview;
    private AppBarLayout home_valuable_fragment_appbar;
    private RelativeLayout listview_fault;
    private RelativeLayout listview_empty;
    private Button fault_btn;
    private FrameLayout home_valuable_fragment_listview_load_more;
    private SmartRefreshLayout swipeRefreshLayout;
    private static final int CAPACITY=0;
    private static final String CAPACITY_STR="智能筛选";
    private static final int LISTEN=1;
    private static final String LISTEN_STR="赞数最多";
    private static final int COMMENT=2;
    private static final String COMMENT_STR="最新评论";
    private String contentType="";
    private int sortType = CAPACITY;
    private int page = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_valuable;
    }

    private HomeValuableListViewAdapter adapter;
    private List<HomeValuanleBean.DataBean.ArtcircleCategoriesBean> contentSortlis = new ArrayList<>();

    private RecyclerValuableTitleAdapter sortAdapter;
    private List<HomeValuanleBean.DataBean.ArtcircleList.ListBean> homevaluableBeen = new ArrayList<>();
    private List<HomeValuanleBean.DataBean.ArtcircleList.ListBean> homevaluableBeen1 = new ArrayList<>();
    private List<HomeValuanleBean.DataBean.ArtcircleList.ListBean> homevaluableBeen2 = new ArrayList<>();


    @Override
    protected void init() {
        home_valuable_fragment_layout = getActivity().findViewById(R.id.home_valuable_fragment_layout);
        home_valuable_fragment_nestscroll = getActivity().findViewById(R.id.home_valuable_fragment_nestscroll);
        advViewpager = getActivity().findViewById(R.id.home_valuable_adv_viewpager);
        homne_valuable_sort_recyclerview = getActivity().findViewById(R.id.homne_valuable_sort_recyclerview);
        home_valuable_fragment_listview = getActivity().findViewById(R.id.home_valuable_fragment_listview);
        home_valuable_fragment_appbar = getActivity().findViewById(R.id.home_valuable_fragment_appbar);
        listview_fault = getActivity().findViewById(R.id.home_valuable_fragment_listview_fault);
        listview_empty = getActivity().findViewById(R.id.home_valuable_fragment_listview_empty);
        fault_btn = getActivity().findViewById(R.id.home_valuable_fragment_listview_fault_btn);
        home_valuable_fragment_listview_load_more = getActivity().findViewById(R.id.home_valuable_fragment_listview_load_more);
        swipeRefreshLayout = getActivity().findViewById(R.id.home_valuable_fragment_swipe);
        HomeValuanleBean.DataBean.ArtcircleCategoriesBean zuixinpinglun = new HomeValuanleBean.DataBean.ArtcircleCategoriesBean();
        zuixinpinglun.setId(COMMENT);
        zuixinpinglun.setName(COMMENT_STR);
        contentSortlis.add(0, zuixinpinglun);
        HomeValuanleBean.DataBean.ArtcircleCategoriesBean zanshuzuiduo = new HomeValuanleBean.DataBean.ArtcircleCategoriesBean();
        zanshuzuiduo.setId(LISTEN);
        zanshuzuiduo.setName(LISTEN_STR);
        contentSortlis.add(0, zanshuzuiduo);
        HomeValuanleBean.DataBean.ArtcircleCategoriesBean zhinengshaixuan = new HomeValuanleBean.DataBean.ArtcircleCategoriesBean();
        zhinengshaixuan.setId(CAPACITY);
        zhinengshaixuan.setName(CAPACITY_STR);
        zhinengshaixuan.setSort(true);
        contentSortlis.add(0, zhinengshaixuan);
        sortAdapter = new RecyclerValuableTitleAdapter(contentSortlis);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homne_valuable_sort_recyclerview.setLayoutManager(linearLayoutManager);
        homne_valuable_sort_recyclerview.setAdapter(sortAdapter);
        sortAdapter.setListener(new RecyclerValuableTitleAdapter.RecyclerValuableSingleSortListener() {
            @Override
            public void onItemClickListener(int position) {
                if (position==0) {
                    home_valuable_fragment_listview.setVisibility(View.VISIBLE);
                    listview_empty.setVisibility(View.GONE);
                    adapter = new HomeValuableListViewAdapter(getContext(), homevaluableBeen);
                    home_valuable_fragment_listview.setAdapter(adapter);
                }if (position==1) {
                    home_valuable_fragment_listview.setVisibility(View.VISIBLE);
                    listview_empty.setVisibility(View.GONE);
                    adapter = new HomeValuableListViewAdapter(getContext(), homevaluableBeen1);
                    home_valuable_fragment_listview.setAdapter(adapter);
                }if (position==2) {
                    home_valuable_fragment_listview.setVisibility(View.VISIBLE);
                    listview_empty.setVisibility(View.GONE);
                    adapter = new HomeValuableListViewAdapter(getContext(), homevaluableBeen2);
                    home_valuable_fragment_listview.setAdapter(adapter);
                }if (position>2) {
                    home_valuable_fragment_listview.setVisibility(View.GONE);
                    listview_empty.setVisibility(View.VISIBLE);
                }

            }
        });
    }
    @Override
    protected void loadDatas() {
        ValiableContract.ValiablePresenter presenterImp = new ValiablePresenterImp(this);
        presenterImp.GetWorkZhiNeng();
        presenterImp.GetWorkZanShu();
        presenterImp.GetWorkZuiXin();
    }

    @Override
    public void laodWorkZhiNeng(final HomeValuanleBean workBean) {
        contentSortlis.addAll(workBean.getData().getArtcircleCategories());
        sortAdapter.notifyDataSetChanged();
        ArrayList<String> urls = new ArrayList<>();
        for (HomeValuanleBean.DataBean.SystemAdsBean systemAdsBean : workBean.getData().getSystemAds()) {
           urls.add(systemAdsBean.getMobileImgUrl()) ;
        }
        advViewpager.setImagesUrl(urls);
        homevaluableBeen.addAll(workBean.getData().getArtcircleList().getList());
    }

    @Override
    public void laodWorkZanShu(final HomeValuanleBean workBean) {

        homevaluableBeen1.addAll(workBean.getData().getArtcircleList().getList());

    }

    @Override
    public void laodWorkZuiXin(final HomeValuanleBean workBean) {

        homevaluableBeen2.addAll(workBean.getData().getArtcircleList().getList());
    }

}
