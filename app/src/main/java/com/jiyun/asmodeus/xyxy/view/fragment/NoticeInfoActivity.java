package com.jiyun.asmodeus.xyxy.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.FavoriteContract;
import com.jiyun.asmodeus.xyxy.contract.NoticeInfoContract;
import com.jiyun.asmodeus.xyxy.model.entity.FavoriteBean;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeInfoBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.presenter.FavoritePresenterImp;
import com.jiyun.asmodeus.xyxy.presenter.NoticeInfoImp;
import com.umeng.commonsdk.debug.UMDebugLog;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

public class NoticeInfoActivity extends AppCompatActivity implements View.OnClickListener, FavoriteContract.NoticeInfoFavoriteView, NoticeInfoContract.NoticaInfoView {

    private CheckBox noticelivedetail_favorite_ck;
    private LinearLayout noticelivedetail_favorite_group;
    private RelativeLayout noticelivedetail_phone_group;
    private TextView noticelivedetail_aty_reservation_tv;
    private LinearLayout noticelivedetail_aty_reservation;
    private LinearLayout noticelivedetail_coachbtn;
    private ImageView noticelivedetail_img;
    private TextView noticelivedetail_subscribe_tv;
    private TextView noticelivedetail_time_tv;
    private TextView noticelivedetail_major_tv;
    private TextView noticelivedetail_address_tv;
    private TextView noticelivedetail_price_tv;
    private WebView noticelivedetail_webview;
    private ImageView noticelivedetail_cancle;
    private ImageView noticelivedetail_aty_share;
    private FrameLayout noticelivedetail_frame;
    private NoticeInfoImp noticeInfoImp;
    private int id;
    private NoticeInfoBean.DataBean data;
    private String mobile;
    private boolean isFavorite = false;
    private FavoritePresenterImp presenterImp;
    private UMShareListener shareListener;
    private String url;
    private int id1;
    private String coverImg;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_info);
        initView();


    }

    private void loadDatas(NoticeInfoBean.DataBean data) {


        //声明WebSettings子类
        WebSettings webSettings = noticelivedetail_webview.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        noticelivedetail_webview.setWebChromeClient(new MyWebChromeClient());

        noticelivedetail_webview.loadUrl(String.format(Constant.Notice_WebView_Url, id));

        Glide.with(getApplicationContext())
                .load(data.getCoverImg())
                .into(noticelivedetail_img);

        noticelivedetail_subscribe_tv.setText(String.format("%s / %s", data.getReservationNum(), data.getSubscribeNum()));

        if (data.getIsReservation() == Constant.NOTFAVORITE) {
            //未预约
            if (data.getReservationNum() == data.getSubscribeNum()) {

                noticelivedetail_aty_reservation.setEnabled(false);
                noticelivedetail_aty_reservation_tv.setText("预约已满");
            } else {

                noticelivedetail_aty_reservation.setEnabled(true);
                noticelivedetail_aty_reservation_tv.setText("预约");
            }

        } else {
            noticelivedetail_aty_reservation.setEnabled(false);
            noticelivedetail_aty_reservation_tv.setText("已预约");
        }

        noticelivedetail_time_tv.setText(TimeShift.getTimeData(data.getStartDate()));

        noticelivedetail_address_tv.setText(data.getAddress());

        mobile = data.getMobile();

        if (!TextUtils.isEmpty(data.getMajorIds())) {

            noticelivedetail_major_tv.setVisibility(View.VISIBLE);

            // SplitStringColorUtils.addForeColorSpan(getApplicationContext(),data.getMajorIds().split(","),noticelivedetail_major_tv);
        } else {
            noticelivedetail_major_tv.setVisibility(View.GONE);
        }

//        noticelivedetail_content_tv.setText(dataBean.getTitle());

        noticelivedetail_price_tv.setText(data.getPrice() + "");

        if (data.getFavorite() == Constant.NOTFAVORITE) {
            isFavorite = false;
            noticelivedetail_favorite_ck.setChecked(false);
        } else {
            isFavorite = true;
            noticelivedetail_favorite_ck.setChecked(true);
        }

    }


    private void initView() {
        url="http://share.univstar.com/view/course.html?courseid="+id1;
        noticelivedetail_favorite_ck = (CheckBox) findViewById(R.id.noticelivedetail_favorite_ck);
        noticelivedetail_favorite_group = (LinearLayout) findViewById(R.id.noticelivedetail_favorite_group);
        noticelivedetail_phone_group = (RelativeLayout) findViewById(R.id.noticelivedetail_phone_group);
        noticelivedetail_aty_reservation_tv = (TextView) findViewById(R.id.noticelivedetail_aty_reservation_tv);
        noticelivedetail_aty_reservation = (LinearLayout) findViewById(R.id.noticelivedetail_aty_reservation);
        noticelivedetail_coachbtn = (LinearLayout) findViewById(R.id.noticelivedetail_coachbtn);
        noticelivedetail_img = (ImageView) findViewById(R.id.noticelivedetail_img);
        noticelivedetail_subscribe_tv = (TextView) findViewById(R.id.noticelivedetail_subscribe_tv);
        noticelivedetail_time_tv = (TextView) findViewById(R.id.noticelivedetail_time_tv);
        noticelivedetail_major_tv = (TextView) findViewById(R.id.noticelivedetail_major_tv);
        noticelivedetail_address_tv = (TextView) findViewById(R.id.noticelivedetail_address_tv);
        noticelivedetail_price_tv = (TextView) findViewById(R.id.noticelivedetail_price_tv);
        noticelivedetail_webview = (WebView) findViewById(R.id.noticelivedetail_webview);
        noticelivedetail_cancle = (ImageView) findViewById(R.id.noticelivedetail_cancle);
        noticelivedetail_aty_share = (ImageView) findViewById(R.id.noticelivedetail_aty_share);
        noticelivedetail_frame = (FrameLayout) findViewById(R.id.noticelivedetail_frame);
        noticelivedetail_cancle.setOnClickListener(this);
        noticelivedetail_phone_group.setOnClickListener(this);
        noticelivedetail_aty_reservation.setOnClickListener(this);
        noticelivedetail_favorite_ck.setOnClickListener(this);
        noticelivedetail_aty_share.setOnClickListener(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        noticeInfoImp = new NoticeInfoImp(this);
        noticeInfoImp.laodNoticeDatas(id);
        presenterImp=new FavoritePresenterImp(this);


    }

    public void shareQQ(){
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
        shareListener = new UMShareListener() {
            /**
             * @descrption 分享开始的回调
             * @param platform 平台类型
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            /**
             * @descrption 分享成功的回调
             * @param platform 平台类型
             */
            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(NoticeInfoActivity.this,"成功了",Toast.LENGTH_LONG).show();
            }

            /**
             * @descrption 分享失败的回调
             * @param platform 平台类型
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(NoticeInfoActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
            }

            /**
             * @descrption 分享取消的回调
             * @param platform 平台类型
             */
            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(NoticeInfoActivity.this,"取消了",Toast.LENGTH_LONG).show();

            }
        };
        UMWeb umWeb1 = new UMWeb(url);
        UMImage umImage1 = new UMImage(NoticeInfoActivity.this, coverImg);
        umWeb1.setTitle(title);
        umWeb1.setThumb(umImage1);  //缩略图
        umWeb1.setDescription("星语心愿");//描述
        new ShareAction(NoticeInfoActivity.this).withText("你好啊")
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .withMedia(umWeb1)
                .setCallback(shareListener).open();



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.noticelivedetail_cancle:
                finish();
                break;
            case R.id.noticelivedetail_phone_group:
                call(mobile);
                break;
            case R.id.noticelivedetail_favorite_ck:
               if (!SharedPreferencesUtils.isLogin(getApplicationContext())){
                   startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                   return;
               }
               if (isFavorite){
                    //发送取消收藏的网络请求
                   int userId = SharedPreferencesUtils.getUserId(getApplicationContext());
                   presenterImp.sendcancelMessgae(Constant.TIYANKE, id, userId);
                   noticelivedetail_favorite_ck.setChecked(false);
                   isFavorite=false;

               }else {
                   //发送收藏的网络请求
                   int userId = SharedPreferencesUtils.getUserId(getApplicationContext());
                   presenterImp.sendMessgae(Constant.TIYANKE, id, userId);
                   noticelivedetail_favorite_ck.setChecked(true);
                   isFavorite=true;
               }
                break;
            case R.id.noticelivedetail_aty_share:
                    shareQQ();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private class MyWebChromeClient extends WebChromeClient {
        private View mCustomView;
        private CustomViewCallback mCustomViewCallback;

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            noticelivedetail_frame.setVisibility(View.VISIBLE);
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mCustomView = view;
            noticelivedetail_frame.addView(mCustomView);
            mCustomViewCallback = callback;
            noticelivedetail_webview.setVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        @Override
        public void onHideCustomView() {
            noticelivedetail_frame.setVisibility(View.GONE);
            noticelivedetail_webview.setVisibility(View.VISIBLE);
            if (mCustomView == null) {
                return;
            }
            mCustomView.setVisibility(View.GONE);
            noticelivedetail_frame.removeView(mCustomView);
            mCustomViewCallback.onCustomViewHidden();
            mCustomView = null;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            super.onHideCustomView();
        }
    }


    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        switch (config.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if (noticelivedetail_webview.canGoBack()) {
            noticelivedetail_webview.goBack();
            return;
        }
        super.onBackPressed();
    }


    @Override
    public void laodNoticeDatas(NoticeInfoBean noticeInfoBean) {

        data = noticeInfoBean.getData();
        Log.e("==", data.getCoverImg());
        id1 = data.getId();
        coverImg = data.getCoverImg();
        title = data.getTitle();
        loadDatas(data);

    }

    @Override
    public void backMessage(FavoriteBean favoriteBean) {
        if (favoriteBean.getCode() == Constant.SuccessCode) {

            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void cancalBackMessage(FavoriteBean favoriteBean) {
        if (favoriteBean.getCode() == Constant.SuccessCode) {
            Log.e("==", "favoriteBean.getCode():" + favoriteBean.getCode());
            Toast.makeText(this, "取消收藏成功", Toast.LENGTH_SHORT).show();
        }
    }
}
