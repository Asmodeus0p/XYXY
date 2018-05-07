package com.jiyun.asmodeus.xyxy.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.view.MainActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    // 拍照回传码
    public final static int CAMERA_REQUEST_CODE = 0;
    // 相册选择回传吗
    public final static int GALLERY_REQUEST_CODE = 1;
    // 拍照的照片的存储位置
    private String mTempPhotoPath;
    // 照片所在的Uri地址
    private Uri imageUri;
    private TextView complete_userinfo_aty_cancle;
    private TextView complete_userinfo_aty_jump;
    private RoundedImageView complete_userinfo_aty_imgbtn;
    private ImageView complete_userinfo_aty_selectimgbtn;
    private EditText complete_userinfo_aty_name_et;
    private ImageView complete_userinfo_aty_name_et_clear;
    private CheckBox complete_userinfo_aty_cbman;
    private CheckBox complete_userinfo_aty_cbwoman;
    private EditText complete_userinfo_aty_pass_et;
    private ImageView complete_userinfo_aty_pass_et_clear;
    private Button complete_userinfo_aty_surebtn;
    private LinearLayout complete_userinfo_aty_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
    }

    private void initView() {

        complete_userinfo_aty_cancle = (TextView) findViewById(R.id.complete_userinfo_aty_cancle);
        complete_userinfo_aty_jump = (TextView) findViewById(R.id.complete_userinfo_aty_jump);
        complete_userinfo_aty_imgbtn = (RoundedImageView) findViewById(R.id.complete_userinfo_aty_imgbtn);
        complete_userinfo_aty_selectimgbtn = (ImageView) findViewById(R.id.complete_userinfo_aty_selectimgbtn);
        complete_userinfo_aty_name_et = (EditText) findViewById(R.id.complete_userinfo_aty_name_et);
        complete_userinfo_aty_name_et_clear = (ImageView) findViewById(R.id.complete_userinfo_aty_name_et_clear);
        complete_userinfo_aty_cbman = (CheckBox) findViewById(R.id.complete_userinfo_aty_cbman);
        complete_userinfo_aty_cbwoman = (CheckBox) findViewById(R.id.complete_userinfo_aty_cbwoman);
        complete_userinfo_aty_pass_et = (EditText) findViewById(R.id.complete_userinfo_aty_pass_et);
        complete_userinfo_aty_pass_et_clear = (ImageView) findViewById(R.id.complete_userinfo_aty_pass_et_clear);
        complete_userinfo_aty_surebtn = (Button) findViewById(R.id.complete_userinfo_aty_surebtn);
        complete_userinfo_aty_layout = (LinearLayout) findViewById(R.id.complete_userinfo_aty_layout);

        complete_userinfo_aty_surebtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.complete_userinfo_aty_surebtn:

                break;
            case R.id.complete_userinfo_aty_selectimgbtn:
                if (ContextCompat.checkSelfPermission(UserActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {   //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在onRequestPermissionsResult中使用到
                    ActivityCompat.requestPermissions(UserActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            UserActivity.CAMERA_REQUEST_CODE);

                }else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    choosePhoto();
                }


                break;
        }
    }
    //当拍摄照片完成时会回调到onActivityResult 在这里处理照片的裁剪
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == MainActivity.RESULT_OK) {
            switch (requestCode) {
                case UserActivity.CAMERA_REQUEST_CODE: {
                    // 获得图片
                    try {
                        //该uri就是照片文件夹对应的uri
                        Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        // 给相应的ImageView设置图片 未裁剪
                        complete_userinfo_aty_imgbtn.setImageBitmap(bit);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case UserActivity.GALLERY_REQUEST_CODE: {
                    // 获取图片
                    try {
                        //该uri是上一个Activity返回的
                        imageUri = data.getData();
                        if(imageUri!=null) {
                            Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                            Log.i("bit", String.valueOf(bit));
                            complete_userinfo_aty_imgbtn.setImageBitmap(bit);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void choosePhoto(){
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
        startActivityForResult(intentToPickPic, UserActivity.GALLERY_REQUEST_CODE);
    }
}
