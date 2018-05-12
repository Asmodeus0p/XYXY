package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;


import android.app.Activity;
import android.content.Intent;
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
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailBean;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetialWokLineBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterDetailCourseAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterDetailLiveAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterDetailSortAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;
import com.jiyun.asmodeus.xyxy.view.ui.MyListView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MasterDetailActivity extends BaseActivity implements View.OnClickListener {
    private static final int ZHIBO = 0;
    private static final int ZUOYE = 1;
    private static final int FUDAO = 2;
    private static final int TIEZI = 3;
    private static final int GUANZHU = 4;
    private static final int FENSI = 5;
    private MasterDetailSortAdapter adapter;
    private MasterDetailLiveAdapter liveAdapter;
    private String name;
    private MasterDetailCourseAdapter courseAdapter;

    private List<MasterDetialWokLineBean> recyclerList = new ArrayList<>();

    private List<MasterDetailBean.DataBean.LiveCoursesBean> liveCoursesBeen = new ArrayList<>();

    private List<MasterDetailBean.DataBean.CoursesBean> coursesBeen = new ArrayList<>();
    private RelativeLayout masterdetail_coachbtn;
    private ImageView masterdetail_img;
    private TextView masterdetail_logtime;
    private CheckBox masterdetail_replynum;
    private RoundedImageView masterdetail_teacherimg;
    private TextView masterdetail_teachername;
    private ImageView masterdetail_teachertype;
    private TextView masterdetail_teacherintro;
    private TextView masterdetail_teacher_Attention;
    private RecyclerView masterdetail_recyclerview;
    private MyListView masterdetail_live_listview;
    private MyListView masterdetail_courses_listview;
    private TextView masterdetail_teacherdetail_tv;
    private ImageView masterdetail_cancle;
    private ImageView masterdetail_aty_share;
    private String[] flag = {"课程", "作业", "辅导", "帖子", "关注", "粉丝"};
    private boolean isAttention = false;
    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_master_detail;
    }

    @Override
    protected void initData() {

        for (int i = 0; i < flag.length; i++) {
            MasterDetialWokLineBean model = new MasterDetialWokLineBean();
            model.setName(flag[i]);
            recyclerList.add(model);
        }
        getData();
    }

    @Override
    protected void initView() {
        masterdetail_coachbtn = (RelativeLayout) findViewById(R.id.masterdetail_coachbtn);
        masterdetail_coachbtn.setOnClickListener(this);
        masterdetail_img = (ImageView) findViewById(R.id.masterdetail_img);
        masterdetail_logtime = (TextView) findViewById(R.id.masterdetail_logtime);
        masterdetail_replynum = (CheckBox) findViewById(R.id.masterdetail_replynum);
        masterdetail_replynum.setOnClickListener(this);
        masterdetail_teacherimg = (RoundedImageView) findViewById(R.id.masterdetail_teacherimg);
        masterdetail_teachername = (TextView) findViewById(R.id.masterdetail_teachername);
        masterdetail_teachertype = (ImageView) findViewById(R.id.masterdetail_teachertype);
        masterdetail_teacherintro = (TextView) findViewById(R.id.masterdetail_teacherintro);
        masterdetail_teacher_Attention = (TextView) findViewById(R.id.masterdetail_teacher_Attention);
        masterdetail_teacher_Attention.setOnClickListener(this);
        masterdetail_recyclerview = (RecyclerView) findViewById(R.id.masterdetail_recyclerview);
        masterdetail_live_listview = (MyListView) findViewById(R.id.masterdetail_live_listview);
        masterdetail_courses_listview = (MyListView) findViewById(R.id.masterdetail_courses_listview);
        masterdetail_teacherdetail_tv = (TextView) findViewById(R.id.masterdetail_teacherdetail_tv);
        masterdetail_cancle = (ImageView) findViewById(R.id.masterdetail_cancle);
        masterdetail_cancle.setOnClickListener(this);
        masterdetail_aty_share = (ImageView) findViewById(R.id.masterdetail_aty_share);
        masterdetail_aty_share.setOnClickListener(this);
        adapter = new MasterDetailSortAdapter(this, R.layout.masterdetail_sortlist_item, recyclerList);
        liveAdapter = new MasterDetailLiveAdapter(this, liveCoursesBeen);
        courseAdapter = new MasterDetailCourseAdapter(this, coursesBeen);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        masterdetail_recyclerview.setLayoutManager(linearLayoutManager);
        masterdetail_recyclerview.setAdapter(adapter);
        masterdetail_live_listview.setAdapter(liveAdapter);
        masterdetail_courses_listview.setAdapter(courseAdapter);
        liveAdapter.notifyDataSetChanged();
        courseAdapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(
                new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (position) {
                            case ZHIBO:
                                Intent intent = new Intent(MasterDetailActivity.this, MasterLiveListActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("teacherid", id);
                                startActivity(intent);
                                break;
                            case ZUOYE:
                                Intent intent1 = new Intent(MasterDetailActivity.this, MasterWorkListActivity.class);
                                intent1.putExtra("name", name);
                                intent1.putExtra("teacherid", id);
                                startActivity(intent1);
                                break;
                            case FUDAO:
                                Intent intent2 = new Intent(MasterDetailActivity.this, MasterFudaoListActivity.class);
                                intent2.putExtra("name", name);
                                intent2.putExtra("teacherid", id);
                                startActivity(intent2);
                                break;
                            case TIEZI:
                                Intent intent3 = new Intent(MasterDetailActivity.this, MasterTieziListActivity.class);
                                intent3.putExtra("name", name);
                                intent3.putExtra("teacherid", id);
                                startActivity(intent3);
                                break;
                            case GUANZHU:
                                Intent intent4 = new Intent(MasterDetailActivity.this, MasterGuanzhuListActivity.class);
                                intent4.putExtra("name", name);
                                intent4.putExtra("teacherid", id);
                                startActivity(intent4);
                                break;
                            case FENSI:
                                Intent intent5 = new Intent(MasterDetailActivity.this, MasterGuanzhuListActivity.class);
                                intent5.putExtra("name", name);
                                intent5.putExtra("teacherid", id);
                                startActivity(intent5);
                                break;
                            default:
                                break;
                        }
                    }
                }
        );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.masterdetail_cancle:
                finish();
                break;
            case R.id.masterdetail_replynum:

                break;
            case R.id.masterdetail_teacher_Attention:

                break;
            case R.id.masterdetail_coachbtn:

                break;
            case R.id.masterdetail_aty_share:

                break;


        }
    }

    public void getData() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        id = getIntent().getIntExtra("id", 0);

        String appToken = (String) SharedPreferencesUtils.getParam(this, "xyxy_apptoken", "String");

        FormBody userId = new FormBody.Builder().add("userId", id + "").build();
        Request build = new Request.Builder().url("https://www.univstar.com/v1/m/user/homepage").post(userId).addHeader("apptoken", appToken).build();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MasterDetailBean detailBean = new Gson().fromJson(string, MasterDetailBean.class);
                        liveCoursesBeen.addAll(detailBean.getData().getLiveCourses());
                        coursesBeen.addAll(detailBean.getData().getCourses());
                        MasterDetailBean.DataBean data = detailBean.getData();
                        if (data.getUser() != null) {
                            Glide.with(MasterDetailActivity.this).load(data.getUser().getImages()).into(masterdetail_img);
                            if (!TextUtils.isEmpty(data.getUser().getSkilled())) {
                                masterdetail_logtime.setText(data.getUser().getSkilled().replace(",", " "));
                            }


                            Glide.with(MasterDetailActivity.this).load(data.getUser().getPhoto()).into(masterdetail_teacherimg);
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
                });
            }
        });
    }
}
