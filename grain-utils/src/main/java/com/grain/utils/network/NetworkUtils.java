package com.grain.utils.network;

import com.grain.utils.Convert;
import com.grain.utils.StringUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @anthor GrainRain
 * @funcation 网络工具
 * @date 2022/04/14
 */
public class NetworkUtils {

    public static void ping(final Listener listener) {
        ping("www.baidu.com", listener);
    }

    /**
     * ping 的地址
     * @param ip
     * @return
     */
    public static void ping(final String ip, final Listener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // ping 网址1次
                    Process process = Runtime.getRuntime().exec("ping -c 1 -w 100 " + ip);

                    /* 0 当前网络可用 */
                    /* 1 需要网页认证的wifi */
                    /* 2 当前网络不可用 */
                    int status = process.waitFor();

                    if (status == 0) {
                        InputStream input = process.getInputStream();
                        BufferedReader in = new BufferedReader(new InputStreamReader(input));

                        while (in.readLine() != null) {
                            String line = in.readLine();

                            if (StringUtils.contains(line, "time=")) {
                                float time = (float) Convert.convertToDouble(StringUtils.split(line, "time=", "ms"), -1);
                                if (listener != null) listener.result(true, time);
                            }
                        }
                    } else {
                        if (listener != null) listener.result(false, -1);
                    }
                } catch (Exception e) {
                    if (listener != null) listener.result(false, -1);
                }
            }
        }).start();
    }

    public interface Listener {
        void result(boolean isConnect, float time);
    }
}
