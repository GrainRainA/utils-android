package com.grain.utils.hint;

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

    public static void show(Object object) {
        show(object, Toast.LENGTH_SHORT);
    }

    public static void show(Object object, int duration) {
        show(InitUtilsModule.getActivity(), object, duration);
    }

    public static void show(final Activity activity, final Object object, final int duration) {

        L.e(object.toString());
        if(activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, object.toString(), duration).show();
                }
            });
        }
    }
}
