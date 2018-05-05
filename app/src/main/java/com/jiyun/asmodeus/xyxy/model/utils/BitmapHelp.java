package com.jiyun.asmodeus.xyxy.model.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lidroid.xutils.BitmapUtils;


/**
 * Created by vicoltree on 15/9/8.
 */
public class BitmapHelp {

    private BitmapHelp() {
    }

    private static BitmapUtils bitmapUtils;


    public static BitmapUtils getBitmapUtils(Context appContext) {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(appContext);
        }
        return bitmapUtils;
    }

    public static  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void setImageView(Context context,String property, ImageView imageView){

        if(TextUtils.isEmpty(property)||TextUtils.equals("null",property)){
            return;
        }

        String[] propertyArry= property.split("_");

        int wi = Integer.parseInt(propertyArry[0]);

        int hi = Integer.parseInt(propertyArry[1]);

        int curenW = 0;

        int curenH = 0;

        if(wi<= Constant.MAX_HEIGHT&&hi<=Constant.MAX_HEIGHT){

            curenW = wi;

            curenH = hi;

        }else{

            if(wi==hi){

                curenW = Constant.MAX_HEIGHT;

                curenH = Constant.MAX_HEIGHT;
            }else{

                curenW = wi/2;

                curenH = hi/2;

            }

        }


        ViewGroup.LayoutParams layoutParams =  imageView.getLayoutParams();
        layoutParams.width = DensityTool.dp2px(context,curenW);
        layoutParams.height = DensityTool.dp2px(context,curenH);
        imageView.setLayoutParams(layoutParams);
    }

    public static void setAudioImageView(Context context,ImageView imageView){
        ViewGroup.LayoutParams layoutParams =  imageView.getLayoutParams();
        layoutParams.width = DensityTool.dp2px(context,Constant.MAX_HEIGHT);
        layoutParams.height = DensityTool.dp2px(context,Constant.AUDIO_MAX_HEIGHT);
        imageView.setLayoutParams(layoutParams);

    }
    public static void setAudioRelayout(Context context,RelativeLayout relativeLayout){
        ViewGroup.LayoutParams layoutParams =  relativeLayout.getLayoutParams();
        layoutParams.width = DensityTool.dp2px(context,Constant.MAX_HEIGHT);
        layoutParams.height = DensityTool.dp2px(context,Constant.AUDIO_MAX_HEIGHT);
        relativeLayout.setLayoutParams(layoutParams);

    }

}
