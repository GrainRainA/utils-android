package com.grain.utils.hint;

import static com.grain.utils.StringUtils.objectToString;

import android.app.Activity;
import android.widget.Toast;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.hint.L;

/**
 * @anthor GrainRain
 * @funcation Toast工具类
 * @date 2019/9/5
 */
public class toast {

    public static void show(Object... msg) {
        show(Toast.LENGTH_SHORT, msg);
    }

    public static void show(int duration, Object... msg) {
        show(InitUtilsModule.getActivity(), duration, msg);
    }

    public static void show(final Activity activity, final int duration, final Object... msg) {

        if(activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, objectToString(msg), duration).show();
                }
            });
        }
    }
}
