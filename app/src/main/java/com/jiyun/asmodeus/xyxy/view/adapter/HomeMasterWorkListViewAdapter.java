package com.jiyun.asmodeus.xyxy.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ViewUtils;
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
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.model.utils.BitmapHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import static com.jiyun.asmodeus.xyxy.model.utils.Constant.PUSH_VIDEO;



public class HomeMasterWorkListViewAdapter extends BaseAdapter {


    private Context context;

    private LayoutInflater layoutInflater;

    private OnItemShareClick onItemShareClick;

    private List<HomeBean.DataBean.HomewoksBean> homewoksBeen;

    public interface OnItemShareClick {

        void onItemShareClick(int position);

        void onReplyClick(int position);

        void onPraiseClick(int position);

        void onRewardClick(int position);

    }

    public void setOnItemShareClick(OnItemShareClick onItemShareClick) {
        this.onItemShareClick = onItemShareClick;
    }

    public HomeMasterWorkListViewAdapter(Context context, List<HomeBean.DataBean.HomewoksBean> homewoksBeen) {
        this.context = context;
        this.homewoksBeen = homewoksBeen;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return homewoksBeen.size();
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

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.home_masterwork_listview_item, null);
            viewHolder.studentimg = convertView.findViewById(R.id.home_masterwork_listitem_studentimg);
            viewHolder.studentname = convertView.findViewById(R.id.home_masterwork_listitem_studentname);
            viewHolder.time = convertView.findViewById(R.id.home_masterwork_listitem_time);

             viewHolder.from = convertView.findViewById(R.id.home_masterwork_listitem_from);
            viewHolder.content = convertView.findViewById(R.id.home_masterwork_listitem_content);
            viewHolder.contentintro = convertView.findViewById(R.id.home_masterwork_listitem_contentintro);
            viewHolder.introimg = convertView.findViewById(R.id.home_masterwork_listitem_introimg);
            viewHolder.worktype = convertView.findViewById(R.id.home_masterwork_listitem_worktype);
            viewHolder.teacherimg = convertView.findViewById(R.id.home_masterwork_listitem_teacherimg);
            viewHolder.teachername = convertView.findViewById(R.id.home_masterwork_listitem_teachername);
            viewHolder.teacherintro = convertView.findViewById(R.id.home_masterwork_listitem_teacherintro);
            viewHolder.teacherlevel = convertView.findViewById(R.id.home_masterwork_listitem_teacherlevel);
            viewHolder.share = convertView.findViewById(R.id.home_masterwork_list_item_share);
            viewHolder.reply_group = convertView.findViewById(R.id.home_masterwork_list_item_reply_group);
            viewHolder.reply_cb = convertView.findViewById(R.id.home_masterwork_list_item_reply_cb);
            viewHolder.praise_group= convertView.findViewById(R.id.home_masterwork_list_item_praise_group);
            viewHolder.praise_cb = convertView.findViewById(R.id.home_masterwork_list_item_praise_cb);
            viewHolder.reward_group = convertView.findViewById(R.id.home_masterwork_list_item_reward_group);
            viewHolder.home_masterwork_listview_item_peep = convertView.findViewById(R.id.home_masterwork_listview_item_peep);
            viewHolder.reward_cb = convertView.findViewById(R.id.home_masterwork_list_item_reward_cb);
            viewHolder.audio_group = convertView.findViewById(R.id.home_masterwork_listitem_audio_gorpu);
            viewHolder.audio_time = convertView.findViewById(R.id.home_masterwork_listitem_audio_time);
            viewHolder.video_group = convertView.findViewById(R.id.home_masterwork_listitem_video_gorpu);
            viewHolder.video_time = convertView.findViewById(R.id.home_masterwork_listitem_video_time);
            viewHolder.home_masterwork_listitem_teacher_group = convertView.findViewById(R.id.home_masterwork_listitem_teacher_group);
            viewHolder.home_masterwork_listview_item_peep_price = convertView.findViewById(R.id.home_masterwork_listview_item_peep_price);
            viewHolder.home_masterwork_listitem_teacher_group_line = convertView.findViewById(R.id.home_masterwork_listitem_teacher_group_line);
            viewHolder.introimg_group = convertView.findViewById(R.id.home_masterwork_listitem_introimg_group);
            viewHolder.audioimg = convertView.findViewById(R.id.home_masterwork_listitem_audioimg);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(context).load(homewoksBeen.get(position).getPhoto()).into(viewHolder.studentimg);
        Glide.with(context).load(homewoksBeen.get(position).getTPhoto()).into(viewHolder.teacherimg);

