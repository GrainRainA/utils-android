package com.grain.utils;

import android.app.Activity;
import android.content.Context;
import com.grain.utils.utils.SharedPreferenceUtils;

/**
 * @anthor GrainRain
 * @funcation 初始化moudle
 * @date 2020/5/11
 */
public class InitUtilsModule {

    private static Activity activity;
    private static Context context;

    public static void init(Activity mActivity) {
        activity = mActivity;
        context = activity.getApplicationContext();
        new SharedPreferenceUtils();
    }

    public static Activity getActivity() {
        return activity;
    }

    public static Context getContext() {
        return context;
    }

}
