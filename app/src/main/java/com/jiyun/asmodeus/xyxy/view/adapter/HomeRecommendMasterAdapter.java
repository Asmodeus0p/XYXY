package com.jiyun.asmodeus.xyxy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;


import java.util.List;


/**
 * Created by vicoltree on 17/10/26.
 */

public class HomeRecommendMasterAdapter extends RecyclerView.Adapter<HomeRecommendMasterAdapter.ViewHolder> implements View.OnClickListener {


    private  List<HomeBean.DataBean.UsersBean> usersbean;

    private Context context;

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!=null){

            mOnItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public HomeRecommendMasterAdapter(List<HomeBean.DataBean.UsersBean> usersbean) {
        this.usersbean = usersbean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recommendmaster_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(usersbean.get(position).getImages()).into(holder.img);
        holder.name.setText(usersbean.get(position).getRealname());

        holder.intro.setText(usersbean.get(position).getIntro());

        SplitStringColorUtils.setImgLevel(holder.usertype,usersbean.get(position).getUserType());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return usersbean.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;

        private TextView name;

        private TextView intro;

        private ImageView usertype;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.home_recommendmaster_item_img);
            name = itemView.findViewById(R.id.home_recommendmaster_item_name);
            intro = itemView.findViewById(R.id.home_recommendmaster_item_intro);
            usertype = itemView.findViewById(R.id.home_recommendmaster_item_usertype);
        }
    }

}
