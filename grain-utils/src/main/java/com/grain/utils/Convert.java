package com.grain.utils;

import java.text.NumberFormat;

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

    /**
     * 截取小数点后位数
     * @param value
     * @param maximumFractionDigits 位数
     * @param defaultValue 异常返回值
     * @return
     */
    public static double cutOutFractionDigits(Object value, int maximumFractionDigits, double defaultValue) {
        double num = convertToDouble(value, defaultValue);
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(maximumFractionDigits);
        String format = nf.format(num);
        num = Convert.convertToDouble(format, defaultValue);
        return num;
    }
}
