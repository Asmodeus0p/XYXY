package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;


import java.util.List;

public class RecyclerValuableTitleAdapter extends RecyclerView.Adapter<RecyclerValuableTitleAdapter.ViewHolder>{

    private Context context;

    private List<HomeValuanleBean.DataBean.ArtcircleCategoriesBean> contentSortlis;

    private TextView tag;

    private Integer curentInt;

    public void setCurentInt(Integer integer){
        curentInt = integer;
        this.notifyDataSetChanged();
    }

    private RecyclerValuableSingleSortListener listener;

    public void setListener(RecyclerValuableSingleSortListener listener) {
        this.listener = listener;
    }

    public interface RecyclerValuableSingleSortListener{
        void onItemClickListener(int position);
    }

    public RecyclerValuableTitleAdapter(List<HomeValuanleBean.DataBean.ArtcircleCategoriesBean> contentSortlis) {
        this.contentSortlis = contentSortlis;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_valuable_sort_recycleritem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv.setText(contentSortlis.get(position).getName());

        if(contentSortlis.get(position).isSort()) {
            holder.line.setVisibility(View.VISIBLE);
            curentInt = position;
            tag=holder.line;
        }else{
            contentSortlis.get(position).setSort(false);
            holder.line.setVisibility(View.INVISIBLE);
        }

        holder.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag!=null){
                    contentSortlis.get(curentInt).setSort(false);
                    tag.setVisibility(View.INVISIBLE);
                }
                holder.line.setVisibility(View.VISIBLE);
                contentSortlis.get(position).setSort(false);
                tag = holder.line;
                curentInt = position;
                if(listener!=null){

                    listener.onItemClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentSortlis.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        private TextView line;

        private RelativeLayout group;

        public ViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.home_valuable_sort_recycleritem_tv);

            line = itemView.findViewById(R.id.home_valuable_sort_recycleritem_line);

            group = itemView.findViewById(R.id.home_work_fragment_capacity_group);
        }
    }

}
