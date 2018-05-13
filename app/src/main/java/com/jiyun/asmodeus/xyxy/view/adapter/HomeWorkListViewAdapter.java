package com.jiyun.asmodeus.xyxy.view.adapter;


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
import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.HomeWokListBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;
import com.jiyun.asmodeus.xyxy.model.utils.BitmapHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.model.utils.TimeShift;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.List;



public class HomeWorkListViewAdapter extends BaseAdapter {


    private Context context;

    private LayoutInflater layoutInflater;

    private OnItemShareClick onItemShareClick;

    private List<WorkBean.DataBean.ListBean> homewoksBeen;


    public interface OnItemShareClick {

        void onItemShareClick(int position);

        void onReplyClick(int position);

        void onPraiseClick(int position);

        void onRewardClick(int position);
//        void onPayOrder(int position);
    }

    public void setOnItemShareClick(OnItemShareClick onItemShareClick) {
        this.onItemShareClick = onItemShareClick;
    }

    public HomeWorkListViewAdapter(Context context, List<WorkBean.DataBean.ListBean> homewoksBeen) {
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

            ViewUtils.inject(viewHolder, convertView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(context).load( homewoksBeen.get(position).getPhoto()).into(viewHolder.studentimg);
        Glide.with(context).load( homewoksBeen.get(position).getTPhoto()).into( viewHolder.teacherimg);
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
            Glide.with(context).load(  homewoksBeen.get(position).getCoverImg()).into( viewHolder.introimg);
            viewHolder.audio_group.setVisibility(View.GONE);
            viewHolder.video_group.setVisibility(View.GONE);
        } else if (TextUtils.equals(Constant.PUSH_VIDEO, homewoksBeen.get(position).getWorksType())) {
            if (!TextUtils.isEmpty(homewoksBeen.get(position).getPicProperty())) {
                BitmapHelp.setImageView(context, homewoksBeen.get(position).getPicProperty(), viewHolder.introimg);
            }
            if (!TextUtils.isEmpty(homewoksBeen.get(position).getDuration())) {
                viewHolder.video_time.setText(homewoksBeen.get(position).getDuration());
            }
            viewHolder.audioimg.setVisibility(View.GONE);
            viewHolder.introimg.setVisibility(View.VISIBLE);

            Glide.with(context).load( homewoksBeen.get(position).getCoverImg()).into( viewHolder.introimg);

            viewHolder.audio_group.setVisibility(View.GONE);
            viewHolder.video_group.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(homewoksBeen.get(position).getMajorIds())) {

//            SplitStringColorUtils.addForeColorSpan(App.context, homewoksBeen.get(position).getMajorIds().split(","), viewHolder.worktype);
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
//                WokDetailAty.start((Activity) context, homewoksBeen.get(position).getId());
//                if(onItemShareClick!=null){
//
//                    onItemShareClick.onReplyClick(position);
//                }
            }
        });
        viewHolder.reply_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.reply_cb.setChecked(false);
//                WokDetailAty.start((Activity) context, homewoksBeen.get(position).getId());
//                if(onItemShareClick!=null){
//
//                    onItemShareClick.onReplyClick(position);
//                }
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


        viewHolder.home_masterwork_listview_item_peep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (homewoksBeen.get(position).getIsPeep() == 0) {
//
//                    if (!HttpHelp.isLogin(context)) {
//                        context.startActivity(new Intent(context, LoginAty.class));
//                        return;
//                    } else {
//                        new AlertBottom((Activity) context, homewoksBeen.get(position).getId()).peepWindePay(viewHolder.home_masterwork_listview_item_peep,
//                                HttpHelp.getUserId(context),
//                                homewoksBeen.get(position).getStudentId(),
//                                homewoksBeen.get(position).getId(), homewoksBeen.get(position).getPeepPrice());
//                    }
//                } else {
//
//                    WokDetailAty.start((Activity) context, homewoksBeen.get(position).getId());
//
//                }

            }
        });

        //打赏
        viewHolder.reward_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.reward_cb.setChecked(true);
//                WokDetailAty.start((Activity) context, homewoksBeen.get(position).getId());
//                if(onItemShareClick!=null){
//
//                    onItemShareClick.onRewardClick(position);
//                }
            }
        });
        viewHolder.reward_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.reward_cb.setChecked(true);
