package com.jiyun.asmodeus.xyxy.model.utils;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import java.io.File;

public class RecordPlayer {

    private static MediaPlayer mediaPlayer;

    private Context mcontext;

    public RecordPlayer(Context context) {
        this.mcontext = context;
    }

    // 播放录音文件
    public void playRecordFile(File file, final View view) {
        if (file.exists() && file != null) {
//            if (mediaPlayer == null) {
            mediaPlayer = null;
            Uri uri = Uri.fromFile(file);
            mediaPlayer = MediaPlayer.create(mcontext, uri);
//            }
            mediaPlayer.start();

            //监听MediaPlayer播放完成
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer paramMediaPlayer) {

                    if(view!=null){
                        view.setActivated(false);
                    }
                }
            });

        }
    }

    // 暂停播放录音
    public void pausePalyer() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }

    }

    // 停止播放录音
    public void stopPalyer() {
        if(mediaPlayer==null){

            return ;
        }
        // 这里不调用stop()，调用seekto(0),把播放进度还原到最开始
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }

    public void cancelPalyer(){
        if(mediaPlayer==null){

            return ;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = null;
        }
    }
}
