package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.MessageListContract;
import com.jiyun.asmodeus.xyxy.model.entity.SystemMessageListBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.view.adapter.MessageListAdapter;

import java.util.List;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener, MessageListContract.messageListView {

    private TextView message_list_aty_cancle;

    private List<SystemMessageListBean.DataBean> datalist;

    private MessageListAdapter adapter;
    private RecyclerView message_list_aty_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
    }

    private void initView() {
        message_list_aty_cancle = (TextView) findViewById(R.id.message_list_aty_cancle);
        message_list_aty_cancle.setOnClickListener(this);

        message_list_aty_recyclerview = (RecyclerView) findViewById(R.id.message_list_aty_recyclerview);
        message_list_aty_recyclerview.setOnClickListener(this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        message_list_aty_recyclerview.setLayoutManager(linearLayoutManager);
//
//        message_list_aty_recyclerview.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Intent intent = new Intent();
//                if(TextUtils.equals(Constant.MSG_ORDER,datalist.get(position).getType())){
//                    //订单
//                    intent.setClass(context, MessageChildListAty.class);
//                    intent.putExtra(Constant.Sort_Type, datalist.get(position).getType());
//                    intent.putExtra(Constant.Teacher_Title, "订单提醒");
//                }
//                if(TextUtils.equals(Constant.MSG_TEACHING,datalist.get(position).getType())){
//                    //直播
//                    intent.setClass(context, MessageChildListAty.class);
//                    intent.putExtra(Constant.Sort_Type, datalist.get(position).getType());
//                    intent.putExtra(Constant.Teacher_Title, "教学提醒");
//                }
//                if(TextUtils.equals(Constant.MSG_HOMEWOK,datalist.get(position).getType())){
//                    //作业
//                    intent.setClass(context, MessageChildListAty.class);
//                    intent.putExtra(Constant.Sort_Type, datalist.get(position).getType());
//                    intent.putExtra(Constant.Teacher_Title, "作业提醒");
//                }
//                if(TextUtils.equals(Constant.MSG_UNIVSTAR,datalist.get(position).getType())){
//                    //官方
//                    intent.setClass(context, MessageChildListAty.class);
//                    intent.putExtra(Constant.Sort_Type, datalist.get(position).getType());
//                    intent.putExtra(Constant.Teacher_Title, "官方消息");
//                }
//
//
//
//                if(TextUtils.equals(Constant.MSG_PRAISE,datalist.get(position).getType())){
//                    //点赞
//                    intent.setClass(context, MessageChildCommentListAty.class);
//                    intent.putExtra(Constant.Sort_Type, datalist.get(position).getType());
//                    intent.putExtra(Constant.Teacher_Title, "点赞提醒");
//                }
//
//                if(TextUtils.equals(Constant.MSG_COMMENTS,datalist.get(position).getType())){
//                    //评论
//                    intent.setClass(context, MessageChildCommentListAty.class);
//                    intent.putExtra(Constant.Sort_Type, datalist.get(position).getType());
//                    intent.putExtra(Constant.Teacher_Title, "评论提醒");
//                }
//                if(TextUtils.equals(Constant.MSG_ATTENTION,datalist.get(position).getType())){
//                    //关注
//                    intent.setClass(context, MessageChildAttListAty.class);
//                    intent.putExtra(Constant.Teacher_Title, "关注提醒");
//                }
//
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_list_aty_cancle:
                finish();
                break;
        }
    }

    @Override
    public void loadMessageListDatas(SystemMessageListBean messageListBean) {

    }
}
