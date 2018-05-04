package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.Holder> {
    private List<NoticeBean.DataBean.ListBean> list;
    private Context context;

    public NoticeAdapter(List<NoticeBean.DataBean.ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.yugao_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        NoticeBean.DataBean.ListBean listBean = list.get(position);
        holder.yuegao_price.setText(listBean.getPrice()+"");
        holder.yugao_address.setText(listBean.getAddress());
        Glide.with(context).load(listBean.getCoverImg()).into(holder.yugao_img_one);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView yugao_img_one;
        public TextView yugao_address;
        public TextView yuegao_price;

        public Holder(View itemView) {
            super(itemView);

            yugao_img_one = itemView.findViewById(R.id.yugao_img_one);
            yugao_address = itemView.findViewById(R.id.yugao_address);
            yuegao_price = itemView.findViewById(R.id.yuegao_price);
        }
    }
}
