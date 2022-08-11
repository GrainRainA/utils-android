package com.grain.utils.network;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.grain.utils.InitUtilsModule;

/**
 * @anthor GrainRain
 * @funcation WIFI管理工具
 * @date 2022/04/14
 */
public class WifiManagerUtils {

    /**
     * 设置WIFI状态
     * @param enabled
     */
    public static void setWifiEnabled(boolean enabled) {
        setWifiEnabled(enabled, InitUtilsModule.getContext());
    }

    public static void setWifiEnabled(boolean enabled, Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(enabled);
    }

    public static boolean isWifiEnabled() {
        return isWifiEnabled(InitUtilsModule.getContext());
    }

    public static boolean isWifiEnabled(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }
}
