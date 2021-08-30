package com.grain.utils.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @anthor GrainRain
 * @funcation 集合工具
 * @date 2021/8/27
 */
public class CollectionUtils {

    /**
     * 列表转数组
     * @param stringList
     * @return
     */
    public static String[] toStringList(List<String> stringList) {
        return stringList.toArray(new String[stringList.size()]);
    }

    /**
     * 数组转列表
     * @param stringArray
     * @return
     */
    public static List<String> toStringList(String[] stringArray) {
        return new ArrayList<>(Arrays.asList(stringArray));
    }
}
