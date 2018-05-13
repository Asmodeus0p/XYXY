package com.jiyun.asmodeus.xyxy.view.base;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;
import com.jiyun.asmodeus.xyxy.model.entity.LoginRegsterSucessBean;
import com.jiyun.asmodeus.xyxy.model.entity.PublicWorkBean;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.MainActivity;

import java.util.regex.Pattern;

public class BaselivstActivity extends AppCompatActivity {

    private  long lastClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //当内存不够 被系统销毁 不保存任何状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        startActivity(new Intent(this, MainActivity.class));
    }


    protected void setTitleTheme(Activity activity, boolean darmode){



        setMStatusBarDarkIcon(activity);
    }

    protected void setTitleThemeTextColor(Activity activity,boolean darmode){


        setMStatusBarDarkIcon(activity);
    }





    protected void setMStatusBarDarkIcon(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_UP) {

            if (isFastDoubleClick()) {
                return false;
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = Math.abs(time - lastClickTime);
        lastClickTime = time;
        return timeD <=  Constant.ClickTime;
    }



    protected boolean showDialog(BasicSuccessBean value , String errTitle){
        if(value==null) {
            return false;
        }

        if(value.getCode()==Constant.SuccessCode){
            //登录成功
            return true;
        }else{
            show_error_Dialog(errTitle,value.getMessage());
            return false;
        }
    }

    protected boolean showDialog(PublicWorkBean value , String errTitle){
        if(value==null) {
            return false;
        }

        if(value.getCode()==Constant.SuccessCode){
            //登录成功
            return true;
        }else{
            show_error_Dialog(errTitle,value.getMessage());
            return false;
        }
    }

    protected boolean showDialog(RegistBean value , String errTitle){
        if(value==null) {
            return false;
        }

        if(value.getCode()==Constant.SuccessCode){
            //登录成功
            return true;
        }else{
            show_error_Dialog(errTitle,value.getMessage());
            return false;
        }
    }

    protected boolean showDialog(LoginRegsterSucessBean value , String errTitle){
        if(value==null) {
            return false;
        }

        if(value.getCode()==Constant.SuccessCode){
            //登录成功
            return true;
        }else{
            show_error_Dialog(errTitle,value.getMessage());
            return false;
        }
    }


    protected void show_error_Dialog(String errTitle,String errMessage){

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        dialogBuilder.setTitle(errTitle);
        dialogBuilder.setMessage(errMessage);
        dialogBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogBuilder.setCancelable(true);
        AlertDialog dialog=dialogBuilder.create();
        dialog.show();
    }

    /**
     * 列表空内容
     */
    protected void emptyList(View emptyhintView , View loadMoreList){
        emptyhintView.setVisibility(View.VISIBLE);
        loadMoreList.setVisibility(View.GONE);
    }

    /**
     * 列表内容刷新
     */
    protected void datafillList(View emptyhintView , View loadMoreList){
        emptyhintView.setVisibility(View.GONE);
        loadMoreList.setVisibility(View.VISIBLE);
    }

    /**
     * 无网络 数据错误
     */
    protected void faultList(View faulthintView, View faultrefresh, View loadMoreList, final View.OnClickListener onClickListener){
        faulthintView.setVisibility(View.VISIBLE);
        loadMoreList.setVisibility(View.GONE);
        faultrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null){
                    onClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 无网络情况下 列表内容刷新
     */
    protected void faultDataFillList(View faulthintView, View loadMoreList){
        faulthintView.setVisibility(View.GONE);
        loadMoreList.setVisibility(View.VISIBLE);
    }

    /**
     * 判断是否有空格
     * @param input
     * @return
     */
    protected boolean containSpace(String input){
        return Pattern.compile("\\s+").matcher(input).find();
    }
}
