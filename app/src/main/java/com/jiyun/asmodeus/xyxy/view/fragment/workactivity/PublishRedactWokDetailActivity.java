package com.jiyun.asmodeus.xyxy.view.fragment.workactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IDefaulPrice;
import com.jiyun.asmodeus.xyxy.model.biz.IGetUpLoadToken;
import com.jiyun.asmodeus.xyxy.model.biz.IPublishRedactWokDetail;
import com.jiyun.asmodeus.xyxy.model.entity.DefaultPriceBean;
import com.jiyun.asmodeus.xyxy.model.entity.GetUpLoadModel;
import com.jiyun.asmodeus.xyxy.model.entity.PreferenceListBean;
import com.jiyun.asmodeus.xyxy.model.entity.PublicWorkBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.CommonPopupWindow;
import com.jiyun.asmodeus.xyxy.model.utils.CommonUtil;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.PermissionChecker;
import com.jiyun.asmodeus.xyxy.model.utils.RecordPlayer;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.view.adapter.PublishRedactWokDetailTypeAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.homeactivity.VideoDetailActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.homeactivity.WorkDataActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qiniu.pili.droid.shortvideo.PLShortVideoUploader;
import com.qiniu.pili.droid.shortvideo.PLUploadProgressListener;
import com.qiniu.pili.droid.shortvideo.PLUploadResultListener;
import com.qiniu.pili.droid.shortvideo.PLUploadSetting;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.jiyun.asmodeus.xyxy.model.utils.Constant.PUSH_VIDEO;

public class PublishRedactWokDetailActivity extends BaselivstActivity implements PLUploadProgressListener, PLUploadResultListener {

    public static final String PayBroadCast_Key = "com.xyxy.artlive_android.publish.PublishRedactWokDetail";

    public static final int SISHUZUOYE = 4;

    public static final int ZHIDINGQIUJIAO = 3;

    public static final int QIUJIAOZUOYE = 2;

    public static final int FABUZUOYE = 1;

    private static final int FOR_REQUEST_IMAGE=1;

    private static final int FOR_REQUEST_CAMERA = 2;

    private static final int FOR_REQUEST_VIDO = 3;

    private static final int FOR_REQUEST_AUDIO = 4;

    private static final int FOR_SELECT_TEACHER = 5;

    @ViewInject(R.id.publishredactwok_detail_group)
    private RelativeLayout publishredactwok_detail_group;

    @ViewInject(R.id.publishredactwok_detail_add_group)
    private RelativeLayout publishredactwok_detail_add_group;

    @ViewInject(R.id.publishredactwok_detail_add_btn)
    private LinearLayout publishredactwok_detail_add_btn;

    @ViewInject(R.id.publishredactwok_detail_genghuan_group)
    private LinearLayout publishredactwok_detail_genghuan_group;

    @ViewInject(R.id.publishredact_progressBar)
    private ProgressBar publishredact_progressBar;

    @ViewInject(R.id.publishredactwok_detail_img)
    private ImageView publishredactwok_detail_img;

    @ViewInject(R.id.publishredactwok_detail_genghuan_btn)
    private LinearLayout publishredactwok_detail_genghuan_btn;

    @ViewInject(R.id.publishredacetwok_detail_select_teacher)
    private RelativeLayout publishredacetwok_detail_select_teacher;

    @ViewInject(R.id.publishredactwok_detail_recyclerview)
    private RecyclerView publishredactwok_detail_recyclerview;

    @ViewInject(R.id.publishredactwok_detail_input)
    private EditText publishredactwok_detail_input;

    @ViewInject(R.id.publishredactwok_detail_select_tv)
    private TextView publishredactwok_detail_select_tv;

    @ViewInject(R.id.publishredactwok_detail_teahcername_tv)
    private TextView publishredactwok_detail_teahcername_tv;

    @ViewInject(R.id.publishredactwok_detail_teahcerhint_tv)
    private TextView publishredactwok_detail_teahcerhint_tv;

    @ViewInject(R.id.publishredactwok_detail_price)
    private TextView publishredactwok_detail_price;

    @ViewInject(R.id.publishredactwok_detail_play_music_group)
    private LinearLayout publishredactwok_detail_play_music_group;

    @ViewInject(R.id.publishredactwok_detail_play_music)
    private ImageView publishredactwok_detail_play_music;