//                WokDetailAty.start((Activity) context, homewoksBeen.get(position).getId());
//                if(onItemShareClick!=null){
//
//                    onItemShareClick.onRewardClick(position);
//                }
            }
        });


        viewHolder.studentimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (homewoksBeen.get(position).getUserType() > 1) {
//                    MasterDetailAty.start((Activity) context, homewoksBeen.get(position).getStudentId());
//                } else {
//                    PersonInfoAty.start((Activity) context, homewoksBeen.get(position).getStudentId());
//
//                }

            }
        });


        viewHolder.teacherimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MasterDetailAty.start((Activity) context, homewoksBeen.get(position).getTUserId());
            }
        });


        //分享
        viewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homewoksBeen.get(position).getIsAnswer() == Constant.NOTFAVORITE) {
//                    new AlertBottom((Activity) context).popupAlter(viewHolder.share, String.format(Constant.WORK_SHARE_URL, homewoksBeen.get(position).getId()), String.format("%s的作品,%s名师已辅导", homewoksBeen.get(position).getNickname(), homewoksBeen.get(position).getTRealname()), Constant.SHARE_CONTENT);
                } else {
//                    new AlertBottom((Activity) context).popupAlter(viewHolder.share, String.format(Constant.WORK_SHARE_URL, homewoksBeen.get(position).getId()), String.format("%s的作品", homewoksBeen.get(position).getNickname()), Constant.SHARE_CONTENT);
                }

            }
        });

        return convertView;
    }


    private class ViewHolder {

        @ViewInject(R.id.home_masterwork_list_item_group)
        private LinearLayout home_masterwork_list_item_group;

        @ViewInject(R.id.home_masterwork_listitem_studentimg)
        private RoundedImageView studentimg;

        @ViewInject(R.id.home_masterwork_listitem_studentname)
        private TextView studentname;

        @ViewInject(R.id.home_masterwork_listitem_time)
        private TextView time;

        @ViewInject(R.id.home_masterwork_listitem_from)
        private TextView from;

        @ViewInject(R.id.home_masterwork_listitem_content)
        private TextView content;

        @ViewInject(R.id.home_masterwork_listitem_contentintro)
        private TextView contentintro;

        @ViewInject(R.id.home_masterwork_listitem_introimg)
        private ImageView introimg;

        @ViewInject(R.id.home_masterwork_listitem_worktype)
        private TextView worktype;

        @ViewInject(R.id.home_masterwork_listitem_teacherimg)
        private RoundedImageView teacherimg;

        @ViewInject(R.id.home_masterwork_listitem_teachername)
        private TextView teachername;

        @ViewInject(R.id.home_masterwork_listitem_teacherintro)
        private TextView teacherintro;

        @ViewInject(R.id.home_masterwork_listitem_teacherlevel)
        private ImageView teacherlevel;

        @ViewInject(R.id.home_masterwork_list_item_share)
        private LinearLayout share;

        @ViewInject(R.id.home_masterwork_list_item_reply_group)
        private LinearLayout reply_group;

        @ViewInject(R.id.home_masterwork_list_item_reply_cb)
        private CheckBox reply_cb;

        @ViewInject(R.id.home_masterwork_list_item_praise_group)
        private LinearLayout praise_group;

        @ViewInject(R.id.home_masterwork_list_item_praise_cb)
        private CheckBox praise_cb;

        @ViewInject(R.id.home_masterwork_list_item_reward_group)
        private LinearLayout reward_group;

        @ViewInject(R.id.home_masterwork_listview_item_peep)
        private LinearLayout home_masterwork_listview_item_peep;

        @ViewInject(R.id.home_masterwork_list_item_reward_cb)
        private CheckBox reward_cb;

        @ViewInject(R.id.home_masterwork_listitem_audio_gorpu)
        private RelativeLayout audio_group;

        @ViewInject(R.id.home_masterwork_listitem_video_gorpu)
        private LinearLayout video_group;

        @ViewInject(R.id.home_masterwork_listitem_audio_time)
        private TextView audio_time;

        @ViewInject(R.id.home_masterwork_listitem_video_time)
        private TextView video_time;

        @ViewInject(R.id.home_masterwork_listitem_teacher_group)
        private RelativeLayout home_masterwork_listitem_teacher_group;

        @ViewInject(R.id.home_masterwork_listview_item_peep_price)
        private TextView home_masterwork_listview_item_peep_price;

        @ViewInject(R.id.home_masterwork_listitem_teacher_group_line)
        private TextView home_masterwork_listitem_teacher_group_line;

        @ViewInject(R.id.home_masterwork_listitem_introimg_group)
        private RelativeLayout introimg_group;

        @ViewInject(R.id.home_masterwork_listitem_audioimg)
        private ImageView audioimg;
    }

}
