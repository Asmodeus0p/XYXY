package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.CenterBean;

import java.util.List;

public class VoucherCenterAdapter extends RecyclerView.Adapter<VoucherCenterAdapter.Holder> implements View.OnClickListener{

    private List<CenterBean.DataBean> data;
    private Context context;

    public VoucherCenterAdapter(List<CenterBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.center_item, null);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        CenterBean.DataBean dataBean = data.get(position);
        holder.recharge_center_listitem_amount.setText(dataBean.getAmountAndroid()+"");
        holder.recharge_center_listitem_price.setText(dataBean.getPriceAndroid()+"");
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class Holder extends RecyclerView.ViewHolder {
        public TextView recharge_center_listitem_amount;
        public TextView recharge_center_listitem_price;

        public Holder(View itemView) {
            super(itemView);
            recharge_center_listitem_amount = itemView.findViewById(R.id.recharge_center_listitem_amount);
            recharge_center_listitem_price = itemView.findViewById(R.id.recharge_center_listitem_price);
        }
    }
    public interface OnClick {
        void setOnClickListener(View v,int postion);
    }
    public OnClick onClick;

    @Override
    public void onClick(View v) {
        if (onClick!=null){
            onClick.setOnClickListener(v,(int)v.getTag());
        }
    }
        public void setClick(OnClick onClick){
                this.onClick=onClick;
        }
}
