package com.jiyun.asmodeus.xyxy.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.PreferenceListBean;

import java.util.ArrayList;
import java.util.List;

public class PublishRedactWokDetailTypeAdapter extends RecyclerView.Adapter<PublishRedactWokDetailTypeAdapter.ViewHolder> {

    private List<PreferenceListBean.DataBean.MajorsBean> typelist;

    private Context context;

    private int[] bgtype={R.drawable.type_blue_bg_active,R.drawable.type_green_bg_active,
                            R.drawable.type_red_bg_active,R.drawable.type_yellow_bg_active};

    private int[] tctype ;

    public interface ItemSelectCallBack{
        void onItemSelect(List<String> selectList);
    }

    private ItemSelectCallBack selectCallBack;

    private List<String> selectList;


    public PublishRedactWokDetailTypeAdapter(Context context, List<PreferenceListBean.DataBean.MajorsBean> typelist) {
        this.typelist = typelist;
        this.context = context;
        tctype = new int[]{context.getResources().getColor(R.color.blue_arlt),context.getResources().getColor(R.color.green_arlt), context.getResources().getColor(R.color.red_arlt),context.getResources().getColor(R.color.yellow_arlt)};
        initSelectList();
    }

    private void initSelectList() {

        selectList = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_wok_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.type_wok_item_tv.setTag(holder.type_wok_item_tv.isChecked());

        holder.type_wok_item_tv.setText(typelist.get(position).getName());
        if(holder.type_wok_item_tv.isChecked()) {
            holder.type_wok_item_tv.setChecked(true);
            holder.type_wok_item_tv.setBackgroundResource(bgtype[position%bgtype.length]);
            holder.type_wok_item_tv.setTextColor(tctype[position%tctype.length]);
        } else {
            holder.type_wok_item_tv.setChecked(false);
            holder.type_wok_item_tv.setBackgroundResource(R.drawable.type_gray_bg_normal);
            holder.type_wok_item_tv.setTextColor(context.getResources().getColor(R.color.gray));
        }

        holder.type_wok_item_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.type_wok_item_tv.isChecked()){

                    selectList.remove(typelist.get(position).getId()+"");
                    holder.type_wok_item_tv.setChecked(false);
                    holder.type_wok_item_tv.setBackgroundResource(R.drawable.type_gray_bg_normal);
                    holder.type_wok_item_tv.setTextColor(context.getResources().getColor(R.color.gray));
                    if(selectCallBack!=null){
                        selectCallBack.onItemSelect(selectList);
                    }

                }else {
                    holder.type_wok_item_tv.setChecked(true);
                    holder.type_wok_item_tv.setBackgroundResource(bgtype[position%bgtype.length]);
                    holder.type_wok_item_tv.setTextColor(tctype[position%tctype.length]);
                    selectList.add(typelist.get(position).getId()+"");
                    if(selectCallBack!=null){
                        selectCallBack.onItemSelect(selectList);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return typelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CheckedTextView type_wok_item_tv;

        public ViewHolder(View itemView) {
            super(itemView);

            type_wok_item_tv = itemView.findViewById(R.id.type_wok_item_tv);
        }
    }

    public void setSelectCallBack(ItemSelectCallBack selectCallBack) {
        this.selectCallBack = selectCallBack;
    }
}
