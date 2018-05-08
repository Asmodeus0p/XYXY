package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailBean;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;



public class MasterDetailCourseAdapter extends BaseAdapter {

    private List<MasterDetailBean.DataBean.CoursesBean> coursesBeen;

    private LayoutInflater layoutInflater;

    private Context context;

    public MasterDetailCourseAdapter(Context context,List<MasterDetailBean.DataBean.CoursesBean> coursesBeen) {
        this.context = context;
        this.coursesBeen = coursesBeen;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return coursesBeen.size();
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

        if(convertView==null){
            viewHolder =new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.master_detail_livelist_item, null);

            ViewUtils.inject(viewHolder,convertView);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(coursesBeen.get(position).getTitle());

        viewHolder.time.setText(String.format("课程时间 : %s",coursesBeen.get(position).getStartDate()));

        viewHolder.type.setText("体验课预告");

        viewHolder.location.setText(coursesBeen.get(position).getAddress());

        return convertView;
    }

    private class ViewHolder{

        @ViewInject(R.id.master_detail_livelist_item_title)
        private TextView title;

        @ViewInject(R.id.master_detail_livelist_item_type)
        private TextView type;

        @ViewInject(R.id.master_detail_livelist_item_time)
        private TextView time;

        @ViewInject(R.id.master_detail_livelist_item_location)
        private TextView location;

    }
}
