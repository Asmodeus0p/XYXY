package com.jiyun.asmodeus.xyxy.view.adapter;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetialWokLineBean;

import java.util.List;

public class MasterDetailSortAdapter extends BaseQuickAdapter<MasterDetialWokLineBean,BaseViewHolder> {

    public MasterDetailSortAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<MasterDetialWokLineBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MasterDetialWokLineBean item) {

        helper.setText(R.id.masterdetail_sortlist_item_status, item.getName());
        if(!TextUtils.isEmpty(item.getNum())){

            helper.setText(R.id.masterdetail_sortlist_item_tv, item.getNum());
        }
    }
}
