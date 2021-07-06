package com.grain.utils;

/**
 * @anthor
 * @funcation 安全转换类
 * @date
 */
public class Convert {

    /**
     * Object转int
     * @param value
     * @param defaultValue
     * @return
     */
    public static int convertToInt(Object value, int defaultValue) {
        if(value == null || "".equals(value.toString().trim()))
            return defaultValue;

        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * Object转double
     * @param value
     * @param defaultValue
     * @return
     */
    public static double convertToDouble(Object value, double defaultValue) {

        if(value == null || "".equals(value.toString().trim()))
            return defaultValue;

        try {
            return Double.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

}
