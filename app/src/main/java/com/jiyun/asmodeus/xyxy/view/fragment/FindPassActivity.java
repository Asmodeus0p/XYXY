package com.jiyun.asmodeus.xyxy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.FindPassContract;
import com.jiyun.asmodeus.xyxy.contract.PhoneCodeContract;
import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;
import com.jiyun.asmodeus.xyxy.model.entity.LoginRegsterSucessBean;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.presenter.FindPassImp;
import com.jiyun.asmodeus.xyxy.presenter.PhoneCodePresenterImp;

public class FindPassActivity extends AppCompatActivity implements View.OnClickListener,PhoneCodeContract.phoneView,FindPassContract.FindPassView{

    private TextView retrievepass_cancle;
    private EditText retrievepass_aty_phone_et;
    private ImageView retrievepass_aty_phone_et_clear;
    private EditText retrievepass_aty_getcode_et;
    private TextView retrievepass_aty_getcode_reset;
    private ImageView retrievepass_aty_getcode_reset_clear;
    private Button retrievepass_aty_loginbtn;
    private PhoneCodePresenterImp phoneCodePresenterImp;
    private FindPassImp passImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass);
        initView();
    }

    private void initView() {
        passImp=new FindPassImp(this);
        phoneCodePresenterImp=new PhoneCodePresenterImp(this);
        retrievepass_cancle = (TextView) findViewById(R.id.retrievepass_cancle);
        retrievepass_aty_phone_et = (EditText) findViewById(R.id.retrievepass_aty_phone_et);
        retrievepass_aty_phone_et_clear = (ImageView) findViewById(R.id.retrievepass_aty_phone_et_clear);
        retrievepass_aty_getcode_et = (EditText) findViewById(R.id.retrievepass_aty_getcode_et);
        retrievepass_aty_getcode_reset = (TextView) findViewById(R.id.retrievepass_aty_getcode_reset);
        retrievepass_aty_getcode_reset_clear = (ImageView) findViewById(R.id.retrievepass_aty_getcode_reset_clear);
        retrievepass_aty_loginbtn = (Button) findViewById(R.id.retrievepass_aty_loginbtn);

        retrievepass_aty_loginbtn.setOnClickListener(this);

        retrievepass_aty_getcode_reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phoneNum = retrievepass_aty_phone_et.getText().toString().trim();
        String phoneCode = retrievepass_aty_getcode_et.getText().toString().trim();

        switch (v.getId()) {
            case R.id.retrievepass_aty_loginbtn:
                passImp.getFindPass(phoneNum,phoneCode);
                Intent intent = new Intent(this, ResetPassActivity.class);
                intent.putExtra("mobile",phoneNum);
                startActivity(intent);
                break;
            case R.id.retrievepass_aty_getcode_reset:
                phoneCodePresenterImp.loadPhoneCode(phoneNum);
        }
    }

    @Override
    public void loadPhoneDatas(RegistBean registBean) {

    }

    @Override
    public void findPhoneDatas(RegistBean registBean) {

    }

    @Override
    public void getFindPassBean(FindPassBean findPassBean) {

    }
}
