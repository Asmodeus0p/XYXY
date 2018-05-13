package com.jiyun.asmodeus.xyxy.view.fragment.workactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IPublishWokDetail;
import com.jiyun.asmodeus.xyxy.model.entity.PreferenceListBean;
import com.jiyun.asmodeus.xyxy.model.entity.PublishWokDetailBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.view.adapter.PublishWokDetailTypeAdapter;
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

public class PublishWokDetailActivity extends BaselivstActivity {


    public static final String Wokid = "wokid";

    @ViewInject(R.id.publishwok_detail_recyclerview)
    private RecyclerView publishwok_detail_recyclerview;

    @ViewInject(R.id.publishwok_detail_from)
    private TextView publishwok_detail_from;

    @ViewInject(R.id.publishwok_detail_title)
    private TextView publishwok_detail_title;

    @ViewInject(R.id.publishwok_detail_name)
    private TextView publishwok_detail_name;

    @ViewInject(R.id.publishwok_detail_usertype)
    private ImageView publishwok_detail_usertype;

    @ViewInject(R.id.publishwok_detail_intro)
    private TextView publishwok_detail_intro;

    @ViewInject(R.id.publishwok_detail_content)
    private TextView publishwok_detail_content;

    public static void start(Activity activity, int wokid) {
        Intent intent = new Intent(activity, PublishWokDetailActivity.class);
        intent.putExtra(Wokid, wokid);
        activity.startActivity(intent);
    }

    private Context context;

    private int id;

    private List<String> typelist;

    private CompositeDisposable compositeDisposable;

    private PublishWokDetailTypeAdapter adapter;

    private PublishWokDetailBean.DataBean dataBean;

    private int teacherId=0;

    private App artliveApp;

    //专业类型
    private List<PreferenceListBean.DataBean.MajorsBean> majorsBeen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_wok_detail);
        ViewUtils.inject(this);
        setTitleTheme(this,true);
        init();

        initView();

        loadContent();
    }

    private void init(){

        context=this;

        artliveApp = (App) context.getApplicationContext();

        compositeDisposable = new CompositeDisposable();

        typelist = new ArrayList<>();

        majorsBeen = new ArrayList<>();

        if(artliveApp.getMajorsBeen()!=null){
            majorsBeen.addAll(artliveApp.getMajorsBeen());
        }

        id = getIntent().getIntExtra(Wokid, 0);

        adapter=new PublishWokDetailTypeAdapter(context, typelist);

    }

    private void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        publishwok_detail_recyclerview.setLayoutManager(linearLayoutManager);

        publishwok_detail_recyclerview.setAdapter(adapter);
    }


    @OnClick({R.id.publishwok_detail_title_cancle,R.id.publishwok_detail_coachbtn})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.publishwok_detail_title_cancle:

                this.finish();

                break;
            case R.id.publishwok_detail_coachbtn:

                if(dataBean==null){

                    return ;
                }

                if(TextUtils.equals("私塾",dataBean.getSource())){
//                    PublishRedactWokDetail.start((Activity) context,teacherId,dataBean.getRealname(),dataBean.getPrice(),dataBean.getSource(),PublishRedactWokDetail.SISHUZUOYE);
                }else if(TextUtils.equals("讲堂",dataBean.getSource())){
//                    PublishRedactWokDetail.start((Activity) context,teacherId,dataBean.getRealname(),dataBean.getPrice(),dataBean.getSource(),PublishRedactWokDetail.FABUZUOYE);
                }

                break;
            default:
                break;
        }
    }

    private void loadContent(){

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IPublishWokDetail.class)
                .getWokDetail(id+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishWokDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(PublishWokDetailBean value) {
                        if(value==null||value.getData()==null){

                            return ;
                        }

                        dataBean = value.getData();

                        publishwok_detail_from.setText(value.getData().getSource());

                        if(!TextUtils.isEmpty(value.getData().getMajorIds())){

                            String []  majors=value.getData().getMajorIds().split(",");
                            if(majors!=null) {

                                for (int i = 0; i < majors.length; i++) {
                                    if (majorsBeen.size() >= i) {

                                        typelist.add(majorsBeen.get(Integer.parseInt(majors[i])).getName());
                                    }
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        publishwok_detail_title.setText(value.getData().getTitle());

                        publishwok_detail_name.setText(value.getData().getRealname());

                        SplitStringColorUtils.setImgLevel(publishwok_detail_usertype,value.getData().getUserType());

                        publishwok_detail_intro.setText(value.getData().getIntro());

                        publishwok_detail_content.setText(value.getData().getContent());

                        teacherId = value.getData().getTeacherId();
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
