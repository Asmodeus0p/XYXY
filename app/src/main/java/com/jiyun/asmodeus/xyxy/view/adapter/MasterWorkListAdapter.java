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
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailWorkBean;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;

import java.util.List;

public class MasterWorkListAdapter extends BaseQuickAdapter<MasterDetailWorkBean.DataBean.ListBean,BaseViewHolder> {

    private Context context;
    public MasterWorkListAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<MasterDetailWorkBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MasterDetailWorkBean.DataBean.ListBean item) {

        helper.setText(R.id.detailwork_list_item_content_tv, item.getTitle());

        helper.setText(R.id.detailwork_list_item_name_tv, item.getRealname());

        helper.setText(R.id.detailwork_list_item_title_tv, item.getIntro());

        SplitStringColorUtils.setImgLevel((ImageView) helper.getView(R.id.detailwork_list_item_usertype),item.getUserType());

        if(!TextUtils.isEmpty(item.getMajorIds())){
            SplitStringColorUtils.addForeColorSpan(context, item.getMajorIds().split(","), (TextView) helper.getView(R.id.detailwork_list_item_major_tv));
        }

        helper.setText(R.id.detailwork_list_item_from_tv, String.format("%s作业", item.getSource()));
    }
}
