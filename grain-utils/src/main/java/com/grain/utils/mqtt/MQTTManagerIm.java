package com.grain.utils.mqtt;

import com.grain.utils.StringUtils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GrainRain
 * @funcation
 * @date 2022/06/24
 */
public class MQTTManagerIm implements MQTTManager {

    private MqttClient client;

    private Callback callback;
    private Map<String, String> subscribeTopicMap;

    @Override
    public void connect(String host, Callback callback) {
        connect(host, MqttClient.generateClientId(), callback);
    }

    @Override
    public void connect(String host, String clientId, Callback callback) {
        connect(host, MqttClient.generateClientId(), "userName", "passWord", callback);
    }

    @Override
    public void connect(String host, String clientId, String userName, String passWord, Callback callback) {
        try {
            this.callback = callback;

            client = new MqttClient(host, clientId, new MemoryPersistence());
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setAutomaticReconnect(true);     //自动重连
            connectOptions.setUserName(userName);
            connectOptions.setPassword(passWord.toCharArray());

            connectOptions.setCleanSession(true);

            client.setCallback(mqttCallback);
            client.connect(connectOptions);
        } catch (MqttException e) {
            if (callback != null) callback.onFailure(e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void disConnect() {
        try {
            if (client != null) {
                client.disconnect();
                client = null;
            }
        } catch (MqttException e) {
            if (callback != null) callback.onFailure("disConnect:" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    @Override
    public void subscribe(String topic, int qos) {
        try {
            if (client != null) {
                client.subscribe(topic, qos);

                if (subscribeTopicMap == null) subscribeTopicMap = new HashMap<>();
                subscribeTopicMap.put(topic, "null");
            } else {
                if (callback != null) callback.onFailure("subscribe: client == null");
            }
        } catch (MqttException e) {
            if (callback != null) callback.onFailure("subscribe:" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void restoreSubscribe() {
        try {
            for (String key : subscribeTopicMap.keySet()) {
                subscribe(key);
            }
        } catch (Exception e) {
            if (callback != null) callback.onFailure("restoreSubscribe:" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void unSubscribe(String topic) {
        try {
            if (client != null) {
                client.unsubscribe(topic);

                if (subscribeTopicMap != null) {
                    subscribeTopicMap = new HashMap<>();
                    subscribeTopicMap.remove(topic);
                }
            }
        } catch (MqttException e) {
            if (callback != null) callback.onFailure("unSubscribe:" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void publish(String topic, Object message) {
        publish(topic, message, 0);
    }

    @Override
    public void publish(String topic, Object message, int qos) {
        try {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);

            if (message instanceof byte[]) {
                mqttMessage.setPayload((byte[]) message);
            } else {
                mqttMessage.setPayload(((String) message).getBytes());
            }

            client.publish(topic, mqttMessage);
        } catch (Exception e) {
            if (callback != null) callback.onFailure("publish:" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void reconnect() {
        if (client != null) {
            try {
                client.reconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCurrentServerURI() {
        if (client != null) {
            return client.getCurrentServerURI();
        }
        return "null";
    }

    private final MqttCallback mqttCallback = new MqttCallbackExtended() {

        @Override
        public void connectComplete(boolean reconnect, String serverURI) {
            if (callback != null) callback.connectComplete(reconnect, serverURI);

            //重新订阅
//            restoreSubscribe();
        }

        @Override
        public void connectionLost(Throwable cause) {

        }

        @Override
        public void messageArrived(String topic, MqttMessage message) {
            if (callback != null) callback.messageArrived(topic, message.getPayload());
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }
    };

    @Override
    public boolean isConnected() {
        if (client != null) {
            return client.isConnected();
        }
        return false;
    }
}
