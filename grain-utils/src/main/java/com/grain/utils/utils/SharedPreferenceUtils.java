package com.grain.utils.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.hint.L;
import com.grain.utils.hint.toast;

import java.util.Map;

/**
 * @anthor GrainRain
 * @funcation SharedPreference工具类 共享引用
 * @date 2019/9/5
 */
public class SharedPreferenceUtils {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public SharedPreferenceUtils() {
        if(InitUtilsModule.getContext() != null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(InitUtilsModule.getContext());
            editor = preferences.edit();
        }
    }

    /**
     * 储存数据
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        if(editor != null) {
            if (object instanceof String) {
                editor.putString(key, (String) object);
            } else if (object instanceof Integer) {
                editor.putInt(key, (Integer) object);
            } else if (object instanceof Boolean) {
                editor.putBoolean(key, (Boolean) object);
            } else if (object instanceof Float) {
                editor.putFloat(key, (Float) object);
            } else if (object instanceof Long) {
                editor.putLong(key, (Long) object);
            } else {
                editor.putString(key, object.toString());
            }
            editor.commit();
        } else {
            toast.show("请重试");
        }
    }

    /**
     * 获取数据
     * @param key
     * @param defaultObject
     * @return Object
     */
    public static Object get(String key, Object defaultObject) {
        if(preferences != null) {
            if (defaultObject instanceof String) {
                return preferences.getString(key, (String) defaultObject);
            } else if (defaultObject instanceof Integer) {
                return preferences.getInt(key, (Integer) defaultObject);
            } else if (defaultObject instanceof Boolean) {
                return preferences.getBoolean(key, (Boolean) defaultObject);
            } else if (defaultObject instanceof Float) {
                return preferences.getFloat(key, (Float) defaultObject);
            } else if (defaultObject instanceof Long) {
                return preferences.getLong(key, (Long) defaultObject);
            } else {
                return preferences.getString(key, null);
            }
        } else {
            toast.show("请重试");
            return null;
        }
    }

    /**
     * 移除key对应的值
     * @param key
     */
    public static void remove(String key) {
        if(editor != null) {
            editor.remove(key);
            editor.commit();
        } else {
            toast.show("请重试");
        }
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        if(editor != null) {
            editor.clear();
            editor.commit();
        } else {
            toast.show("请重试");
        }
    }

    /**
     * 查询key是否存在
     */
    public static Boolean contain(String key) {
        if(preferences != null) {
            return preferences.contains(key);
        } else {
            toast.show("请重试");
            return false;
        }
    }

    /**
     * 返回所有键值对
     * @return
     */
    public static Map<String, ?> getAll() {
        if(preferences != null) {
            return preferences.getAll();
        } else {
            toast.show("请重试");
            return null;
        }
    }

    //打印获取的所有键值对
    public static void LogAll() {
        Map<String, ?> map = SharedPreferenceUtils.getAll();
        for (String key : map.keySet()) {
            String value = String.valueOf(map.get(key));
            L.e("Key = " + key + "  ----  " + "Value = " + value);
        }
    }


}
