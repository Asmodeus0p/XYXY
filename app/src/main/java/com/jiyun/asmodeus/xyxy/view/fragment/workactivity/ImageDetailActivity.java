package com.jiyun.asmodeus.xyxy.view.fragment.workactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.AtyAnim;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.Serializable;

import butterknife.OnClick;

public class ImageDetailActivity extends BaselivstActivity {

    @ViewInject(R.id.image_detail_activity_img)
    private PhotoView imageView;

    @ViewInject(R.id.image_detail_activity_progreebar)
    private ProgressBar image_detail_activity_progreebar;


    public static void start(Activity activity, String path) {
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(Constant.User_icon, path);
        activity.startActivity(intent);
    }


    public static void start(Activity activity, String path,int type) {
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(Constant.User_icon, path);
        intent.putExtra(Constant.Sort_Type, type);
        activity.startActivity(intent);
    }

    private Context context;

    private String path="";

    private boolean isMax = false;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ViewUtils.inject(this);
        setTitleTheme(this,true);

        init();

        initView();
    }

    private void init (){

        context = this;

        path = getIntent().getStringExtra(Constant.User_icon);

        type = getIntent().getIntExtra(Constant.Sort_Type,0);
    }

    private void initView(){

        Glide.with(context)
                .load(type==0?path:new File(path))
                .into(imageView);
    }

//    @OnClick({R.id.image_detail_activity_cancle,R.id.image_detail_activity_group,
//            R.id.image_detail_activity_img,R.id.image_detail_activity_group})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.image_detail_activity_cancle:
            case R.id.image_detail_activity_group:
            case R.id.image_detail_activity_img:
                this.finish();
                AtyAnim.centOut((Activity) context);
                break;
            default:
                break;
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        AtyAnim.centOut((Activity) context);
    }

}
