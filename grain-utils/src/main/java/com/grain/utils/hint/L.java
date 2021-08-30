package com.grain.utils.hint;

import android.util.Log;

import com.grain.utils.StringUtils;
import com.grain.utils.utils.ByteUtils;

import java.util.Arrays;

/**
 * @anthor GrainRain
 * @funcation 简单封装打印类
 * @date 2019/9/1
 */
public class L {

    private static final String TAG = "----------";
    private static boolean debug = true;

    private static final String TOP_CORNER     = "┌";
    private static final String MIDDLE_CORNER  = "├";
    private static final String LEFT_BORDER    = "│ ";
    private static final String BOTTOM_CORNER  = "└";
    private static final String SIDE_DIVIDER   = "────────────────────────────────────────────────────────";

    public static void v(Object... msg) {
        if (debug) Log.e(TAG, objectToString(msg));
    }

    public static void d(Object... msg) {
        if (debug) Log.e(TAG, objectToString(msg));
    }

    /**
     * 打印
     * @param msg
     */
    public static void e(Object... msg) {
        if (debug) Log.e(TAG, objectToString(msg));
    }


    public static void special(Object... msg) {
        if (debug) {
            L.e("   \n" +
                    TOP_CORNER + SIDE_DIVIDER + "\n" +
                    LEFT_BORDER + objectToString(msg) + "\n" +
                    BOTTOM_CORNER + SIDE_DIVIDER);
        }
    }



    /**
     * 泛型列表转字符
     * @param msg
     * @return
     */
    private static String objectToString(Object... msg) {
        String string = "";

        if (msg != null && msg.length != 0) {
            for (int i = 0; i < msg.length; i++) {
                if (!StringUtils.isEmpty(objectToString(msg[i]))) {
                    string += objectToString(msg[i]) + " ";
                } else {
                    string += "null ";
                }
            }
        } else {
            string = "null";
        }

        return string;
    }

    /**
     * 泛型转字符
     *
     * @param msg
     * @return
     */
    private static String objectToString(Object msg) {
        try {
            if (debug) {
                if (msg instanceof int[]) {
                    return (Arrays.toString((int[]) msg));
                } else if (msg instanceof byte[]) {
                    return (ByteUtils.byteArrayToHexString((byte[]) msg));
                } else if (msg instanceof Exception) {
                    return "Exception: " + ((Exception) msg).getMessage();
                } else {
                    return (msg.toString());
                }
            }
        } catch (Exception e) {
            return ("Object cannot be logcat " + e.toString());
        }

        return "";
    }
}
