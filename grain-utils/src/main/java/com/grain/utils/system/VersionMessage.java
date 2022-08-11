package com.grain.utils.system;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.grain.utils.InitUtilsModule;

/**
 * @anthor GrainRain
 * @funcation 获取app版本信息
 * @date 2020/5/9
 */
public class VersionMessage {

    public static String getVersion() {

        String version = "null";

        if(InitUtilsModule.getContext() != null) {
            PackageManager packageManager = InitUtilsModule.getContext().getPackageManager();

            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(InitUtilsModule.getContext().getPackageName(), 0);

                if(packageInfo != null) {
                    version = packageInfo.versionName + "." + packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return version;
    }
}
