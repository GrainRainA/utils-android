package com.grain.utils.system;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

import androidx.core.app.ActivityCompat;

/**
 * @anthor
 * @funcation 获取设备信息工具类
 * @date 2020/3/30
 */
public class TelephonyUtils {

    /**
     * 获取设备唯一编号
     *
     * @param context
     * @return
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String getDeviceID(Context context) {
        String deviceID = "";
        TelephonyManager manager = phonePermissionCheck(context);

        if (manager != null) {
            try {
                if (manager.getDeviceId() != null) {
                    deviceID = manager.getDeviceId();
                } else {
                    deviceID = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deviceID;
    }

    /**
     * 获取本机号码
     * 并非所有设备都能获取到SIM卡号
     * 只有当运营商将SIM卡号写入SIM卡时才能获取到
     *
     * @param context
     * @return
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getTe1(Context context) {
        String te1 = "-";
        TelephonyManager manager = phonePermissionCheck(context);

        if (manager != null) {
            te1 = manager.getLine1Number();
        }
        return te1;
    }

    /**
     * 获得SIM卡的序号
     *
     * @param context
     * @return
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMEI(Context context) {
        String imei = "-";
        TelephonyManager manager = phonePermissionCheck(context);

        if (manager != null) {
            imei = manager.getSimSerialNumber();
        }
        return imei;
    }

    public static String getMEID(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Method method = null;
        try {
            method = telephonyManager.getClass().getMethod("getDeviceId", int.class);
            //获取IMEI号
//            @SuppressLint("MissingPermission")
//            String imei1 = telephonyManager.getDeviceId();
//            String imei2 = (String) method.invoke(telephonyManager, 1);
            //获取MEID号
            String meid = (String) method.invoke(telephonyManager, 2);
            return meid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 得到用户ID
     *
     * @param context
     * @return
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMSI(Context context) {
        String imsi = "-";
        TelephonyManager manager = phonePermissionCheck(context);

        if (manager != null) {
            imsi = manager.getSubscriberId();
        }
        return imsi;
    }

    /**
     * 电话权限检查
     *
     * @param context
     * @return
     */
    private static TelephonyManager phonePermissionCheck(Context context) {

        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                try {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return manager;
    }
}