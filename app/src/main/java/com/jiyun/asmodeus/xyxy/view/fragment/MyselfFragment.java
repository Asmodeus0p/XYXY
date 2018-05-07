package com.jiyun.asmodeus.xyxy.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;
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
    private LinearLayout home_myselft_fragment_teacher_daizhibo;
    private LinearLayout home_myselft_fragment_teacher_daizuoye;
    private LinearLayout home_myselft_fragment_teacher_daifudao;
    private LinearLayout home_myselft_fragment_teacher_jiaoxue;
    private LinearLayout home_myselft_fragment_teacher_toolline;
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
        home_myselft_fragment_teacher_daizhibo = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_teacher_daizhibo);
        home_myselft_fragment_teacher_daizuoye = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_teacher_daizuoye);
        home_myselft_fragment_teacher_daifudao = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_teacher_daifudao);
        home_myselft_fragment_teacher_jiaoxue = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_teacher_jiaoxue);
        home_myselft_fragment_teacher_toolline = (LinearLayout) getView().findViewById(R.id.home_myselft_fragment_teacher_toolline);
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

        home_myselft_register_btn.setOnClickListener(this);
        home_myselft_login_btn.setOnClickListener(this);
      //  userStatus();

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
        }

    }
//    private void userStatus(){
//
//        if(SharedPreferencesUtils.isLogin(getContext())){
//            登录
//            home_myselft_fragment_nologin_body.setVisibility(View.GONE);
//            home_myselft_fragment_login_head.setVisibility(View.VISIBLE);
//            home_myselft_fragment_nologin_head.setVisibility(View.GONE);
//            home_myselft_fragment_login_body.setVisibility(View.VISIBLE);
//        }else{
//           未登录
//            home_myselft_fragment_login_head.setVisibility(View.GONE);
//            home_myselft_fragment_nologin_head.setVisibility(View.VISIBLE);
//            home_myselft_fragment_login_body.setVisibility(View.GONE);
//            home_myselft_fragment_nologin_body.setVisibility(View.VISIBLE);
//        }
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10&&resultCode==10){
            String nickname = data.getStringExtra("nickname");
            String photo = data.getStringExtra("photo");
            if (nickname!=null){
                home_myself_fragment_username.setText(nickname);
                Glide.with(getContext()).load(photo).into(home_myself_fragment_userimg);
                home_myselft_fragment_login_head.setVisibility(View.VISIBLE);
                home_myselft_fragment_login_body.setVisibility(View.VISIBLE);
                home_myselft_fragment_nologin_body.setVisibility(View.GONE);
                home_myselft_fragment_nologin_head.setVisibility(View.GONE);
                home_myselft_fragment_student_toolline.setVisibility(View.VISIBLE);
            }
        }
    }
}
