package com.grain.utils.utils.json;

import com.alibaba.fastjson.JSON;

/**
 * @anthor GrainRain
 * @funcation fastjson工具类
 * @date 2021/3/19
 */
public class JSONUtils {

    /**
     * 类对象转JSON对象
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
    public static final <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }
}
