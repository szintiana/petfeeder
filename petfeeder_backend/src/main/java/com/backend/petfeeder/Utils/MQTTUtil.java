package com.backend.petfeeder.Utils;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

@Service
public class MQTTUtil implements MqttCallback {
    private MqttClient client;

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection Lost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        handleReceivedMessage(s, mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println(iMqttDeliveryToken);
    }

    public void subscribe(String topic) {
        try {
            if (client.isConnected()) {
                client.subscribe(topic);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String content) {
        MqttMessage message = new MqttMessage();
        message.setPayload(content.getBytes());
        message.setQos(2);
        try {
            if (client.isConnected()) {
                client.publish(topic, message);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void startMqtt() {
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            String mqttIpAddress = "127.0.0.1";
            String mqttPort = "1883";
            client = new MqttClient("tcp://" + mqttIpAddress + ":" + mqttPort, MqttClient.generateClientId(),
                    persistence);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            connectOptions.setMaxInflight(3000);
            connectOptions.setAutomaticReconnect(true);

            client.setCallback(this);
            client.connect(connectOptions);
        } catch (MqttException e1) {
            e1.printStackTrace();
        }
    }

    public void handleReceivedMessage(String topic, MqttMessage message) {
        if (topic.contains("petfeeder")) {
            System.out.println("Pet fed");
        }
    }
}
