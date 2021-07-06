package com.grain.utils.view;

import android.os.PowerManager;
import android.view.WindowManager;

import static android.content.Context.POWER_SERVICE;
import static com.grain.utils.InitUtilsModule.getActivity;

/**
 * @anthor GrainRain
 * @funcation 屏幕亮度
 * @date 2021/4/7
 */
public class ScreenBrightness {

    /**
     * 保持屏幕常亮
     */
    public static void brightness() {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //此方法需要当前View可见
        getActivity().getWindow().getDecorView().setKeepScreenOn(true);
    }
}