    @ViewInject(R.id.publishredactwok_detail_play_music_time)
    private TextView publishredactwok_detail_play_music_time;

    @ViewInject(R.id.publishredactwok_detail_play_video_group)
    private LinearLayout publishredactwok_detail_play_video_group;

    @ViewInject(R.id.publishredactwok_detail_play_video_time)
    private TextView publishredactwok_detail_play_video_time;

    @ViewInject(R.id.publishredactwok_detail_coachbtn)
    private TextView publishredactwok_detail_coachbtn;

    @ViewInject(R.id.publishredactwok_detail_pushload)
    private RelativeLayout publishredactwok_detail_pushload;

    public static void start(Activity activity, int teacherid,int woktype) {
        Intent intent = new Intent(activity, PublishRedactWokDetailActivity.class);
        intent.putExtra(Constant.Teacher_id, teacherid);
        intent.putExtra(Constant.Wok_type, woktype);
        activity.startActivity(intent);
    }

    public static void start(Activity activity, int teacherid,String name,double price,String type,int woktype) {
        Intent intent = new Intent(activity, PublishRedactWokDetailActivity.class);
        intent.putExtra(Constant.Teacher_id, teacherid);
        intent.putExtra(Constant.Wok_type, woktype);
        intent.putExtra(Constant.Teacher_price, price);
        intent.putExtra(Constant.Sort_Type, type);
        intent.putExtra(Constant.User_name, name);
        activity.startActivity(intent);
    }

    private Context context;

    private App artliveApp;

    private CompositeDisposable compositeDisposable;

    private CommonPopupWindow popupWindow;

    private PLShortVideoUploader mVideoUploadManager;

    private List<PreferenceListBean.DataBean.MajorsBean> typelist;

    private List<String> selectLists;

    private PublishRedactWokDetailTypeAdapter adapter;

    private File fileUri;

    private Uri imageUri;

    private int uptype;

    private String pushtoken;

    private RecordPlayer player;

    private Integer teacherId;
    private String mediaPath;

    private String showMediaPath;

    private String showMediaTime;

    private int pushtype=0;

    private String mediaType = "";

    private String content = "";

    private double price;

    private String workSource;

    private double normal_price;

    private int woktype;

    private String teacherName;

    private boolean isPay = false;

