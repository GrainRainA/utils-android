package com.grain.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {

    /**
     * 获取当前时间 格式 hh:mm:ss
     * @return
     */
    public static String getCurrentTime() {

//        String h = String.valueOf(getCurrentHour() < 10 ? "0" + getCurrentHour() : getCurrentHour());
//        String m = String.valueOf(getCurrentMinute() < 10 ? "0" + getCurrentMinute() : getCurrentMinute());
//        String s = String.valueOf(getCurrentSecond() < 10 ? "0" + getCurrentSecond() : getCurrentSecond());
//        return h + ":" +  m + ":" + s;
        return getCurrentTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前时间
     * @param timeFormat 时间格式 yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String getCurrentTime(String timeFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前时间秒数总数
     *
     * @return
     */
    public static int getCurrentTotalSeconds() {
        android.text.format.Time time = new android.text.format.Time();
        time.setToNow();
        int t = time.hour * 60 * 60 + time.minute * 60 + time.second;
        return t;
    }

    /**
     * 获取当前分钟总数
     *
     * @return
     */
    public static int getCurrentTotalMinute() {
        android.text.format.Time time = new android.text.format.Time();
        time.setToNow();
        int t = time.hour * 60 + time.minute;
        return t;
    }

    /**
     * 获取当前小时
     *
     * @return
     */
    public static int getCurrentHour() {
        android.text.format.Time time = new android.text.format.Time();
        time.setToNow();
        return time.hour;
    }

    /**
     * 获取当前分钟
     *
     * @return
     */
    public static int getCurrentMinute() {
        android.text.format.Time time = new android.text.format.Time();
        time.setToNow();
        return time.minute;
    }

    /**
     * 获取当前秒
     *
     * @return
     */
    public static int getCurrentSecond() {
        android.text.format.Time time = new android.text.format.Time();
        time.setToNow();
        return time.second;
    }
}
