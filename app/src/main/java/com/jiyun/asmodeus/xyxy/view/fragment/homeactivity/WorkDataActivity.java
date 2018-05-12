package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.WokDetailBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.BitmapHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;
import com.jiyun.asmodeus.xyxy.view.ui.MyListView;
import com.jiyun.asmodeus.xyxy.view.ui.MyScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WorkDataActivity extends BaseActivity implements View.OnClickListener {

    public static final String PayBroadCast_Key = "com.xyxy.artlive_android.detail.WokDetailAty";

    private final int STUDENT = 1;

    private final int TEACHER = 2;

    @ViewInject(R.id.wok_detail_aty_group)
    private LinearLayout wok_detail_aty_group;

    @ViewInject(R.id.wok_detail_aty_listview)
    private MyListView listView;

    @ViewInject(R.id.wok_detail_aty_pinglun_list_group)
    private LinearLayout wok_detail_aty_pinglun_list_group;

    @ViewInject(R.id.wok_detail_aty_scrollview)
    private MyScrollView wok_detail_aty_scrollview;

    @ViewInject(R.id.wok_detail_aty_pinglun_input_group)
    private RelativeLayout wok_detail_aty_pinglun_input_group;


    //学生作品展示
    @ViewInject(R.id.wok_detail_aty_studentimg)
    private RoundedImageView wok_detail_aty_studentimg;

    @ViewInject(R.id.wok_detail_aty_studentname)
    private TextView wok_detail_aty_studentname;

    @ViewInject(R.id.wok_detail_aty_time)
    private TextView wok_detail_aty_time;

    @ViewInject(R.id.wok_detail_aty_from)
    private TextView wok_detail_aty_from;

    @ViewInject(R.id.wok_detail_aty_content)
    private TextView wok_detail_aty_content;

    @ViewInject(R.id.wok_detail_aty_introimg)
    private ImageView wok_detail_aty_introimg;

    @ViewInject(R.id.wok_detail_aty_audio_gorpu)
    private RelativeLayout wok_detail_aty_audio_gorpu;

    @ViewInject(R.id.wok_detail_aty_audio_active_img)
    private ImageView wok_detail_aty_audio_active_img;

    @ViewInject(R.id.wok_detail_aty_audio_gan_img)
    private ImageView wok_detail_aty_audio_gan_img;

    @ViewInject(R.id.wok_detail_aty_audio_time)
    private TextView wok_detail_aty_audio_time;

    @ViewInject(R.id.wok_detail_aty_video_gorpu)
    private LinearLayout wok_detail_aty_video_gorpu;

    @ViewInject(R.id.wok_detail_aty_video_time)
    private TextView wok_detail_aty_video_time;

    @ViewInject(R.id.wok_detail_aty_worktype)
    private TextView wok_detail_aty_worktype;

    //没有付费显示的偷听
    @ViewInject(R.id.wok_detail_aty_priced_group)
    private RelativeLayout wok_detail_aty_priced_group;

    @ViewInject(R.id.wok_detail_aty_teacherimg)
    private RoundedImageView wok_detail_aty_teacherimg;

    @ViewInject(R.id.wok_detail_aty_teachername)
    private TextView wok_detail_aty_teachername;

    @ViewInject(R.id.wok_detail_aty_teacherlevel)
    private ImageView wok_detail_aty_teacherlevel;

    @ViewInject(R.id.wok_detail_aty_teacherintro)
    private TextView wok_detail_aty_teacherintro;

    @ViewInject(R.id.wok_detail_aty_priceing_tv)
    private TextView wok_detail_aty_priceing_tv;

    //花钱偷看之后
    @ViewInject(R.id.wok_detail_aty_pay_teacher_group)
    private RelativeLayout wok_detail_aty_pay_teacher_group;

    @ViewInject(R.id.wok_detail_aty_pay_teachertimg)
    private RoundedImageView wok_detail_aty_pay_teachertimg;

    @ViewInject(R.id.wok_detail_aty_pay_teachername)
    private TextView wok_detail_aty_pay_teachername;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_intro)
    private TextView wok_detail_aty_pay_teacher_intro;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_content)
    private TextView wok_detail_aty_pay_teacher_content;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_introimg)
    private ImageView wok_detail_aty_pay_teacher_introimg;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_audio_gorpu)
    private RelativeLayout wok_detail_aty_pay_teacher_audio_gorpu;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_active_img)
    private ImageView wok_detail_aty_pay_teacher_active_img;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_gan_img)
    private ImageView wok_detail_aty_pay_teacher_gan_img;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_audio_time)
    private TextView wok_detail_aty_pay_teacher_audio_time;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_video_gorpu)
    private LinearLayout wok_detail_aty_pay_teacher_video_gorpu;

    @ViewInject(R.id.wok_detail_aty_pay_teacher_video_time)
    private TextView wok_detail_aty_pay_teacher_video_time;

    @ViewInject(R.id.wok_detail_aty_pay_time)
    private TextView wok_detail_aty_pay_time;

    @ViewInject(R.id.wok_detail_aty_listview_loadmore)
    private TextView wok_detail_aty_listview_loadmore;

    @ViewInject(R.id.wok_detail_aty_praise_btn)
    private TextView wok_detail_aty_praise_btn;

    @ViewInject(R.id.wok_detail_aty_praise_num)
    private TextView wok_detail_aty_praise_num;

    @ViewInject(R.id.wok_detail_aty_comment_num)
    private TextView wok_detail_aty_comment_num;

    @ViewInject(R.id.wok_detail_aty_listview_empty_group)
    private RelativeLayout wok_detail_aty_listview_empty_group;

    @ViewInject(R.id.wok_detail_aty_pinglun_input)
    private EditText wok_detail_aty_pinglun_input;

    @ViewInject(R.id.wok_detail_aty_permission)
    private TextView wok_detail_aty_permission;


    @ViewInject(R.id.wok_detail_aty_contentView)
    private LinearLayout wok_detail_aty_contentView;

    @ViewInject(R.id.wok_detail_aty_dashangRecyclerView)
    private RecyclerView wok_detail_aty_dashangRecyclerView;

    @ViewInject(R.id.wok_detail_aty_dashang)
    private TextView wok_detail_aty_dashang;



    private Context context;

    private int workId;

    private String pinglun_string;

    private MediaPlayer mediaPlayer;

    private MediaPlayer netmediaPlayer;

    private CompositeDisposable compositeDisposable;

    private boolean isPraise = false;

    private WokDetailBean.DataBean.HomewokBean homewokBean;
    private WokDetailBean.DataBean dataBeans;

    private List<WokDetailBean.DataBean.CommentsBean.ListBean> commentList;

    private WorkDetailCommentAdapter adapter;


    private List<WokDetailBean.DataBean.RewardUserListBean> dashangLists;
    private DetailDaShangAdapter dashangAdapter;

    private List<GiftListModel.DataBean.GiftListBean> giftLists;

    private int page = 1;

    private RotateAnimation dieAnimation;
    private RotateAnimation ganAnimation;

    private int audioType;

    private WorkPaySucessRecceiver workPaySucessRecceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wok_detail_aty);
        ViewUtils.inject(this);
        setTitleTheme(this,true);
        AndroidBug5497Workaround.assistActivity(this);

        init();

        initView();

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();

        commentList.clear();

        loadContent();

    }


    @Override
    protected void onPause() {
        super.onPause();

        if(mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = null;
        }

        if(netmediaPlayer!=null){
            netmediaPlayer.stop();
            netmediaPlayer.reset();
            netmediaPlayer.release();
            netmediaPlayer = null;
        }

        dieAnimation.cancel();
        wok_detail_aty_audio_active_img.clearAnimation();
        ganAnimation.cancel();
        wok_detail_aty_audio_gan_img.clearAnimation();
        wok_detail_aty_pay_teacher_active_img.clearAnimation();
        wok_detail_aty_pay_teacher_gan_img.clearAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();

        unregisterReceiver(workPaySucessRecceiver);
    }

    private void init(){

        context=this;

        compositeDisposable = new CompositeDisposable();

        commentList = new ArrayList<>();

        giftLists = new ArrayList<>();

        dashangLists = new ArrayList<>();

        dashangAdapter = new DetailDaShangAdapter(context,R.layout.detail_dashang_listitem,dashangLists);

        adapter = new WorkDetailCommentAdapter(context,commentList);

        if(getIntent()!=null){
            workId = getIntent().getIntExtra(Constant.Work_Id, 0);

        }
        createRotateAnimation();
        createRotateGanAnimation();
        IntentFilter filter=new IntentFilter(PayBroadCast_Key);
        workPaySucessRecceiver = new WorkPaySucessRecceiver();
        registerReceiver(workPaySucessRecceiver,filter);
    }

    private void initView(){


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        wok_detail_aty_dashangRecyclerView.setLayoutManager(linearLayoutManager);
        wok_detail_aty_dashangRecyclerView.setAdapter(dashangAdapter);
        wok_detail_aty_dashangRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildPosition(view) != (dashangLists.size() - 1)) {
                    outRect.right = -30;
                }
            }
        });

        listView.setAdapter(adapter);


        adapter.setOnItemShareClick(new WorkDetailCommentAdapter.OnItemShareClick() {
            @Override
            public void onPraiseClick(int position) {

                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginAty.class));
                    return ;
                }

                if(commentList.isEmpty()){
                    return;
                }

                int favorite= commentList.get(position).getIsPraise();

                if(favorite==Constant.NOTFAVORITE){

                    parise(commentList.get(position).getUserId(),commentList.get(position).getId(),Constant.Praise_work_comment);
                    commentList.get(position).setIsPraise(Constant.FAVORITE);
                }else{
                    pariseCancle(commentList.get(position).getUserId(),commentList.get(position).getId(),Constant.Praise_work_comment);
                    commentList.get(position).setIsPraise(Constant.NOTFAVORITE);
                }

            }

            @Override
            public void onCommentClick(int position) {
                if(commentList.isEmpty()){
                    return;
                }
                WokDetailCommentAty.start((Activity) context,commentList.get(position).getId(),commentList.get(position).getUserId(),workId,commentList.get(position).getContent(),commentList.get(position).getReplyNum());
            }

            @Override
            public void onReplyAt(int position) {

            }


        });

        wok_detail_aty_scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (wok_detail_aty_pinglun_input_group.getVisibility() == View.VISIBLE) {
                    HideKeyboard(wok_detail_aty_group);
                    wok_detail_aty_pinglun_input_group.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });

    }


    @OnClick({R.id.wok_detail_aty_cancle,R.id.wok_detail_aty_input_btn,R.id.wok_detail_aty_pinglun_send,R.id.wok_detail_aty_praise_group,
            R.id.wok_detail_aty_comment_group,
            R.id.wok_detail_aty_listview_loadmore,
            R.id.wok_detail_aty_introimg,
            R.id.wok_detail_aty_pay_teacher_introimg,
            R.id.wok_detail_aty_more,
            R.id.wok_detail_aty_priceing_group,
            R.id.wok_detail_aty_dashang})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.wok_detail_aty_cancle:

                this.finish();

                break;
            case R.id.wok_detail_aty_input_btn:


                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginAty.class));
                    return ;
                }

                popinput();

                break;
            case R.id.wok_detail_aty_pinglun_send:


                String content = wok_detail_aty_pinglun_input.getText().toString();

                if(!TextUtils.isEmpty(content)){
                    wok_detail_aty_pinglun_input.setText("");
                    postComment(content);
                    wok_detail_aty_pinglun_input_group.setVisibility(View.GONE);
                    HideKeyboard(wok_detail_aty_group);
                }
                break;
            case R.id.wok_detail_aty_praise_group:


                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginAty.class));
                    return ;
                }

                if(homewokBean==null){
                    return;
                }

                if(isPraise){
                    pariseCancle(homewokBean.getStudentId(),homewokBean.getId(),Constant.Praise_work);
                    isPraise = false;
                    wok_detail_aty_praise_btn.setActivated(false);
                    if(homewokBean.getPraiseNum()==0){
                        wok_detail_aty_praise_num.setText(homewokBean.getPraiseNum()+"");
                    }else{
                        homewokBean.setPraiseNum(homewokBean.getPraiseNum()-1);
                        wok_detail_aty_praise_num.setText(homewokBean.getPraiseNum()+"");
                    }
                }else{
                    parise(homewokBean.getStudentId(),homewokBean.getId(),Constant.Praise_work);
                    isPraise = true;
                    wok_detail_aty_praise_btn.setActivated(true);
                    homewokBean.setPraiseNum(homewokBean.getPraiseNum()+1);
                    wok_detail_aty_praise_num.setText(homewokBean.getPraiseNum()+"");
                }

                break;

            case R.id.wok_detail_aty_comment_group:

                wok_detail_aty_scrollview.post(new Runnable() {
                    @Override
                    public void run() {

                        wok_detail_aty_scrollview.scrollTo(0,listView.getTop());
                    }
                });
                break;
            case R.id.wok_detail_aty_listview_loadmore:
                //评论分页

                wok_detail_aty_listview_loadmore.setEnabled(false);
                page++;
                loadPageComment();

                break;
            case R.id.wok_detail_aty_introimg:

                if(homewokBean==null){
                    return ;
                }

                if(TextUtils.equals(homewokBean.getWorksType(),Constant.PUSH_IMAGE)){

                    ImageDetailActivity.start((Activity) context,homewokBean.getPath());
                    AtyAnim.centUp(WokDetailAty.this);
                }
                if(TextUtils.equals(homewokBean.getWorksType(),Constant.PUSH_AUDIO)){

                    if(audioType==STUDENT&&netmediaPlayer!=null&&netmediaPlayer.isPlaying()){
                        if(mediaPlayer!=null) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer = null;
                        }

                        if(netmediaPlayer!=null){
                            netmediaPlayer.stop();
                            netmediaPlayer.reset();
                            netmediaPlayer.release();
                            netmediaPlayer = null;
                        }
//
                        dieAnimation.cancel();
                        wok_detail_aty_audio_active_img.clearAnimation();

                        ganAnimation.cancel();
                        wok_detail_aty_audio_gan_img.clearAnimation();

                        audioType = 0;

                        return ;
                    }

                    audioType = STUDENT;

                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer = null;
                    }

                    if(netmediaPlayer!=null){
                        netmediaPlayer.stop();
                        netmediaPlayer.reset();
                        netmediaPlayer.release();
                        netmediaPlayer = null;
                    }

                    if(!TextUtils.isEmpty(homewokBean.getPath())){

                        dieAnimation.cancel();
                        wok_detail_aty_audio_active_img.clearAnimation();
                        wok_detail_aty_pay_teacher_active_img.clearAnimation();

                        ganAnimation.cancel();
                        wok_detail_aty_audio_gan_img.clearAnimation();
                        wok_detail_aty_pay_teacher_gan_img.clearAnimation();

                        wok_detail_aty_audio_active_img.startAnimation(dieAnimation);

                        wok_detail_aty_audio_gan_img.startAnimation(ganAnimation);

                        mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(homewokBean.getPath());
                            mediaPlayer.prepareAsync();
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                    netmediaPlayer = mp;
                                    netmediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            dieAnimation.cancel();
                                            wok_detail_aty_audio_active_img.clearAnimation();

                                            ganAnimation.cancel();
                                            wok_detail_aty_audio_gan_img.clearAnimation();

                                        }
                                    });
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
                if(TextUtils.equals(homewokBean.getWorksType(),Constant.PUSH_VIDEO)){

                    VideoDetailActivity.start((Activity)context,homewokBean.getPath());
                    AtyAnim.centUp(WokDetailAty.this);
                }
                break;
            case R.id.wok_detail_aty_pay_teacher_introimg:


                if(homewokBean==null){
                    return ;
                }

                if(TextUtils.equals(homewokBean.getAnswerWorksType(),Constant.PUSH_IMAGE)){

                    ImageDetailActivity.start((Activity) context,homewokBean.getAnswerPath());
                    AtyAnim.centUp(WokDetailAty.this);
                }
                if(TextUtils.equals(homewokBean.getAnswerWorksType(),Constant.PUSH_AUDIO)){


                    if(audioType==TEACHER&&netmediaPlayer!=null&&netmediaPlayer.isPlaying()){
                        if(mediaPlayer!=null) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer = null;
                        }

                        if(netmediaPlayer!=null){
                            netmediaPlayer.stop();
                            netmediaPlayer.reset();
                            netmediaPlayer.release();
                            netmediaPlayer = null;
                        }

                        dieAnimation.cancel();
                        wok_detail_aty_pay_teacher_active_img.clearAnimation();

                        ganAnimation.cancel();
                        wok_detail_aty_pay_teacher_gan_img.clearAnimation();

                        audioType = 0;

                        return ;
                    }

                    audioType = TEACHER;

                    if(mediaPlayer!=null) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer = null;
                    }

                    if(netmediaPlayer!=null){
                        netmediaPlayer.stop();
                        netmediaPlayer.reset();
                        netmediaPlayer.release();
                        netmediaPlayer = null;
                    }

                    if(!TextUtils.isEmpty(homewokBean.getAnswerPath())){

                        dieAnimation.cancel();
                        wok_detail_aty_audio_active_img.clearAnimation();
                        wok_detail_aty_pay_teacher_active_img.clearAnimation();

                        ganAnimation.cancel();
                        wok_detail_aty_audio_gan_img.clearAnimation();
                        wok_detail_aty_pay_teacher_gan_img.clearAnimation();

                        wok_detail_aty_pay_teacher_active_img.startAnimation(dieAnimation);

                        wok_detail_aty_pay_teacher_gan_img.startAnimation(ganAnimation);

                        mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(homewokBean.getAnswerPath());
                            mediaPlayer.prepareAsync();
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                    netmediaPlayer = mp;
                                    netmediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {

                                            dieAnimation.cancel();
                                            wok_detail_aty_pay_teacher_active_img.clearAnimation();

                                            ganAnimation.cancel();
                                            wok_detail_aty_pay_teacher_gan_img.clearAnimation();

                                        }
                                    });
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
                if(TextUtils.equals(homewokBean.getAnswerWorksType(),Constant.PUSH_VIDEO)){

                    VideoDetailActivity.start((Activity)context,homewokBean.getAnswerPath());
                    AtyAnim.centUp(WokDetailAty.this);
                }

                break;

            case R.id.wok_detail_aty_more:

                if(homewokBean==null){
                    return;
                }

