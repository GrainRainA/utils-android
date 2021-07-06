package com.grain.utils.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.grain.utils.InitUtilsModule;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import static com.grain.utils.InitUtilsModule.getActivity;

/**
 * @anthor GrainRain
 * @funcation 设置沉浸式主题
 * @date 2021/3/23
 */
public class FullScreen {

    /**
     * 隐藏状态栏和标题栏
     */
    public static void setHideAllBar() {
        setHideTitleBar();
        setHideStatusBar();
        hideNavKey();
    }

    /**
     * 隐藏标题栏
     */
    public static void setHideTitleBar() {
        ActionBar actionBar = getActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * 隐藏状态栏
     */
    public static void setHideStatusBar() {
        WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getActivity().getWindow().setAttributes(attrs);
    }

    /**
     * 隐藏底部按键
     */
    public static void hideNavKey() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = getActivity().getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getActivity().getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
