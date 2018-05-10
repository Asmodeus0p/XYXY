package com.jiyun.asmodeus.xyxy.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.SystemMessageListBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;

import java.util.List;

public class MessageListAdapter extends BaseQuickAdapter<SystemMessageListBean.DataBean,BaseViewHolder> {


    public MessageListAdapter(@LayoutRes int layoutResId, @Nullable List<SystemMessageListBean.DataBean> data) {

        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemMessageListBean.DataBean item) {

        helper.getView(R.id.message_listitem_flag).setVisibility(View.GONE);

        if(TextUtils.equals(Constant.MSG_ORDER,item.getType())){
            //订单
            ((ImageView)helper.getView(R.id.message_listitem_typeimg)).setImageResource(R.mipmap.message_order);
            helper.setText(R.id.message_listitem_title, "订单提醒");
        }

        if(TextUtils.equals(Constant.MSG_PRAISE,item.getType())){
            //点赞
            ((ImageView)helper.getView(R.id.message_listitem_typeimg)).setImageResource(R.mipmap.message_praise);
            helper.setText(R.id.message_listitem_title, "赞我的");
        }

        if(TextUtils.equals(Constant.MSG_COMMENTS,item.getType())){
            //评论
            ((ImageView)helper.getView(R.id.message_listitem_typeimg)).setImageResource(R.mipmap.message_comment);
            helper.setText(R.id.message_listitem_title, "评论我的");
        }
        if(TextUtils.equals(Constant.MSG_TEACHING,item.getType())){
            //直播
            ((ImageView)helper.getView(R.id.message_listitem_typeimg)).setImageResource(R.mipmap.message_teaching);
            helper.setText(R.id.message_listitem_title, "教学提醒");
        }
        if(TextUtils.equals(Constant.MSG_HOMEWOK,item.getType())){
            //作业
            ((ImageView)helper.getView(R.id.message_listitem_typeimg)).setImageResource(R.mipmap.message_job);
            helper.setText(R.id.message_listitem_title, "作业提醒");
        }
        if(TextUtils.equals(Constant.MSG_UNIVSTAR,item.getType())){
            //官方
            ((ImageView)helper.getView(R.id.message_listitem_typeimg)).setImageResource(R.mipmap.message_official);
            helper.getView(R.id.message_listitem_flag).setVisibility(View.VISIBLE);
            helper.setText(R.id.message_listitem_title, "UnivStar团队");
        }
        if(TextUtils.equals(Constant.MSG_ATTENTION,item.getType())){
            //关注
            ((ImageView)helper.getView(R.id.message_listitem_typeimg)).setImageResource(R.mipmap.message_attention);
            helper.setText(R.id.message_listitem_title, "关注提醒");
        }

        if(item.getIsRead()== RetrofitUtils.Sucess_code){
            helper.getView(R.id.message_listitem_newimg).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.message_listitem_newimg).setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.message_listitem_content, item.getContent());

        helper.setText(R.id.message_listitem_time, TimeShift.getChatTime(item.getCreateDate()));

    }
}
