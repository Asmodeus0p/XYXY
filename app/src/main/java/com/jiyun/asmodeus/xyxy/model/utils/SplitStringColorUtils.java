package com.jiyun.asmodeus.xyxy.model.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;


import com.jiyun.asmodeus.xyxy.R;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vicoltree on 17/11/4.
 */

public class SplitStringColorUtils {


//    public static void addForeColorSpan(Context context, String[] array, TextView tv) {
//
//        tv.append("");
//        tv.setText("");
//
//        int[] colors ={context.getResources().getColor(R.color.blue_arlt), Color.RED,context.getResources().getColor(R.color.orange)};
//
////        List<PreferenceListBean.DataBean.MajorsBean> majorsBeen=((ArtliveApp) context.getApplicationContext()).getMajorsBeen();
////
//
//
//        if(array==null||majorsBeen==null){
//
//            tv.setVisibility(View.INVISIBLE);
//
//            return;
//        }
//
//
//        tv.setVisibility(View.VISIBLE);
//
//        for (int i = 0; i < array.length; i++) {
//            SpannableString gString = null;
//            for (int j = 0; j < majorsBeen.size(); j++) {
//                if(!isNumeric(array[i]))
//                    continue;
//                if(majorsBeen.get(j).getId() == Integer.parseInt(array[i])){
//
//                    gString  = new SpannableString(majorsBeen.get(j).getName());
//                }
//            }
//
//            if(gString==null){
//                return ;
//            }
//            ForegroundColorSpan gspan = new ForegroundColorSpan(colors[i%colors.length]);
//            gString.setSpan(gspan, 0, gString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            if(tv.getText().toString().contains(gString))
//                continue;
//            tv.append(gString);
//            if(array.length<1)
//                continue;
//            tv.append(" ");
//
//        }
//    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }


//    public static void addForeWhiteSpan(Context context, String[] array, TextView tv) {
//        tv.append("");
//        tv.setText("");
//
//        int[] colors ={context.getResources().getColor(R.color.white)};
//
//        List<PreferenceListBean.DataBean.MajorsBean> majorsBeen=((ArtliveApp) context.getApplicationContext()).getMajorsBeen();
//
//        if(array==null||majorsBeen==null){
//
//            return;
//        }
//
//        for (int i = 0; i < array.length; i++) {
//            SpannableString gString = null;
//            for (int j = 0; j < majorsBeen.size(); j++) {
//                if(!isNumeric(array[i]))
//                    continue;
//                if(majorsBeen.get(j).getId() == Integer.parseInt(array[i])){
//
//                    gString  = new SpannableString(majorsBeen.get(j).getName());
//                }
//            }
//
//            if(gString==null){
//                return ;
//            }
//
//            ForegroundColorSpan gspan = new ForegroundColorSpan(colors[0]);
//            gString.setSpan(gspan, 0, gString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            if(tv.getText().toString().contains(gString))
//                continue;
//            tv.append(gString);
//            if(array.length<1)
//                continue;
//            tv.append(" ");
//
//        }
//
//        String content = tv.getText().toString();
//
//        if(!TextUtils.isEmpty(content)) {
//            if (content.length() > 3) {
//                tv.setBackgroundResource(R.drawable.lable_r_bg_active);
//            } else {
//                if (content.contains("表演")) {
//                    tv.setBackgroundResource(R.drawable.lable_green_bg_active);
//                } else if (content.contains("播音")) {
//                    tv.setBackgroundResource(R.drawable.lable_yellow_bg_active);
//                } else if (content.contains("音乐")) {
//                    tv.setBackgroundResource(R.drawable.lable_purple_bg_active);
//                } else if (content.contains("舞蹈")) {
//                    tv.setBackgroundResource(R.drawable.lable_red_bg_active);
//                } else if (content.contains("编导")) {
//                    tv.setBackgroundResource(R.drawable.lable_blue_bg_active);
//                } else if (content.contains("空乘")) {
//                    tv.setBackgroundResource(R.drawable.lable_darkred_bg_active);
//                } else {
//                    tv.setBackgroundResource(R.drawable.lable_r_bg_active);
//                }
//            }
//        }
//
//    }


    public static void setImgLevel(ImageView view,int flag){

        if(flag==0){

            view.setImageResource(0);
        }

        if(flag==2){

            view.setImageResource(R.mipmap.home_level_vip_blue);
        }
        if(flag==3){

            view.setImageResource(R.mipmap.home_level_vip_yellow);
        }
        if(flag>=4){

            view.setImageResource(R.mipmap.home_level_vip_red);
        }

    }
}
