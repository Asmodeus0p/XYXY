package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.view.ui.MyGridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyselfPianhao extends AppCompatActivity {


    @BindView(R.id.usermajor_select_aty_num)
    TextView usermajorSelectAtyNum;
    @BindView(R.id.usermajor_select_aty_unistar_btn)
    TextView usermajorSelectAtyUnistarBtn;
    @BindView(R.id.usermajor_select_aty_bottombar)
    RelativeLayout usermajorSelectAtyBottombar;
    @BindView(R.id.complete_userinfo_aty_cancle)
    TextView completeUserinfoAtyCancle;
    @BindView(R.id.usermajor_select_aty_jump)
    TextView usermajorSelectAtyJump;
    @BindView(R.id.usermajor_select_atygridview)
    MyGridView usermajorSelectAtygridview;
    @BindView(R.id.userschool_select_atygridview)
    MyGridView userschoolSelectAtygridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_pianhao);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.complete_userinfo_aty_cancle)
    public void onViewClicked() {
        finish();
    }
}
