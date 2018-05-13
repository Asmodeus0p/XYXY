package com.jiyun.asmodeus.xyxy.view.fragment.workactivity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IWokSubjectList;
import com.jiyun.asmodeus.xyxy.model.entity.WokSubjectListBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.PublishWorkListAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.RecycleViewFooter;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
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

public class PublishSelectWokListActivity extends BaselivstActivity {

    @ViewInject(R.id.masterworklist_recyclerview)
    private RecyclerView masterworklist_recyclerview;

    @ViewInject(R.id.masterworklist_title_tv)
    private TextView masterworklist_title_tv;

    @ViewInject(R.id.masterworklist_recyclerview_empty)
    private RelativeLayout recyclerview_empty;

    @ViewInject(R.id.masterworklist_recyclerview_fault)
    private RelativeLayout recyclerview_fault;

    @ViewInject(R.id.masterworklist_recyclerview_fault_btn)
    private Button fault_btn;


    private Context context;

    private List<WokSubjectListBean.DataBean.ListBean> datalist;

    private PublishWorkListAdapter adapter;

    private int page = 1;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_select_wok_list);

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

    private  void init (){

        context=this;

        compositeDisposable = new CompositeDisposable();

        datalist = new ArrayList<>();

        adapter=new PublishWorkListAdapter(context,R.layout.detailwork_list_item, datalist);
    }

    private void initView(){

        masterworklist_title_tv.setText("作业题列表");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        masterworklist_recyclerview.setLayoutManager(linearLayoutManager);

        masterworklist_recyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                PublishWokDetailActivity.start((Activity) context,datalist.get(position).getId());
            }
        });

        adapter.bindToRecyclerView(masterworklist_recyclerview);
        adapter.disableLoadMoreIfNotFullPage();

        adapter.setLoadMoreView(new RecycleViewFooter());
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadContent();
            }
        },masterworklist_recyclerview);

    }


    @OnClick({R.id.masterworklist_title_cancle})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.masterworklist_title_cancle:

                this.finish();

                break;
            default:
                break;
        }


    }

    private void loadContent(){

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IWokSubjectList.class)
                .getWokSubjectList(HttpHelp.getUserId(context),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WokSubjectListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WokSubjectListBean value) {

                        if(page==1&&(value==null||value.getData()==null||value.getData().getList()==null||value.getData().getList().size()<=0)){
                            emptyList(recyclerview_empty,masterworklist_recyclerview);
                            return ;
                        }


                        if(page>1&&(value==null||value.getData()==null||value.getData().getList()==null||value.getData().getList().size()<=0)){
                            page--;
                            adapter.loadMoreEnd();
                            return ;
                        }


                        datalist.addAll(value.getData().getList());
                        adapter.notifyDataSetChanged();
                        if(Constant.PAGE_LOAD_INDEX>datalist.size()){
                            adapter.loadMoreEnd();
                            return;
                        }
                        adapter.loadMoreComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(page==1){
                            faultList(recyclerview_fault, fault_btn, masterworklist_recyclerview, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    faultDataFillList(recyclerview_fault,masterworklist_recyclerview);
                                    loadContent();
                                }
                            });
                        }

                        if(page>1){

                            page--;
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
