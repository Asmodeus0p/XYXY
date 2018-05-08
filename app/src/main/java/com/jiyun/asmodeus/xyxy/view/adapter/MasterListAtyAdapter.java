package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.MasterListAtyBean;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;

import java.util.List;


/**
 * Created by vicoltree on 17/11/4.
 */

public class MasterListAtyAdapter extends BaseQuickAdapter<MasterListAtyBean.DataBean.ListBean,BaseViewHolder> {

    private Context context;

    public MasterListAtyAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<MasterListAtyBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MasterListAtyBean.DataBean.ListBean item) {

        Glide.with(context).load(item.getImages()).into((ImageView) helper.getView(R.id.masterlistaty_listitem_img));
        helper.setText(R.id.masterlistaty_listitem_name, item.getRealname());

        helper.setText(R.id.masterlistaty_listitem_intro, item.getIntro());

        SplitStringColorUtils.setImgLevel((ImageView) helper.getView(R.id.masterlistaty_listitem_usertype),item.getUserType());

    }

}
