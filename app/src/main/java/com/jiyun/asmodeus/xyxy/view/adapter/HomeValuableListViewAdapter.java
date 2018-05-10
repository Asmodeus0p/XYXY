package com.jiyun.asmodeus.xyxy.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;
import com.jiyun.asmodeus.xyxy.model.utils.BitmapHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;



public class HomeValuableListViewAdapter extends BaseAdapter {


    private Context context;

    private LayoutInflater layoutInflater;

    private OnValuableItemShareClick onItemShareClick;


    public interface OnValuableItemShareClick{

        void onItemShareClick(int position);
        void onReplyClick(int position);
        void onPraiseClick(int position);
        void onFavoriteClick(int position);

    }

    private List<HomeValuanleBean.DataBean.ArtcircleList.ListBean> list;

    public HomeValuableListViewAdapter(Context context ,List<HomeValuanleBean.DataBean.ArtcircleList.ListBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }


    public void setOnValuableItemShareClick(OnValuableItemShareClick onItemShareClick) {
        this.onItemShareClick = onItemShareClick;
    }
    @Override
    public int getCount() {
        return list.size();
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
            convertView = layoutInflater.inflate(R.layout.home_valuable_listview_item, null);
            ViewUtils.inject(viewHolder,convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(context).load(list.get(position).getPhoto()).into(viewHolder.img);
        if(TextUtils.equals(Constant.PUSH_AUDIO,list.get(position).getWorksType())){
            BitmapHelp.setAudioRelayout(context,viewHolder.audio_group);
            if(!TextUtils.isEmpty(list.get(position).getDuration())){
                viewHolder.audio_time.setText(list.get(position).getDuration());
            }else{
                viewHolder.audio_time.setText("");
            }
            viewHolder.introimg.setVisibility(View.GONE);
            viewHolder.audioimg.setVisibility(View.VISIBLE);
            viewHolder.audio_group.setVisibility(View.VISIBLE);
            viewHolder.video_group.setVisibility(View.GONE);
        }else if(TextUtils.equals(Constant.PUSH_IMAGE,list.get(position).getWorksType())){
            if(!TextUtils.isEmpty(list.get(position).getPicProperty())) {
                BitmapHelp.setImageView(context,list.get(position).getPicProperty(),viewHolder.introimg);
            }
            viewHolder.audioimg.setVisibility(View.GONE);
            viewHolder.introimg.setVisibility(View.VISIBLE);
            Glide.with(context).load(list.get(position).getCoverImg()).into(viewHolder.introimg);
            viewHolder.audio_group.setVisibility(View.GONE);
            viewHolder.video_group.setVisibility(View.GONE);
        }else if(TextUtils.equals(Constant.PUSH_VIDEO,list.get(position).getWorksType())){
            if(!TextUtils.isEmpty(list.get(position).getPicProperty())) {
                BitmapHelp.setImageView(context,list.get(position).getPicProperty(),viewHolder.introimg);
            }
            if(!TextUtils.isEmpty(list.get(position).getDuration())){
                viewHolder.video_time.setText(list.get(position).getDuration());
            }
            viewHolder.audioimg.setVisibility(View.GONE);
            viewHolder.introimg.setVisibility(View.VISIBLE);

            Glide.with(context).load(list.get(position).getCoverImg()).into(viewHolder.introimg);
            viewHolder.audio_group.setVisibility(View.GONE);
            viewHolder.video_group.setVisibility(View.VISIBLE);
        }else{
            viewHolder.introimg.setVisibility(View.GONE);
            viewHolder.audio_group.setVisibility(View.GONE);
            viewHolder.video_group.setVisibility(View.GONE);
            viewHolder.audioimg.setVisibility(View.GONE);
        }




            viewHolder.name.setText(list.get(position).getRealname());



            viewHolder.name.setText(list.get(position).getNickname());


        if(TextUtils.isEmpty(list.get(position).getContentType())){
            viewHolder.content_type.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.content_type.setVisibility(View.VISIBLE);
            viewHolder.content_type.setText(list.get(position).getContentType());
        }

        viewHolder.time.setText(TimeShift.getChatTime(list.get(position).getCreateDate()));

        viewHolder.title.setText(list.get(position).getTitle());

        viewHolder.content.setText(list.get(position).getContent());

        if(!TextUtils.isEmpty(list.get(position).getMajorIds())){

//            SplitStringColorUtils.addForeColorSpan(context,list.get(position).getMajorIds().split(","),viewHolder.worktype);
        }

        //是否点赞
        if(list.get(position).getIsPraise()==Constant.NOTFAVORITE){

            viewHolder.praise_cb.setChecked(false);

        }else{
            viewHolder.praise_cb.setChecked(true);
        }

        //是否关注
        if(list.get(position).getIsFavorite()==Constant.NOTFAVORITE){

            viewHolder.collect_cb.setChecked(false);

        }else{
            viewHolder.collect_cb.setChecked(true);
        }


        //关注
        if(list.get(position).getFavoriteNum()!=0){
            viewHolder.collect_cb.setText(list.get(position).getFavoriteNum()+"");
        }else{
            viewHolder.collect_cb.setText("");
        }

        if(list.get(position).getCommentNum()!=0){
            viewHolder.reply_cb.setText(list.get(position).getCommentNum()+"");
        }else{
            viewHolder.reply_cb.setText("");
        }

        if(list.get(position).getPraiseNum()!=0){
            viewHolder.praise_cb.setText(list.get(position).getPraiseNum()+"");
        }else{
            viewHolder.praise_cb.setText("");
        }


        //评论
        viewHolder.reply_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.reply_cb.setChecked(false);

            }
        });
        viewHolder.reply_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.reply_cb.setChecked(false);

            }
        });

        //点赞
        viewHolder.praise_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.praise_cb.isChecked()){
                    viewHolder.praise_cb.setChecked(false);
                    if(list.get(position).getPraiseNum()==0){
                        viewHolder.praise_cb.setText(list.get(position).getPraiseNum()+"");
                    }else{
                        list.get(position).setPraiseNum(list.get(position).getPraiseNum()-1);
                        viewHolder.praise_cb.setText(list.get(position).getPraiseNum()+"");
                    }
                }else{
                    viewHolder.praise_cb.setChecked(true);
                    list.get(position).setPraiseNum(list.get(position).getPraiseNum()+1);
                    viewHolder.praise_cb.setText(list.get(position).getPraiseNum()+"");
                }
                if(onItemShareClick!=null){

                    onItemShareClick.onPraiseClick(position);
                }
            }
        });

        viewHolder.praise_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.praise_cb.isChecked()){
                    viewHolder.praise_cb.setChecked(true);
                    list.get(position).setPraiseNum(list.get(position).getPraiseNum()+1);
                    viewHolder.praise_cb.setText(list.get(position).getPraiseNum()+"");
                }else{
                    viewHolder.praise_cb.setChecked(false);
                    if(list.get(position).getPraiseNum()==0){
                        viewHolder.praise_cb.setText(list.get(position).getPraiseNum()+"");
                    }else{
                        list.get(position).setPraiseNum(list.get(position).getPraiseNum()-1);
                        viewHolder.praise_cb.setText(list.get(position).getPraiseNum()+"");
                    }
                }
                if(onItemShareClick!=null){

                    onItemShareClick.onPraiseClick(position);
                }
            }
        });

        //关注
        viewHolder.collect_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.collect_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //分享
        viewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return convertView;
    }


    private class ViewHolder{

        @ViewInject(R.id.home_valuable_listitem_img)
        private RoundedImageView img;

        @ViewInject(R.id.home_valuable_listitem_name)
        private TextView name;

        @ViewInject(R.id.home_valuable_listitem_contenttype)
        private TextView content_type;

        @ViewInject(R.id.home_valuable_listitem_time)
        private TextView time;

        @ViewInject(R.id.home_valuable_listitem_title)
        private TextView title;

        @ViewInject(R.id.home_valuable_listitem_content)
        private TextView content;

        @ViewInject(R.id.home_valuable_listitem_contentimg)
        private ImageView introimg;

        @ViewInject(R.id.home_valuable_listitem_worktype)
        private TextView worktype;

        @ViewInject(R.id.home_valuable_list_item_share)
        private LinearLayout share;

        @ViewInject(R.id.home_valuable_list_item_reply_group)
        private LinearLayout reply_group;

        @ViewInject(R.id.home_valuable_list_item_reply_cb)
        private CheckBox reply_cb;

        @ViewInject(R.id.home_valuable_list_item_praise_group)
        private LinearLayout praise_group;

        @ViewInject(R.id.home_valuable_list_item_praise_cb)
        private CheckBox praise_cb;

        @ViewInject(R.id.home_valuable_list_item_collect_group)
        private LinearLayout collect_group;

        @ViewInject(R.id.home_valuable_list_item_collect_cb)
        private CheckBox collect_cb;

        @ViewInject(R.id.home_valuable_listitem_audio_gorpu)
        private RelativeLayout audio_group;

        @ViewInject(R.id.home_valuable_listitem_audio_time)
        private TextView audio_time;

        @ViewInject(R.id.home_valuable_listitem_video_gorpu)
        private LinearLayout video_group;

        @ViewInject(R.id.home_valuable_listitem_video_time)
        private TextView video_time;

        @ViewInject(R.id.home_valuable_listitem_contentimg_group)
        private RelativeLayout contentimg_group;

        @ViewInject(R.id.home_valuable_listitem_audioimg)
        private ImageView audioimg;
    }

}
