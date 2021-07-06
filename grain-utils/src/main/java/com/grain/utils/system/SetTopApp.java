package com.grain.utils.system;

import android.app.ActivityManager;
import android.content.Context;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.hint.L;

import java.util.List;

/**
 * @anthor GrainRain
 * @funcation 置APP为顶层
 * @date 2021/4/24
 */
public class SetTopApp {

    public static void top() {
        if (isRunningForeground()) {
            return;
        }

        //获取ActivityManager
        ActivityManager activityManager = (ActivityManager) InitUtilsModule.getContext().getSystemService(Context.ACTIVITY_SERVICE);

        //获得当前运行的task(任务)
        List<ActivityManager.RunningTaskInfo> taskInfoList = activityManager.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
            //找到本应用的 task，并将它切换到前台
            if (taskInfo.topActivity.getPackageName().equals(InitUtilsModule.getContext().getPackageName())) {
                activityManager.moveTaskToFront(taskInfo.id, 0);
                break;
            }
        }
    }

    /**
     * 判断本应用是否已经位于最前端
     * @return
     */
    public static boolean isRunningForeground() {
        ActivityManager activityManager = (ActivityManager) InitUtilsModule.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && appProcessInfo.processName.equals(InitUtilsModule.getContext().getApplicationInfo().processName)) {
                return true;
            }
        }
        return false;
    }
}
