package com.jiyun.asmodeus.xyxy.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IMyselfFavoriteTiYanKeService;
import com.jiyun.asmodeus.xyxy.model.entity.HomeNoticeBean;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.LoadMore;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeNoticeListAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeNoticeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavotiteTiyaFragment extends BasicFragment {

    /**
     * 加载任务已完成
     */
    private static final int LOADED=1;

    /**
     * 加载任务正在进行
     */
    private static final int LOADING=2;

    /**
     * 当前的加载状态  pull_up
     */
    private int pull_up_load_state;

    private Button fault_btn;

    private Context context;

    private CompositeDisposable compositeDisposable;


    private int page = 1;

    private List<HomeNoticeBean.DataBean.ListBean> noticebean;

    private HomeNoticeListAdapter adapter;


    @BindView(R.id.my_collect_fragment_tiyan_loadMoreList)
    LoadMore loadMoreList;
    @BindView(R.id.my_collect_fragment_tiyan_listview_empty)
    RelativeLayout listview_empty;
    @BindView(R.id.my_collect_fragment_tiyan_listview_fault_btn)
    Button myCollectFragmentTiyanListviewFaultBtn;
    @BindView(R.id.my_collect_fragment_tiyan_listview_fault)
    RelativeLayout listview_fault;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favotite_tiya, container, false);
        unbinder = ButterKnife.bind(this, view);

        init();


        initView();

        upLoad();

        loadContent();
        return view;
    }

    private void upLoad() {
        loadMoreList.setLoadMoreListen(new LoadMore.OnLoadMore() {
            @Override
            public void loadMore() {
                if (pull_up_load_state == LOADING){

                    return;
                }

                pull_up_load_state = LOADING;

                page++;

                loadContent();
            }
        });
    }
    private void loadContent(){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IMyselfFavoriteTiYanKeService.class)
                .getFavoriteTiYanKe(HttpHelp.getUserId(context),Constant.TIYANKE_ID,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeNoticeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeNoticeBean value) {
                        if(page==1&&(value==null||value.getData()==null||value.getData().getList()==null||value.getData().getList().size()<=0)){

                            emptyList(listview_empty,loadMoreList);
                            resetState();
                            return ;
                        }


                        if(page>1&&(value==null||value.getData()==null||value.getData().getList()==null||value.getData().getList().size()<=0)){
                            resetState();
                            loadMoreList.onLoadEnd();
                            page--;
                            return ;
                        }

                        noticebean.addAll(value.getData().getList());
                        adapter.notifyDataSetChanged();
                        resetState();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(page==1){

                            faultList(listview_fault, fault_btn, loadMoreList, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    faultDataFillList(listview_fault,loadMoreList);
                                    loadContent();
                                }
                            });

                        }

                        resetState();
                        if(page>1){

                            page--;
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    private void resetState(){

        if(pull_up_load_state==LOADING){

            loadMoreList.onLoadComplete();
        }

        pull_up_load_state=LOADED;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }

    private void initView() {
        loadMoreList.setAdapter(adapter);
        loadMoreList.removeFooter();

        loadMoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, NoticeInfoActivity.class);
                intent.putExtra(Constant.Notice_id, noticebean.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void init() {

        context = getActivity();

        compositeDisposable = new CompositeDisposable();

        noticebean = new ArrayList<>();

        adapter=new HomeNoticeListAdapter(context,noticebean);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
