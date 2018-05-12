package com.jiyun.asmodeus.xyxy.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.WokDetailBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.jiyun.asmodeus.xyxy.view.fragment.LoginActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class WorkDetailCommentAdapter extends BaseAdapter {

    private Context context;

    private OnItemShareClick onItemShareClick;

    public interface OnItemShareClick{

        void onPraiseClick(int position);

        void onCommentClick(int position);

        void onReplyAt(int position);
    }

    public void setOnItemShareClick(OnItemShareClick onItemShareClick){
        this.onItemShareClick = onItemShareClick;
    }

    private List<WokDetailBean.DataBean.CommentsBean.ListBean> commentList;

    private LayoutInflater layoutInflater;

    public WorkDetailCommentAdapter(Context context, List<WokDetailBean.DataBean.CommentsBean.ListBean> commentList) {
        this.context = context;
        this.commentList = commentList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return commentList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if(convertView==null){
            viewHolder =new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.work_detail_comment_listitem, null);

            ViewUtils.inject(viewHolder,convertView);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(commentList.get(position).getPhoto()).into((ImageView) viewHolder.userimg);
        viewHolder.name.setText(commentList.get(position).getNickname());

        SplitStringColorUtils.setImgLevel((ImageView) viewHolder.usertype,commentList.get(position).getUserType());

        viewHolder.time.setText(TimeShift.getChatTime(commentList.get(position).getCreateDate()));

        viewHolder.content.setText(commentList.get(position).getContent());

        viewHolder.praise.setText(commentList.get(position).getPraiseNum()+"");

        if(commentList.get(position).getReplyId()!=0){
            viewHolder.work_detail_comment_listitem_atname.setVisibility(View.VISIBLE);
            viewHolder.work_detail_comment_listitem_atname.setText(String.format("@%s",commentList.get(position).getReplyName()));
        }else{
            viewHolder.work_detail_comment_listitem_atname.setVisibility(View.GONE);
        }


        if(Constant.NOTFAVORITE==commentList.get(position).getIsPraise()){
            viewHolder.praise.setChecked(false);
        }else{
            viewHolder.praise.setChecked(true);
        }

        if(Constant.NOTFAVORITE==commentList.get(position).getReplyNum()){

            viewHolder.replynum.setVisibility(View.GONE);

        }else{

            viewHolder.replynum.setVisibility(View.VISIBLE);

            viewHolder.replynum.setText(String.format("共%s条回复",commentList.get(position).getReplyNum()));
        }

        viewHolder.praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!HttpHelp.isLogin(context)){
                  context.startActivity(new Intent(context,LoginActivity.class));
                    viewHolder.praise.setChecked(!viewHolder.praise.isChecked());
                    return ;
                }

                if(viewHolder.praise.isChecked()){
                    viewHolder.praise.setChecked(true);
                    commentList.get(position).setPraiseNum(commentList.get(position).getPraiseNum()+1);
                    viewHolder.praise.setText(commentList.get(position).getPraiseNum()+"");
                }else{
                    viewHolder.praise.setChecked(false);
                    if(commentList.get(position).getPraiseNum()==0){
                        viewHolder.praise.setText(commentList.get(position).getPraiseNum()+"");
                    }else{
                        commentList.get(position).setPraiseNum(commentList.get(position).getPraiseNum()-1);
                        viewHolder.praise.setText(commentList.get(position).getPraiseNum()+"");
                    }
                }
                if(onItemShareClick!=null){

                    onItemShareClick.onPraiseClick(position);
                }
            }
        });

        viewHolder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!HttpHelp.isLogin(context)){
                    context.startActivity(new Intent(context,LoginActivity.class));
                    return ;
                }

                if(onItemShareClick!=null){

                    onItemShareClick.onCommentClick(position);
                }

            }
        });
        viewHolder.replynum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!HttpHelp.isLogin(context)){
                    context.startActivity(new Intent(context,LoginActivity.class));
                    return ;
                }

                if(onItemShareClick!=null){

                    onItemShareClick.onCommentClick(position);
                }
            }
        });

        viewHolder.work_detail_comment_listitem_atname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!HttpHelp.isLogin(context)){
                    context.startActivity(new Intent(context,LoginActivity.class));
                    return ;
                }

                if(onItemShareClick!=null){

                    onItemShareClick.onReplyAt(position);
                }
            }
        });

        return convertView;
    }

    private class ViewHolder{

        @ViewInject(R.id.work_detail_comment_listitem_img)
        private RoundedImageView userimg;

        @ViewInject(R.id.work_detail_comment_listitem_name)
        private TextView name;

        @ViewInject(R.id.work_detail_comment_listitem_usertype)
        private ImageView usertype;

        @ViewInject(R.id.work_detail_comment_listitem_time)
        private TextView time;

        @ViewInject(R.id.work_detail_comment_listitem_content)
        private TextView content;

        @ViewInject(R.id.work_detail_comment_listitem_praise_cb)
        private CheckBox praise;

        @ViewInject(R.id.work_detail_comment_listitem_comment)
        private TextView comment;

        @ViewInject(R.id.work_detail_comment_listitem_replyNum)
        private TextView replynum;

        @ViewInject(R.id.work_detail_comment_listitem_atname)
        private TextView work_detail_comment_listitem_atname;

    }
}