    private PaySucessRecceiver paySucessRecceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_redact_wok_detail);
        ViewUtils.inject(this);
        setTitleTheme(this,true);

        init();
        loadContent();
        initView();
        if(woktype==QIUJIAOZUOYE){
            loadDefaultPrice();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(player!=null){
            player.stopPalyer();
            player.cancelPalyer();
        }


        publishredactwok_detail_img.setEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(player!=null){
            player.stopPalyer();
            player.cancelPalyer();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        //拍摄之后的回调
        if(intent==null){

            return;
        }

        showMediaTime = TimeShift.getAudioTime(intent.getLongExtra(Constant.Media_time, 0));
        showMediaPath = intent.getStringExtra(Constant.Vido_path);

        Glide.with(context).load(intent.getStringExtra(Constant.Vido_path)).into(publishredactwok_detail_img);
        publishredactwok_detail_img.setVisibility(View.VISIBLE);
        mVideoUploadManager.startUpload(intent.getStringExtra(Constant.Vido_path), pushtoken);
        publishredactwok_detail_img.setEnabled(false);
        mediaType = PUSH_VIDEO;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        unregisterReceiver(paySucessRecceiver);
    }

    private void init(){

        context=this;



        if(getIntent()!=null){

            woktype = getIntent().getIntExtra(Constant.Wok_type,0);
            if(woktype==QIUJIAOZUOYE){
                workSource = Constant.QIUJIOAZUOPIN;
            }else if(woktype==FABUZUOYE){
                String type = getIntent().getStringExtra(Constant.Sort_Type);
                teacherId = getIntent().getIntExtra(Constant.Teacher_id,0);
                price = getIntent().getDoubleExtra(Constant.Teacher_price, 0);
                workSource = String.format("%s作业", type);
                teacherName = getIntent().getStringExtra(Constant.User_name);
            }else if(woktype==ZHIDINGQIUJIAO){
                workSource = Constant.QIUJIOAZUOPIN;
                teacherId = getIntent().getIntExtra(Constant.Teacher_id,0);
                price = getIntent().getDoubleExtra(Constant.Teacher_price, 0);
                teacherName = getIntent().getStringExtra(Constant.User_name);
            }else if(woktype==SISHUZUOYE){
                String type = getIntent().getStringExtra(Constant.Sort_Type);
                teacherId = getIntent().getIntExtra(Constant.Teacher_id,0);
                price = getIntent().getDoubleExtra(Constant.Teacher_price, 0);
                workSource = String.format("%s作业", type);
                teacherName = getIntent().getStringExtra(Constant.User_name);
            }
        }

        teacherId = getIntent().getIntExtra(Constant.Teacher_id, 0);
        compositeDisposable = new CompositeDisposable();

        typelist = new ArrayList<>();

        selectLists = new ArrayList<>();

        PLUploadSetting uploadSetting = new PLUploadSetting();

        mVideoUploadManager = new PLShortVideoUploader(getApplicationContext(), uploadSetting);
        mVideoUploadManager.setUploadProgressListener(this);
        mVideoUploadManager.setUploadResultListener(this);

        IntentFilter filter=new IntentFilter(PayBroadCast_Key);
        paySucessRecceiver = new PaySucessRecceiver();
        registerReceiver(paySucessRecceiver,filter);

    }

    private void initView(){

//        List<PreferenceListBean.DataBean.MajorsBean> list=artliveApp.getMajorsBeen();
//
//        if(list!=null){
//            typelist.addAll(list);
//        }

        adapter = new PublishRedactWokDetailTypeAdapter(context, typelist);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        publishredactwok_detail_recyclerview.setLayoutManager(linearLayoutManager);
        publishredactwok_detail_recyclerview.setAdapter(adapter);
        adapter.setSelectCallBack(new PublishRedactWokDetailTypeAdapter.ItemSelectCallBack() {
            @Override
            public void onItemSelect(List<String> selectList) {
                selectLists.clear();
                selectLists.addAll(selectList);
            }
        });

        if(woktype==FABUZUOYE||woktype==ZHIDINGQIUJIAO||woktype==SISHUZUOYE){

            publishredactwok_detail_teahcerhint_tv.setVisibility(View.GONE);

            publishredactwok_detail_teahcername_tv.setText(teacherName);

            publishredactwok_detail_price.setText(price+"");
        }

    }



    @OnClick({  R.id.publishredactwokdetail_cancle,
            R.id.publishredactwok_detail_add_group,
            R.id.publishredacetwok_detail_select_teacher,
            R.id.publishredactwok_detail_genghuan_btn,
            R.id.publishredactwok_detail_selecttype,
            R.id.publishredactwok_detail_coachbtn,
            R.id.publishredactwok_detail_img})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.publishredactwokdetail_cancle:

                this.finish();
                break;
            case R.id.publishredacetwok_detail_select_teacher:

                if(woktype==FABUZUOYE||woktype==ZHIDINGQIUJIAO||woktype==SISHUZUOYE){

                }else {

                    if (teacherId == null) {
                        teacherId = 0;
                    }
                    if(!isPay){
                        PublishSelectTeacherListActivity.start((Activity) context, teacherId, FOR_SELECT_TEACHER);
                    }else{

                    }
                }
                break;
            case R.id.publishredactwok_detail_genghuan_btn:
                resetAddGruop();
                break;
            case R.id.publishredactwok_detail_selecttype:

                final String[] items = new String[] { "对所有人公开","仅对所有老师公开","仅对指定老师公开" };

                AlertDialog dialog = new AlertDialog.Builder(this).setTitle("请选择")
                        .setItems(items, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pushtype = which;
                                publishredactwok_detail_select_tv.setText(items[which]);
                            }
                        }).create();
                dialog.show();

                break;
            case R.id.publishredactwok_detail_coachbtn:

                content = publishredactwok_detail_input.getText().toString();

                if(TextUtils.isEmpty(content)){
                    show_error_Dialog("提示","请填写内容");
                    return;
                }

                if(TextUtils.isEmpty(mediaPath)){
                    show_error_Dialog("提示","请上传图片/视频/音频");
                    return;
                }

                if(selectLists.isEmpty()){
                    show_error_Dialog("提示","请选择标签");
                    return;
                }

                if(price==0){
                    publishredactwok_detail_coachbtn.setEnabled(false);
                    pushContent();
                    return;
                }

                if(isPay){
                    publishredactwok_detail_coachbtn.setEnabled(false);
                    pushContent();
                }else{
                    if(teacherId==null){
                        teacherId = 0;
                    }
                    int type = 0;
                    if(woktype==FABUZUOYE){
                        type = Constant.Oride_Zuoye;
                    }else if(woktype==QIUJIAOZUOYE||woktype==ZHIDINGQIUJIAO){
                        type = Constant.Oride_Qiujiao;
                    }else if(woktype==SISHUZUOYE){
                        type = Constant.Oride_Sishu;
                    }


                }
                break;
            case R.id.publishredactwok_detail_add_group:
            case R.id.publishredactwok_detail_img:

                //预览音频
                if(TextUtils.equals(mediaType,Constant.PUSH_AUDIO)){

                    playRecording();
                    return;
                }
                //预览视频
                if(TextUtils.equals(mediaType,Constant.PUSH_VIDEO)){

                    VideoDetailActivity.start((Activity) context,showMediaPath);

                    return;
                }

                //预览图片
                if(TextUtils.equals(mediaType,Constant.PUSH_IMAGE)){

                    ImageDetailActivity.start((Activity) context,showMediaPath,1);

                    return;
                }


                popup();

                break;
            default:
        }

    }

    private void popup(){
        View view = LayoutInflater.from(context).inflate(R.layout.publishredact_bottom_pop, null);

        TextView publishredact_bottom_pushvideo = view.findViewById(R.id.publishredact_bottom_pushvideo);

        TextView publishredact_bottom_redactvideo = view.findViewById(R.id.publishredact_bottom_redactvideo);

        TextView publishredact_bottom_redactvoice = view.findViewById(R.id.publishredact_bottom_redactvoice);

        TextView publishredact_bottom_pushphoto = view.findViewById(R.id.publishredact_bottom_pushphoto);

        TextView publishredact_bottom_redactphoto = view.findViewById(R.id.publishredact_bottom_redactphoto);

        Button canle_btn = view.findViewById(R.id.publishredact_bottom_cancle);

        //测量View的宽高
        CommonUtil.measureWidthAndHeight(view);
        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(view)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, view.getMeasuredHeight())
                .setBackGroundLevel(0.5f)
                .setAnimationStyle(R.style.AnimUp)
                .create();

        //上传视频
        publishredact_bottom_pushvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionOK()){

                    requestVido();
                }
            }
        });
        //录制视频
        publishredact_bottom_redactvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionOK()){

                    requestRedactvideo();
                }
            }
        });
        //录制音频
        publishredact_bottom_redactvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionOK()) {
                    requestAudio();
                }
            }
        });
        //选择相片
        publishredact_bottom_pushphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionOK()){

                    requestImage();
                }
            }
        });
        //拍照
        publishredact_bottom_redactphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPermissionOK()){

                    requestCamera();
                }
            }
        });

        canle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }
            }
        });

        popupWindow.showAtLocation(publishredactwok_detail_group, Gravity.BOTTOM, 0, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            switch (requestCode){

                //选取照片
                case FOR_REQUEST_IMAGE:
                    if(data==null||data.getData()==null){

                        return;
                    }

                    showMediaPath = getFilePathFromContentUri(data.getData(), context.getContentResolver());

                    Glide.with(context).load(getFilePathFromContentUri(data.getData(), context.getContentResolver())).into(publishredactwok_detail_img);
                    publishredactwok_detail_img.setVisibility(View.VISIBLE);
                    mVideoUploadManager.startUpload(getFilePathFromContentUri(data.getData(), context.getContentResolver()), pushtoken);
                    mediaType = Constant.PUSH_IMAGE;
                    publishredactwok_detail_img.setEnabled(false);
                    break;
                //拍摄照片
                case FOR_REQUEST_CAMERA:
                    if(fileUri==null){

                        return;
                    }

                    showMediaPath = fileUri.toString();

                    Glide.with(context).load(fileUri.toString()).into(publishredactwok_detail_img);
                    publishredactwok_detail_img.setVisibility(View.VISIBLE);
                    mVideoUploadManager.startUpload(fileUri.toString(), pushtoken);
                    mediaType = Constant.PUSH_IMAGE;
                    publishredactwok_detail_img.setEnabled(false);
                    break;
                //上传视频
                case FOR_REQUEST_VIDO:
                    if(data==null||data.getData()==null){

                        return;
                    }


                    showMediaPath = getFilePathFromContentUri(data.getData(), context.getContentResolver());

                    String time= getFilePathFromUriTime(data.getData(), context.getContentResolver());

                    showMediaTime = TimeShift.getAudioTime(Long.parseLong(time) * 1000);


                    Glide.with(context).load(getFilePathFromContentUri(data.getData(), context.getContentResolver())).into(publishredactwok_detail_img);
                    publishredactwok_detail_img.setVisibility(View.VISIBLE);
                    mVideoUploadManager.startUpload(getFilePathFromContentUri(data.getData(), context.getContentResolver()), pushtoken);
                    mediaType = PUSH_VIDEO;
                    publishredactwok_detail_img.setEnabled(false);
                    break;
                //录制语音
                case FOR_REQUEST_AUDIO:
                    if(data==null){

                        return;
                    }
                    showMediaPath= data.getStringExtra(Constant.Audio_key);

                    showMediaTime = data.getStringExtra(Constant.Media_time);

                    if(TextUtils.isEmpty(showMediaPath)){
                        publishredactwok_detail_img.setEnabled(true);
                        return ;
                    }


                    publishredactwok_detail_img.setVisibility(View.VISIBLE);
                    publishredactwok_detail_img.setImageResource(R.mipmap.play_music_bg);

                    mVideoUploadManager.startUpload(showMediaPath, pushtoken);
                    mediaType = Constant.PUSH_AUDIO;
                    publishredactwok_detail_img.setEnabled(false);
                    break;
                case FOR_SELECT_TEACHER:

                    if(data==null){

                        return;
                    }

                    String name = data.getStringExtra(Constant.User_name);

                    if(TextUtils.isEmpty(name)){
                        publishredactwok_detail_teahcerhint_tv.setVisibility(View.VISIBLE);
                    }else{
                        publishredactwok_detail_teahcerhint_tv.setVisibility(View.GONE);
                    }

                    publishredactwok_detail_teahcername_tv.setText(data.getStringExtra(Constant.User_name));

                    teacherId = data.getIntExtra(Constant.Teacher_id, 0);
                    price = data.getDoubleExtra(Constant.Teacher_price,0);
                    if(teacherId==0){
                        teacherId = null;
                        price = normal_price;
                    }

                    publishredactwok_detail_price.setText(price+"");

                    break;
                default:
            }

        }
    }


    private void requestImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, FOR_REQUEST_IMAGE);
        hintpop();
    }

    private void requestCamera(){
        fileUri = new File(Environment.getExternalStorageDirectory() +File.separator+ Environment.DIRECTORY_DCIM+ File.separator+"xyxy"+System.currentTimeMillis()+"photo.jpg");

        if(fileUri!=null){

            imageUri = Uri.fromFile(fileUri);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if(fileUri!=null){

                imageUri = FileProvider.getUriForFile(context, context.getPackageName(), fileUri);//通过FileProvider创建一个content类型的Uri
            }
        }

        Intent intentCamera = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intentCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intentCamera, FOR_REQUEST_CAMERA);
        hintpop();
    }

    private void requestVido(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, FOR_REQUEST_VIDO);
        hintpop();
    }

    private void requestAudio(){
////        Intent intent = new Intent(context, PublishAudioAty.class);
//        startActivityForResult(intent,FOR_REQUEST_AUDIO);
//        hintpop();
    }

    private void requestRedactvideo(){
        VideoRecordActivity.start((Activity) context,VideoRecordActivity.WOK_KEY);
        hintpop();
    }

    private void resetAddGruop(){
        showMediaPath = "";
        mediaType = "";
        mediaPath = "";
        publishredactwok_detail_play_video_group.setVisibility(View.GONE);
        publishredactwok_detail_add_btn.setVisibility(View.VISIBLE);
        publishredactwok_detail_genghuan_group.setVisibility(View.GONE);
        publishredactwok_detail_genghuan_btn.setVisibility(View.GONE);
        publishredactwok_detail_img.setVisibility(View.GONE);
        publishredactwok_detail_play_music_group.setVisibility(View.GONE);
        publishredactwok_detail_img.setEnabled(true);

        if(TextUtils.equals(mediaType,Constant.PUSH_AUDIO)){
            if(player!=null){
                player.stopPalyer();
                player.cancelPalyer();
            }
        }
    }

    private void hintpop(){
        if(popupWindow!=null){
            popupWindow.dismiss();
        }
        publishredactwok_detail_img.setEnabled(false);
    }

    public  String getFilePathFromContentUri(Uri selectedVideoUri,
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

    public  String getFilePathFromUriTime(Uri selectedVideoUri,
                                          ContentResolver contentResolver){
        String filePath;
        String[] filePathColumn = {MediaStore.Video.Media.DURATION};
        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;

    }

    @Override
    public void onUploadProgress(String s, double percent) {
        publishredactwok_detail_add_btn.setVisibility(View.GONE);
        publishredactwok_detail_genghuan_group.setVisibility(View.VISIBLE);
        publishredact_progressBar.setProgress((int)(percent * 100));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onUploadVideoSuccess(JSONObject jsonObject) {
        publishredactwok_detail_img.setEnabled(true);

        if(TextUtils.equals(mediaType,Constant.PUSH_AUDIO)){
            publishredactwok_detail_play_music_group.setVisibility(View.VISIBLE);
            publishredactwok_detail_play_music_time.setText(showMediaTime);
        }

        if(TextUtils.equals(mediaType, PUSH_VIDEO)){
            publishredactwok_detail_play_video_group.setVisibility(View.VISIBLE);
            publishredactwok_detail_play_video_time.setText(showMediaTime);
        }

        try {

            publishredactwok_detail_genghuan_btn.setVisibility(View.VISIBLE);

            mediaPath = jsonObject.getString(Constant.QINIUPUSH_KEY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUploadVideoFailed(int i, String s) {
        publishredactwok_detail_img.setEnabled(true);
    }

    private boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(PublishRedactWokDetailActivity.this);
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
        }
        return isPermissionOK;
    }

    private void loadDefaultPrice(){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IDefaulPrice.class)
                .getDefaultPrice(Constant.Default_price_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultPriceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DefaultPriceBean value) {
                        if(value==null){
                            return ;
                        }

                        price = value.getData().getDefaultPrice();

                        normal_price = price;

                        publishredactwok_detail_price.setText(price+"");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void loadContent(){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IGetUpLoadToken.class)
                .getUpLoadToken(Constant.Get_upload_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetUpLoadModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GetUpLoadModel value) {

                        if(value==null){
                            return ;
                        }
                        pushtoken = value.getData().getToken();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void pushContent(){
        //TODO要先判断是否支付成功
//        PublishRedactWokDetailModel mode = new PublishRedactWokDetailModel();

        StringBuilder majorids = new StringBuilder("");
        if(selectLists!=null){
            for (int i = 0; i < selectLists.size(); i++) {
                majorids.append(selectLists.get(i));
                if(i==selectLists.size()-1){

                }else if(selectLists.size()==1){

                }else{
                    majorids.append(",");
                }
            }
        }



        if(teacherId!=null){
            teacherId = teacherId == 0 ? null : teacherId;
        }

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IPublishRedactWokDetail.class)
                .publishRedactWok(HttpHelp.getUserId(context),majorids.toString(),content,mediaPath,mediaType,pushtype,price,workSource,teacherId,artliveApp.getWorkOrder())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublicWorkBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(PublicWorkBean value) {

                        publishredactwok_detail_pushload.setVisibility(View.GONE);
                        publishredactwok_detail_coachbtn.setEnabled(true);

                        if (showDialog(value, "提交失败")) {

                            if(value.getData()!=null){
                                artliveApp.setWorkOrder("");
                                finish();
                                WorkDataActivity.start((Activity) context,value.getData().getId());
                            }

                        }else{


                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        publishredactwok_detail_pushload.setVisibility(View.GONE);
                        publishredactwok_detail_coachbtn.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void playRecording() {
        if(player==null){
            player = new RecordPlayer(PublishRedactWokDetailActivity.this);
        }
        stopplayer();
        publishredactwok_detail_play_music.setActivated(true);
        player.playRecordFile(new File(showMediaPath),publishredactwok_detail_play_music);
    }

    private void stopplayer() {
        if(player==null){
            return ;
        }
        player.cancelPalyer();
    }

    public class PaySucessRecceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            publishredactwok_detail_pushload.setVisibility(View.VISIBLE);
            isPay = true;
            publishredactwok_detail_coachbtn.setEnabled(false);
            pushContent();

        }
    }
}
