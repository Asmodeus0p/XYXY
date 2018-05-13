package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.PublishSelectTeacherModel;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.view.fragment.workactivity.PublishSelectTeacherListActivity;

import java.util.List;

public class PublishSelectTeacherListAdapter extends BaseQuickAdapter<PublishSelectTeacherModel.DataBean.ListBean,BaseViewHolder> {


    private int selectId;

    private Context context;

    public PublishSelectTeacherListAdapter(Context context, int selectId, @LayoutRes int layoutResId, @Nullable List<PublishSelectTeacherModel.DataBean.ListBean> data) {
        super(layoutResId, data);

        this.context = context;
        this.selectId = selectId;
    }



    @Override
    protected void convert(BaseViewHolder helper, PublishSelectTeacherModel.DataBean.ListBean item) {
        if(item.getTeacherId() == PublishSelectTeacherListActivity.NORMAL_POSITION){
            helper.getView(R.id.publish_selectteacher_listitem_active_gorup).setVisibility(View.GONE);
            helper.getView(R.id.publish_selectteacher_listitem_normal_gorup).setVisibility(View.VISIBLE);

        }else{
            helper.getView(R.id.publish_selectteacher_listitem_active_gorup).setVisibility(View.VISIBLE);
            helper.getView(R.id.publish_selectteacher_listitem_normal_gorup).setVisibility(View.GONE);
            Glide.with(context).load(item.getPhoto()).into((ImageView) helper.getView(R.id.publish_selectteacher_listitem_img));
            helper.setText(R.id.publish_selectteacher_listitem_name, item.getRealname());
            SplitStringColorUtils.setImgLevel((ImageView) helper.getView(R.id.publish_selectteacher_listitem_usertype),item.getUserType());
        }

        TextView selecttv = helper.getView(R.id.publish_selectteacher_listitem_select_tv);

        if(selectId==PublishSelectTeacherListActivity.NORMAL_POSITION){
            selecttv.setText("已选择");
            selecttv.setEnabled(false);
        }else{
            selecttv.setText("选择");
            selecttv.setEnabled(true);
        }

        if(selectId==item.getTeacherId()){
            selecttv.setText("已选择");
            selecttv.setEnabled(false);
        }else{
            selecttv.setText("选择");
            selecttv.setEnabled(true);
        }

    }
}
