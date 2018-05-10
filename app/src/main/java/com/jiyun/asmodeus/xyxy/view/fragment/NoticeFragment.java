package com.jiyun.asmodeus.xyxy.view.fragment;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.NoticeContract;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;
import com.jiyun.asmodeus.xyxy.presenter.NoticePresenterImp;
import com.jiyun.asmodeus.xyxy.view.adapter.NoticeAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends BaseFragment implements NoticeContract.NoticaView,View.OnClickListener{
    private NoticePresenterImp presenterImp;
    private RecyclerView mRecy;
    private List<NoticeBean.DataBean.ListBean> list;
    private NoticeAdapter adapter;
    private TextView time_chose;
    private PopupWindow popupWindow;
    private LinearLayout linearLayout;
    private Button data_queding;
    private Button data_chongzhi;
    private EditText end_data;
    private EditText start_data;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void init() {
        list=new ArrayList<>();
        mRecy=getView().findViewById(R.id.yugaoRecy);
        time_chose=getView().findViewById(R.id.time_chose);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mRecy.setLayoutManager(linearLayoutManager);
        presenterImp=new NoticePresenterImp(this);
        presenterImp.laodNoticeDatas();
        adapter=new NoticeAdapter(list);
        time_chose.setOnClickListener(this);
        mRecy.setAdapter(adapter);
        adapter.setOnAdapterClick(new NoticeAdapter.OnItemListener() {
            @Override
            public void setOnClick(View v, int postion) {

            }
        });
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    public void laodNoticeDatas(NoticeBean noticeBean) {
    list.addAll(noticeBean.getData().getList());
    adapter.notifyDataSetChanged();
       // Log.d("NoticeFragment", list.get(0).getAddress());
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_chose:
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.time_shaixuan, null);
                data_queding = inflate.findViewById(R.id.data_queding);
                data_chongzhi = inflate.findViewById(R.id.data_chongzhi);
                start_data = inflate.findViewById(R.id.start_data);
                end_data = inflate.findViewById(R.id.end_data);
                popupWindow = new PopupWindow(inflate, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f2f0f0")));
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(getView().findViewById(R.id.yugao_shaixuan), 0, 0, Gravity.CENTER);
                start_data.clearFocus();
                start_data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                start_data.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                            }
                        }, 2000, 1, 2).show();
                    }
                });

                end_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                end_data.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                            }
                        }, 2000, 1, 2).show();


                    }
                });

                data_chongzhi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        start_data.setText("");
                        end_data.setText("");
                        popupWindow.dismiss();
                    }
                });
                data_queding.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                //DatePickerDialog日历选择器的对话框，监听为OnDateSetListener(){..}
                break;
        }


    }
}


