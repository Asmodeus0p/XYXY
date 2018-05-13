package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.HomeNoticeBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

public class HomeNoticeListAdapter extends BaseAdapter{

    private Context context;

    private LayoutInflater layoutInflater;

    private List<HomeNoticeBean.DataBean.ListBean> listBeen;

    private String[] majors;

    public HomeNoticeListAdapter(Context context,List<HomeNoticeBean.DataBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;

        //majors=context.getResources().getStringArray(R.array.majors);
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

            ViewUtils.inject(viewHolder,convertView);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

       // HttpHelp.glideLoad(context,viewHolder.img,listBeen.get(position).getCoverImg(),R.mipmap.home_master_viewpagr_normal);
        Glide.with(context).load(listBeen.get(position).getCoverImg()).into(viewHolder.img);

        viewHolder.time.setText(TimeShift.getTimeData(listBeen.get(position).getStartDate()));

        viewHolder.address.setText(String.format("地址 : %s",listBeen.get(position).getAddress()));

        viewHolder.num.setText(String.format("%s / %s",listBeen.get(position).getReservationNum(),listBeen.get(position).getSubscribeNum()));

        viewHolder.price.setText(listBeen.get(position).getPrice()+"");


        if(!TextUtils.isEmpty(listBeen.get(position).getMajorIds())){

            SplitStringColorUtils.addForeWhiteSpan(context,listBeen.get(position).getMajorIds().split(","),viewHolder.type);
        }

        return convertView;
    }

    private class ViewHolder{

        @ViewInject(R.id.home_notice_listitem_img)
        private ImageView img;

        @ViewInject(R.id.home_notice_listitem_type)
        private TextView type;

        @ViewInject(R.id.home_notice_listitem_time)
        private TextView time;

        @ViewInject(R.id.home_notice_listitem_address)
        private TextView address;

        @ViewInject(R.id.home_notice_listitem_num)
        private TextView num;

        @ViewInject(R.id.home_notice_listitem_price)
        private TextView price;

    }



}
