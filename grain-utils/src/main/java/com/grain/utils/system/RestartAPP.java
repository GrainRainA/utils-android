package com.grain.utils.system;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.grain.utils.InitUtilsModule;

/**
 * @anthor GrainRain
 * @funcation 重启APP
 * @date 2021/4/30
 */
public class RestartAPP {

    /**
     * 重启APP
     *
     * @param delayTime 延时 秒
     */
    public RestartAPP(int delayTime) {
        Intent intent = InitUtilsModule.getActivity().getPackageManager().getLaunchIntentForPackage(
                InitUtilsModule.getActivity().getApplication().getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(InitUtilsModule.getActivity(), 0, intent, 0);
        AlarmManager mgr = (AlarmManager) InitUtilsModule.getActivity().getSystemService(Context.ALARM_SERVICE);
        //延时重启应用
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + (delayTime * 1000), restartIntent);
        System.exit(0);
    }
}
