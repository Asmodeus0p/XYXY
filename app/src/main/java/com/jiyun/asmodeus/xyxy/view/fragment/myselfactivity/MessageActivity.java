package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView message_list_aty_cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
    }

    private void initView() {
        message_list_aty_cancle = (TextView) findViewById(R.id.message_list_aty_cancle);
        message_list_aty_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message_list_aty_cancle:
                finish();
                break;
        }
    }
}
