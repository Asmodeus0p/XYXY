package com.jiyun.asmodeus.xyxy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.LoginSucessContract;
import com.jiyun.asmodeus.xyxy.model.entity.LoginRegsterSucessBean;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.presenter.LoginPresenterImp;
import com.jiyun.asmodeus.xyxy.view.MainActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginSucessContract.LoginView {
    public TextView login_aty_close_tv;
    public TextView login_aty_register_tv;
    public EditText login_aty_phone_et;
    public ImageView login_aty_phone_et_clear;
    public EditText login_aty_pass_et;
    public ImageView login_aty_pass_et_clear;
    public TextView login_aty_findpass_tv;
    public Button login_aty_loginbtn;
    public LinearLayout login_aty_weixin_btn;
    public LinearLayout login_aty_qq_btn;
    public LinearLayout login_aty_weibo_btn;
    private LoginPresenterImp presenterImp;
    private UMAuthListener authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        presenterImp = new LoginPresenterImp(this);
        login_aty_close_tv = (TextView) findViewById(R.id.login_aty_close_tv);
        login_aty_register_tv = (TextView) findViewById(R.id.login_aty_register_tv);
        login_aty_phone_et = (EditText) findViewById(R.id.login_aty_phone_et);
        login_aty_phone_et_clear = (ImageView) findViewById(R.id.login_aty_phone_et_clear);
        login_aty_pass_et = (EditText) findViewById(R.id.login_aty_pass_et);
        login_aty_pass_et_clear = (ImageView) findViewById(R.id.login_aty_pass_et_clear);
        login_aty_findpass_tv = (TextView) findViewById(R.id.login_aty_findpass_tv);
        login_aty_loginbtn = (Button) findViewById(R.id.login_aty_loginbtn);
        login_aty_weixin_btn = (LinearLayout) findViewById(R.id.login_aty_weixin_btn);
        login_aty_qq_btn = (LinearLayout) findViewById(R.id.login_aty_qq_btn);
        login_aty_weibo_btn = (LinearLayout) findViewById(R.id.login_aty_weibo_btn);
        login_aty_register_tv.setOnClickListener(this);
        login_aty_loginbtn.setOnClickListener(this);
        login_aty_close_tv.setOnClickListener(this);
        login_aty_phone_et_clear.setOnClickListener(this);
        login_aty_pass_et_clear.setOnClickListener(this);
        login_aty_findpass_tv.setOnClickListener(this);
        login_aty_qq_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_aty_register_tv:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.login_aty_close_tv:
                finish();
                break;
            case R.id.login_aty_phone_et_clear:
                login_aty_phone_et.setText("");
                break;
            case R.id.login_aty_pass_et_clear:
                login_aty_pass_et.setText("");
                break;
            case R.id.login_aty_loginbtn:
                String userName = login_aty_phone_et.getText().toString().trim();
                String pasw = login_aty_pass_et.getText().toString().trim();
                if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(pasw)){
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Log.e("===",userName);
                //Log.e("===",pasw);
                presenterImp.laodLoginDatas(userName, pasw);
                break;
            case R.id.login_aty_findpass_tv:
                startActivity(new Intent(this,FindPassActivity.class));
                break;
            case R.id.login_aty_qq_btn:
                QQLogin();
                break;
        }
    }
    public void QQLogin(){

        authListener = new UMAuthListener() {
            /**
             * @desc 授权开始的回调
             * @param platform 平台名称
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            /**
             * @desc 授权成功的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param data 用户资料返回
             */
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

            }

            /**
             * @desc 授权失败的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
            }

            /**
             * @desc 授权取消的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             */
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
            }
        };
        UMShareAPI umShareAPI = UMShareAPI.get(LoginActivity.this);
        umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
    }

    @Override
    public void laodLoginDatas(LoginRegsterSucessBean loginRegsterSucessBean) {
        Log.e("==", loginRegsterSucessBean.getMessage());
       if (loginRegsterSucessBean.getMessage().equals("cid为空")){
           Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("nickname",loginRegsterSucessBean.getData().getNickname());
            intent.putExtra("photo",loginRegsterSucessBean.getData().getPhoto());
            intent.putExtra("mobile",loginRegsterSucessBean.getData().getMobile());
            SharedPreferencesUtils.saveUsarInfo(getApplicationContext(),loginRegsterSucessBean.getData().getMobile()
            ,loginRegsterSucessBean.getData().getPhoto(),loginRegsterSucessBean.getData().getNickname());
            setResult(10,intent);
           int id = loginRegsterSucessBean.getData().getId();
           SharedPreferencesUtils.saveUserId(getApplicationContext(),id);
           SharedPreferencesUtils.saveId(this,loginRegsterSucessBean.getData().getId()+"");
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            finish();

       }else {
           Toast.makeText(this, "登录失败,账号密码错误,请重新登录", Toast.LENGTH_SHORT).show();
       }

    }
}

