package com.jiyun.asmodeus.xyxy.view.adapter;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.WokDetailBean;


import java.util.List;

public class DetailDaShangAdapter extends BaseQuickAdapter<WokDetailBean.DataBean.RewardUserListBean,BaseViewHolder> {

    private Context context;

    public DetailDaShangAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<WokDetailBean.DataBean.RewardUserListBean> data) {
        super(layoutResId, data);

        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WokDetailBean.DataBean.RewardUserListBean item) {


        Glide.with(context).load(item.getUserPhoto()).into((ImageView) helper.getView(R.id.detail_dashang_listitem_img));

    }
}
