package com.jiyun.asmodeus.xyxy.view.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.FavoritesAty;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.FensActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.GiftActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.GuanZhuActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.MessageActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.MyselfPianhao;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.MyselfRenZheng;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.SettingActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.StudentDingdanActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.TieZiActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity.VoucherCenterActivity;
import com.jiyun.asmodeus.xyxy.view.ui.CImageView;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyselfFragment extends BaseFragment implements View.OnClickListener {


    private ImageView home_myselft_fragment_message;
    private ImageView home_myselft_fragment_message_newimg;
    private RelativeLayout home_myself_message;
    private ImageView home_myselft_fragment_setting;
    private CImageView home_myselft_notlogin_img;
    private RelativeLayout home_myselft_fragment_nologin_head;
    private RoundedImageView home_myself_fragment_userimg;
    private TextView home_myself_fragment_username;
    private LinearLayout home_myself_fragment_userinfo_group;
    private LinearLayout home_myselft_fragment_livebtn;
    private RelativeLayout home_myselft_fragment_login_head;
    private Button home_myselft_register_btn;
    private Button home_myselft_login_btn;
    private LinearLayout home_myselft_fragment_nologin_body;
    private TextView home_myselft_fragment_homewokCount_tv;
    private TextView home_myselft_fragment_homewokCount_status;
    private LinearLayout home_myselft_fragment_homewokCount_group;
    private TextView home_myselft_fragment_artcircleCount_tv;
    private LinearLayout home_myselft_fragment_tiezi_group;
    private TextView home_myselft_fragment_attentionCount_tv;
    private LinearLayout home_myselft_fragment_guanzhu_group;
    private TextView home_myselft_fragment_fansCount_tv;
    private LinearLayout home_myselft_fragment_fensi_group;
    private LinearLayout home_myselft_fragment_student_fukuan;
    private LinearLayout home_myselft_fragment_student_shiyong;
    private LinearLayout home_myselft_fragment_student_tuiguo;
    private LinearLayout home_myselft_fragment_student_dingdan;
    private LinearLayout home_myselft_fragment_student_toolline;
    private TextView home_myself_fragment_jindou_tv;
    private RelativeLayout home_myself_fragment_jindou_group;
    private RelativeLayout home_myselft_fragment_havegift_group;
    private RelativeLayout home_myselft_fragment_favorites;
    private RelativeLayout home_myself_fragment_selectmajor;
    private TextView home_myself_fragment_isauth_tv;
    private RelativeLayout home_myselft_fragment_approve;
    private LinearLayout home_myselft_fragment_login_body;
    private String nickname;
    private String photo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myself;
    }



    @Override
    protected void init() {

        home_myselft_fragment_message = (ImageView) getView().findViewById(R.id.home_myselft_fragment_message);
        home_myselft_fragment_message_newimg = (ImageView) getView().findViewById(R.id.home_myselft_fragment_message_newimg);
        home_myself_message = (RelativeLayout) getView().findViewById(R.id.home_myself_message);
        home_myselft_fragment_setting = (ImageView) getView().findViewById(R.id.home_myselft_fragment_setting);
        home_myselft_notlogin_img = (CImageView) getView().findViewById(R.id.home_myselft_notlogin_img);
        home_myselft_fragment_nologin_head = (RelativeLayout) getView().findViewById(R.id.home_myselft_fragment_nologin_head);
        home_myself_fragment_userimg = (RoundedImageView) getView().findViewById(R.id.home_myself_fragment_userimg);
        home_myself_fragment_username = (TextView) getView().findViewById(R.id.home_myself_fragment_username);
        home_myself_fragment_userinfo_group = (LinearLayout) getView().findViewById(R.id.home_myself_fragment_userinfo_group);
        home_myselft_fragment_livebtn = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_livebtn);
        home_myselft_fragment_login_head = (RelativeLayout) getView().findViewById(R.id.home_myselft_fragment_login_head);
        home_myselft_register_btn = (Button) getView().findViewById(R.id.home_myselft_register_btn);
        home_myselft_login_btn = (Button) getView().findViewById(R.id.home_myselft_login_btn);
        home_myselft_fragment_nologin_body = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_nologin_body);
        home_myselft_fragment_homewokCount_tv = (TextView) getView().findViewById(R.id.home_myselft_fragment_homewokCount_tv);
        home_myselft_fragment_homewokCount_status = (TextView) getView().findViewById(R.id.home_myselft_fragment_homewokCount_status);
        home_myselft_fragment_homewokCount_group = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_homewokCount_group);
        home_myselft_fragment_artcircleCount_tv = (TextView) getView().findViewById(R.id.home_myselft_fragment_artcircleCount_tv);
        home_myselft_fragment_tiezi_group = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_tiezi_group);
        home_myselft_fragment_attentionCount_tv = (TextView) getView().findViewById(R.id.home_myselft_fragment_attentionCount_tv);
        home_myselft_fragment_guanzhu_group = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_guanzhu_group);
        home_myselft_fragment_fansCount_tv = (TextView) getView().findViewById(R.id.home_myselft_fragment_fansCount_tv);
        home_myselft_fragment_fensi_group = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_fensi_group);
        home_myselft_fragment_student_fukuan = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_student_fukuan);
        home_myselft_fragment_student_shiyong = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_student_shiyong);
        home_myselft_fragment_student_tuiguo = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_student_tuiguo);
        home_myselft_fragment_student_dingdan = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_student_dingdan);
        home_myselft_fragment_student_toolline = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_student_toolline);
        home_myself_fragment_jindou_tv = (TextView) getView().findViewById(R.id.home_myself_fragment_jindou_tv);
        home_myself_fragment_jindou_group = (RelativeLayout) getView().findViewById(R.id.home_myself_fragment_jindou_group);
        home_myselft_fragment_havegift_group = (RelativeLayout) getView().findViewById(R.id.home_myselft_fragment_havegift_group);
        home_myselft_fragment_favorites = (RelativeLayout) getView().findViewById(R.id.home_myselft_fragment_favorites);
        home_myself_fragment_selectmajor = (RelativeLayout) getView().findViewById(R.id.home_myself_fragment_selectmajor);
        home_myself_fragment_isauth_tv = (TextView) getView().findViewById(R.id.home_myself_fragment_isauth_tv);
        home_myselft_fragment_approve = (RelativeLayout) getView().findViewById(R.id.home_myselft_fragment_approve);
        home_myselft_fragment_login_body = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_login_body);
        home_myselft_fragment_approve.setOnClickListener(this);
        home_myselft_register_btn.setOnClickListener(this);
        home_myselft_login_btn.setOnClickListener(this);
        home_myselft_fragment_tiezi_group.setOnClickListener(this);
        home_myselft_fragment_guanzhu_group.setOnClickListener(this);
        home_myselft_fragment_fansCount_tv.setOnClickListener(this);
        home_myselft_fragment_student_fukuan.setOnClickListener(this);
        home_myselft_fragment_fensi_group.setOnClickListener(this);
        home_myself_fragment_userinfo_group.setOnClickListener(this);
        home_myselft_fragment_student_dingdan.setOnClickListener(this);
        home_myself_fragment_jindou_group.setOnClickListener(this);
        home_myselft_fragment_havegift_group.setOnClickListener(this);
        home_myselft_fragment_setting.setOnClickListener(this);
        home_myselft_fragment_message.setOnClickListener(this);
        home_myself_fragment_userinfo_group.setOnClickListener(this);
        home_myselft_fragment_student_shiyong.setOnClickListener(this);
        home_myselft_fragment_student_tuiguo.setOnClickListener(this);
        home_myselft_fragment_favorites.setOnClickListener(this);
        home_myself_fragment_selectmajor.setOnClickListener(this);
        //userStatus();

        checkLogin();
    }

    @Override
    protected void loadDatas() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_myselft_login_btn:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,10);
            break;
            case R.id.home_myselft_register_btn:
            startActivity(new Intent(getActivity(),RegistActivity.class));
            break;
            case R.id.home_myselft_fragment_tiezi_group:
                startActivity(new Intent(getActivity(),TieZiActivity.class));
                break;
            case R.id.home_myselft_fragment_guanzhu_group:
                startActivity(new Intent(getActivity(),GuanZhuActivity.class));
                break;
            case R.id.home_myselft_fragment_fensi_group:
                startActivity(new Intent(getActivity(), FensActivity.class));
                break;
            case R.id.home_myselft_fragment_student_fukuan:
                startActivity(new Intent(getActivity(),StudentDingdanActivity.class));
                break;
            case R.id.home_myselft_fragment_student_shiyong:
                startActivity(new Intent(getActivity(),StudentDingdanActivity.class));
                break;
            case R.id.home_myselft_fragment_student_tuiguo:
                startActivity(new Intent(getActivity(),StudentDingdanActivity.class));
                break;
            case R.id.home_myself_fragment_userinfo_group:
               startActivity(new Intent(getActivity(),UserInfoActivity.class));
                break;
            case R.id.home_myselft_fragment_student_dingdan:
                startActivity(new Intent(getActivity(),StudentDingdanActivity.class));
                break;

            case R.id.home_myself_fragment_jindou_group:
                String userMobile = SharedPreferencesUtils.getUserMobile(getContext());
                Intent jindouIntent = new Intent(getActivity(), VoucherCenterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mobile",userMobile);
                    jindouIntent.putExtras(bundle);
                    startActivity(jindouIntent);
                break;
            case R.id.home_myselft_fragment_havegift_group:
                startActivity(new Intent(getActivity(), GiftActivity.class));
                break;
            case R.id.home_myselft_fragment_setting:
                if (SharedPreferencesUtils.isLogin(getActivity())){
                String userMobile1 = SharedPreferencesUtils.getUserMobile(getContext());
                Intent bing = new Intent(getActivity(), SettingActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("bingPhoneNum",userMobile1);
                bing.putExtras(bundle1);
                startActivity(bing);
                }else {
                    checkLogin();
                }
                break;
            case R.id.home_myselft_fragment_message:
                if (SharedPreferencesUtils.isLogin(getActivity())){
                    startActivity(new Intent(getActivity(), MessageActivity.class));
                }else {
                    checkLogin();
                }

                break;
            case R.id.home_myselft_fragment_favorites:
                startActivity(new Intent(getContext(),FavoritesAty.class));
                break;
            case R.id.home_myself_fragment_selectmajor:
                startActivity(new Intent(getContext(), MyselfPianhao.class));

                break;
            case R.id.home_myselft_fragment_approve:

                startActivity(new Intent(getContext(),MyselfRenZheng.class));
                break;




        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10&&resultCode==10){
            nickname= data.getStringExtra("nickname");
            photo = data.getStringExtra("photo");
            home_myself_fragment_username.setText(nickname);
            Glide.with(getContext()).load(photo).into(home_myself_fragment_userimg);
                home_myselft_fragment_login_head.setVisibility(View.VISIBLE);
                home_myselft_fragment_login_body.setVisibility(View.VISIBLE);
                home_myselft_fragment_nologin_body.setVisibility(View.GONE);
                home_myselft_fragment_nologin_head.setVisibility(View.GONE);
                home_myselft_fragment_student_toolline.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();


    }

    private void checkLogin() {
        //userStatus();
        boolean login = SharedPreferencesUtils.isLogin(getContext());

        if (login==false){
            //未登录
            home_myselft_fragment_login_head.setVisibility(View.GONE);
            home_myselft_fragment_nologin_head.setVisibility(View.VISIBLE);
            home_myselft_fragment_login_body.setVisibility(View.GONE);
            home_myselft_fragment_nologin_body.setVisibility(View.VISIBLE);
        }else {
            //登录
            home_myselft_fragment_nologin_body.setVisibility(View.GONE);
            home_myselft_fragment_login_head.setVisibility(View.VISIBLE);
            home_myselft_fragment_nologin_head.setVisibility(View.GONE);
            home_myselft_fragment_login_body.setVisibility(View.VISIBLE);
            home_myselft_fragment_student_toolline.setVisibility(View.VISIBLE);
            String name = SharedPreferencesUtils.getUserInfo(getContext());
            home_myself_fragment_username.setText(name);
            String url = SharedPreferencesUtils.getUserInfoUrl(getContext());
            Glide.with(getContext()).load(url).into(home_myself_fragment_userimg);
        }
    }


}