        viewHolder.studentname.setText(homewoksBeen.get(position).getNickname());

        viewHolder.time.setText(TimeShift.getChatTime(homewoksBeen.get(position).getCreateDate()));

        viewHolder.from.setText(homewoksBeen.get(position).getSource());

        viewHolder.content.setText(homewoksBeen.get(position).getContent());

        if (TextUtils.equals(Constant.PUSH_AUDIO, homewoksBeen.get(position).getWorksType())) {
            BitmapHelp.setAudioRelayout(context, viewHolder.audio_group);
            if (!TextUtils.isEmpty(homewoksBeen.get(position).getDuration())) {
                viewHolder.audio_time.setText(homewoksBeen.get(position).getDuration());
            } else {
                viewHolder.audio_time.setText("");
            }
            viewHolder.audioimg.setVisibility(View.VISIBLE);
            viewHolder.introimg.setVisibility(View.GONE);
            viewHolder.audio_group.setVisibility(View.VISIBLE);
            viewHolder.video_group.setVisibility(View.GONE);
        } else if (TextUtils.equals(Constant.PUSH_IMAGE, homewoksBeen.get(position).getWorksType())) {
            if (!TextUtils.isEmpty(homewoksBeen.get(position).getPicProperty())) {
                BitmapHelp.setImageView(context, homewoksBeen.get(position).getPicProperty(), viewHolder.introimg);
            }
            viewHolder.audioimg.setVisibility(View.GONE);
            viewHolder.introimg.setVisibility(View.VISIBLE);
            Glide.with(context).load(homewoksBeen.get(position).getCoverImg()).into(viewHolder.introimg);
            viewHolder.audio_group.setVisibility(View.GONE);
            viewHolder.video_group.setVisibility(View.GONE);
        } else if (TextUtils.equals(PUSH_VIDEO, homewoksBeen.get(position).getWorksType())) {
            if (!TextUtils.isEmpty(homewoksBeen.get(position).getPicProperty())) {
                BitmapHelp.setImageView(context, homewoksBeen.get(position).getPicProperty(), viewHolder.introimg);
            }
            viewHolder.audioimg.setVisibility(View.GONE);
            viewHolder.introimg.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(homewoksBeen.get(position).getDuration())) {
                viewHolder.video_time.setText(homewoksBeen.get(position).getDuration());
            }

