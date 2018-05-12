package com.jiyun.asmodeus.xyxy.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.disposables.CompositeDisposable;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userinfo_aty_title_cancle;
    private RoundedImageView userinfo_aty_userimg;
    private RelativeLayout userinfo_aty_userimg_group;
    private TextView userinfo_aty_changename_tv;
    private RelativeLayout userinfo_aty_changename;
    private TextView userinfo_aty_changensex_tv;
    private RelativeLayout userinfo_aty_changesex;
    private TextView userinfo_aty_changenaddress_tv;
    private RelativeLayout userinfo_aty_changcity;
    private TextView userinfo_aty_changenbirthday_tv;
    private RelativeLayout userinfo_aty_changenbirthday;
    private ImageView userinfo_aty_changeteacherimg;
    private TextView userinfo_aty_changetitle;
    private RelativeLayout userinfo_aty_changetitle_group;
    private TextView userinfo_detail_tv;
    private TextView userinfo_aty_changedetails;
    private RelativeLayout userinfo_aty_changedetails_group;
    private LinearLayout userinfo_aty_teacherstatus_group;
    //头像裁剪
    private static final int FOR_REQUEST_IMAGE = 1;
    private static final int FOR_IMAGE_CAT = 2;
    //姓名
    private static final int FOR_NAME = 3;
    //头衔
    private static final int FOR_TITLE = 4;
    //简介
    private static final int FOR_DETAIL = 5;
    //形象照
    private static final int FOR_DETAIL_IMG = 6;
    //城市
    private static final int FOR_CITY = 7;
    //选择类型
    private int selectType;

    private File image_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
    }

    private void initView() {
        userinfo_aty_title_cancle = (TextView) findViewById(R.id.userinfo_aty_title_cancle);
        userinfo_aty_userimg = (RoundedImageView) findViewById(R.id.userinfo_aty_userimg);
        userinfo_aty_userimg_group = (RelativeLayout) findViewById(R.id.userinfo_aty_userimg_group);
        userinfo_aty_changename_tv = (TextView) findViewById(R.id.userinfo_aty_changename_tv);
        userinfo_aty_changename = (RelativeLayout) findViewById(R.id.userinfo_aty_changename);
        userinfo_aty_changensex_tv = (TextView) findViewById(R.id.userinfo_aty_changensex_tv);
        userinfo_aty_changesex = (RelativeLayout) findViewById(R.id.userinfo_aty_changesex);
        userinfo_aty_changenaddress_tv = (TextView) findViewById(R.id.userinfo_aty_changenaddress_tv);
        userinfo_aty_changcity = (RelativeLayout) findViewById(R.id.userinfo_aty_changcity);
        userinfo_aty_changenbirthday_tv = (TextView) findViewById(R.id.userinfo_aty_changenbirthday_tv);
        userinfo_aty_changenbirthday = (RelativeLayout) findViewById(R.id.userinfo_aty_changenbirthday);
        userinfo_aty_changeteacherimg = (ImageView) findViewById(R.id.userinfo_aty_changeteacherimg);
        userinfo_aty_changetitle = (TextView) findViewById(R.id.userinfo_aty_changetitle);
        userinfo_aty_changetitle_group = (RelativeLayout) findViewById(R.id.userinfo_aty_changetitle_group);
        userinfo_detail_tv = (TextView) findViewById(R.id.userinfo_detail_tv);
        userinfo_aty_changedetails = (TextView) findViewById(R.id.userinfo_aty_changedetails);
        userinfo_aty_changedetails_group = (RelativeLayout) findViewById(R.id.userinfo_aty_changedetails_group);
        userinfo_aty_teacherstatus_group = (LinearLayout) findViewById(R.id.userinfo_aty_teacherstatus_group);
        userinfo_aty_title_cancle.setOnClickListener(this);
        userinfo_aty_userimg_group.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userinfo_aty_title_cancle:
                finish();
                break;
            case R.id.userinfo_aty_userimg_group:
                requestImage();
                selectType = FOR_REQUEST_IMAGE;
                break;

        }
    }

    private void requestImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 11);
    }


    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 160);
        intent.putExtra("outputY", 160);
        intent.putExtra("return-data", true);
        image_file = getPhotoFileName();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image_file));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, FOR_IMAGE_CAT);
    }

    //得到剪裁后图片的路径
    private File getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return new File(Environment.getExternalStorageDirectory() + File.separator + dateFormat.format(date) + ".jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                return;
            }

            Bundle bundle = data.getExtras();

            switch (requestCode) {

                case FOR_REQUEST_IMAGE:
                    startPhotoZoom(data.getData());
                    break;
                case FOR_IMAGE_CAT:

                    if (image_file == null) {
                        return;
                    }


                    Glide.with(getApplicationContext()).load(image_file.getPath()).into(userinfo_aty_userimg);


                    break;
            }
        }
    }
}
