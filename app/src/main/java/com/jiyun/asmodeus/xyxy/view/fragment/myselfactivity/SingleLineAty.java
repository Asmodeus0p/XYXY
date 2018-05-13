package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;

public class SingleLineAty extends AppCompatActivity implements View.OnClickListener{
    public static final int NAME = 0;

    public static final int TITLE = 1;

    public static final int DETAIL = 2;

    public static final int CITY = 3;
    private Context context;

    private int type;

    private int maxnL = 15;

    private String inputStr;
    private TextView singleline_aty_title_cancle;
    private TextView singleline_aty_title_tv;
    private TextView singleline_aty_savebtn;
    private EditText singleline_aty_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_line_aty);

        initView();
        init();
    }

    private void init() {
        context = this;

        if (getIntent() != null) {
            type = getIntent().getIntExtra(Constant.INPUT_TYPE, 0);
            maxnL = getIntent().getIntExtra(Constant.INPUT_MAXL, 15);
            inputStr = getIntent().getStringExtra(Constant.INPUT_TEXT);
        }


        singleline_aty_input.setText(inputStr);


        if (type == NAME){

            singleline_aty_title_tv.setText("编辑昵称");
        }


        if(type==CITY){

            singleline_aty_title_tv.setText("编辑详细地址");
        }
        singleline_aty_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxnL)});
    }

    private void initView() {
        singleline_aty_title_cancle = (TextView) findViewById(R.id.singleline_aty_title_cancle);
        singleline_aty_title_tv = (TextView) findViewById(R.id.singleline_aty_title_tv);
        singleline_aty_savebtn = (TextView) findViewById(R.id.singleline_aty_savebtn);
        singleline_aty_input = (EditText) findViewById(R.id.singleline_aty_input);
        singleline_aty_title_cancle.setOnClickListener(this);
        singleline_aty_savebtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.singleline_aty_title_cancle:

                this.finish();

                break;
            case R.id.singleline_aty_savebtn:

                inputStr = singleline_aty_input.getText().toString();

                if(TextUtils.isEmpty(inputStr)){
                    return;
                }


                Intent intent = new Intent();

                Bundle bundle = new Bundle();

                bundle.putString(Constant.INPUT_TEXT,inputStr);

                intent.putExtras(bundle);

                this.setResult(RESULT_OK, intent);

                this.finish();

                break;
            default:
                break;
        }
    }
}
