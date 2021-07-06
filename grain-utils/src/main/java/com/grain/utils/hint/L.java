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

    public static final String TAG = "----------";
    public static boolean debug = true;

    public static void v(Object msg) {
        if (debug) Log.v(TAG, msg.toString());
    }

    public static void d(Object msg) {
        if (debug) Log.d(TAG, msg.toString());
    }

    public static void e(Exception e) {
        e("Exception: " + e.getMessage());
    }

    public static void e(Object... msg) {
        String string = "";
        for (int i = 0; i < msg.length; i++) {
            if(!objectToString(msg[i]).equals("")) {
                string += objectToString(msg[i]) + " ";
            }
        }
        e(string);
    }

    public static void e(Object msg) {
        e(objectToString(msg));
    }

    public static void e(String msg) {
        Log.e(TAG, (StringUtils.isEmpty(msg) ? "null" : msg));
    }

    public static String objectToString(Object msg) {
        try {
            if (debug) {
                if (msg instanceof int[]) {
                    return (Arrays.toString((int[]) msg));
                } else if (msg instanceof byte[]) {

                    return (ByteUtils.byteArrayToHexString((byte[]) msg));

//                    byte[] data = (byte[]) msg;
//                    e(Arrays.toString(ByteUtils.byteToInt(data)));
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
