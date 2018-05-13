package com.jiyun.asmodeus.xyxy.view.fragment;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IPostUserInfoService;
import com.jiyun.asmodeus.xyxy.model.biz.IUpLoadImg;
import com.jiyun.asmodeus.xyxy.model.biz.UserInfoService;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.UpLoadImgBean;
import com.jiyun.asmodeus.xyxy.model.entity.UserInfoBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.MultiLineAty;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.SingleLineAty;
import com.makeramen.roundedimageview.RoundedImageView;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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


    private CompositeDisposable compositeDisposable;

    private String pushtoken;


    private String name;

    private String relaName;

    private String title;

    private String detail;

    private String iconurl;

    private String userImage;

    private String city;

    private String birthday;

    private boolean isStudent = true;

    private int sex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        loadContent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }


    private void initView() {
        compositeDisposable = new CompositeDisposable();
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

        userinfo_aty_changesex.setOnClickListener(this);

        userinfo_aty_changename.setOnClickListener(this);

        userinfo_aty_changesex.setOnClickListener(this);

        userinfo_aty_changcity.setOnClickListener(this);

        userinfo_aty_changenbirthday.setOnClickListener(this);

        userinfo_aty_changetitle_group.setOnClickListener(this);

        userinfo_aty_changedetails_group.setOnClickListener(this);

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
            case R.id.userinfo_aty_changename:

                Intent intentName = new Intent(getApplicationContext(), SingleLineAty.class);

                intentName.putExtra(Constant.INPUT_TYPE, SingleLineAty.NAME);

                intentName.putExtra(Constant.INPUT_MAXL, 15);

                intentName.putExtra(Constant.INPUT_TEXT, userinfo_aty_changename_tv.getText().toString());

                startActivityForResult(intentName, FOR_NAME);
                break;
            case R.id.userinfo_aty_changesex:

                final String[] items = new String[]{"男", "女"};

                AlertDialog dialog = new AlertDialog.Builder(this).setTitle("请选择")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sex = which;
                                userinfo_aty_changensex_tv.setText(items[which]);
                                postUserInfo();
                            }
                        }).create();
                dialog.show();
                break;
            case R.id.userinfo_aty_changcity:

                Intent intentCity = new Intent(getApplicationContext(), SingleLineAty.class);

                intentCity.putExtra(Constant.INPUT_TYPE, SingleLineAty.CITY);

                intentCity.putExtra(Constant.INPUT_MAXL, 15);

                intentCity.putExtra(Constant.INPUT_TEXT, userinfo_aty_changenaddress_tv.getText().toString());

                startActivityForResult(intentCity, FOR_CITY);
                break;
            case R.id.userinfo_aty_changenbirthday:
                showStartData();
                break;

            case R.id.userinfo_aty_changetitle_group:

                Intent intentTitle = new Intent(getApplicationContext(), SingleLineAty.class);

                intentTitle.putExtra(Constant.INPUT_TYPE, SingleLineAty.TITLE);

                intentTitle.putExtra(Constant.INPUT_MAXL, 50);

                intentTitle.putExtra(Constant.INPUT_TEXT, userinfo_aty_changetitle.getText().toString());

                startActivityForResult(intentTitle, FOR_TITLE);
                break;
            case R.id.userinfo_aty_changedetails_group:

                Intent intentDetail = new Intent(getApplicationContext(), MultiLineAty.class);

                intentDetail.putExtra(Constant.INPUT_MAXL, 400);

                intentDetail.putExtra(Constant.INPUT_TEXT, userinfo_aty_changedetails.getText().toString());

                startActivityForResult(intentDetail, FOR_DETAIL);
                break;
            case R.id.userinfo_aty_changeteacherimg:

                requestUserImage();
                selectType = FOR_DETAIL_IMG;

                break;
            default:
                break;
        }
    }

    //    private boolean isPermissionOK() {
