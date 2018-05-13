package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IPersonInfoWok;
import com.jiyun.asmodeus.xyxy.model.biz.IPraise;
import com.jiyun.asmodeus.xyxy.model.biz.IPraiseCancle;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.HomeWokListBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeWorkListViewAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.LoginActivity;
import com.jiyun.asmodeus.xyxy.view.ui.LoadMoreListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersionInfoWokFragment extends BaselivstFragment {


    private static final int LOADED=1;


    private static final int LOADING=2;


    private int pull_up_load_state;

    @ViewInject(R.id.my_collect_fragment_tiyan_loadMoreList)
    private LoadMoreListView loadMoreList;

    @ViewInject(R.id.my_collect_fragment_tiyan_listview_empty)
    private RelativeLayout listview_empty;

    @ViewInject(R.id.my_collect_fragment_tiyan_listview_fault)
    private RelativeLayout listview_fault;

    @ViewInject(R.id.my_collect_fragment_tiyan_listview_fault_btn)
    private Button fault_btn;

    private Context context;

    private CompositeDisposable compositeDisposable;

    private List<WorkBean.DataBean.ListBean> homewoksBeen;

    private HomeWorkListViewAdapter adapter;

    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.my_collect_fragment_tiyan_list,null);
        ViewUtils.inject(this, view);

        init();

        initView();

        loadContent();



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }

    private void init(){

        context = getActivity();

        compositeDisposable = new CompositeDisposable();

        homewoksBeen = new ArrayList<>();

        adapter = new HomeWorkListViewAdapter(context,homewoksBeen);
    }

    private void initView(){

        loadMoreList.setAdapter(adapter);
        loadMoreList.removeFooter();


        loadMoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if(homewoksBeen.isEmpty()){
                    return;
                }

                WorkDataActivity.start((Activity) context,homewoksBeen.get(position).getId());
            }
        });

        adapter.setOnItemShareClick(new HomeWorkListViewAdapter.OnItemShareClick() {
            @Override
            public void onItemShareClick(int position) {

            }

            @Override
            public void onReplyClick(int position) {

            }

            @Override
            public void onPraiseClick(int position) {

                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                if(homewoksBeen.isEmpty()){
                    return ;
                }

                int favorite= homewoksBeen.get(position).getIsPraise();

                if(favorite== Constant.NOTFAVORITE){

                    parise(homewoksBeen.get(position).getStudentId(),homewoksBeen.get(position).getId(),Constant.Praise_work);
                    homewoksBeen.get(position).setIsPraise(Constant.FAVORITE);
                }else{
                    pariseCancle(homewoksBeen.get(position).getStudentId(),homewoksBeen.get(position).getId(),Constant.Praise_work);
                    homewoksBeen.get(position).setIsPraise(Constant.NOTFAVORITE);
                }


            }

            @Override
            public void onRewardClick(int position) {

            }
        });

    }



    private void parise(int userid,int itemid,String type){
        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IPraise.class)
                .postPraise(userid,itemid,HttpHelp.getUserId(context),type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {
                        if(value==null){
                            return;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void pariseCancle(int userid,int itemid,String type){
        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IPraiseCancle.class)
                .postPraiseCancle(userid,itemid,HttpHelp.getUserId(context),type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {
                        if(value==null){
                            return;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void loadContent(){

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IPersonInfoWok.class)
                .getWokList(HttpHelp.getUserId(context),((PersonInfoActivity)getActivity()).getId(),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WorkBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WorkBean value) {

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

                        homewoksBeen.addAll(value.getData().getList());
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


    public void flashLoad(){

        homewoksBeen.clear();
        adapter.notifyDataSetChanged();
        loadMoreList.onLoadComplete();
        pull_up_load_state=LOADED;
        page = 1;
        datafillList(listview_empty,loadMoreList);
        faultDataFillList(listview_fault,loadMoreList);
        loadContent();
    }



    private void resetState(){

        if(pull_up_load_state==LOADING){

            loadMoreList.onLoadComplete();
        }

        pull_up_load_state=LOADED;

    }

}
