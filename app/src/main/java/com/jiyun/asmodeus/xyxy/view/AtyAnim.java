package com.jiyun.asmodeus.xyxy.view;

import android.app.Activity;

import com.jiyun.asmodeus.xyxy.R;

public class AtyAnim {


    public static void centUp(Activity activity){
        activity.overridePendingTransition(R.anim.push_scale_in,0);
    }


    public static void centOut(Activity activity){
        activity.overridePendingTransition(R.anim.push_scale_out,0);
    }



}
