package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.LiveDetailBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MasterLiveDetailActivity extends BaseActivity implements View.OnClickListener {
    private boolean isFavorite = false;
    private boolean isAttention = false;
    private int livestatus;

    private static final int YUYUE = 1;
    private static final int YIYUYUE = 2;
    private static final int YUYUE_MAX = 3;
    private static final int ZHIBO = 4;
    private static final int CHONGBO = 5;
    private CheckBox masterlivedetail_favorites;
    private LinearLayout masterlivedetail_favorites_group;
    private TextView masterlivedetail_subscribe_btn;
    private LinearLayout masterlivedetail_subscribe_group;
    private LinearLayout masterlivedetail_coachbtn;
    private ImageView masterlivedetail_img;
    private TextView masterlivedetail_livestatus_tv;
    private TextView masterlivedetail_type_tv;
    private TextView masterlivedetail_time_tv;
    private TextView masterlivedetail_major_tv;
    private TextView masterlivedetail_subscribe_tv;
    private TextView masterlivedetail_price_tv;
    private RoundedImageView masterlivedetail_teacherimg;
    private TextView masterlivedetail_name_tv;
    private ImageView masterlivedetail_usertype_tv;
    private TextView masterlivedetail_title_tv;
    private TextView masterlivedetail_attention_tv;
    private WebView masterlivedetail_aty_webview;
    private ImageView masterlivedetail_cancle;
    private ImageView masterlivedetail_aty_share;
    private FrameLayout masterlivedetail_frame;
    private RelativeLayout masterlivedetail_aty_group;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_master_live_detail;
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {
        masterlivedetail_favorites = (CheckBox) findViewById(R.id.masterlivedetail_favorites);
        masterlivedetail_favorites_group = (LinearLayout) findViewById(R.id.masterlivedetail_favorites_group);
        masterlivedetail_subscribe_btn = (TextView) findViewById(R.id.masterlivedetail_subscribe_btn);
        masterlivedetail_subscribe_group = (LinearLayout) findViewById(R.id.masterlivedetail_subscribe_group);
        masterlivedetail_coachbtn = (LinearLayout) findViewById(R.id.masterlivedetail_coachbtn);
        masterlivedetail_img = (ImageView) findViewById(R.id.masterlivedetail_img);
        masterlivedetail_livestatus_tv = (TextView) findViewById(R.id.masterlivedetail_livestatus_tv);
        masterlivedetail_type_tv = (TextView) findViewById(R.id.masterlivedetail_type_tv);
        masterlivedetail_time_tv = (TextView) findViewById(R.id.masterlivedetail_time_tv);
        masterlivedetail_major_tv = (TextView) findViewById(R.id.masterlivedetail_major_tv);
        masterlivedetail_subscribe_tv = (TextView) findViewById(R.id.masterlivedetail_subscribe_tv);
        masterlivedetail_price_tv = (TextView) findViewById(R.id.masterlivedetail_price_tv);
        masterlivedetail_teacherimg = (RoundedImageView) findViewById(R.id.masterlivedetail_teacherimg);
        masterlivedetail_name_tv = (TextView) findViewById(R.id.masterlivedetail_name_tv);
        masterlivedetail_usertype_tv = (ImageView) findViewById(R.id.masterlivedetail_usertype_tv);
        masterlivedetail_title_tv = (TextView) findViewById(R.id.masterlivedetail_title_tv);
        masterlivedetail_attention_tv = (TextView) findViewById(R.id.masterlivedetail_attention_tv);
        masterlivedetail_aty_webview = (WebView) findViewById(R.id.masterlivedetail_aty_webview);
        masterlivedetail_cancle = (ImageView) findViewById(R.id.masterlivedetail_cancle);
        masterlivedetail_aty_share = (ImageView) findViewById(R.id.masterlivedetail_aty_share);
        masterlivedetail_frame = (FrameLayout) findViewById(R.id.masterlivedetail_frame);
        masterlivedetail_aty_group = (RelativeLayout) findViewById(R.id.masterlivedetail_aty_group);
        masterlivedetail_cancle.setOnClickListener(this);
        //声明WebSettings子类
        WebSettings webSettings = masterlivedetail_aty_webview.getSettings();


        webSettings.setJavaScriptEnabled(true);

        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小


        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件


        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        int ids = getIntent().getIntExtra("ids", 0);
        masterlivedetail_aty_webview.loadUrl(String.format(Constant.Live_WebView_Url,ids));
        GetData(ids);


    }

    private void GetData(final int id) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        String appToken = (String) SharedPreferencesUtils.getParam(this, "xyxy_apptoken", "String");
        FormBody body = new FormBody.Builder().add("id", id + "").build();
        Request build = new Request.Builder().url("https://www.univstar.com/v1/m/liveCourse/detail").post(body).addHeader("apptoken", appToken).build();
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
                        LiveDetailBean liveDetailBean = new Gson().fromJson(string, LiveDetailBean.class);
                        LiveDetailBean.DataBean data = liveDetailBean.getData();
                        if (data == null) {

                            return;
                        }




                        Glide.with(MasterLiveDetailActivity.this).load(data.getCoverImg()).into(masterlivedetail_img);
                        Glide.with(MasterLiveDetailActivity.this).load(data.getPhoto()).into(masterlivedetail_teacherimg);
                        if (Constant.Live_status_yugao == data.getLiveStatus()) {
                            masterlivedetail_price_tv.setText(data.getPrice() + "");
                            masterlivedetail_livestatus_tv.setText(Constant.Live_status_yugao_str);
                            if (data.getIsSubscribe() == Constant.Subscribe_not) {
                                masterlivedetail_subscribe_btn.setText("预约");
                                masterlivedetail_subscribe_group.setEnabled(true);
                                livestatus = YUYUE;
                            } else if (data.getIsSubscribe() == Constant.Subscribe_yuyue) {
                                masterlivedetail_subscribe_btn.setText("已预约");
                                masterlivedetail_subscribe_group.setEnabled(false);
                                livestatus = YIYUYUE;
                            } else if (data.getIsSubscribe() != Constant.Subscribe_yuyue&&data.getSubscribe() == data.getSubscribeNum()) {
                                masterlivedetail_subscribe_group.setEnabled(false);
                                masterlivedetail_subscribe_btn.setText("预约已满");
                                livestatus = YUYUE_MAX;
                            }

                        } else if (Constant.Live_status_zhibo == data.getLiveStatus()) {
                            masterlivedetail_price_tv.setText(data.getPrice() + "");
                            masterlivedetail_livestatus_tv.setText(Constant.Live_status_zhibo_str);
                            masterlivedetail_subscribe_group.setEnabled(true);
                            masterlivedetail_subscribe_btn.setText("播放");
                            livestatus = ZHIBO;
                        } else if (Constant.Live_status_chongbo == data.getLiveStatus()) {
                            masterlivedetail_price_tv.setText(data.getReplay() + "");
                            masterlivedetail_subscribe_group.setEnabled(true);
                            masterlivedetail_subscribe_btn.setText("购买");
                            masterlivedetail_livestatus_tv.setText(Constant.Live_status_chongbo_str);
                            livestatus = CHONGBO;
                        }



                        masterlivedetail_type_tv.setText(String.format("%s课堂 :", data.getType()));

                        masterlivedetail_time_tv.setText(TimeShift.getTimeData(data.getStartDate()));



                        masterlivedetail_subscribe_tv.setText(String.format("%s / %s", data.getSubscribe() + "", data.getSubscribeNum() + ""));

                        masterlivedetail_name_tv.setText(data.getRealname());

                        masterlivedetail_title_tv.setText(data.getIntro());
                        SplitStringColorUtils.setImgLevel(masterlivedetail_usertype_tv, data.getUserType());

                        if (data.getAttention() == Constant.Attention) {
                            masterlivedetail_attention_tv.setText("关注");
                            masterlivedetail_attention_tv.setActivated(true);
                            isAttention = false;
                        } else if (data.getAttention() == Constant.Attention_yiguanzhu) {
                            masterlivedetail_attention_tv.setText("已关注");
                            masterlivedetail_attention_tv.setActivated(false);
                            isAttention = true;
                        } else if (data.getAttention() == Constant.Attention_xianghu) {
                            masterlivedetail_attention_tv.setText("相互关注");
                            masterlivedetail_attention_tv.setActivated(false);
                            isAttention = true;
                        }


                        if (data.getFavorite() == Constant.NOTFAVORITE) {
                            masterlivedetail_favorites.setChecked(false);
                            isFavorite = false;
                        } else {
                            masterlivedetail_favorites.setChecked(true);
                            isFavorite = true;
                        }

                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.masterlivedetail_cancle:
                finish();
                break;
        }
    }
}
