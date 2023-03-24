package src;

import org.eclipse.paho.client.mqttv3.*;

public class Main {
    // Host of the MQTT broker
    public static String MQTT_HOST = "tcp://mqtt.hva-robots.nl:1883";
    // Client id, unique name for each client, prefix with your username
    public static String MQTT_CLIENT_ID = "nao_test";
    // Username from hva-robots.nl
    public static String MQTT_USERNAME = "gritla";
    // Password from hva-robots.nl (don't use your HvA password!)
    public static String MQTT_PASSWORD = "D6G9E1b95x8h3LaGFtxA";

    public static void main(String[] args) throws Exception {
//        MovementTalking nao = new MovementTalking();
//
//        nao.fysiekVerbinden();
//        nao.staan();
//
//        new Thread(new MovementTalking.PresenterenBeweging(nao)).start();
//        new Thread(new MovementTalking.PresenterenTekst(nao)).start();

        MqttClient client = new MqttClient (MQTT_HOST, MQTT_CLIENT_ID);
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setUserName (MQTT_USERNAME);
        connectOptions.setPassword (MQTT_PASSWORD.toCharArray());
        client.connect(connectOptions);

        System.out.print("Verbonden? ");
        System.out.println(client.isConnected());

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("connection lost...");
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                System.out.println("Bericht ontvangen");
                System.out.print("Topic:");
                System.out.println(topic);
                System.out.print("Bericht: ");
                System.out.println(mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });

        client.subscribe("gritla/test");
    }
}
