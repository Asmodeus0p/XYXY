package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IAttention;
import com.jiyun.asmodeus.xyxy.model.biz.IAttentionCancle;
import com.jiyun.asmodeus.xyxy.model.biz.IDetailMaster;
import com.jiyun.asmodeus.xyxy.model.biz.IPraise;
import com.jiyun.asmodeus.xyxy.model.biz.IPraiseCancle;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailBean;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetialWokLineBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterDetailCourseAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterDetailLiveAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterDetailSortAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.LoginActivity;
import com.jiyun.asmodeus.xyxy.view.ui.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MasterDetailActivity extends BaselivstActivity {
    private static final int ZHIBO = 0;
    private static final int ZUOYE = 1;
    private static final int FUDAO = 2;
    private static final int TIEZI = 3;
    private static final int GUANZHU = 4;
    private static final int FENSI = 5;


    @ViewInject(R.id.masterdetail_img)
    private ImageView masterdetail_img;

    @ViewInject(R.id.masterdetail_logtime)
    private TextView masterdetail_logtime;

    @ViewInject(R.id.masterdetail_replynum)
    private CheckBox masterdetail_replynum;

    @ViewInject(R.id.masterdetail_teacherimg)
    private RoundedImageView masterdetail_teacherimg;

    @ViewInject(R.id.masterdetail_teachername)
    private TextView masterdetail_teachername;

    @ViewInject(R.id.masterdetail_teachertype)
    private ImageView masterdetail_teachertype;

    @ViewInject(R.id.masterdetail_teacherintro)
    private TextView masterdetail_teacherintro;

    @ViewInject(R.id.masterdetail_teacherdetail_tv)
    private TextView masterdetail_teacherdetail_tv;

    @ViewInject(R.id.masterdetail_recyclerview)
    private RecyclerView masterdetail_recyclerview;

    @ViewInject(R.id.masterdetail_live_listview)
    private MyListView live_listview;

    @ViewInject(R.id.masterdetail_courses_listview)
    private MyListView courses_listview;

    @ViewInject(R.id.masterdetail_teacher_Attention)
    private TextView masterdetail_teacher_Attention;

    public static void start(Activity activity, int teacherid) {
        Intent intent = new Intent(activity, MasterDetailActivity.class);
        intent.putExtra(Constant.Teacher_id, teacherid);
        activity.startActivity(intent);
    }

    private Context context;

    private MasterDetailSortAdapter adapter;

    private MasterDetailLiveAdapter liveAdapter;

    private MasterDetailCourseAdapter courseAdapter;


    private List<MasterDetialWokLineBean> recyclerList;

    private List<MasterDetailBean.DataBean.LiveCoursesBean> liveCoursesBeen;

    private List<MasterDetailBean.DataBean.CoursesBean> coursesBeen;

    private int teacherid;

    private CompositeDisposable compositeDisposable;

    private String[] flag = {"课程", "作业", "辅导", "帖子", "关注", "粉丝"};

    private String name;

    private boolean isAttention = false;

    private MasterDetailBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);
        ViewUtils.inject(this);
        setTitleTheme(this, true);

        init();

        initView();


    }

    @Override
    protected void onResume() {
        super.onResume();


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

        recyclerList = new ArrayList<>();

        liveCoursesBeen = new ArrayList<>();

        coursesBeen = new ArrayList<>();

        for (int i = 0; i < flag.length; i++) {
            MasterDetialWokLineBean model = new MasterDetialWokLineBean();
            model.setName(flag[i]);
            recyclerList.add(model);
        }

        adapter = new MasterDetailSortAdapter(context, R.layout.masterdetail_sortlist_item, recyclerList);

        liveAdapter = new MasterDetailLiveAdapter(context, liveCoursesBeen);

        courseAdapter = new MasterDetailCourseAdapter(context, coursesBeen);

        teacherid = getIntent().getIntExtra(Constant.Teacher_id, 0);

        Log.e("id",teacherid+"");
    }

    private void initView() {

        live_listview.setAdapter(liveAdapter);

        courses_listview.setAdapter(courseAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        masterdetail_recyclerview.setLayoutManager(linearLayoutManager);
        masterdetail_recyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                switch (position) {
                    case ZHIBO:

                        MasterLiveListActivity.start((Activity) context, name, teacherid);

                        break;
                    case ZUOYE:

                        MasterWorkListActivity.start((Activity) context, name, teacherid);

                        break;
                    case FUDAO:

                        MasterFudaoListActivity.start((Activity) context, name, teacherid);

                        break;
                    case TIEZI:

                        MasterTieziListActivity.start((Activity) context, name, teacherid);

                        break;

                    case GUANZHU:


                        MasterGuanzhuListActivity.start((Activity) context, name, teacherid);

                        break;

                    case FENSI:

                        MasterFansListActivity.start((Activity) context, name, teacherid);

                        break;
                    default:
                        break;
                }
            }
        });
    }


    @OnClick({R.id.masterdetail_cancle, R.id.masterdetail_replynum,
            R.id.masterdetail_teacher_Attention, R.id.masterdetail_coachbtn,
            R.id.masterdetail_aty_share})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.masterdetail_cancle:

                this.finish();

                break;
            case R.id.masterdetail_replynum:

                if (!HttpHelp.isLogin(context)) {
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }

                if (data == null || data.getUser() == null) {
                    return;
                }

                if (data.getPraise().getIsPraise() == Constant.NOTFAVORITE) {

                    parise(teacherid, data.getUser().getId(), Constant.Praise_teacher);

                    data.getPraise().setIsPraise(Constant.FAVORITE);

                    masterdetail_replynum.setChecked(true);

                    data.getPraise().setPraiseCount(data.getPraise().getPraiseCount() + 1);
                    masterdetail_replynum.setText(data.getPraise().getPraiseCount() + "");
                } else {

                    pariseCancle(teacherid, data.getUser().getId(), Constant.Praise_teacher);

                    data.getPraise().setIsPraise(Constant.NOTFAVORITE);

                    masterdetail_replynum.setChecked(false);
                    if (data.getPraise().getPraiseCount() == 0) {
                        masterdetail_replynum.setText(data.getPraise().getPraiseCount() + "");
                    } else {
                        data.getPraise().setPraiseCount(data.getPraise().getPraiseCount() - 1);
                        masterdetail_replynum.setText(data.getPraise().getPraiseCount() + "");
                    }
                }


                break;
            case R.id.masterdetail_teacher_Attention:

                if (!HttpHelp.isLogin(context)) {
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }

                if (data == null) {
                    return;
                }

                if (isAttention) {
                    attentionCancle(teacherid);
                    isAttention = false;
                    masterdetail_teacher_Attention.setText("关注");
                    masterdetail_teacher_Attention.setActivated(true);
                } else {
                    attention(teacherid);
                    isAttention = true;
                    masterdetail_teacher_Attention.setText("已关注");
                    masterdetail_teacher_Attention.setActivated(false);
                }

                break;
            case R.id.masterdetail_coachbtn:

                if (!HttpHelp.isLogin(context)) {
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }

                if (data == null || data.getUser() == null) {
                    return;
                }


                break;
            case R.id.masterdetail_aty_share:

                if (data.getUser() != null) {
                }

                break;
            default:
                break;
        }

    }


    private void loadContent() {

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IDetailMaster.class)
                .getMasterDetail(teacherid, HttpHelp.getUserId(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MasterDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MasterDetailBean value) {

                        if (value == null) {

                            return;
                        }

                        fillData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void fillData(MasterDetailBean value) {
        if (value.getData() == null) {

            return;
        }

        data = value.getData();
        if (data.getUser() != null) {

            Glide.with(context).load(data.getUser().getImages()).into(masterdetail_img);
            masterdetail_logtime.setText(TimeShift.getChatTime(data.getUser().getLastTime()));
            if (!TextUtils.isEmpty(data.getUser().getSkilled())) {
                masterdetail_logtime.setText(data.getUser().getSkilled().replace(",", " "));
            }

            Glide.with(context).load(data.getUser().getPhoto()).into(masterdetail_teacherimg);
            name = data.getUser().getRealname();
            masterdetail_teachername.setText(data.getUser().getRealname());

            SplitStringColorUtils.setImgLevel(masterdetail_teachertype, data.getUser().getUserType());

            masterdetail_teacherintro.setText(data.getUser().getIntro());

            masterdetail_teacherdetail_tv.setText(data.getUser().getDetails());
        }

        masterdetail_replynum.setText(data.getPraise().getPraiseCount() + "");

        if (data.getPraise().getIsPraise() == Constant.NOTFAVORITE) {
            masterdetail_replynum.setChecked(false);
        } else {
            masterdetail_replynum.setChecked(true);
        }

        recyclerList.get(ZHIBO).setNum(data.getLiveCount() + "");
        recyclerList.get(ZUOYE).setNum(data.getHomewokPublishCount() + "");
        recyclerList.get(FUDAO).setNum(data.getCoachingCount() + "");
        recyclerList.get(TIEZI).setNum(data.getPostsCount() + "");
        recyclerList.get(GUANZHU).setNum(data.getAttentionCount() + "");
        recyclerList.get(FENSI).setNum(data.getFansCount() + "");
        adapter.notifyDataSetChanged();

        liveCoursesBeen.addAll(data.getLiveCourses());
        liveAdapter.notifyDataSetChanged();

        coursesBeen.addAll(data.getCourses());
        courseAdapter.notifyDataSetChanged();

        if (data.getIsAttention() == Constant.Attention) {
            masterdetail_teacher_Attention.setText("关注");
            masterdetail_teacher_Attention.setActivated(true);
            isAttention = false;
        } else if (data.getIsAttention() == Constant.Attention_yiguanzhu) {
            masterdetail_teacher_Attention.setText("已关注");
            masterdetail_teacher_Attention.setActivated(false);
            isAttention = true;
        } else if (data.getIsAttention() == Constant.Attention_xianghu) {
            masterdetail_teacher_Attention.setText("相互关注");
            masterdetail_teacher_Attention.setActivated(false);
            isAttention = true;
        }

    }

    private void attention(int userId) {

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IAttention.class)
                .postAttention(userId, HttpHelp.getUserId(context))
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


    private void attentionCancle(int userId) {

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IAttentionCancle.class)
                .postAttention(userId, HttpHelp.getUserId(context))
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


    private void parise(int userid, int itemid, String type) {
        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IPraise.class)
                .postPraise(userid, itemid, HttpHelp.getUserId(context), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {
                        if (value == null) {
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

    private void pariseCancle(int userid, int itemid, String type) {
        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IPraiseCancle.class)
                .postPraiseCancle(userid, itemid, HttpHelp.getUserId(context), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {
                        if (value == null) {
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

}
