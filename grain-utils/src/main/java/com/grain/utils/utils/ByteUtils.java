package com.grain.utils.utils;

import com.grain.utils.StringUtils;
import com.grain.utils.hint.L;
import com.grain.utils.hint.toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Byte转换工具
 *
 * @author yangle
 */
public class ByteUtils {

    /**
     * 16进制byte转10进制int
     * @param b
     * @return
     */
    public static int byteToInt(byte b) {
        return (b & 0xFF);
    }

    /**
     * 16进制byte转10进制int
     * @param bytes
     * @return
     */
    public static int[] byteToInt(byte[] bytes) {
        int[] data = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            data[i] = byteToInt(bytes[i]);
        }
        return data;
    }

    /**
     * 十六进制字符串转byte[]
     *
     * @param hex 十六进制字符串
     * @return byte[]
     */
    public static byte[] hexStrToBytes(String hex) {
        try {
            if (hex == null) {
                return new byte[]{};
            }

            //判断是否有空格
            if(StringUtils.contains(hex, " ")) {
                String[] hexStrList = StringUtils.split(hex, " ");
                String hexStr = "";
                for (int i = 0; i < hexStrList.length; i++) {
                    hexStr += hexStrList[i];
                }
                hex = hexStr;
            }

            // 奇数位补0
            if (hex.length() % 2 != 0) {
                hex = "0" + hex;
            }

            int length = hex.length();
            ByteBuffer buffer = ByteBuffer.allocate(length / 2);
            for (int i = 0; i < length; i++) {
                String hexStr = hex.charAt(i) + "";
                i++;
                hexStr += hex.charAt(i);
                byte b = (byte) Integer.parseInt(hexStr, 16);
                buffer.put(b);
            }
            return buffer.array();
        } catch (Exception e) {
            toast.show("请检查输入的值 \n" + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * byte[]转十六进制字符串
     * @param array
     * @return
     */
    public static String byteArrayToHexString(byte[] array) {
        return byteArrayToHexString(array, true);
    }

    /**
     * byte[]转十六进制字符串
     *
     * @param array byte[]
     * @return 十六进制字符串
     */
    public static String byteArrayToHexString(byte[] array, boolean space) {
        if (array == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if (space) {
                buffer.append(byteToHexString(array[i]) + " ");
            } else {
                buffer.append(byteToHexString(array[i]));
            }
        }
        return buffer.toString();
    }

    /**
     * byte转十六进制字符
     *
     * @param b byte
     * @return 十六进制字符
     */
    public static String byteToHexString(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return hex.toUpperCase(Locale.getDefault());
    }

    /**
     * 拆分byte数组
     *
     * @param bytes
     * @param size
     * @return
     */
    public static byte[][] splitBytes(byte[] bytes, int size) {
        double splitLength = Double.parseDouble(size + "");
        int arrayLength = (int) Math.ceil(bytes.length / splitLength);
        byte[][] result = new byte[arrayLength][];
        int from, to;
        for (int i = 0; i < arrayLength; i++) {

            from = (int) (i * splitLength);
            to = (int) (from + splitLength);
            if (to > bytes.length)
                to = bytes.length;
            result[i] = Arrays.copyOfRange(bytes, from, to);

            //不足长度的补0xFF
            if (result[i].length < size) {
                byte[] data = new byte[size];

                for (int j = 0; j < data.length; j++) {
                    data[j] = (byte) 0xFF;
                }

                for (int j = 0; j < result[i].length; j++) {
                    data[j] = result[i][j];
                }
                result[i] = data;
            }
        }
        return result;
    }

    /**
     * 合并多个byte
     * @param values
     * @return
     */
    public static byte[] byteMergerAll(List<byte[]> values) {
        byte[][] bytes = new byte[values.size()][];
        for (int i = 0; i < values.size(); i++) {
            bytes[i] = values.get(i);
        }
        return byteMergerAll(bytes);
    }

    /**
     * 合并多个byte[]
     *
     * @param values
     * @return
     */
    public static byte[] byteMergerAll_1(byte[]... values) {
        byte[][] bytes = new byte[values.length][];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = values[i];
        }
        return byteMergerAll(bytes);
    }

    /**
     * 合并多个byte[]
     *
     * @param values
     * @return
     */
    public static byte[] byteMergerAll(byte[][] values) {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }

    /**
     * File转byte[]
     *
     * @param file
     * @return
     */
    public static byte[] getFileStream(File file) {
        byte[] buffer = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[32 * 1024 * 1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * byte[]转File
     *
     * @param byteArray
     * @param targetPath
     */
    public static File readBin2Image(byte[] byteArray, String targetPath) {
        InputStream in = new ByteArrayInputStream(byteArray);
        File file = new File(targetPath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    //去除数组尾部的0
    public static byte[] removeEndZero(byte[] bytes) {

        for (int i = bytes.length - 1; i > 0; i--) {
            if (bytes[i] != 0) {

                byte[] data = new byte[i + 1];
                for (int j = 0; j < data.length; j++) {
                    data[j] = bytes[j];
                }
                return data;
            }
        }
        return bytes;
    }

//    /**
//     * byte[]转File
//     * @param byteArray
//     * @param targetPath
//     */
//    public static void readBin2Image(byte[] byteArray, String targetPath) {
//        InputStream in = new ByteArrayInputStream(byteArray);
//        File file = new File(targetPath);
//        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
//        if (!file.exists()) {
//            new File(path).mkdir();
//        }
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//            int len = 0;
//            byte[] buf = new byte[1024];
//            while ((len = in.read(buf)) != -1) {
//                fos.write(buf, 0, len);
//            }
//            fos.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (null != fos) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
