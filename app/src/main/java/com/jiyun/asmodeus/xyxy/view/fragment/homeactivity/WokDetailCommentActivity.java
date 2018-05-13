package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IPraise;
import com.jiyun.asmodeus.xyxy.model.biz.IPraiseCancle;
import com.jiyun.asmodeus.xyxy.model.biz.IWorkCommentReplyPost;
import com.jiyun.asmodeus.xyxy.model.biz.IWorkReplyList;
import com.jiyun.asmodeus.xyxy.model.entity.BasicCommentSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.BasicCommentSuccessModel;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.WokDetailBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkReplyListBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.view.adapter.WorkDetailCommentAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.LoginActivity;
import com.jiyun.asmodeus.xyxy.view.ui.MyScrollView;
import com.jiyun.asmodeus.xyxy.view.ui.ScrollLoadMoreList;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WokDetailCommentActivity extends BaselivstActivity {

    /**
     * 加载任务已完成
     */
    private static final int LOADED=1;

    /**
     * 加载任务正在进行
     */
    private static final int LOADING=2;

    /**
     * 当前的加载状态  pull_up
     */
    private int pull_up_load_state;

    @ViewInject(R.id.wok_detail_comment_aty_group)
    private LinearLayout wok_detail_comment_aty_group;

    @ViewInject(R.id.wok_detail_comment_aty_title_tv)
    private TextView wok_detail_comment_aty_title_tv;

    @ViewInject(R.id.wok_detail_comment_aty_scrollview)
    private MyScrollView wok_detail_comment_aty_scrollview;

    @ViewInject(R.id.wok_detail_comment_aty_loadmore)
    private ScrollLoadMoreList loadMoreList;

    @ViewInject(R.id.wok_detail_comment_aty_listview_load_more)
    private FrameLayout wok_detail_comment_aty_listview_load_more;

    @ViewInject(R.id.wok_detail_comment_aty_empty)
    private RelativeLayout wok_detail_comment_aty_empty;

    @ViewInject(R.id.wok_detail_comment_aty_pinglun_input)
    private EditText wok_detail_comment_aty_pinglun_input;


    //当前回复的主评论
    @ViewInject(R.id.wok_detail_comment_aty_img)
    private RoundedImageView wok_detail_comment_aty_img;

    @ViewInject(R.id.wok_detail_comment_aty_name)
    private TextView wok_detail_comment_aty_name;

    @ViewInject(R.id.wok_detail_comment_aty_usertype)
    private ImageView wok_detail_comment_aty_usertype;

    @ViewInject(R.id.wok_detail_comment_aty_time)
    private TextView wok_detail_comment_aty_time;

    @ViewInject(R.id.wok_detail_comment_aty_content)
    private TextView wok_detail_comment_aty_content;

    @ViewInject(R.id.wok_detail_comment_aty_praise_cb)
    private CheckBox wok_detail_comment_aty_praise_cb;

    public static void start(Activity activity, int commentId, int userId, int workId, String tContent, int replyNum) {
        Intent intent = new Intent(activity, WokDetailCommentActivity.class);
        intent.putExtra(Constant.Comment_Id, commentId);
        intent.putExtra(Constant.UserId, userId);
        intent.putExtra(Constant.Work_Id, workId);
        intent.putExtra(Constant.Comment_content, tContent);
        intent.putExtra(Constant.Comment_num, replyNum);
        activity.startActivity(intent);
    }

    private Context context;

    private CompositeDisposable compositeDisposable;

    private List<WokDetailBean.DataBean.CommentsBean.ListBean> commentList;

    private WorkDetailCommentAdapter adapter;

    private int page = 1;

    private int userId;

    private int commentId;

    private int workId;

    private String tContent;

    private WorkReplyListBean.DataBean.CommentBean commentBean;

    private int replyId;

    private int replyNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wok_detail_comment);
        ViewUtils.inject(this);
        setTitleTheme(this,true);


        init();

        initView();

        upLoad();

        loadContent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void init() {

        context=this;

        compositeDisposable = new CompositeDisposable();

        commentList = new ArrayList<>();

        adapter = new WorkDetailCommentAdapter(context, commentList);

        if(getIntent()!=null){
            userId = getIntent().getIntExtra(Constant.UserId, 0);
            commentId = getIntent().getIntExtra(Constant.Comment_Id, 0);
            workId = getIntent().getIntExtra(Constant.Work_Id, 0);
            tContent = getIntent().getStringExtra(Constant.Comment_content);
            replyNum = getIntent().getIntExtra(Constant.Comment_num, 0);
        }
    }

    private void initView(){

        if(replyNum!=0){
            wok_detail_comment_aty_title_tv.setText(String.format("%s条评论",replyNum));
        }

        loadMoreList.setAdapter(adapter);

        adapter.setOnItemShareClick(new WorkDetailCommentAdapter.OnItemShareClick() {
            @Override
            public void onPraiseClick(int position) {

                if(commentList.isEmpty()){
                    return;
                }

                int favorite= commentList.get(position).getIsPraise();

                if(favorite==Constant.NOTFAVORITE){
                    parise(commentList.get(position).getUserId(),commentList.get(position).getId(),Constant.Praise_work_huifu);
                    commentList.get(position).setIsPraise(Constant.FAVORITE);
                }else{
                    pariseCancle(commentList.get(position).getUserId(),commentList.get(position).getId(),Constant.Praise_work_huifu);
                    commentList.get(position).setIsPraise(Constant.NOTFAVORITE);
                }
            }

            @Override
            public void onCommentClick(int position) {
                wok_detail_comment_aty_pinglun_input.setHint(String.format("@%s",commentList.get(position).getNickname()));
                popinput();
                replyId = commentList.get(position).getUserId();
            }

            @Override
            public void onReplyAt(int position) {

            }
        });

    }

    @OnClick({R.id.wok_detail_comment_aty_cancle,R.id.wok_detail_comment_aty_pinglun_send,
            R.id.wok_detail_comment_aty_praise_cb})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.wok_detail_comment_aty_cancle:

                this.finish();

                break;
            case R.id.wok_detail_comment_aty_pinglun_send:

                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                String content = wok_detail_comment_aty_pinglun_input.getText().toString();

                if(!TextUtils.isEmpty(content)){
                    wok_detail_comment_aty_pinglun_input.setText("");
                    postComment(content,replyId);
                    replyId = 0;
                    HideKeyboard(wok_detail_comment_aty_group);
                }

                break;
            case R.id.wok_detail_comment_aty_praise_cb:

                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                if(commentBean==null){
                    return;
                }

                int favorite= commentBean.getIsPraise();

                if(favorite==Constant.NOTFAVORITE){

                    parise(commentBean.getUserId(),commentBean.getId(),Constant.Praise_work_comment);
                    commentBean.setIsPraise(Constant.FAVORITE);
                    wok_detail_comment_aty_praise_cb.setText((commentBean.getPraiseNum()+1)+"");
                }else{
                    pariseCancle(commentBean.getUserId(),commentBean.getId(),Constant.Praise_work_comment);
                    commentBean.setIsPraise(Constant.NOTFAVORITE);
                    if(commentBean.getPraiseNum()==0){
                        wok_detail_comment_aty_praise_cb.setText(commentBean.getPraiseNum()+"");
                    }else{
                        wok_detail_comment_aty_praise_cb.setText((commentBean.getPraiseNum()-1)+"");
                    }
                }


                break;
            default:
                break;
        }
    }


    /**
     * 上拉加载
     */
    private void upLoad(){
        loadMoreList.setLoadView(wok_detail_comment_aty_scrollview,wok_detail_comment_aty_listview_load_more);
        loadMoreList.setLoadMoreListen(new ScrollLoadMoreList.OnLoadMore() {
            @Override
            public void loadMore() {

                if (pull_up_load_state == LOADING){

                    return;
                }

                pull_up_load_state = LOADING;

                page++;

                loadContent();
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


    private void postComment(String content,int replyId){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IWorkCommentReplyPost.class)
                .postWorkComment(HttpHelp.getUserId(context),content,workId,commentId,userId,tContent,replyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BasicCommentSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BasicCommentSuccessBean value) {

                        wok_detail_comment_aty_pinglun_input.setHint("说点什么...");

                        if(value==null||value.getData()==null||value.getData()==null){
                            Toast.makeText(context,"评论失败",Toast.LENGTH_SHORT).show();
                        }

                        commentList.add(0,value.getData());
                        adapter.notifyDataSetChanged();

                        wok_detail_comment_aty_scrollview.post(new Runnable() {
                            @Override
                            public void run() {

                                wok_detail_comment_aty_scrollview.scrollTo(0,loadMoreList.getTop());
                            }
                        });


                        loadMoreList.setVisibility(View.VISIBLE);
                        wok_detail_comment_aty_empty.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Throwable e) {

                        wok_detail_comment_aty_pinglun_input.setHint("说点什么...");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private void loadContent(){

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IWorkReplyList.class)
                .getWorkReplyList(userId,commentId,workId,HttpHelp.getUserId(context),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WorkReplyListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WorkReplyListBean value) {
                        if(commentBean==null) {
                            if (value != null && value.getData() != null && value.getData().getComment() != null) {
                                commentBean = value.getData().getComment();
                                Glide.with(context).load(commentBean.getPhoto()).into(wok_detail_comment_aty_img);
                                wok_detail_comment_aty_name.setText(commentBean.getNickname());
                                SplitStringColorUtils.setImgLevel(wok_detail_comment_aty_usertype, commentBean.getUserType());
                                wok_detail_comment_aty_time.setText(TimeShift.getChatTime(commentBean.getCreateDate()));
                                wok_detail_comment_aty_content.setText(commentBean.getContent());
                                if (Constant.NOTFAVORITE == commentBean.getIsPraise()) {
                                    wok_detail_comment_aty_praise_cb.setChecked(false);
                                } else {
                                    wok_detail_comment_aty_praise_cb.setChecked(true);
                                }
                                wok_detail_comment_aty_praise_cb.setText(commentBean.getPraiseNum() + "");
                            }

                        }

                        if(page==1&&(value==null||value.getData()==null||value.getData().getComments()==null||value.getData().getComments().getList()==null||value.getData().getComments().getList().size()<=0)){

                            loadMoreList.setVisibility(View.GONE);
                            wok_detail_comment_aty_empty.setVisibility(View.VISIBLE);
                            resetState();
                            return ;
                        }

                        if(page>1&&(value==null||value.getData()==null||value.getData().getComments()==null||value.getData().getComments().getList()==null||value.getData().getComments().getList().size()<=0)){
                            resetState();
                            loadMoreList.onLoadEnd();
                            page--;
                            return ;
                        }

                        loadMoreList.setVisibility(View.VISIBLE);
                        wok_detail_comment_aty_empty.setVisibility(View.GONE);

                        commentList.addAll(value.getData().getComments().getList());
                        adapter.notifyDataSetChanged();

                        resetState();

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(page==1){
                            loadMoreList.setVisibility(View.GONE);
                            wok_detail_comment_aty_empty.setVisibility(View.VISIBLE);
                        }
                        resetState();

                        if(page>1){

                            page--;
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }


    private void resetState(){

        if(pull_up_load_state==LOADING){

            loadMoreList.onLoadComplete();
        }

        pull_up_load_state=LOADED;

    }

    private void popinput(){
        wok_detail_comment_aty_pinglun_input.setFocusable(true);
        wok_detail_comment_aty_pinglun_input.setFocusableInTouchMode(true);
        wok_detail_comment_aty_pinglun_input.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        InputMethodManager imm = (InputMethodManager) wok_detail_comment_aty_pinglun_input.getContext().getSystemService(Context.INPUT_METHOD_SERVICE); imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    public void HideKeyboard(View v){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        InputMethodManager imm = ( InputMethodManager) v.getContext( ).getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken() , 0 );
        }
    }
}
