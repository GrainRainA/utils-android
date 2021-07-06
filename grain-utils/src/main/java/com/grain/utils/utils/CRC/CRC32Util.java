package com.grain.utils.utils.CRC;

import com.grain.utils.hint.L;

import java.util.zip.CRC32;

/**
 * @anthor GrainRain
 * @funcation CRC校验工具 32位
 * @date 2020/12/17
 */
public class CRC32Util {

    public static byte[] calcCrc(byte[] bytes, int start, int end) {
        byte[] datas = new byte[end - start];
        for (int i = 0; i < end - start; i++) {
            datas[0] = bytes[start];
        }
        return calcCrc(datas);
    }

    /**
     * 获取CRC校验值
     * @param bytes
     * @return
     */
    public static byte[] calcCrc(byte[] bytes) {

        long crc32 = calcCrc32(bytes);
        L.e(crc32);

        byte[] crc = new byte[4];
        crc[0] = (byte)(crc32 >> 24 & 0xFF);
        crc[1] = (byte)(crc32 >> 16 & 0xFF);
        crc[2] = (byte)(crc32 >> 8 & 0xFF);
        crc[3] = (byte)(crc32 & 0xFF);

        return crc;
    }

    /**
     * 获取CRC校验值
     * @param bytes
     * @return
     */
    public static long calcCrc32(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.reset();
        crc32.update(bytes);
        return crc32.getValue();
    }
}
