package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;

public class ChangePhoneNumActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView change_phone_aty_cancle;
    private TextView change_phone_aty_mobile_tv;
    private EditText change_phone_aty_getcode_et;
    private TextView change_phone_aty_getcode_reset;
    private ImageView change_phone_aty_getcode_reset_clear;
    private Button change_phone_aty_loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_num);
        initView();
        Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        change_phone_aty_mobile_tv.setText("手机号: "+number);
    }

    private void initView() {
        change_phone_aty_cancle = (TextView) findViewById(R.id.change_phone_aty_cancle);
        change_phone_aty_mobile_tv = (TextView) findViewById(R.id.change_phone_aty_mobile_tv);
        change_phone_aty_getcode_et = (EditText) findViewById(R.id.change_phone_aty_getcode_et);
        change_phone_aty_getcode_reset = (TextView) findViewById(R.id.change_phone_aty_getcode_reset);
        change_phone_aty_getcode_reset_clear = (ImageView) findViewById(R.id.change_phone_aty_getcode_reset_clear);
        change_phone_aty_loginbtn = (Button) findViewById(R.id.change_phone_aty_loginbtn);
        change_phone_aty_cancle.setOnClickListener(this);
        change_phone_aty_loginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_phone_aty_loginbtn:

                break;
            case R.id.change_phone_aty_cancle:
                finish();
                break;
        }
    }


}
