package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IAttention;
import com.jiyun.asmodeus.xyxy.model.biz.IAttentionCancle;
import com.jiyun.asmodeus.xyxy.model.biz.IPersonDetail;
import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.PersonDetailBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.LoginActivity;
import com.jiyun.asmodeus.xyxy.view.ui.CustomViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.makeramen.roundedimageview.RoundedImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonInfoActivity extends BaselivstActivity {

    private static final int ZUOPIN = 0;

    private static final int TIZI = 1;


    @ViewInject(R.id.persion_info_aty_userimg)
    private RoundedImageView userimg;

    @ViewInject(R.id.persion_info_aty_username)
    private TextView name;

    @ViewInject(R.id.persion_info_aty_guanzhunum)
    private TextView guanzhunum;

    @ViewInject(R.id.persion_info_aty_fensinum)
    private TextView fensinum;

    @ViewInject(R.id.persion_info_aty_sort_zuopin_tv)
    private TextView persion_info_aty_sort_zuopin_tv;

    @ViewInject(R.id.persion_info_aty_sort_zuopin_line)
    private TextView persion_info_aty_sort_zuopin_line;

    @ViewInject(R.id.persion_info_aty_sort_tiezi_tv)
    private TextView persion_info_aty_sort_tiezi_tv;

    @ViewInject(R.id.persion_info_aty_sort_tiezi_line)
    private TextView persion_info_aty_sort_tiezi_line;

    @ViewInject(R.id.persion_info_aty_viewpager)
    private CustomViewPager viewpager;

    @ViewInject(R.id.person_info_aty_attention)
    private TextView person_info_aty_attention;


    public static void start(Activity activity, int id) {
        Intent intent = new Intent(activity, PersonInfoActivity.class);
        intent.putExtra(Constant.UserId, id);
        activity.startActivity(intent);
    }


    /**
     *
     * 选中集
     */
    private List<TextView> sortBars_tv;

    private List<TextView> sortBars_line;

    private List<Fragment> fragments;

    private Context context;

    private int sortord;

    private int id;

    private CompositeDisposable compositeDisposable;

    private boolean isAttention = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ViewUtils.inject(this);
        setTitleTheme(this,true);

        init();

        initViewPager();

        loadContent();

        setViewPagerScrollSpeed();

        fillArrayList();

        setEnable(ZUOPIN);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }

    private void init(){

        context=this;

        compositeDisposable = new CompositeDisposable();

        sortBars_tv = new ArrayList<>();

        sortBars_line = new ArrayList<>();

        fragments = new ArrayList<>();

        if(getIntent()!=null){
            id = getIntent().getIntExtra(Constant.UserId, 0);
        }
    }

    private void initViewPager() {

        fragments = new ArrayList<>();

        Fragment persionwok = new PersionInfoWokFragment();

        fragments.add(persionwok);

        Fragment persiontiezi = new PersonInfoTieZiFragment();

        fragments.add(persiontiezi);

        viewpager.setAdapter(new ShowInfosPagerAdapter(getSupportFragmentManager(), fragments));

        viewpager.setOffscreenPageLimit(fragments.size());

    }

    private void fillArrayList(){
        sortBars_tv.add(persion_info_aty_sort_zuopin_tv);
        sortBars_tv.add(persion_info_aty_sort_tiezi_tv);

        sortBars_line.add(persion_info_aty_sort_zuopin_line);
        sortBars_line.add(persion_info_aty_sort_tiezi_line);
    }



    @OnClick({R.id.person_info_aty_cancle,R.id.persion_info_aty_sort_zuopin_group,R.id.persion_info_aty_sort_tiezi_group,R.id.person_info_aty_attention})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.person_info_aty_cancle:

                this.finish();
                break;
            case R.id.persion_info_aty_sort_zuopin_group:

                if(sortord!=ZUOPIN){
                    setEnable(ZUOPIN);
                }
                break;
            case R.id.persion_info_aty_sort_tiezi_group:

                if(sortord!=TIZI){
                    setEnable(TIZI);
                }

                break;
            case R.id.person_info_aty_attention:


                if(!HttpHelp.isLogin(context)){
                    startActivity(new Intent(context, LoginActivity.class));
                    return ;
                }

                if(isAttention){
                    attentionCancle(id);
                    isAttention = false;
                    person_info_aty_attention.setText("关注");
                    person_info_aty_attention.setActivated(true);
                }else{
                    attention(id);
                    isAttention = true;
                    person_info_aty_attention.setText("已关注");
                    person_info_aty_attention.setActivated(false);
                }

                break;
            default:
                break;
        }
    }




    private void attention(int userId){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IAttention.class)
                .postAttention(userId,HttpHelp.getUserId(context))
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


    private void attentionCancle(int userId){

        HttpHelp.baseHttpRequest(context,Constant.Root_url)
                .create(IAttentionCancle.class)
                .postAttention(userId,HttpHelp.getUserId(context))
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


    private void loadContent(){

        HttpHelp.baseHttpRequest(context, Constant.Root_url)
                .create(IPersonDetail.class)
                .getPersonDetail(id,HttpHelp.getUserId(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PersonDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PersonDetailBean value) {

                        if(value==null||value.getData()==null||value.getData().getUserInfo()==null){

                            return ;
                        }

                        fillData(value.getData().getUserInfo());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private void fillData(PersonDetailBean.DataBean.UserInfoBean userInfoBean){


        Glide.with(context).load(userInfoBean.getPhoto()).into(userimg);
        name.setText(userInfoBean.getNickname());
        guanzhunum.setText(userInfoBean.getAttentionCount()+"");

        fensinum.setText(userInfoBean.getFansCount()+"");


        if(userInfoBean.getAttention()==Constant.Attention){
            person_info_aty_attention.setText("关注");
            person_info_aty_attention.setActivated(true);
            isAttention = false;
        }else if(userInfoBean.getAttention()==Constant.Attention_yiguanzhu){
            person_info_aty_attention.setText("已关注");
            person_info_aty_attention.setActivated(false);
            isAttention = true;
        }else if(userInfoBean.getAttention()==Constant.Attention_xianghu){
            person_info_aty_attention.setText("相互关注");
            person_info_aty_attention.setActivated(false);
            isAttention = true;
        }

    }

    private void setEnable(int position) {
        sortord = position;
        for (int i = 0; i <sortBars_tv.size() ; i++) {
            sortBars_tv.get(i).setTextAppearance(context,R.style.home_work_sorbar_enabled_true_style);
            sortBars_line.get(i).setVisibility(View.INVISIBLE);
        }

        sortBars_line.get(position).setVisibility(View.VISIBLE);
        viewpager.setCurrentItem(position);
    }


    private void setViewPagerScrollSpeed(){
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);

        }catch(NoSuchFieldException e){

        }catch (IllegalArgumentException e){

        }
    }



    class ShowInfosPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> list;

        public ShowInfosPagerAdapter(FragmentManager fm,
                                     List<Fragment> fragments) {
            super(fm);
            this.list = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public int getCount() {
            return list.size();
        }

    }

    public int getId(){
        return this.id;
    }
}
