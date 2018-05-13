package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IMasterDetailWork;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailWorkBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterWorkListAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MasterWorkListActivity extends BaselivstActivity {




    @ViewInject(R.id.masterworklist_recyclerview)
    private RecyclerView recyclerview;

    @ViewInject(R.id.masterworklist_title_tv)
    private TextView masterworklist_title_tv;

    @ViewInject(R.id.masterworklist_recyclerview_empty)
    private RelativeLayout recyclerview_empty;

    @ViewInject(R.id.masterworklist_recyclerview_fault)
    private RelativeLayout recyclerview_fault;

    @ViewInject(R.id.masterworklist_recyclerview_fault_btn)
    private Button fault_btn;


    public static void start(Activity activity, String name, int teacher_id) {
        Intent intent = new Intent(activity, MasterWorkListActivity.class);
        intent.putExtra(Constant.User_name, name);
        intent.putExtra(Constant.Teacher_id, teacher_id);
        activity.startActivity(intent);
    }


    private Context context;

    private CompositeDisposable compositeDisposable;

    private List<MasterDetailWorkBean.DataBean.ListBean> datalist;

    private MasterWorkListAdapter adapter;

    private String name;

    private int teacherid;

    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_work_list);
        ButterKnife.bind(this);

        ViewUtils.inject(this);

        setTitleTheme(this, true);

        init();

        initView();

        loadContent();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }

    private void init() {

        context = this;

        compositeDisposable = new CompositeDisposable();

        datalist = new ArrayList<>();

        adapter = new MasterWorkListAdapter(context, R.layout.detailwork_list_item, datalist);

        if (getIntent() != null) {

            name = getIntent().getStringExtra(Constant.User_name);

            teacherid = getIntent().getIntExtra(Constant.Teacher_id, 0);
        }
    }

    private void initView() {

        masterworklist_title_tv.setText(String.format("%s的作业", name));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);

        recyclerview.setAdapter(adapter);

        adapter.bindToRecyclerView(recyclerview);
        adapter.disableLoadMoreIfNotFullPage();


        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadContent();
            }
        }, recyclerview);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

//                if(datalist.isEmpty()){
//                    return;
//                }
//
//                PublishWokDetail.start((Activity) context,datalist.get(position).getId());
            }
        });
    }

    @OnClick({R.id.masterworklist_title_cancle})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.masterworklist_title_cancle:

                this.finish();

                break;
            default:
                break;
        }

    }


    public void loadContent() {
        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IMasterDetailWork.class)
                .getMasterDetailWork(teacherid, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MasterDetailWorkBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MasterDetailWorkBean value) {

                        if (page == 1 && (value == null || value.getData() == null || value.getData().getList() == null || value.getData().getList().size() <= 0)) {
                            emptyList(recyclerview_empty, recyclerview);
                            return;
                        }


                        if (page > 1 && (value == null || value.getData() == null || value.getData().getList() == null || value.getData().getList().size() <= 0)) {
                            page--;
                            adapter.loadMoreEnd();
                            return;
                        }

                        datalist.addAll(value.getData().getList());
                        adapter.notifyDataSetChanged();
                        if (Constant.PAGE_LOAD_INDEX > datalist.size()) {
                            adapter.loadMoreEnd();
                            return;
                        }
                        adapter.loadMoreComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (page == 1) {
                            faultList(recyclerview_fault, fault_btn, recyclerview, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    faultDataFillList(recyclerview_fault, recyclerview);
                                    loadContent();
                                }
                            });
                        }

                        if (page > 1) {

                            page--;
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
