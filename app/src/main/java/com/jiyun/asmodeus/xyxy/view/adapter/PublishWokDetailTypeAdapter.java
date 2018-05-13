package com.jiyun.asmodeus.xyxy.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.jiyun.asmodeus.xyxy.R;

import java.util.List;

public class PublishWokDetailTypeAdapter extends RecyclerView.Adapter<PublishWokDetailTypeAdapter.ViewHolder> {

    private List<String> typelist;

    private Context context;

    private int[] bgtype={R.drawable.type_blue_bg_active,R.drawable.type_green_bg_active,
                            R.drawable.type_red_bg_active,R.drawable.type_yellow_bg_active};

    private int[] tctype ;

    public PublishWokDetailTypeAdapter(Context context,List<String> typelist) {
        this.typelist = typelist;
        this.context = context;
        tctype = new int[]{context.getResources().getColor(R.color.blue_arlt),context.getResources().getColor(R.color.green_arlt), context.getResources().getColor(R.color.red_arlt),context.getResources().getColor(R.color.yellow_arlt)};
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_wok_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.type_wok_item_tv.setText(typelist.get(position));
        holder.type_wok_item_tv.setBackgroundResource(bgtype[position%bgtype.length]);
        holder.type_wok_item_tv.setTextColor(tctype[position%tctype.length]);
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
}
