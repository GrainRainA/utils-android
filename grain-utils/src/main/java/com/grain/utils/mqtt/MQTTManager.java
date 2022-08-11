package com.grain.utils.mqtt;

import com.lzy.okgo.callback.StringCallback;

/**
 * @author GrainRain
 * @funcation
 * @date 2022/06/24
 */
public interface MQTTManager {

    /** 连接 */
    void connect(String host, Callback callback);

    /** 连接 */
    void connect(String host, String clientId, Callback callback);

    /** 连接 */
    void connect(String host, String clientId, String userName, String passWord, Callback callback);

    /** 断开连接 */
    void disConnect();

    /** 订阅 */
    void subscribe(String topic);

    /** 订阅 */
    void subscribe(String topic, int qos);

    /** 恢复订阅 */
    void restoreSubscribe();

    /** 取消订阅 */
    void unSubscribe(String topic);

    /** 发布 */
    void publish(String topic, Object message);

    /** 发布 */
    void publish(String topic, Object message, int qos);

    /** 重新连接 */
    void reconnect();

    /** 获取当前服务器 URI */
    String getCurrentServerURI();

    /** 是否连接 */
    boolean isConnected();

    interface Callback {
        void connectComplete(boolean reconnect, String serverURI);
        void messageArrived(String topic, byte[] bytes);
        void onFailure(String error);
    }
}
