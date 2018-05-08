package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailBean;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetialWokLineBean;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.model.utils.Urls;
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

    private MasterDetailSortAdapter adapter;
    private MasterDetailLiveAdapter liveAdapter;

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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_master_detail;
    }

    @Override
    protected void initData() {
        getData();
        for (int i = 0; i < flag.length; i++) {
            MasterDetialWokLineBean model = new MasterDetialWokLineBean();
            model.setName(flag[i]);
            recyclerList.add(model);
        }

    }

    @Override
    protected void initView() {
        masterdetail_coachbtn = (RelativeLayout) findViewById(R.id.masterdetail_coachbtn);
//        masterdetail_coachbtn.setOnClickListener(this);
        masterdetail_img = (ImageView) findViewById(R.id.masterdetail_img);
//        masterdetail_img.setOnClickListener(this);
        masterdetail_logtime = (TextView) findViewById(R.id.masterdetail_logtime);
//        masterdetail_logtime.setOnClickListener(this);
        masterdetail_replynum = (CheckBox) findViewById(R.id.masterdetail_replynum);
//        masterdetail_replynum.setOnClickListener(this);
        masterdetail_teacherimg = (RoundedImageView) findViewById(R.id.masterdetail_teacherimg);
//        masterdetail_teacherimg.setOnClickListener(this);
        masterdetail_teachername = (TextView) findViewById(R.id.masterdetail_teachername);
//        masterdetail_teachername.setOnClickListener(this);
        masterdetail_teachertype = (ImageView) findViewById(R.id.masterdetail_teachertype);
//        masterdetail_teachertype.setOnClickListener(this);
        masterdetail_teacherintro = (TextView) findViewById(R.id.masterdetail_teacherintro);
//        masterdetail_teacherintro.setOnClickListener(this);
        masterdetail_teacher_Attention = (TextView) findViewById(R.id.masterdetail_teacher_Attention);
//        masterdetail_teacher_Attention.setOnClickListener(this);
        masterdetail_recyclerview = (RecyclerView) findViewById(R.id.masterdetail_recyclerview);
        masterdetail_live_listview = (MyListView) findViewById(R.id.masterdetail_live_listview);
//        masterdetail_live_listview.setOnClickListener(this);
        masterdetail_courses_listview = (MyListView) findViewById(R.id.masterdetail_courses_listview);
//        masterdetail_courses_listview.setOnClickListener(this);
        masterdetail_teacherdetail_tv = (TextView) findViewById(R.id.masterdetail_teacherdetail_tv);
//        masterdetail_teacherdetail_tv.setOnClickListener(this);
        masterdetail_cancle = (ImageView) findViewById(R.id.masterdetail_cancle);
//        masterdetail_cancle.setOnClickListener(this);
        masterdetail_aty_share = (ImageView) findViewById(R.id.masterdetail_aty_share);
//        masterdetail_aty_share.setOnClickListener(this);
        adapter = new MasterDetailSortAdapter(this, R.layout.masterdetail_sortlist_item, recyclerList);
        liveAdapter = new MasterDetailLiveAdapter(this, liveCoursesBeen);
        courseAdapter = new MasterDetailCourseAdapter(this, coursesBeen);
        masterdetail_recyclerview.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

    }

    public void getData() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        int id = getIntent().getIntExtra("id", 0);
        Log.e("1234",id+"");
        String appToken = (String) SharedPreferencesUtils.getParam(this, "xyxy_apptoken", "String");
        Log.e("1234",appToken);
        FormBody userId = new FormBody.Builder().add("userId", id + "").build();
        Request build = new Request.Builder().url("https://www.univstar.com/v1/m/user/homepage").post(userId).addHeader("apptoken", appToken).build();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.e("1234",string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        MasterDetailBean detailBean = new Gson().fromJson(string, MasterDetailBean.class);
                        liveCoursesBeen.addAll(detailBean.getData().getLiveCourses());
                        coursesBeen.addAll(detailBean.getData().getCourses());

                        masterdetail_live_listview.setAdapter(liveAdapter);
                        masterdetail_courses_listview.setAdapter(courseAdapter);
                        liveAdapter.notifyDataSetChanged();
                        courseAdapter.notifyDataSetChanged();

                    }
                });
            }
        });
    }
}
