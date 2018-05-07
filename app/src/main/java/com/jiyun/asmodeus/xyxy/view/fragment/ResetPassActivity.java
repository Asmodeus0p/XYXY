package com.jiyun.asmodeus.xyxy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.IResetPassContract;
import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;
import com.jiyun.asmodeus.xyxy.presenter.IResetPassImp;

public class ResetPassActivity extends AppCompatActivity implements View.OnClickListener,IResetPassContract.ResetPassView {

    private TextView resetpass_aty_cancle;
    private EditText resetpass_aty_et;
    private EditText resetpass_aty_rp_et;
    private Button resetpass_aty_loginbtn;
    private IResetPassImp passImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        initView();
    }

    private void initView() {
        passImp=new IResetPassImp(this);
        resetpass_aty_cancle = (TextView) findViewById(R.id.resetpass_aty_cancle);
        resetpass_aty_et = (EditText) findViewById(R.id.resetpass_aty_et);
        resetpass_aty_rp_et = (EditText) findViewById(R.id.resetpass_aty_rp_et);
        resetpass_aty_loginbtn = (Button) findViewById(R.id.resetpass_aty_loginbtn);

        resetpass_aty_loginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String pass = resetpass_aty_et.getText().toString().trim();
        String passs = resetpass_aty_rp_et.getText().toString().trim();
        switch (v.getId()) {
            case R.id.resetpass_aty_loginbtn:
            if (!pass.equals(passs)){
                Toast.makeText(this, "两次输入不一致,请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }
                Intent intent = getIntent();
                String mobile = intent.getStringExtra("mobile");
                passImp.getResetPass(mobile,passs);
                startActivity(new Intent(this,MyselfFragment.class));
                break;
        }
    }

  

    @Override
    public void IresetPass(FindPassBean findPassBean) {

    }
}
