
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
import com.jiyun.asmodeus.xyxy.model.biz.IAttention;
import com.jiyun.asmodeus.xyxy.model.biz.IAttentionCancle;
import com.jiyun.asmodeus.xyxy.model.biz.ITeacherFans;
import com.jiyun.asmodeus.xyxy.model.entity.AttentionListBean;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterGuanzhuListAdapter;
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

public class MasterFansListActivity extends BaselivstActivity {


    @ViewInject(R.id.masterguanzhulist_recyclerview)
    private RecyclerView recyclerView;

    @ViewInject(R.id.masterguanzhulist_aty_title_tv)
    private TextView masterguanzhulist_aty_title_tv;

    @ViewInject(R.id.masterguanzhulist_recyclerview_empty)
    private RelativeLayout recyclerview_empty;

    @ViewInject(R.id.masterguanzhulist_recyclerview_fault)
    private RelativeLayout recyclerview_fault;

    @ViewInject(R.id.masterguanzhulist_recyclerview_fault_btn)
    private Button fault_btn;


    public static void start(Activity activity, String name, int teacher_id) {
        Intent intent = new Intent(activity, MasterFansListActivity.class);
        intent.putExtra(Constant.User_name, name);
        intent.putExtra(Constant.Teacher_id, teacher_id);
        activity.startActivity(intent);
    }

    private Context context;

    private List<AttentionListBean.DataBean.ListBean> datalist;

    private MasterGuanzhuListAdapter adapter;

    private int page = 1;

    private CompositeDisposable compositeDisposable;

    private String name;

    private int teacherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_guanzhu_list);
        ViewUtils.inject(this);
        setTitleTheme(this,true);

        init();

        initView();

        loadContent();
    }

    private void init(){
        context=this;

        compositeDisposable = new CompositeDisposable();

        datalist = new ArrayList<>();

        adapter = new MasterGuanzhuListAdapter(context,R.layout.masterguanzhu_listitem, datalist);

        if(getIntent()!=null){

            name = getIntent().getStringExtra(Constant.User_name);

            teacherId = getIntent().getIntExtra(Constant.Teacher_id, 0);

        }
    }

    private void initView(){

        masterguanzhulist_aty_title_tv.setText(String.format("%s的粉丝",name));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        adapter.bindToRecyclerView(recyclerView);
        adapter.disableLoadMoreIfNotFullPage();



        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadContent();
            }
        },recyclerView);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.masterguanzhu_listitem_guanzhu_tv:

                        if(datalist.get(position).getAttention()== Constant.NOTFAVORITE){
                            attention(datalist.get(position).getAttentionId());
                            datalist.get(position).setAttention(Constant.FAVORITE);
                        }else{
                            attentionCancle(datalist.get(position).getAttentionId());
                            datalist.get(position).setAttention(Constant.NOTFAVORITE);
                        }

                        break;
                    case R.id.masterguanzhu_listitem_img:

                        if(datalist.get(position).getUserType()>=2){
                            MasterDetailActivity.start((Activity) context,datalist.get(position).getFansId());
                        }else{
                            PersonInfoActivity.start((Activity) context,datalist.get(position).getFansId());
                        }
                        break;
                    default:
                        break;
                }
                adapter.notifyItemChanged(position);
            }
        });

    }


    @OnClick({R.id.masterguanzhulist_aty_title_cancle})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.masterguanzhulist_aty_title_cancle:

                this.finish();

                break;
            default:
                break;
        }

    }

    private void loadContent(){

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(ITeacherFans.class)
                .getTeacherFans(teacherId,HttpHelp.getUserId(context),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AttentionListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AttentionListBean value) {

                        if(page==1&&(value==null||value.getData()==null||value.getData().getList()==null||value.getData().getList().size()<=0)){
                            emptyList(recyclerview_empty,recyclerView);
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
                            faultList(recyclerview_fault, fault_btn, recyclerView, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    faultDataFillList(recyclerview_fault,recyclerView);
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



    private void attention(int userId){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IAttention.class)
                .postAttention(userId,HttpHelp.getUserId(context))
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


    private void attentionCancle(int userId){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IAttentionCancle.class)
                .postAttention(userId,HttpHelp.getUserId(context))
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
}
