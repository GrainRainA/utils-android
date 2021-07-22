package com.grain.utils.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.List;

/**
 * @anthor GrainRain
 * @funcation fastjson工具类
 * @date 2021/3/19
 */
public class JSONUtils {

    /**
     * 类对象转JSON
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 解析JSON数据
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 类列表转JSON
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Object> String toJSONListString(List<T> list) {
        return JSONObject.toJSON(list).toString();
    }

    /**
     * JSON列表转类列表
     * @param listStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String listStr, Class<T> clazz) {
        return JSONObject.parseArray(listStr, clazz);
    }
}
