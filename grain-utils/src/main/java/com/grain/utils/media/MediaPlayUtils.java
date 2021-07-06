package com.grain.utils.media;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.R;
import com.grain.utils.hint.L;

import java.io.File;
import java.io.IOException;

/**
 * @anthor GrainRain
 * @funcation 媒体播放工具
 * @date 2021/5/13
 */
public class MediaPlayUtils {

    private static MediaPlayer player;
    private static MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;

    public MediaPlayUtils(SurfaceView mSurfaceView) {
        surfaceView = mSurfaceView;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String path = Environment.getExternalStorageDirectory() + "/Dragonfish/ZOOM_0115_Trim.mp4";
        try {
//            AssetManager am = InitUtilsModule.getActivity().getResources().getAssets();
//            AssetFileDescriptor afd = am.openFd("test.mp4");
            mediaPlayer.setDataSource(path);

            /* 准备 */
            mediaPlayer.prepare();
            //将播放器捕捉的画面展示到SurfaceView画面上
            mediaPlayer.setDisplay(surfaceView.getHolder());
            mediaPlayer.start();
            //循环播放
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });

        } catch (Exception ex) {
            L.e(ex);
            ex.printStackTrace();
        }
    }

//    public MediaPlayUtils(SurfaceView mSurfaceView) {
//
//        surfaceView = mSurfaceView;
//        String path = Environment.getExternalStorageDirectory() + "/ZOOM_0115_Trim.mp4";
//
//        if (player == null) {
//            player = new MediaPlayer();
//        }
//
//        try {
//
//            AssetManager am = InitUtilsModule.getActivity().getAssets();
//            AssetFileDescriptor afd = am.openFd("ZOOM_0115.mp4");
//
//            player.reset();
//
//            //设置播放源
////            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
//            player.setDataSource(path);
//
//            player.setScreenOnWhilePlaying(true);
//            player.prepare();
//
//            //安卓6.0以后
//            if (Build.VERSION.SDK_INT >= 23) {
//                //配置播放器
//                AudioAttributes aa = new AudioAttributes.Builder()
//                        .setUsage(AudioAttributes.USAGE_MEDIA)
//                        .setContentType(AudioAttributes.CONTENT_TYPE_MOVIE)
//                        .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
//                        .build();
//                player.setAudioAttributes(aa);
//            } else {
//                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            }
//
//
//            holder = surfaceView.getHolder();
//            holder.setKeepScreenOn(true);   //保持屏幕常亮
//
//            player.setDisplay(holder);
//            player.prepare();
//            player.start();
//
//            //循环播放
//            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    player.start();
//                }
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void start() {
        if (player != null) {
            player.start();
        }
    }

}
