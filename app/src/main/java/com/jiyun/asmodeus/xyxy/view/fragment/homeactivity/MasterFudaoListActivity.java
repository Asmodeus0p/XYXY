package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IMasterDetailFudaoList;
import com.jiyun.asmodeus.xyxy.model.biz.IPraise;
import com.jiyun.asmodeus.xyxy.model.biz.IPraiseCancle;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.HomeWokListBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeWorkListViewAdapter;

import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.LoginActivity;
import com.jiyun.asmodeus.xyxy.view.ui.LoadMoreListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MasterFudaoListActivity extends BaselivstActivity  {
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

    @ViewInject(R.id.masterfudaolist_loadMorelist)
    private LoadMoreListView loadMoreList;
    @ViewInject(R.id.masterfudaolist_aty_title_tv)
    private TextView masterfudaolist_aty_title_tv;

    @ViewInject(R.id.masterfudaolist_list_empty)
    private RelativeLayout masterfudaolist_list_empty;

    @ViewInject(R.id.masterfudaolist_list_fault)
    private RelativeLayout masterfudaolist_list_fault;

    @ViewInject(R.id.masterfudaolist_list_fault_btn)
    private Button masterfudaolist_list_fault_btn;

    private Context context;


    public static void start(Activity activity, String name, int teacher_id) {
        Intent intent = new Intent(activity, MasterFudaoListActivity.class);
        intent.putExtra(Constant.User_name, name);
        intent.putExtra(Constant.Teacher_id, teacher_id);
        activity.startActivity(intent);
    }

    private int page = 1;

    private List<WorkBean.DataBean.ListBean> homewoksBeen;

    private HomeWorkListViewAdapter adapter;

    private CompositeDisposable compositeDisposable;

    private String name;

    private int teacherid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_fudao_list);
        ViewUtils.inject(this);

        setTitleTheme(this,true);

        init();

        initView();



        loadContent();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }


    private void init(){
        context=this;

        compositeDisposable = new CompositeDisposable();

        homewoksBeen = new ArrayList<>();

        adapter = new HomeWorkListViewAdapter(context, homewoksBeen);

        if(getIntent()!=null){

            name = getIntent().getStringExtra(Constant.User_name);

            teacherid = getIntent().getIntExtra(Constant.Teacher_id, 0);
        }

    }

    private void initView(){
        masterfudaolist_aty_title_tv.setText(String.format("%s辅导的作业",name));

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

                if(favorite==Constant.NOTFAVORITE){

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


    @OnClick({R.id.masterfudaolist_aty_cancle})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.masterfudaolist_aty_cancle:

                this.finish();

                break;
            default:
                break;
        }

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
                .create(IMasterDetailFudaoList.class)
                .getMasterDetailFudao(HttpHelp.getUserId(context),teacherid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WorkBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WorkBean value) {

                        Log.d("TAG", "onNext: "+page);

                        if(page==1&&(value==null||value.getData()==null||value.getData().getList()==null||value.getData().getList().size()<=0)){
                            emptyList(masterfudaolist_list_empty,loadMoreList);
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

                            faultList(masterfudaolist_list_fault, masterfudaolist_list_fault_btn, loadMoreList, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    faultDataFillList(masterfudaolist_list_fault,loadMoreList);
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
}
