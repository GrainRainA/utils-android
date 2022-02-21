package com.grain.utils;

import com.grain.utils.hint.L;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;

/**
 * @anthor GrainRain
 * @funcation mqtt管理
 * @date 2020/2/18
 */
public class MQTTManager {

    private static String host = "";
    private static String userName = "userName";
    private static String passWord = "passWord";
    private static boolean restoreSubscribeState = false;   //是否重连

    private static MQTTManager mqttManager = null;
    private static MqttClient client;
    private static MqttConnectOptions connectOptions;

    public static String MQTTPublishTopic = "";
    private static String clientId = "";

    //订阅列表
    private static Map<String, String> subscribeTopicMap = new HashMap<>();
    private static MessageInterdace messageInterdace;

    public interface MessageInterdace {
        void messageArrived(String topic, byte[] bytes);
    }

    public MQTTManager() {
        clientId = MqttClient.generateClientId();
        if (!isConnected()) {
            connect(host, messageInterdace);
        }
    }

    public static MQTTManager getInstance() {
        if (mqttManager == null) {
            mqttManager = new MQTTManager();
        } else {
            return mqttManager;
        }
        return null;
    }

    /**
     * 连接MQTT
     * @param mHost 地址
     * @param interdace 信息回调接口
     */
    public static void connect(String mHost, MessageInterdace interdace) {
        try {
            if (client == null) {
                host = mHost;
                client = new MqttClient(host, clientId, new MemoryPersistence());
            }
            connectOptions = new MqttConnectOptions();
            connectOptions.setAutomaticReconnect(true);     //自动重连
            connectOptions.setUserName(userName);
            connectOptions.setPassword(passWord.toCharArray());
            client.setCallback(mqttCallback);
            client.connect(connectOptions);
            if(interdace != null) {
                messageInterdace = interdace;
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    //订阅
    public static void subscribe(String topic) {
        subscribe(topic, 0);
    }

    public static void subscribe(String topic, int qos) {
        if (client != null) {
            int[] Qos = {qos};
            String[] topic1 = {topic};
            try {
                client.subscribe(topic1, Qos);

                subscribeTopicMap.put(topic, "");
                L.e("订阅topic : " + topic);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    //退订
    public static void unSubscribe(String topic) {
        try {
            client.unsubscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /* 切换MQTT地址 */
    public static void switchHost(String newHost) {
        try {
            client.disconnect();
            client = null;

            connect(newHost, messageInterdace);
            restoreSubscribe();
        } catch (MqttException e) {
            L.e(e);
            e.printStackTrace();
        }
    }

    //发布
    public static void publish(final String topic, Object msg) {
        publish(topic, msg, false, 0);
    }

    //发布
    public static void publish(final String topic, Object msg, int qos) {
        publish(topic, msg, false, qos);
    }

    //发布
    public static void publish(final String topic, final Object msg, final boolean isRetained, final int qos) {
        if (!isConnected()) connect(host, messageInterdace);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (client != null) {
                        MqttMessage message = new MqttMessage();
                        message.setQos(qos);
                        message.setRetained(isRetained);

                        if(msg != null) {
                            if (msg instanceof byte[]) {
                                message.setPayload((byte[]) msg);
                            } else if (msg instanceof Integer) {
                                message.setPayload(String.valueOf(msg).getBytes());
                            } else {
                                message.setPayload(((String) msg).getBytes());
                            }
                        }

                        client.publish(topic, message);

                        if (restoreSubscribeState) {
                            if (isConnected()) {
                                restoreSubscribeState = false;
                                restoreSubscribe();
                            }
                        }
                    }
                } catch (MqttPersistenceException e) {
                    e.printStackTrace();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //信息回调接口
    private static MqttCallback mqttCallback = new MqttCallbackExtended() {

        @Override
        public void connectComplete(boolean reconnect, String serverURI) {
            //如果连接成功，那么则订阅主题
            if (isConnected()) {
                restoreSubscribe();
            }
        }

        @Override
        public void connectionLost(Throwable cause) {
            //连接丢失
            L.v("MqttCallback: connection lost");
            restoreSubscribeState = true;
            //进行重连
            mqttReload();
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) {
            //接收消息
            if(messageInterdace != null) {
                messageInterdace.messageArrived(topic, message.getPayload());
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
            //提交信息完成回调
        }
    };

    //重连MQTT后重新订阅
    public static void restoreSubscribe() {
        try {
            for (String key : subscribeTopicMap.keySet()) {
                subscribe(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            L.e("MQTT重连失败 请检查网络后重试");
        }
    }

    //判断服务是否连接
    public static boolean isConnected() {
        if(client != null) {
            return client.isConnected();
        }
        return false;
    }

    private static void mqttReload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isConnected()) {
                    try {
                        connect(host, messageInterdace);
                        Thread.sleep(connectOptions.getConnectionTimeout() * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void mqttConnectPrompt() {
        L.e(isConnected() ? "MQTT已连接" : "MQTT连接失败");
    }

}
