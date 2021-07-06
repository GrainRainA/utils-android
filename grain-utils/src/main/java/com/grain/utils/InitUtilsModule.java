package com.grain.utils;

import android.content.Context;

import com.grain.utils.utils.SharedPreferenceUtils;

import androidx.appcompat.app.AppCompatActivity;


/**
 * @anthor GrainRain
 * @funcation 初始化moudle
 * @date 2020/5/11
 */
public class InitUtilsModule {

    private static AppCompatActivity activity;
    private static Context context;

    public static void init(AppCompatActivity mActivity) {
        activity = mActivity;
        context = activity.getApplicationContext();
        new SharedPreferenceUtils();
    }

    public static AppCompatActivity getActivity() {
        return activity;
    }

    public static Context getContext() {
        return context;
    }

}
