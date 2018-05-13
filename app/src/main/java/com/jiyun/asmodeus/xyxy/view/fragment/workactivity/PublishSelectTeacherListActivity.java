package com.jiyun.asmodeus.xyxy.view.fragment.workactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IPublishSelectTeacherList;
import com.jiyun.asmodeus.xyxy.model.entity.PublishSelectTeacherModel;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.PublishSelectTeacherListAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.RecycleViewFooter;
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

public class PublishSelectTeacherListActivity extends BaselivstActivity {

    public static final int NORMAL_POSITION = 0;


    @ViewInject(R.id.publish_select_teacher_recyclerview)
    private RecyclerView recyclerView;

    public static void start(Activity activity, int teacherid, int forResult) {
        Intent intent = new Intent(activity, PublishSelectTeacherListActivity.class);
        intent.putExtra(Constant.Teacher_id, teacherid);
        activity.startActivityForResult(intent,forResult);
    }


    private Context context;

    private CompositeDisposable compositeDisposable;

    private int page = 1;

    private int teacher_id;

    private List<PublishSelectTeacherModel.DataBean.ListBean> listBeen;

    private PublishSelectTeacherListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_select_teacher_list);
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

        if(getIntent()!=null){
            teacher_id = getIntent().getIntExtra(Constant.Teacher_id, 0);
        }

        compositeDisposable = new CompositeDisposable();

        listBeen = new ArrayList<>();

        PublishSelectTeacherModel.DataBean.ListBean  bean= new PublishSelectTeacherModel.DataBean.ListBean();

        bean.setTeacherId(0);

        listBeen.add(bean);

        adapter = new PublishSelectTeacherListAdapter(context,teacher_id,R.layout.publish_selectteacher_listitem,listBeen);
    }

    private void initView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        adapter.bindToRecyclerView(recyclerView);
        adapter.disableLoadMoreIfNotFullPage();

        adapter.setLoadMoreView(new RecycleViewFooter());

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadContent();
            }
        },recyclerView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra(Constant.Teacher_id, listBeen.get(position).getTeacherId());
                intent.putExtra(Constant.User_name, listBeen.get(position).getRealname());
                intent.putExtra(Constant.Teacher_price, listBeen.get(position).getPrice());
                //设置返回数据
                PublishSelectTeacherListActivity.this.setResult(RESULT_OK, intent);
                //关闭Activity
                PublishSelectTeacherListActivity.this.finish();
            }
        });
    }


    @OnClick({R.id.publish_select_teacher_listayt_cancle})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.publish_select_teacher_listayt_cancle:

                this.finish();

                break;
            default:
                break;

        }

    }


    private void loadContent(){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IPublishSelectTeacherList.class)
                .getSelectTeacher(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishSelectTeacherModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(PublishSelectTeacherModel value) {

                        if(value==null){
                            if(page>1){

                                page--;
                            }
                            return ;
                        }
                        if(value.getData()==null||value.getData().getList()==null||value.getData().getList().size()<=0){

                            adapter.loadMoreEnd();
                        }

                        listBeen.addAll(value.getData().getList());
                        adapter.notifyDataSetChanged();
                        adapter.loadMoreComplete();

                    }

                    @Override
                    public void onError(Throwable e) {
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