//                new AlertBottom((Activity) context).popupWokValuableAlter(wok_detail_aty_group,false,0,"","学生作业",homewokBean.getContent());

                new AlertBottom((Activity) context).popupAlter(wok_detail_aty_group,String.format(Constant.WORK_SHARE_URL,homewokBean.getId()),String.format("%s的作品",homewokBean.getNickname()),Constant.SHARE_CONTENT);

                break;
            case R.id.wok_detail_aty_priceing_group:


                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginAty.class));
                    return ;
                }

                if(homewokBean==null){
                    return;
                }

                new AlertBottom((Activity) context).peepWindePay(wok_detail_aty_group,HttpHelp.getUserId(context),homewokBean.getStudentId(),homewokBean.getId(),homewokBean.getPeepPrice());

                break;
            case R.id.wok_detail_aty_dashang:

                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginAty.class));
                    return ;
                }

                wok_detail_aty_dashang.setEnabled(false);

                getGift();

                break;
            default:
                break;
        }
    }


    private void getGift(){

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IGiftList.class)
                .getGiftList(HttpHelp.getUserId(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GiftListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GiftListModel value) {
                        wok_detail_aty_dashang.setEnabled(true);

                        if(value==null||value.getData()==null){
                            return;
                        }

                        giftLists.clear();
                        giftLists.addAll(value.getData().getGiftList());

                        new GiftPopup(context,PayBroadCast_Key).poopWindePorPay(wok_detail_aty_group,giftLists,value.getData().getUserBeanAmount(),homewokBean.getStudentId(),homewokBean.getTUserId(),homewokBean.getId(),3,"0");

                    }

                    @Override
                    public void onError(Throwable e) {
                        wok_detail_aty_dashang.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    private void parise(int userid,int itemid,String type){
        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IPraise.class)
                .postPraise(userid,itemid,HttpHelp.getUserId(context),type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessModel value) {
                        if(value==null){
                            return;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void pariseCancle(int userid,int itemid,String type){
        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IPraiseCancle.class)
                .postPraiseCancle(userid,itemid,HttpHelp.getUserId(context),type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessModel value) {
                        if(value==null){
                            return;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private void loadPageComment(){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IWorkCommentPageList.class)
                .workCommentPage(workId,HttpHelp.getUserId(context),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WokDetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WokDetailModel value) {

                        if(value==null||value.getData()==null||value.getData().getComments()==null){
                            if(page>1){
                                page--;
                            }
                            return ;
                        }

                        wok_detail_aty_listview_loadmore.setEnabled(true);
                        commentList.addAll(value.getData().getComments().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(page>1){
                            page--;
                        }
                        wok_detail_aty_listview_loadmore.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void loadContent(){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IWorkDetail.class)
                .loadWorkDetail(workId,HttpHelp.getUserId(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WokDetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WokDetailModel value) {

                        if(value==null){
                            return ;
                        }
                        wok_detail_aty_loadView.setVisibility(View.GONE);
                        wok_detail_aty_contentView.setVisibility(View.VISIBLE);
                        fillData(value.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("TAG", "onError: "+e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void fillData(WokDetailModel.DataBean dataBean){

        if(dataBean==null||dataBean.getHomewok()==null){
            return ;
        }

        if(dataBean.getRewardUserList()!=null){
            dashangLists.clear();
            dashangLists.addAll(dataBean.getRewardUserList());
            dashangAdapter.notifyDataSetChanged();
        }else{
            dashangLists.clear();
            dashangAdapter.notifyDataSetChanged();
        }

        dataBeans = dataBean;

        homewokBean = dataBean.getHomewok();

        if(homewokBean.getAnswerDate()==0){
            wok_detail_aty_priced_group.setVisibility(View.GONE);
            wok_detail_aty_pay_teacher_group.setVisibility(View.GONE);
            wok_detail_aty_permission.setVisibility(View.GONE);
        }else {

            if(homewokBean.getAnswerPermission()==Constant.NOTFAVORITE){

                wok_detail_aty_permission.setVisibility(View.GONE);

                if (homewokBean.getIsPeep() == Constant.TEACHER_DAIFUDAO) {
                    //未偷听
                    wok_detail_aty_priced_group.setVisibility(View.VISIBLE);
                    wok_detail_aty_pay_teacher_group.setVisibility(View.GONE);
                    HttpHelp.glideLoadC(context, wok_detail_aty_teacherimg, homewokBean.getTPhoto(), R.mipmap.user_head_portrait);
                    wok_detail_aty_teachername.setText(homewokBean.getTRealname());
                    SplitStringColorUtils.setImgLevel(wok_detail_aty_teacherlevel, homewokBean.getTUserType());
                    wok_detail_aty_teacherintro.setText(homewokBean.getTIntro());
                    wok_detail_aty_priceing_tv.setText(String.format("%s元偷听", homewokBean.getPeepPrice()));

                } else if (homewokBean.getStatus() == Constant.TEACHER_YIWANCHENG) {
                    //付费过
                    wok_detail_aty_priced_group.setVisibility(View.GONE);
                    wok_detail_aty_pay_teacher_group.setVisibility(View.VISIBLE);

                    HttpHelp.glideLoadC(context, wok_detail_aty_pay_teachertimg, homewokBean.getTPhoto(), R.mipmap.user_head_portrait);
                    wok_detail_aty_pay_teachername.setText(homewokBean.getTRealname());
                    wok_detail_aty_pay_teacher_intro.setText(homewokBean.getTIntro());
                    wok_detail_aty_pay_teacher_content.setText(homewokBean.getAnswerContent());

                    wok_detail_aty_pay_time.setText(TimeShift.getChatTime(homewokBean.getAnswerDate()));

                    if (TextUtils.equals(Constant.PUSH_AUDIO, homewokBean.getAnswerWorksType())) {
                        BitmapHelp.setAudioRelayout(context,wok_detail_aty_pay_teacher_audio_gorpu);
                        BitmapHelp.setAudioImageView(context,wok_detail_aty_pay_teacher_introimg);
                        wok_detail_aty_pay_teacher_introimg.setImageResource(R.mipmap.audio_play_bg);
                        wok_detail_aty_pay_teacher_audio_gorpu.setVisibility(View.VISIBLE);
                        wok_detail_aty_pay_teacher_video_gorpu.setVisibility(View.GONE);
                        wok_detail_aty_pay_teacher_audio_time.setText(homewokBean.getAnswerDuration());
                    } else if (TextUtils.equals(Constant.PUSH_IMAGE, homewokBean.getAnswerWorksType())) {
                        BitmapHelp.setImageView(context,homewokBean.getAnswerPicProperty(),wok_detail_aty_pay_teacher_introimg);
                        HttpHelp.glideLoadF(context, wok_detail_aty_pay_teacher_introimg, homewokBean.getAnswerCoverImg());
                        wok_detail_aty_pay_teacher_audio_gorpu.setVisibility(View.GONE);
                        wok_detail_aty_pay_teacher_video_gorpu.setVisibility(View.GONE);
                    } else if (TextUtils.equals(Constant.PUSH_VIDEO, homewokBean.getAnswerWorksType())) {
                        BitmapHelp.setImageView(context,homewokBean.getAnswerPicProperty(),wok_detail_aty_pay_teacher_introimg);
                        HttpHelp.glideLoadF(context, wok_detail_aty_pay_teacher_introimg, homewokBean.getAnswerCoverImg());
                        wok_detail_aty_pay_teacher_audio_gorpu.setVisibility(View.GONE);
                        wok_detail_aty_pay_teacher_video_gorpu.setVisibility(View.VISIBLE);
                        wok_detail_aty_pay_teacher_audio_time.setText(homewokBean.getAnswerDuration());
                    }
                }

            }else{
                wok_detail_aty_priced_group.setVisibility(View.GONE);
                wok_detail_aty_pay_teacher_group.setVisibility(View.GONE);
                wok_detail_aty_permission.setVisibility(View.VISIBLE);
            }

        }

        //学生作业内容
        HttpHelp.glideLoadC(context,wok_detail_aty_studentimg,homewokBean.getPhoto(),R.mipmap.user_head_portrait);

        wok_detail_aty_studentname.setText(homewokBean.getNickname());

        wok_detail_aty_time.setText(TimeShift.getChatTime(homewokBean.getCreateDate()));

        wok_detail_aty_from.setText(homewokBean.getSource());

        wok_detail_aty_content.setText(homewokBean.getContent());

        if(TextUtils.equals(Constant.PUSH_AUDIO,homewokBean.getWorksType())){
            wok_detail_aty_audio_gorpu.setVisibility(View.VISIBLE);
            wok_detail_aty_video_gorpu.setVisibility(View.GONE);
            BitmapHelp.setAudioImageView(context,wok_detail_aty_introimg);
            BitmapHelp.setAudioRelayout(context,wok_detail_aty_audio_gorpu);
            wok_detail_aty_introimg.setImageResource(R.mipmap.audio_play_bg);
            wok_detail_aty_audio_time.setText(homewokBean.getDuration());
        }else if(TextUtils.equals(Constant.PUSH_IMAGE,homewokBean.getWorksType())){
            BitmapHelp.setImageView(context,homewokBean.getPicProperty(),wok_detail_aty_introimg);
            HttpHelp.glideLoadF(context,wok_detail_aty_introimg,homewokBean.getCoverImg());
            wok_detail_aty_audio_gorpu.setVisibility(View.GONE);
            wok_detail_aty_video_gorpu.setVisibility(View.GONE);
        }else if(TextUtils.equals(Constant.PUSH_VIDEO,homewokBean.getWorksType())){
            BitmapHelp.setImageView(context,homewokBean.getPicProperty(),wok_detail_aty_introimg);
            HttpHelp.glideLoadF(context,wok_detail_aty_introimg,homewokBean.getCoverImg());
            wok_detail_aty_audio_gorpu.setVisibility(View.GONE);
            wok_detail_aty_video_gorpu.setVisibility(View.VISIBLE);
            wok_detail_aty_video_time.setText(homewokBean.getDuration());
        }

        if(!TextUtils.isEmpty(homewokBean.getMajorIds())){
            SplitStringColorUtils.addForeColorSpan(context,homewokBean.getMajorIds().split(","),wok_detail_aty_worktype);
        }

        wok_detail_aty_praise_num.setText(homewokBean.getPraiseNum()+"");
        wok_detail_aty_comment_num.setText(homewokBean.getCommentNum()+"");

        if(homewokBean.getIsPraise()==Constant.NOTFAVORITE){
            isPraise = false;
            wok_detail_aty_praise_btn.setActivated(false);
        }else{
            isPraise = true;
            wok_detail_aty_praise_btn.setActivated(true);
        }

        if(dataBeans.getComments()!=null&&dataBeans.getComments().getList()!=null&&dataBeans.getComments().getList().size()>0){
            listView.setVisibility(View.VISIBLE);
            wok_detail_aty_listview_empty_group.setVisibility(View.GONE);
            commentList.addAll(dataBeans.getComments().getList());
            adapter.notifyDataSetChanged();
            if(commentList.size()>=3){
                if(!(wok_detail_aty_listview_loadmore.getVisibility()==View.VISIBLE)){
                    wok_detail_aty_listview_loadmore.setVisibility(View.VISIBLE);
                }
            }else{
                wok_detail_aty_listview_loadmore.setVisibility(View.GONE);
            }
        }else{
            listView.setVisibility(View.GONE);
            wok_detail_aty_listview_loadmore.setVisibility(View.GONE);
            wok_detail_aty_listview_empty_group.setVisibility(View.VISIBLE);
        }

    }


    private void postComment(String content){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IWorkCommentPost.class)
                .postWorkComment(HttpHelp.getUserId(context),content,workId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicCommentSuccessModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicCommentSuccessModel value) {

                        if(value==null||value.getData()==null||value.getData()==null){
                            Toast.makeText(context,"评论失败",Toast.LENGTH_SHORT).show();
                        }

                        commentList.add(0,value.getData());
                        adapter.notifyDataSetChanged();

                        wok_detail_aty_scrollview.post(new Runnable() {
                            @Override
                            public void run() {

                                wok_detail_aty_scrollview.scrollTo(0,listView.getTop());
                            }
                        });


                        listView.setVisibility(View.VISIBLE);
                        wok_detail_aty_listview_empty_group.setVisibility(View.GONE);
                        if(commentList.size()>=3){
                            if(!(wok_detail_aty_listview_loadmore.getVisibility()==View.VISIBLE)){
                                wok_detail_aty_listview_loadmore.setVisibility(View.VISIBLE);
                            }
                        }else{
                            wok_detail_aty_listview_loadmore.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }




    private void popinput(){
        wok_detail_aty_pinglun_input_group.setVisibility(View.VISIBLE);
        wok_detail_aty_pinglun_input.setFocusable(true);
        wok_detail_aty_pinglun_input.setFocusableInTouchMode(true);
        wok_detail_aty_pinglun_input.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        InputMethodManager imm = (InputMethodManager) wok_detail_aty_pinglun_input_group.getContext().getSystemService(Context.INPUT_METHOD_SERVICE); imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    public void HideKeyboard(View v){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        InputMethodManager imm = ( InputMethodManager) v.getContext( ).getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken() , 0 );
        }
    }


    /**
     * 创建旋转动画
     */
    private void createRotateAnimation() {
        dieAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF ,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        dieAnimation.setFillAfter(true);
        dieAnimation.setDuration(5000);
        dieAnimation.setRepeatCount(-1);
    }

    /**
     * 创建旋转动画
     */
    private void createRotateGanAnimation() {
        ganAnimation = new RotateAnimation(0, 20, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.0f);
        ganAnimation.setFillAfter(true);
        ganAnimation.setDuration(1000);
    }


    public class WorkPaySucessRecceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            WokDetailModel.DataBean.RewardUserListBean rewardUserListBean = new WokDetailModel.DataBean.RewardUserListBean();

            rewardUserListBean.setUserPhoto(intent.getStringExtra(Constant.User_icon));

            if(dashangLists!=null){

                if(dashangLists.size()>=5){
                    dashangLists.remove(0);
                }
                dashangLists.add(rewardUserListBean);
                dashangAdapter.notifyDataSetChanged();
            }

        }
    }



}
