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
import com.jiyun.asmodeus.xyxy.model.biz.IMasterDetailLive;
import com.jiyun.asmodeus.xyxy.model.entity.LivingListBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.LivingListAtyAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;

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

public class MasterLiveListActivity extends BaselivstActivity {


    @BindView(R.id.masterlive_list_aty_cancle)
    TextView masterliveListAtyCancle;
    @BindView(R.id.masterlivelist_teachername_title)
    TextView masterlivelistTeachernameTitle;
    @BindView(R.id.masterlivelist_recyclerview)
    RecyclerView masterlivelistRecyclerview;
    @BindView(R.id.masterlivelist_recyclerview_empty)
    RelativeLayout masterlivelistRecyclerviewEmpty;
    @BindView(R.id.masterlivelist_recyclerview_fault_btn)
    Button masterlivelistRecyclerviewFaultBtn;
    @BindView(R.id.masterlivelist_recyclerview_fault)
    RelativeLayout masterlivelistRecyclerviewFault;

    public static void start(Activity activity, String name, int teacher_id) {
        Intent intent = new Intent(activity, MasterLiveListActivity.class);
        intent.putExtra(Constant.User_name, name);
        intent.putExtra(Constant.Teacher_id, teacher_id);
        activity.startActivity(intent);
    }


    private Context context;

    private CompositeDisposable compositeDisposable;

    private List<LivingListBean.DataBean.ListBean> datalist;

    private LivingListAtyAdapter adapter;

    private int page = 1;

    private String name;

    private int teacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_live_list);
        ButterKnife.bind(this);
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

        adapter = new LivingListAtyAdapter(context, R.layout.masterlive_list_aty_item, datalist);

        if (getIntent() != null) {
            name = getIntent().getStringExtra(Constant.User_name);
            teacher = getIntent().getIntExtra(Constant.Teacher_id, 0);
        }

    }

    private void initView() {

//        masterlivelist_teachername_title.setText(String.format("%s的直播",name));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        masterlivelistRecyclerview.setLayoutManager(linearLayoutManager);
        masterlivelistRecyclerview.setAdapter(adapter);

        adapter.bindToRecyclerView(masterlivelistRecyclerview);
        adapter.disableLoadMoreIfNotFullPage();


        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadContent();
            }
        }, masterlivelistRecyclerview);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, MasterLiveDetailActivity.class);
                intent.putExtra(Constant.Live_id, datalist.get(position).getId());
                startActivity(intent);
            }
        });

    }


    @OnClick({R.id.masterlive_list_aty_cancle})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.masterlive_list_aty_cancle:
                this.finish();
                break;
            default:
                break;
        }

    }


    private void loadContent() {

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IMasterDetailLive.class)
                .getMasterDetailLive(HttpHelp.getUserId(context), teacher, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LivingListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LivingListBean value) {

                        if (page == 1 && (value == null || value.getData() == null || value.getData().getList() == null || value.getData().getList().size() <= 0)) {
                            emptyList(masterlivelistRecyclerviewEmpty, masterlivelistRecyclerview);
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
                            faultList(masterlivelistRecyclerviewFault, masterlivelistRecyclerviewFaultBtn, masterlivelistRecyclerview, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    faultDataFillList(masterlivelistRecyclerviewFault, masterlivelistRecyclerview);
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
