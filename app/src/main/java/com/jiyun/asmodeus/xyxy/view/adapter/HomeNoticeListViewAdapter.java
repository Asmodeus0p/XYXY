package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;

import java.util.List;


/**
 * Created by vicoltree on 17/10/28.
 */

public class HomeNoticeListViewAdapter extends BaseAdapter {


    private Context context;

    private LayoutInflater layoutInflater;

    private List<NoticeBean.DataBean.ListBean> listBeen;

    private String[] majors;

    public HomeNoticeListViewAdapter(Context context,List<NoticeBean.DataBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listBeen.size();
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
            convertView = layoutInflater.inflate(R.layout.home_notice_listview_item, null);

               viewHolder.img=convertView.findViewById(R.id.home_notice_listitem_img);
            viewHolder.address=  convertView.findViewById(R.id.home_notice_listitem_address);
            viewHolder.type=   convertView.findViewById(R.id.home_notice_listitem_type);
            viewHolder.time=   convertView.findViewById(R.id.home_notice_listitem_time);
            viewHolder.num=     convertView.findViewById(R.id.home_notice_listitem_num);
            viewHolder.price=    convertView.findViewById(R.id.home_notice_listitem_price);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(App.context).load(listBeen.get(position).getCoverImg()).into(viewHolder.img);
        viewHolder.time.setText(TimeShift.getTimeData(listBeen.get(position).getStartDate()));
        viewHolder.address.setText(String.format("地址 : %s",listBeen.get(position).getAddress()));
        viewHolder.num.setText(String.format("%s / %s",listBeen.get(position).getReservationNum(),listBeen.get(position).getSubscribeNum()));
        viewHolder.price.setText(listBeen.get(position).getPrice()+"");



        return convertView;
    }

    private class ViewHolder{


        private ImageView img;

        private TextView type;


        private TextView time;


        private TextView address;


        private TextView num;


        private TextView price;

    }

}
