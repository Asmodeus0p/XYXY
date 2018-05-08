package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.makeramen.roundedimageview.RoundedImageView;

public class MasterLiveDetailActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_live_detail);
        initView();
    }

    private void initView() {
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






    }

}
