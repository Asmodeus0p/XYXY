package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.PermissionChecker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyselfRenZheng extends AppCompatActivity {
    private static final int FOR_REQUEST_IMAGE=1;
    @BindView(R.id.approve_user_aty_back)
    TextView approveUserAtyBack;
    @BindView(R.id.approve_user_aty_name)
    EditText approveUserAtyName;
    @BindView(R.id.approve_user_aty_majors)
    EditText approveUserAtyMajors;
    @BindView(R.id.approve_user_aty_input)
    EditText approveUserAtyInput;
    @BindView(R.id.approve_user_aty_show_idcard)
    ImageView approveUserAtyShowIdcard;
    @BindView(R.id.approve_user_aty_post_idcard)
    LinearLayout approveUserAtyPostIdcard;
    @BindView(R.id.approve_user_aty_genghuan_btn)
    LinearLayout approveUserAtyGenghuanBtn;
    @BindView(R.id.approve_user_aty_progressBar)
    ProgressBar approveUserAtyProgressBar;
    @BindView(R.id.approve_user_aty_genghuan_group)
    LinearLayout approveUserAtyGenghuanGroup;
    @BindView(R.id.approve_user_aty_post_btn)
    Button approveUserAtyPostBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_ren_zheng);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.approve_user_aty_back, R.id.approve_user_aty_name, R.id.approve_user_aty_majors, R.id.approve_user_aty_input, R.id.approve_user_aty_show_idcard, R.id.approve_user_aty_post_idcard, R.id.approve_user_aty_genghuan_btn, R.id.approve_user_aty_progressBar, R.id.approve_user_aty_genghuan_group, R.id.approve_user_aty_post_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.approve_user_aty_back:
                finish();
                break;
            case R.id.approve_user_aty_name:
                break;
            case R.id.approve_user_aty_majors:
                break;
            case R.id.approve_user_aty_input:
                break;
            case R.id.approve_user_aty_show_idcard:
                break;
            case R.id.approve_user_aty_post_idcard:
                if (isPermissionOK()) {

                    requestImage();

                }

                break;
            case R.id.approve_user_aty_genghuan_btn:
                break;
            case R.id.approve_user_aty_progressBar:
                break;
            case R.id.approve_user_aty_genghuan_group:
                break;
            case R.id.approve_user_aty_post_btn:
                break;
        }
    }
    private void requestImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, FOR_REQUEST_IMAGE);
    }
    private boolean isPermissionOK() {
        PermissionChecker checker = new PermissionChecker(MyselfRenZheng.this);
        boolean isPermissionOK = Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checker.checkPermission();
        if (!isPermissionOK) {
        }
        return isPermissionOK;
    }
}
