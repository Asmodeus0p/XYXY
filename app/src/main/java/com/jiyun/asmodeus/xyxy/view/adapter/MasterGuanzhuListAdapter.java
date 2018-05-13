package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.AttentionListBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;

import java.util.List;


public class MasterGuanzhuListAdapter extends BaseQuickAdapter<AttentionListBean.DataBean.ListBean,BaseViewHolder> {
    private Context context;

    public MasterGuanzhuListAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<AttentionListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AttentionListBean.DataBean.ListBean item) {


        Glide.with(context).load(item.getPhoto()).into( (ImageView) helper.getView(R.id.masterguanzhu_listitem_img));
        if(item.getUserType()>=2){
            helper.setText(R.id.masterguanzhu_listitem_name, item.getRealname());
        }else{

            helper.setText(R.id.masterguanzhu_listitem_name, item.getNickname());
        }

        SplitStringColorUtils.setImgLevel((ImageView) helper.getView(R.id.masterguanzhu_listitem_userType),item.getUserType());

        TextView attentionTextView = helper.getView(R.id.masterguanzhu_listitem_guanzhu_tv);

        if(item.getAttention()== Constant.Attention){
            attentionTextView.setText("关注");
            attentionTextView.setActivated(true);
        }else if(item.getAttention()==Constant.Attention_yiguanzhu){
            attentionTextView.setText("已关注");
            attentionTextView.setActivated(false);
        }else if(item.getAttention()==Constant.Attention_xianghu){
            attentionTextView.setText("相互关注");
            attentionTextView.setActivated(false);
        }
        helper.addOnClickListener(R.id.masterguanzhu_listitem_guanzhu_tv);
        helper.addOnClickListener(R.id.masterguanzhu_listitem_img);


    }
}
