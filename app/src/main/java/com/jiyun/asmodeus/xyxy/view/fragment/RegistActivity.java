package com.jiyun.asmodeus.xyxy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.PhoneCodeContract;
import com.jiyun.asmodeus.xyxy.contract.RegistContract;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.presenter.PhoneCodePresenterImp;
import com.jiyun.asmodeus.xyxy.presenter.RegistPresneterImp;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener,RegistContract.registView{

    private String phone_str;

    private String getcode_str;

    private TextView register_cancle;
    private EditText register_aty_phone_et;
    private ImageView register_aty_phone_et_clear;
    private EditText register_aty_getcode_et;
    private TextView register_aty_getcode_reset;
    private ImageView register_aty_getcode_reset_clear;
    private Button register_aty_loginbtn;
    private LinearLayout register_aty_weixin_btn;
    private LinearLayout register_aty_qq_btn;
    private LinearLayout register_aty_weibo_btn;
    private RegistPresneterImp registPresneterImp;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
        isEdit();
    }

    private void isEdit() {
        /**
         * 判断是否输入
         */

        register_aty_phone_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    phone_str = s.toString().trim();

                    if(TextUtils.isEmpty(phone_str)){
                        register_aty_phone_et_clear.setVisibility(View.INVISIBLE);
                    }else{
                        register_aty_phone_et_clear.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        register_aty_getcode_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    getcode_str = s.toString().trim();

                    if(TextUtils.isEmpty(getcode_str)){
                        register_aty_getcode_reset_clear.setVisibility(View.INVISIBLE);
                    }else{
                        register_aty_getcode_reset_clear.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


    }

    private void initView() {

        register_cancle = (TextView) findViewById(R.id.register_cancle);
        register_cancle.setOnClickListener(this);
        register_aty_phone_et = (EditText) findViewById(R.id.register_aty_phone_et);
        register_aty_phone_et.setOnClickListener(this);
        register_aty_phone_et_clear = (ImageView) findViewById(R.id.register_aty_phone_et_clear);
        register_aty_phone_et_clear.setOnClickListener(this);
        register_aty_getcode_et = (EditText) findViewById(R.id.register_aty_getcode_et);
        register_aty_getcode_et.setOnClickListener(this);
        register_aty_getcode_reset = (TextView) findViewById(R.id.register_aty_getcode_reset);
        register_aty_getcode_reset.setOnClickListener(this);
        register_aty_getcode_reset_clear = (ImageView) findViewById(R.id.register_aty_getcode_reset_clear);
        register_aty_getcode_reset_clear.setOnClickListener(this);
        register_aty_loginbtn = (Button) findViewById(R.id.register_aty_loginbtn);
        register_aty_loginbtn.setOnClickListener(this);
        register_aty_weixin_btn = (LinearLayout) findViewById(R.id.register_aty_weixin_btn);
        register_aty_weixin_btn.setOnClickListener(this);
        register_aty_qq_btn = (LinearLayout) findViewById(R.id.register_aty_qq_btn);
        register_aty_qq_btn.setOnClickListener(this);
        register_aty_weibo_btn = (LinearLayout) findViewById(R.id.register_aty_weibo_btn);
        register_aty_weibo_btn.setOnClickListener(this);
        registPresneterImp=new RegistPresneterImp(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_cancle:
                finish();
                break;
            case R.id.register_aty_loginbtn:
                if(TextUtils.isEmpty(phone_str)||TextUtils.isEmpty(getcode_str)){

                    return ;

                }
                registPresneterImp.GotoRegist(phone_str,getcode_str);
                Intent intent= new Intent(this, UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.User_mobile,phone_str);
                bundle.putInt(Constant.Approve_login,Constant.REGISTER_LOGIN);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.register_aty_getcode_reset:
                registPresneterImp.loadPhoneCode(phone_str);
                break;
                //11212
        }
    }
    @Override
    public void loadUserDatas(RegistBean registBean) {

    }

    @Override
    public void showPhoneDatas(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
