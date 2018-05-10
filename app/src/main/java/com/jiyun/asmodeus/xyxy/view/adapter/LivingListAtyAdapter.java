package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.LivingListBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;

import java.util.List;



public class LivingListAtyAdapter extends BaseQuickAdapter<LivingListBean.DataBean.ListBean,BaseViewHolder> {

    private Context context;

    public LivingListAtyAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<LivingListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, LivingListBean.DataBean.ListBean item) {

        TextView typeText = helper.getView(R.id.masterlive_list_aty_item_type);

        if(Constant.Live_status_yugao==item.getLiveStatus()){
            typeText.setText(Constant.Live_status_yugao_str);
        }else if(Constant.Live_status_zhibo==item.getLiveStatus()){
            typeText.setText(Constant.Live_status_zhibo_str);
        }else if(Constant.Live_status_chongbo==item.getLiveStatus()){
            typeText.setText(Constant.Live_status_chongbo_str);
        }


        Glide.with(context).load(item.getCoverImg()).into((ImageView) helper.getView(R.id.masterlive_list_aty_item_img));
        helper.setText(R.id.masterlive_list_aty_item_time, TimeShift.getTimeData(item.getStartDate()));

        helper.setText(R.id.masterlive_list_aty_item_name, item.getRealname());



        if(!TextUtils.isEmpty(item.getMajorIds())){
            helper.getView(R.id.masterlive_list_aty_item_majorIds).setVisibility(View.VISIBLE);

        }else{

            helper.getView(R.id.masterlive_list_aty_item_majorIds).setVisibility(View.GONE);
        }

        helper.setText(R.id.masterlive_list_aty_item_flag, item.getType());

        helper.setText(R.id.masterlive_list_aty_item_yuyue,String.format("%s / %s", item.getIsSubscribe(), item.getSubscribeNum()));

        helper.setText(R.id.masterlive_list_aty_item_price, item.getPrice()+"");
    }

}
