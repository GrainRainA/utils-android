package com.grain.utils.view;

import android.content.pm.ActivityInfo;
import static com.grain.utils.InitUtilsModule.getActivity;

/**
 * @anthor GrainRain
 * @funcation 设置屏幕方向
 * @date 2021/4/7
 */
public class SetScreenOrientation {

    /**
     * 强制横屏
     */
    public static void horizontal() {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 强制竖屏
     */
    public static void portrait() {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
