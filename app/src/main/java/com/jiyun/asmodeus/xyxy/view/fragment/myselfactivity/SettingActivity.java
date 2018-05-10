package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.CleanMessageUtil;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.view.fragment.LoginActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.MyselfFragment;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView setting_back;
    private TextView setting_aty_phone_tv;
    private RelativeLayout setting_aty_changephone_group;
    private RelativeLayout setting_aty_bind_group;
    private RelativeLayout setting_aty_pass_group;
    private TextView setting_glide_cahce_tv;
    private RelativeLayout setting_aty_about_group;
    private RelativeLayout setting_aty_tuichu_group;
    private String bingPhoneNum;
    private String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initDatas();
    }

    private void initDatas() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bingPhoneNum = bundle.getString("bingPhoneNum");
        setting_aty_phone_tv.setText(bingPhoneNum);
    }

    private void initView() {
        setting_back = (TextView) findViewById(R.id.setting_back);
        setting_aty_phone_tv = (TextView) findViewById(R.id.setting_aty_phone_tv);
        setting_aty_changephone_group = (RelativeLayout) findViewById(R.id.setting_aty_changephone_group);
        setting_aty_bind_group = (RelativeLayout) findViewById(R.id.setting_aty_bind_group);
        setting_aty_pass_group = (RelativeLayout) findViewById(R.id.setting_aty_pass_group);
        setting_glide_cahce_tv = (TextView) findViewById(R.id.setting_glide_cahce_tv);
        setting_aty_about_group = (RelativeLayout) findViewById(R.id.setting_aty_about_group);
        setting_aty_tuichu_group = (RelativeLayout) findViewById(R.id.setting_aty_tuichu_group);
        setting_back.setOnClickListener(this);
        setting_aty_changephone_group.setOnClickListener(this);
        setting_aty_bind_group.setOnClickListener(this);
        setting_aty_pass_group.setOnClickListener(this);
        setting_aty_about_group.setOnClickListener(this);
        setting_aty_tuichu_group.setOnClickListener(this);
        setting_glide_cahce_tv.setOnClickListener(this);
        try {
            size = CleanMessageUtil.getTotalCacheSize(getApplicationContext());
            setting_glide_cahce_tv.setText(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.setting_back:
               finish();
               break;
            case R.id.setting_aty_changephone_group:
                Intent settingNum = new Intent(this,ChangePhoneNumActivity.class);
                settingNum.putExtra("number", bingPhoneNum);
                startActivity(settingNum);
                break;
            case R.id.setting_glide_cahce_tv:
                CleanMessageUtil.clearAllCache(getApplicationContext());
                try {
                    String cacheSize = CleanMessageUtil.getTotalCacheSize(getApplicationContext());
                    setting_glide_cahce_tv.setText(cacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.setting_aty_tuichu_group:
                SharedPreferencesUtils.loginOut(getApplicationContext());
                startActivity(new Intent(SettingActivity.this, MyselfFragment.class));
                finish();
                break;

        }
    }
}
