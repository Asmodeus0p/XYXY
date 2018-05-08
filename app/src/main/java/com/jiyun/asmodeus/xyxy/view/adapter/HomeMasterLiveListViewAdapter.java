package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.v7.widget.ViewUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;

import java.util.List;



public class HomeMasterLiveListViewAdapter extends BaseAdapter {


    private Context context;

    private List<HomeBean.DataBean.LivesBean> lives;

    private LayoutInflater layoutInflater;

    public HomeMasterLiveListViewAdapter(Context context, List<HomeBean.DataBean.LivesBean> lives) {
        this.context = context;
        this.lives = lives;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lives.size();
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
            convertView = layoutInflater.inflate(R.layout.home_masterlive_listview_item, null);

           viewHolder.title= convertView.findViewById(R.id.home_masterlive_listitem_title);
            viewHolder.name=  convertView.findViewById(R.id.home_masterlive_listitem_name);
            viewHolder.usertype=  convertView.findViewById(R.id.home_masterlive_listitem_usertype);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(lives.get(position).getTitle());

        viewHolder.name.setText(lives.get(position).getNickname());

        SplitStringColorUtils.setImgLevel(viewHolder.usertype, lives.get(position).getUserType());

        return convertView;
    }

    private class ViewHolder {


        private TextView title;


        private TextView name;


        private ImageView usertype;

    }

}