            Glide.with(context).load(homewoksBeen.get(position).getCoverImg()).into(viewHolder.introimg);
            viewHolder.audio_group.setVisibility(View.GONE);
            viewHolder.video_group.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(homewoksBeen.get(position).getMajorIds())) {

//            SplitStringColorUtils.addForeColorSpan(context, homewoksBeen.get(position).getMajorIds().split(","), viewHolder.worktype);
        }

        //是否有老师点评
        if (homewoksBeen.get(position).getAnswerDate() != 0) {

            if (homewoksBeen.get(position).getAnswerPermission() == Constant.NOTFAVORITE) {

                viewHolder.home_masterwork_listitem_teacher_group.setVisibility(View.VISIBLE);

                viewHolder.home_masterwork_listitem_teacher_group_line.setVisibility(View.VISIBLE);

                viewHolder.teachername.setText(homewoksBeen.get(position).getTRealname());

                viewHolder.teacherintro.setText(homewoksBeen.get(position).getTIntro());

                SplitStringColorUtils.setImgLevel(viewHolder.teacherlevel, homewoksBeen.get(position).getTUserType());

                if (homewoksBeen.get(position).getIsPeep() == 0) {
                    viewHolder.home_masterwork_listview_item_peep_price.setText(String.format("%s元偷看", homewoksBeen.get(position).getPeepPrice()));
                } else {
                    viewHolder.home_masterwork_listview_item_peep_price.setText("查看");
                }

            } else {

                viewHolder.home_masterwork_listitem_teacher_group.setVisibility(View.GONE);
                viewHolder.home_masterwork_listitem_teacher_group_line.setVisibility(View.GONE);

            }

        } else {

            viewHolder.home_masterwork_listitem_teacher_group.setVisibility(View.GONE);
            viewHolder.home_masterwork_listitem_teacher_group_line.setVisibility(View.GONE);
        }


        if (homewoksBeen.get(position).getCommentNum() != 0) {
            viewHolder.reply_cb.setText(homewoksBeen.get(position).getCommentNum() + "");
        } else {
            viewHolder.reply_cb.setText("");
        }

        if (homewoksBeen.get(position).getPraiseNum() != 0) {
            viewHolder.praise_cb.setText(homewoksBeen.get(position).getPraiseNum() + "");
        } else {
            viewHolder.praise_cb.setText("");
        }

        //是否点赞
        if (homewoksBeen.get(position).getIsPraise() == Constant.NOTFAVORITE) {

            viewHolder.praise_cb.setChecked(false);

        } else {
            //点赞

            viewHolder.praise_cb.setChecked(true);
        }

        if (homewoksBeen.get(position).getGiftNum() != 0) {
            viewHolder.reward_cb.setText(homewoksBeen.get(position).getGiftNum() + "");
        } else {
            viewHolder.reward_cb.setText("");
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
                if (viewHolder.praise_cb.isChecked()) {
                    viewHolder.praise_cb.setChecked(false);
                    if (homewoksBeen.get(position).getPraiseNum() == 0) {
                        viewHolder.praise_cb.setText(homewoksBeen.get(position).getPraiseNum() + "");
                    } else {
                        homewoksBeen.get(position).setPraiseNum(homewoksBeen.get(position).getPraiseNum() - 1);
                        viewHolder.praise_cb.setText(homewoksBeen.get(position).getPraiseNum() + "");
                    }
                } else {
                    viewHolder.praise_cb.setChecked(true);
                    homewoksBeen.get(position).setPraiseNum(homewoksBeen.get(position).getPraiseNum() + 1);
                    viewHolder.praise_cb.setText(homewoksBeen.get(position).getPraiseNum() + "");
                }
                if (onItemShareClick != null) {

                    onItemShareClick.onPraiseClick(position);
                }
            }
        });

        viewHolder.praise_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.praise_cb.isChecked()) {
                    viewHolder.praise_cb.setChecked(true);
                    homewoksBeen.get(position).setPraiseNum(homewoksBeen.get(position).getPraiseNum() + 1);
                    viewHolder.praise_cb.setText(homewoksBeen.get(position).getPraiseNum() + "");
                } else {
                    viewHolder.praise_cb.setChecked(false);
                    if (homewoksBeen.get(position).getPraiseNum() == 0) {
                        viewHolder.praise_cb.setText(homewoksBeen.get(position).getPraiseNum() + "");
                    } else {
                        homewoksBeen.get(position).setPraiseNum(homewoksBeen.get(position).getPraiseNum() - 1);
                        viewHolder.praise_cb.setText(homewoksBeen.get(position).getPraiseNum() + "");
                    }
                }
                if (onItemShareClick != null) {

                    onItemShareClick.onPraiseClick(position);
                }
            }
        });

        //打赏
        viewHolder.reward_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.reward_cb.setChecked(true);

            }
        });
        viewHolder.reward_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.reward_cb.setChecked(true);

            }
        });

        viewHolder.home_masterwork_listview_item_peep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        viewHolder.studentimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        viewHolder.teacherimg.setOnClickListener(new View.OnClickListener() {
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


    private class ViewHolder {
        private RoundedImageView studentimg;
        private TextView studentname;
        private TextView time;
        private TextView from;
        private TextView content;
        private TextView contentintro;
        private ImageView introimg;
        private TextView worktype;
        private RoundedImageView teacherimg;
        private TextView teachername;
        private TextView teacherintro;
        private ImageView teacherlevel;
        private LinearLayout share;
        private LinearLayout reply_group;
        private CheckBox reply_cb;
        private LinearLayout praise_group;
        private CheckBox praise_cb;
        private LinearLayout reward_group;
        private LinearLayout home_masterwork_listview_item_peep;
        private CheckBox reward_cb;
        private RelativeLayout audio_group;
        private TextView audio_time;
        private LinearLayout video_group;
        private TextView video_time;
        private RelativeLayout home_masterwork_listitem_teacher_group;
        private TextView home_masterwork_listview_item_peep_price;
        private TextView home_masterwork_listitem_teacher_group_line;
        private RelativeLayout introimg_group;
        private ImageView audioimg;
    }

}
