package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.App;

import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.List;

/**
 * Created by vicoltree on 17/10/28.
 */

public class HomeMasterLiveGridViewAdapter extends BaseAdapter {

    private Context context;

    private List<HomeBean.DataBean.LiveCoursesBean> liveCoursesBeen;

    private LayoutInflater layoutInflater;

    public HomeMasterLiveGridViewAdapter(Context context, List<HomeBean.DataBean.LiveCoursesBean> liveCoursesBeen) {
        this.context = context;
        this.liveCoursesBeen = liveCoursesBeen;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return liveCoursesBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.home_masterlive_gridview_item, null);
            convertView.findViewById(R.id.home_masterlive_griditem_coverImg);

            convertView.findViewById(R.id.home_masterlive_griditem_name);
            convertView.findViewById(R.id.home_masterlive_griditem_major);
            convertView.findViewById(R.id.home_masterlive_griditem_usertype);
            convertView.findViewById(R.id.home_masterlive_griditem_time);
            convertView.findViewById(R.id.home_masterlive_griditem_typetitle);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(App.context).load(liveCoursesBeen.get(position).getCoverImg()).into(viewHolder.coverImg);
        viewHolder.type.setText(liveCoursesBeen.get(position).getType());

        viewHolder.name.setText(liveCoursesBeen.get(position).getRealname());

        if (!TextUtils.isEmpty(liveCoursesBeen.get(position).getMajorIds())) {

            SplitStringColorUtils.addForeColorSpan(context, liveCoursesBeen.get(position).getMajorIds().split(","), viewHolder.major);
        } else {
            viewHolder.major.setVisibility(View.GONE);
        }

        viewHolder.typeTitle.setText(String.format("%s开播: ", liveCoursesBeen.get(position).getType()));

        SplitStringColorUtils.setImgLevel(viewHolder.usertype, liveCoursesBeen.get(position).getUserType());

//        viewHolder.time.setText(HttpHelp.getDateToString(liveCoursesBeen.get(position).getStartDate()));

        return convertView;
    }

    private class ViewHolder {


        private RoundedImageView coverImg;


        private TextView type;


        private TextView name;

        private ImageView usertype;


        private TextView major;


        private TextView time;


        private TextView typeTitle;

    }

}
