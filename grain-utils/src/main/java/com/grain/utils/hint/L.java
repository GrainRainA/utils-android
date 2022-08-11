package com.grain.utils.hint;

import static com.grain.utils.StringUtils.objectToString;

import android.util.Log;

import com.grain.utils.StringUtils;
import com.grain.utils.utils.ByteUtils;

import java.util.Arrays;

/**
 * @anthor GrainRain
 * @funcation 封装打印类
 * @date 2019/9/1
 */
public class L {

    private static String TAG = "----------";
    private static boolean debug = true;


    public static void v(Object... msg) {
        if (debug) Log.v(TAG, objectToString(msg));
    }

    public static void d(Object... msg) {
        if (debug) Log.d(TAG, objectToString(msg));
    }

    public static void i(Object... msg) {
        if (debug) Log.i(TAG, objectToString(msg));
    }

    public static void w(Object... msg) {
        if (debug) Log.w(TAG, objectToString(msg));
    }

    public static void e(Object... msg) {
        if (debug) Log.e(TAG, objectToString(msg));
    }



    /** 设置是否打印 */
    public static void setDebug(boolean debug) {
        L.debug = debug;
    }

    /** 设置默认标签 */
    public static void setTAG(String tag) {
        L.TAG = tag;
    }
}
