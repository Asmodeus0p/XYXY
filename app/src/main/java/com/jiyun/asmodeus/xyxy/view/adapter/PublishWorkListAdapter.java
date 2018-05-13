package com.jiyun.asmodeus.xyxy.view.adapter;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.WokSubjectListBean;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;

import java.util.List;

public class PublishWorkListAdapter extends BaseQuickAdapter<WokSubjectListBean.DataBean.ListBean,BaseViewHolder> {

    private Context context;

    public PublishWorkListAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<WokSubjectListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WokSubjectListBean.DataBean.ListBean item) {

        helper.setText(R.id.detailwork_list_item_content_tv, item.getContent());

        helper.setText(R.id.detailwork_list_item_name_tv, item.getRealname());

        SplitStringColorUtils.setImgLevel((ImageView) helper.getView(R.id.detailwork_list_item_usertype),item.getUserType());

        helper.setText(R.id.detailwork_list_item_title_tv,item.getIntro());

        helper.setText(R.id.detailwork_list_item_from_tv, String.format("%s作业",item.getSource()));

        if(!TextUtils.isEmpty(item.getMajorIds())){

            SplitStringColorUtils.addForeColorSpan(context,item.getMajorIds().split(","), (TextView) helper.getView(R.id.detailwork_list_item_major_tv));
        }
    }
}
