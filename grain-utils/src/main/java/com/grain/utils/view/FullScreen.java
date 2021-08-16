package com.grain.utils.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.grain.utils.InitUtilsModule;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @anthor GrainRain
 * @funcation 设置沉浸式主题
 * @date 2021/3/23
 */
public class FullScreen {

    public static void setHideAllBar(AppCompatActivity activity) {
        setHideTitleBar(activity);
        setHideStatusBar(activity);
        hideNavKey(activity);
    }

    /**
     * 隐藏状态栏和标题栏
     *
     * @param activity
     */
    public static void setHideAllBar(Activity activity) {
        setHideTitleBar(activity);
        setHideStatusBar(activity);
        hideNavKey(activity);
    }

    /**
     * 隐藏标题栏
     *
     * @param activity
     */
    public static void setHideTitleBar(AppCompatActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * 隐藏标题栏
     * 必须在setContentView之前调用
     *
     * @param activity
     */
    public static void setHideTitleBar(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 隐藏状态栏
     *
     * @param activity
     */
    public static void setHideStatusBar(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(attrs);
    }

    /**
     * 隐藏底部按键
     *
     * @param activity
     */
    public static void hideNavKey(Activity activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
