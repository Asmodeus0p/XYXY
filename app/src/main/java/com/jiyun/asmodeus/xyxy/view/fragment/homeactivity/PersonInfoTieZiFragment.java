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
import com.jiyun.asmodeus.xyxy.model.biz.ICancleFavorite;
import com.jiyun.asmodeus.xyxy.model.biz.IFavorite;
import com.jiyun.asmodeus.xyxy.model.biz.IPersonValuable;
import com.jiyun.asmodeus.xyxy.model.biz.IPraise;
import com.jiyun.asmodeus.xyxy.model.biz.IPraiseCancle;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeValuableListViewAdapter;
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

public class PersonInfoTieZiFragment extends BaselivstFragment {

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

    private List<HomeValuanleBean.DataBean.ArtcircleList.ListBean> homevaluableBeen;

    private HomeValuableListViewAdapter adapter;

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

    private void init(){
        context = getActivity();

        compositeDisposable = new CompositeDisposable();

        homevaluableBeen = new ArrayList<>();

        adapter = new HomeValuableListViewAdapter(context, homevaluableBeen);
    }

    private void initView(){

        loadMoreList.setAdapter(adapter);
        loadMoreList.removeFooter();
        loadMoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(homevaluableBeen.isEmpty()){
                    return ;
                }

                ValuableDetailActivity.start((Activity) context, homevaluableBeen.get(position).getId());


            }
        });

        adapter.setOnValuableItemShareClick(new HomeValuableListViewAdapter.OnValuableItemShareClick() {
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

                if(homevaluableBeen.isEmpty()){
                    return ;
                }

                int favorite= homevaluableBeen.get(position).getIsPraise();

                if(favorite== Constant.NOTFAVORITE){

                    parise(homevaluableBeen.get(position).getUserId(),homevaluableBeen.get(position).getId(),Constant.Praise_valuable);
                    homevaluableBeen.get(position).setIsPraise(Constant.FAVORITE);
                }else{
                    pariseCancle(homevaluableBeen.get(position).getUserId(),homevaluableBeen.get(position).getId(),Constant.Praise_valuable);
                    homevaluableBeen.get(position).setIsPraise(Constant.NOTFAVORITE);
                }


            }

            @Override
            public void onFavoriteClick(int position) {


                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                if(homevaluableBeen.isEmpty()){
                    return ;
                }

                int favorite= homevaluableBeen.get(position).getIsFavorite();

                if(favorite==Constant.NOTFAVORITE){

                    favorite(homevaluableBeen.get(position).getId());
                    homevaluableBeen.get(position).setIsFavorite(Constant.FAVORITE);
                }else{
                    cancleFavorite(homevaluableBeen.get(position).getId());
                    homevaluableBeen.get(position).setIsFavorite(Constant.NOTFAVORITE);
                }


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



    private void favorite(int favoriteid){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IFavorite.class)
                .favorite(favoriteid,HttpHelp.getUserId(context),Constant.YIKAOQUAN)
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
                            return ;
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

    private void cancleFavorite(int favoriteid){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(ICancleFavorite.class)
                .cancleFravorite(favoriteid,HttpHelp.getUserId(context),Constant.YIKAOQUAN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    private void loadContent() {

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IPersonValuable.class)
                .getValualbeList(HttpHelp.getUserId(context),((PersonInfoActivity)getActivity()).getId(),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeValuanleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeValuanleBean value) {

                        if(page==1&&(value==null||value.getData()==null||value.getData().getArtcircleList().getList()==null||value.getData().getArtcircleList().getList().size()<=0)){

                            emptyList(listview_empty,loadMoreList);
                            resetState();
                            return ;
                        }


                        if(page>1&&(value==null||value.getData()==null||value.getData().getArtcircleList()==null||value.getData().getArtcircleList().getList().size()<=0)){
                            resetState();
                            loadMoreList.onLoadEnd();
                            page--;
                            return ;
                        }

                        homevaluableBeen.addAll(value.getData().getArtcircleList().getList());
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

        homevaluableBeen.clear();
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
