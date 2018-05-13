package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.MediaController;
import com.jiyun.asmodeus.xyxy.view.AtyAnim;
import com.jiyun.asmodeus.xyxy.view.base.BaselivstActivity;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;

public class VideoDetailActivity extends AppCompatActivity {

    private static final String MP4_PATH = "MP4_PATH";

    private PLVideoTextureView mVideoView;

    private ImageView video_detail_aty_cancle;

    private ProgressBar video_detail_aty_progreebar;

    private boolean mIsUpload = false;
    private String mVideoPath;

    private Context context;

    public static void start(Activity activity, String mp4Path) {
        Intent intent = new Intent(activity, VideoDetailActivity.class);
        intent.putExtra(MP4_PATH, mp4Path);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);


        init();

        initView();
    }

    private void init(){

        context=this;


    }

    private void initView(){
        video_detail_aty_cancle = (ImageView) findViewById(R.id.video_detail_aty_cancle);
        video_detail_aty_progreebar = (ProgressBar) findViewById(R.id.video_detail_aty_progreebar);
        mVideoView = (PLVideoTextureView) findViewById(R.id.video_detail_aty_video);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);
        mVideoPath = getIntent().getStringExtra(MP4_PATH);
        mVideoView.setLooping(true);
        mVideoView.setAVOptions(new AVOptions());
        mVideoView.setVideoPath(mVideoPath);
        MediaController mediaController = new MediaController(this, true, false);
        mediaController.setOnClickSpeedAdjustListener(mOnClickSpeedAdjustListener);
        mVideoView.setMediaController(mediaController);
        mVideoView.setOnInfoListener(mOnInfoListener);
        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        mVideoView.setOnCompletionListener(mOnCompletionListener);
        mVideoView.setOnErrorListener(mOnErrorListener);
        mVideoView.setOnVideoFrameListener(mOnVideoFrameListener);
        mVideoView.setOnAudioFrameListener(mOnAudioFrameListener);

        video_detail_aty_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                AtyAnim.centOut((Activity) context);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
        Log.d("TAG", "onResume: start");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }


    public void copyToClipboard(String filePath) {
        ClipData clipData = ClipData.newPlainText("VideoFilePath", filePath);
        ClipboardManager clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(clipData);
    }



    private MediaController.OnClickSpeedAdjustListener mOnClickSpeedAdjustListener = new MediaController.OnClickSpeedAdjustListener() {
        @Override
        public void onClickNormal() {
            // 0x0001/0x0001 = 2
            mVideoView.setPlaySpeed(0X00010001);
        }

        @Override
        public void onClickFaster() {
            // 0x0002/0x0001 = 2
            mVideoView.setPlaySpeed(0X00020001);
        }

        @Override
        public void onClickSlower() {
            // 0x0001/0x0002 = 0.5
            mVideoView.setPlaySpeed(0X00010002);
        }
    };

    private PLMediaPlayer.OnVideoFrameListener mOnVideoFrameListener = new PLMediaPlayer.OnVideoFrameListener() {
        @Override
        public void onVideoFrameAvailable(byte[] data, int size, int width, int height, int format, long ts) {
        }
    };

    private PLMediaPlayer.OnAudioFrameListener mOnAudioFrameListener = new PLMediaPlayer.OnAudioFrameListener() {
        @Override
        public void onAudioFrameAvailable(byte[] data, int size, int samplerate, int channels, int datawidth, long ts) {
        }
    };

    private PLMediaPlayer.OnInfoListener mOnInfoListener = new PLMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int extra) {

            video_detail_aty_progreebar.setVisibility(View.GONE);

            switch (what) {
                case PLMediaPlayer.MEDIA_INFO_BUFFERING_START:
                    video_detail_aty_progreebar.setVisibility(View.GONE);
                    break;
                case PLMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
//                    ToastUtils.s(PlaybackActivity.this, "first video render time: " + extra + "ms");
                    video_detail_aty_progreebar.setVisibility(View.GONE);
                    break;
                case PLMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                    video_detail_aty_progreebar.setVisibility(View.GONE);
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_FRAME_RENDERING:
                    break;
                case PLMediaPlayer.MEDIA_INFO_AUDIO_FRAME_RENDERING:
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_GOP_TIME:
                    break;
                case PLMediaPlayer.MEDIA_INFO_SWITCHING_SW_DECODE:
                    break;
                case PLMediaPlayer.MEDIA_INFO_METADATA:
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_BITRATE:
                case PLMediaPlayer.MEDIA_INFO_VIDEO_FPS:
                    break;
                case PLMediaPlayer.MEDIA_INFO_CONNECTED:
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                    mVideoView.setDisplayOrientation(360 - extra);
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    private PLMediaPlayer.OnErrorListener mOnErrorListener = new PLMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(PLMediaPlayer mp, int errorCode) {
            switch (errorCode) {
                case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                    return false;
                case PLMediaPlayer.ERROR_CODE_OPEN_FAILED:
                    break;
                case PLMediaPlayer.ERROR_CODE_SEEK_FAILED:
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    private PLMediaPlayer.OnCompletionListener mOnCompletionListener = new PLMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(PLMediaPlayer plMediaPlayer) {

        }
    };

    private PLMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new PLMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int precent) {
        }
    };

    private PLMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int width, int height) {
        }
    };



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        AtyAnim.centOut((Activity) context);
    }
}