//        PermissionChecker checker = new PermissionChecker(getApplicationContext());
//        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
//        if (!isPermissionOK) {
//        }
//        return isPermissionOK;
//    }
    private void requestUserImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, FOR_DETAIL_IMG);
    }


    private void showStartData() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog pickDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int iyear, int monthOfYear, int dayOfMonth) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String time = iyear + "-" + ((monthOfYear + 1)) + "-" + dayOfMonth;
                Date date = null;
                try {
                    date = format.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                birthday = TimeShift.getBirthdatyData(date.getTime());

                userinfo_aty_changenbirthday_tv.setText(birthday);

                postUserInfo();

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pickDialog.show();
    }

    private void postUserInfo() {

        HttpHelp.baseHttpRequest(getApplicationContext(), Constant.Root_url)
                .create(IPostUserInfoService.class)
                .postUserInfo(HttpHelp.getUserId(getApplicationContext()), name, relaName, iconurl, userImage, title, detail, sex, birthday, city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {
                        Log.d("TAG", "onNext: " + value.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void loadContent() {

        HttpHelp.baseHttpRequest(getApplicationContext(), Constant.Root_url)
                .create(UserInfoService.class)
                .getUserInfo(HttpHelp.getUserId(getApplicationContext()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfoBean value) {
                        if (value == null || value.getData() == null) {

                            return;
                        }

                        fillData(value.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void requestImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 11);
    }

    private void fillData(UserInfoBean.DataBean dataBean) {

        // HttpHelp.glideLoadC(context,userinfo_aty_userimg,dataBean.getPhoto(),R.mipmap.user_head_portrait);
        Glide.with(getApplicationContext()).load(dataBean.getPhoto()).into(userinfo_aty_userimg);

        if (dataBean.getSex() == 0) {
            userinfo_aty_changensex_tv.setText("男");
            sex = 0;
        } else if (dataBean.getSex() == 1) {
            userinfo_aty_changensex_tv.setText("女");
            sex = 1;
        } else {
            userinfo_aty_changensex_tv.setText("");
        }

        if (TextUtils.equals("null", dataBean.getAddress())) {
            dataBean.setAddress("");
        }

        userinfo_aty_changenaddress_tv.setText(dataBean.getAddress());

        if (dataBean.getBirthday() != 0) {
            userinfo_aty_changenbirthday_tv.setText(TimeShift.getBirthdatyData(dataBean.getBirthday()));
            birthday = TimeShift.getBirthdatyData(dataBean.getBirthday());
        }

        if (dataBean.getUserType() >= 2) {
            isStudent = false;
            userinfo_aty_teacherstatus_group.setVisibility(View.VISIBLE);

            //HttpHelp.glideLoad(context,userinfo_aty_changeteacherimg,dataBean.getImages(),R.mipmap.home_master_viewpagr_normal);

            Glide.with(getApplicationContext()).load(dataBean.getImages()).into(userinfo_aty_changeteacherimg);
            userinfo_aty_changetitle.setText(dataBean.getIntro());
            userinfo_aty_changedetails.setText(dataBean.getDetails());
            userinfo_aty_changename_tv.setText(dataBean.getRealname());
        } else {
            isStudent = true;
            userinfo_aty_teacherstatus_group.setVisibility(View.GONE);
            userinfo_aty_changename_tv.setText(dataBean.getNickname());
        }


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
                    uploadImg();
                    break;
                case FOR_NAME:

                    if (isStudent) {
                        name = bundle.getString(Constant.INPUT_TEXT);
                        userinfo_aty_changename_tv.setText(name);
                    } else {
                        relaName = bundle.getString(Constant.INPUT_TEXT);
                        userinfo_aty_changename_tv.setText(relaName);
                    }
                    postUserInfo();
                    break;
                case FOR_CITY:
                    city = bundle.getString(Constant.INPUT_TEXT);
                    userinfo_aty_changenaddress_tv.setText(city);

                    postUserInfo();
                    break;
                case FOR_TITLE:

                    title = bundle.getString(Constant.INPUT_TEXT);

                    userinfo_aty_changetitle.setText(title);

                    postUserInfo();
                    break;
                case FOR_DETAIL:

                    detail = bundle.getString(Constant.INPUT_TEXT);

                    userinfo_aty_changedetails.setText(detail);

                    postUserInfo();

                    break;

                case FOR_DETAIL_IMG:

                    if (data.getData() == null) {

                        return;
                    }

                    // HttpHelp.glideLoad(context,userinfo_aty_changeteacherimg,getFilePathFromContentUri(data.getData(), context.getContentResolver()),R.mipmap.home_master_viewpagr_normal);
                    Glide.with(getApplicationContext()).load(getFilePathFromContentUri(data.getData(), getApplicationContext().getContentResolver())).into(userinfo_aty_changeteacherimg);
                    userinfo_aty_changeteacherimg.setVisibility(View.VISIBLE);

                    uploadImg();

                    break;
                default:
                    break;
            }
        }
    }

    public String getFilePathFromContentUri(Uri selectedVideoUri,
                                            ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    private void uploadImg() {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), image_file);
        builder.addFormDataPart("file", image_file.getName(), imageBody);
        List<MultipartBody.Part> parts = builder.build().parts();
        HttpHelp.baseHttpRequest(getApplicationContext(), Constant.Root_url).create(IUpLoadImg.class)
                .uploadImg(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpLoadImgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpLoadImgBean value) {
                        if (selectType == FOR_REQUEST_IMAGE) {

                            iconurl = value.getData().getFileName();
                        } else {
                            userImage = value.getData().getFileName();
                        }

                        postUserInfo();
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
