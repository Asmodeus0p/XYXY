package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.ICancleFavorite;
import com.jiyun.asmodeus.xyxy.model.biz.IFavorite;
import com.jiyun.asmodeus.xyxy.model.biz.IGiftList;
import com.jiyun.asmodeus.xyxy.model.biz.IPraise;
import com.jiyun.asmodeus.xyxy.model.biz.IPraiseCancle;
import com.jiyun.asmodeus.xyxy.model.biz.IValuableCommentPageList;
import com.jiyun.asmodeus.xyxy.model.biz.IValuableCommentPost;
import com.jiyun.asmodeus.xyxy.model.biz.IValuableDetail;
import com.jiyun.asmodeus.xyxy.model.entity.BasicCommentSuccessModel;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.GiftListModel;
import com.jiyun.asmodeus.xyxy.model.entity.ValuableDetailBeanl;
import com.jiyun.asmodeus.xyxy.model.entity.WokDetailBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.BitmapHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.view.adapter.ValualbeDetailDaShangAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.WorkDetailCommentAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.LoginActivity;
import com.jiyun.asmodeus.xyxy.view.ui.MyListView;
import com.jiyun.asmodeus.xyxy.view.ui.MyScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ValuableDetailActivity extends BaselivstActivity {

    public static final String PayBroadCast_Key = "com.xyxy.artlive_android.detail.ValuableDetailAty";

    @ViewInject(R.id.valuable_detail_aty_group)
    private LinearLayout valuable_detail_aty_group;

    @ViewInject(R.id.valuable_detail_aty_scrollview)
    private MyScrollView valuable_detail_aty_scrollview;

    @ViewInject(R.id.valuable_detail_aty_img)
    private RoundedImageView valuable_detail_aty_img;

    @ViewInject(R.id.valuable_detail_aty_name)
    private TextView valuable_detail_aty_name;

    @ViewInject(R.id.valuable_detail_aty_contenttype)
    private TextView valuable_detail_aty_contenttype;

    @ViewInject(R.id.valuable_detail_aty_time)
    private TextView valuable_detail_aty_time;

    @ViewInject(R.id.valuable_detail_aty_title)
    private TextView valuable_detail_aty_title;

    @ViewInject(R.id.valuable_detail_aty_content)
    private TextView valuable_detail_aty_content;

    @ViewInject(R.id.valuable_detail_aty_contentimg)
    private ImageView valuable_detail_aty_contentimg;

    @ViewInject(R.id.valuable_detail_aty_audio_gorpu)
    private RelativeLayout valuable_detail_aty_audio_gorpu;

    @ViewInject(R.id.valuable_detail_aty_audio_active_img)
    private ImageView valuable_detail_aty_audio_active_img;

    @ViewInject(R.id.valuable_detail_aty_audio_gan_img)
    private ImageView valuable_detail_aty_audio_gan_img;

    @ViewInject(R.id.valuable_detail_aty_audio_time)
    private TextView valuable_detail_aty_audio_time;

    @ViewInject(R.id.valuable_detail_aty_video_gorpu)
    private LinearLayout valuable_detail_aty_video_gorpu;

    @ViewInject(R.id.valuable_detail_aty_video_time)
    private TextView valuable_detail_aty_video_time;

    @ViewInject(R.id.valuable_detail_aty_worktype)
    private TextView valuable_detail_aty_worktype;

    @ViewInject(R.id.valuable_detail_aty_listview_empty_group)
    private RelativeLayout valuable_detail_aty_listview_empty_group;

    @ViewInject(R.id.valuable_detail_aty_listview)
    private MyListView listView;

    @ViewInject(R.id.valuable_detail_aty_listview_loadmore)
    private TextView valuable_detail_aty_listview_loadmore;

    @ViewInject(R.id.valuable_detail_aty_input_group)
    private RelativeLayout valuable_detail_aty_input_group;

    @ViewInject(R.id.valuable_detail_aty_praise_num)
    private TextView valuable_detail_aty_praise_num;

    @ViewInject(R.id.valuable_detail_aty_praise_btn)
    private TextView valuable_detail_aty_praise_btn;

    @ViewInject(R.id.valuable_detail_aty_comment_num)
    private TextView valuable_detail_aty_comment_num;

    @ViewInject(R.id.valuable_detail_aty_pinglun_input_group)
    private RelativeLayout valuable_detail_aty_pinglun_input_group;

    @ViewInject(R.id.valuable_detail_aty_pinglun_input)
    private EditText valuable_detail_aty_pinglun_input;

    @ViewInject(R.id.valuable_detail_aty_collect_cb)
    private CheckBox valuable_detail_aty_collect_cb;

    @ViewInject(R.id.valuable_detail_aty_loadview)
    private RelativeLayout valuable_detail_aty_loadview;

    @ViewInject(R.id.valuable_detail_aty_contentview)
    private LinearLayout valuable_detail_aty_contentview;

    @ViewInject(R.id.valuable_detail_aty_dashang)
    private TextView valuable_detail_aty_dashang;

    @ViewInject(R.id.valuable_detail_aty_dashangRecyclerView)
    private RecyclerView valuable_detail_aty_dashangRecyclerView;

    public static void start(Activity activity, int valuableid) {
        Intent intent = new Intent(activity, ValuableDetailActivity.class);
        intent.putExtra(Constant.Valuable_Id, valuableid);
        activity.startActivity(intent);
    }

    private Context context;

    private CompositeDisposable compositeDisposable;

    private int valuableid;

    private boolean isPraise = false;

    private int page = 1;

    private List<WokDetailBean.DataBean.CommentsBean.ListBean> commentList;

    private WorkDetailCommentAdapter adapter;

    private ValuableDetailBeanl.DataBean.ArtcircleBean artcircleBean;
    private ValuableDetailBeanl.DataBean dataBeans;

    private MediaPlayer mediaPlayer;

    private MediaPlayer netmediaPlayer;

    private boolean isFavorite=false;

    private RotateAnimation dieAnimation;
    private RotateAnimation ganAnimation;

    private List<ValuableDetailBeanl.DataBean.RewardUserListBean> dashangLists;
    private ValualbeDetailDaShangAdapter dashangAdapter;
    private List<GiftListModel.DataBean.GiftListBean> giftLists;

    private ValuablePaySucessRecceiver valuablePaySucessRecceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuable_detail);
        ViewUtils.inject(this);
        setTitleTheme(this,true);


        init();

        initView();

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
        valuable_detail_aty_audio_active_img.clearAnimation();
        ganAnimation.cancel();
        valuable_detail_aty_audio_gan_img.clearAnimation();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
        unregisterReceiver(valuablePaySucessRecceiver);
    }

    private void init(){

        context=this;

        compositeDisposable = new CompositeDisposable();

        commentList = new ArrayList<>();

        giftLists = new ArrayList<>();

        dashangLists = new ArrayList<>();

        dashangAdapter = new ValualbeDetailDaShangAdapter(context,R.layout.detail_dashang_listitem,dashangLists);

        adapter = new WorkDetailCommentAdapter(context, commentList);

        if(getIntent()!=null){
            valuableid = getIntent().getIntExtra(Constant.Valuable_Id, 0);
        }

        createRotateAnimation();
        createRotateGanAnimation();

        IntentFilter filter=new IntentFilter(PayBroadCast_Key);
        valuablePaySucessRecceiver = new ValuablePaySucessRecceiver();
        registerReceiver(valuablePaySucessRecceiver,filter);
    }

    private void initView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        valuable_detail_aty_dashangRecyclerView.setLayoutManager(linearLayoutManager);
        valuable_detail_aty_dashangRecyclerView.setAdapter(dashangAdapter);
        valuable_detail_aty_dashangRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
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
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                if(commentList.isEmpty()){
                    return;
                }

                int favorite= commentList.get(position).getIsPraise();

                if(favorite==Constant.NOTFAVORITE){

                    parise(commentList.get(position).getUserId(),commentList.get(position).getId(),Constant.Praise_valuable_comment);
                    commentList.get(position).setIsPraise(Constant.FAVORITE);
                }else{
                    pariseCancle(commentList.get(position).getUserId(),commentList.get(position).getId(),Constant.Praise_valuable_comment);
                    commentList.get(position).setIsPraise(Constant.NOTFAVORITE);
                }


            }

            @Override
            public void onCommentClick(int position) {

                ValuableDetailCommentActivity.start((Activity) context,commentList.get(position).getId(),commentList.get(position).getUserId(),valuableid,commentList.get(position).getContent(),commentList.get(position).getReplyNum());

            }

            @Override
            public void onReplyAt(int position) {

            }
        });

        valuable_detail_aty_scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (valuable_detail_aty_pinglun_input_group.getVisibility() == View.VISIBLE) {
                    HideKeyboard(valuable_detail_aty_group);
                    valuable_detail_aty_pinglun_input_group.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });

    }


    @OnClick({  R.id.valuable_detail_aty_cancle,
            R.id.valuable_detail_aty_input_btn,
            R.id.valuable_detail_aty_pinglun_send,
            R.id.valuable_detail_aty_praise_group,
            R.id.valuable_detail_aty_comment_group,
            R.id.valuable_detail_aty_collect_cb,
            R.id.valuable_detail_aty_listview_loadmore,
            R.id.valuable_detail_aty_contentimg,
            R.id.valuable_detail_aty_dashang,
            R.id.valuable_detail_aty_more})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.valuable_detail_aty_cancle:

                this.finish();

                break;
            case R.id.valuable_detail_aty_input_btn:

                if(dataBeans==null){
                    return;
                }

                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                popinput();


                break;
            case R.id.valuable_detail_aty_pinglun_send:


                String content = valuable_detail_aty_pinglun_input.getText().toString();

                if(!TextUtils.isEmpty(content)){
                    postComment(content);
                    valuable_detail_aty_pinglun_input.setText("");
                    valuable_detail_aty_pinglun_input_group.setVisibility(View.GONE);
                    HideKeyboard(valuable_detail_aty_group);
                }

                break;
            case R.id.valuable_detail_aty_praise_group:


                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                if(artcircleBean==null){
                    return;
                }

                if(isPraise){
                    pariseCancle(artcircleBean.getUserId(),artcircleBean.getId(),Constant.Praise_valuable);
                    isPraise = false;
                    valuable_detail_aty_praise_btn.setActivated(false);
                    if(artcircleBean.getPraiseNum()==0){
                        valuable_detail_aty_praise_num.setText(artcircleBean.getPraiseNum()+"");
                    }else{
                        artcircleBean.setPraiseNum(artcircleBean.getPraiseNum()-1);
                        valuable_detail_aty_praise_num.setText(artcircleBean.getPraiseNum()+"");
                    }
                }else{

                    parise(artcircleBean.getUserId(),artcircleBean.getId(),Constant.Praise_valuable);
                    isPraise = true;
                    valuable_detail_aty_praise_btn.setActivated(true);
                    artcircleBean.setPraiseNum(artcircleBean.getPraiseNum()+1);
                    valuable_detail_aty_praise_num.setText(artcircleBean.getPraiseNum()+"");
                }


                break;
            case R.id.valuable_detail_aty_comment_group:
                valuable_detail_aty_scrollview.post(new Runnable() {
                    @Override
                    public void run() {

                        valuable_detail_aty_scrollview.scrollTo(0,listView.getTop());
                    }
                });

                break;
            case R.id.valuable_detail_aty_collect_cb:


                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));

                    return ;
                }

                if(isFavorite){
                    //取消收藏
                    cancleFavorite(valuableid);

                }else{
                    //收藏
                    favorite(valuableid);
                }

                break;
            case R.id.valuable_detail_aty_listview_loadmore:

                valuable_detail_aty_listview_loadmore.setEnabled(false);
                page++;
                loadPageComment();

                break;
            case R.id.valuable_detail_aty_contentimg:
                if(artcircleBean==null){
                    return ;
                }

                if(TextUtils.equals(artcircleBean.getWorksType(),Constant.PUSH_IMAGE)){


                }
                if(TextUtils.equals(artcircleBean.getWorksType(),Constant.PUSH_AUDIO)){

                    if(netmediaPlayer!=null&&netmediaPlayer.isPlaying()){
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
                        valuable_detail_aty_audio_active_img.clearAnimation();
                        ganAnimation.cancel();
                        valuable_detail_aty_audio_gan_img.clearAnimation();

                        return ;
                    }

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

                    if(!TextUtils.isEmpty(artcircleBean.getPath())){

                        dieAnimation.cancel();
                        valuable_detail_aty_audio_active_img.clearAnimation();

                        ganAnimation.cancel();
                        valuable_detail_aty_audio_gan_img.clearAnimation();

                        valuable_detail_aty_audio_active_img.startAnimation(dieAnimation);

                        valuable_detail_aty_audio_gan_img.startAnimation(ganAnimation);

                        mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(artcircleBean.getPath());
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
                                            valuable_detail_aty_audio_active_img.clearAnimation();

                                            ganAnimation.cancel();
                                            valuable_detail_aty_audio_gan_img.clearAnimation();
                                        }
                                    });
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
                if(TextUtils.equals(artcircleBean.getWorksType(),Constant.PUSH_VIDEO)){


                }

                break;
            case R.id.valuable_detail_aty_dashang:

                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                valuable_detail_aty_dashang.setEnabled(false);

                getGift();

                break;
            case R.id.valuable_detail_aty_more:

                if(artcircleBean==null){
                    return;
                }

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
                        valuable_detail_aty_dashang.setEnabled(true);

                        if(value==null||value.getData()==null){
                            return;
                        }

                        giftLists.clear();
                        giftLists.addAll(value.getData().getGiftList());


                    }

                    @Override
                    public void onError(Throwable e) {
                        valuable_detail_aty_dashang.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void loadPageComment(){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IValuableCommentPageList.class)
                .getValuableList(valuableid,HttpHelp.getUserId(context),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ValuableDetailBeanl>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ValuableDetailBeanl value) {

                        if(value==null||value.getData()==null||value.getData().getComments()==null){
                            if(page>1){
                                page--;
                            }
                            return ;
                        }

                        valuable_detail_aty_listview_loadmore.setEnabled(true);
                        commentList.addAll(value.getData().getComments().getList());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(page>1){
                            page--;
                        }
                        valuable_detail_aty_listview_loadmore.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void loadContent(){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IValuableDetail.class)
                .getValuableDetail(valuableid,HttpHelp.getUserId(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ValuableDetailBeanl>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ValuableDetailBeanl value) {

                        if(value==null){
                            return ;
                        }
                        valuable_detail_aty_loadview.setVisibility(View.GONE);
                        valuable_detail_aty_contentview.setVisibility(View.VISIBLE);
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



    private void fillData(ValuableDetailBeanl.DataBean dataBean){

        if(dataBean==null||dataBean.getArtcircle()==null){
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

        artcircleBean = dataBean.getArtcircle();

        Glide.with(context).load(artcircleBean.getPhoto()).into(valuable_detail_aty_img);
        if(artcircleBean.getUserType()>=2){
            valuable_detail_aty_name.setText(artcircleBean.getRealname());
        }else{
            valuable_detail_aty_name.setText(artcircleBean.getNickname());
        }

        valuable_detail_aty_contenttype.setText(artcircleBean.getContentType());

        valuable_detail_aty_time.setText(TimeShift.getChatTime(artcircleBean.getCreateDate()));

        valuable_detail_aty_title.setText(artcircleBean.getTitle());

        valuable_detail_aty_content.setText(artcircleBean.getContent());

        if(TextUtils.equals(Constant.PUSH_AUDIO,artcircleBean.getWorksType())){
            valuable_detail_aty_contentimg.setVisibility(View.VISIBLE);
            BitmapHelp.setAudioImageView(context,valuable_detail_aty_contentimg);
            BitmapHelp.setAudioRelayout(context,valuable_detail_aty_audio_gorpu);
            valuable_detail_aty_contentimg.setImageResource(R.mipmap.audio_play_bg);
            valuable_detail_aty_audio_time.setText(artcircleBean.getDuration());
            valuable_detail_aty_audio_gorpu.setVisibility(View.VISIBLE);
            valuable_detail_aty_video_gorpu.setVisibility(View.GONE);
        }else if(TextUtils.equals(Constant.PUSH_IMAGE,artcircleBean.getWorksType())){
            valuable_detail_aty_video_gorpu.setVisibility(View.GONE);
            valuable_detail_aty_audio_gorpu.setVisibility(View.GONE);
            valuable_detail_aty_contentimg.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(artcircleBean.getPicProperty())){
                BitmapHelp.setImageView(context,artcircleBean.getPicProperty(),valuable_detail_aty_contentimg);
            }
            Glide.with(context).load(artcircleBean.getCoverImg()).into(valuable_detail_aty_contentimg);
        }else if(TextUtils.equals(Constant.PUSH_VIDEO,artcircleBean.getWorksType())){
            valuable_detail_aty_audio_gorpu.setVisibility(View.GONE);
            valuable_detail_aty_video_gorpu.setVisibility(View.VISIBLE);
            valuable_detail_aty_contentimg.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(artcircleBean.getPicProperty())){
                BitmapHelp.setImageView(context,artcircleBean.getPicProperty(),valuable_detail_aty_contentimg);
            }
            valuable_detail_aty_video_time.setText(artcircleBean.getDuration());
            Glide.with(context).load(artcircleBean.getCoverImg()).into(valuable_detail_aty_contentimg);
        }else{
            valuable_detail_aty_contentimg.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(artcircleBean.getMajorIds())){
            SplitStringColorUtils.addForeColorSpan(context,artcircleBean.getMajorIds().split(","),valuable_detail_aty_worktype);
        }

        valuable_detail_aty_praise_num.setText(artcircleBean.getPraiseNum()+"");
        valuable_detail_aty_comment_num.setText(artcircleBean.getCommentNum()+"");

        if(artcircleBean.getIsPraise()==Constant.NOTFAVORITE){
            isPraise = false;
            valuable_detail_aty_praise_btn.setActivated(false);
        }else{
            isPraise = true;
            valuable_detail_aty_praise_btn.setActivated(true);
        }
        if(artcircleBean.getIsFavorite()==Constant.NOTFAVORITE){
            isFavorite = false;
            valuable_detail_aty_collect_cb.setChecked(false);
        }else{
            isFavorite = true;
            valuable_detail_aty_collect_cb.setChecked(true);
        }


        if(dataBeans.getComments()!=null&&dataBeans.getComments().getList()!=null&&dataBeans.getComments().getList().size()>0){
            listView.setVisibility(View.VISIBLE);
            valuable_detail_aty_listview_empty_group.setVisibility(View.GONE);
            commentList.addAll(dataBeans.getComments().getList());
            adapter.notifyDataSetChanged();
            if(commentList.size()>=3){
                if(!(valuable_detail_aty_listview_loadmore.getVisibility()==View.VISIBLE)){
                    valuable_detail_aty_listview_loadmore.setVisibility(View.VISIBLE);
                }
            }else{
                valuable_detail_aty_listview_loadmore.setVisibility(View.GONE);
            }
        }else{
            listView.setVisibility(View.GONE);
            valuable_detail_aty_listview_loadmore.setVisibility(View.GONE);
            valuable_detail_aty_listview_empty_group.setVisibility(View.VISIBLE);
        }

    }


    private void postComment(String content){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IValuableCommentPost.class)
                .postValuableComment(HttpHelp.getUserId(context),content,valuableid)
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

                        valuable_detail_aty_scrollview.post(new Runnable() {
                            @Override
                            public void run() {

                                valuable_detail_aty_scrollview.scrollTo(0,listView.getTop());
                            }
                        });


                        listView.setVisibility(View.VISIBLE);
                        valuable_detail_aty_listview_empty_group.setVisibility(View.GONE);

                        if(commentList.size()>=3){
                            if(!(valuable_detail_aty_listview_loadmore.getVisibility()==View.VISIBLE)){
                                valuable_detail_aty_listview_loadmore.setVisibility(View.VISIBLE);
                            }
                        }else{
                            valuable_detail_aty_listview_loadmore.setVisibility(View.GONE);
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


    private void favorite(int id){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IFavorite.class)
                .favorite(id,HttpHelp.getUserId(context),Constant.YIKAOQUAN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void cancleFavorite(int id){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(ICancleFavorite.class)
                .cancleFravorite(id,HttpHelp.getUserId(context),Constant.YIKAOQUAN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {

                    }

                    @Override
                    public void onError(Throwable e) {

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
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {
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
                .subscribe(new Observer<BasicSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicSuccessBean value) {
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



    private void popinput(){
        valuable_detail_aty_pinglun_input_group.setVisibility(View.VISIBLE);
        valuable_detail_aty_pinglun_input.setFocusable(true);
        valuable_detail_aty_pinglun_input.setFocusableInTouchMode(true);
        valuable_detail_aty_pinglun_input.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        InputMethodManager imm = (InputMethodManager) valuable_detail_aty_pinglun_input_group.getContext().getSystemService(Context.INPUT_METHOD_SERVICE); imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
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
        dieAnimation.setDuration(10000);
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

    public class ValuablePaySucessRecceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            ValuableDetailBeanl.DataBean.RewardUserListBean rewardUserListBean = new ValuableDetailBeanl.DataBean.RewardUserListBean();

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
