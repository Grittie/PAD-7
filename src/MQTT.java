package src;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Scanner;

public class MQTT {
    // MQTT broker credentials
    public static String MQTT_HOST = "tcp://mqtt.hva-robots.nl:1883";
    public static String MQTT_CLIENT_ID = "gritla_0";
    public static String MQTT_USERNAME = "gritla";
    public static String MQTT_PASSWORD = "D6G9E1b95x8h3LaGFtxA";

    private NAO nao;

    // Function that runs the MQTT Client
    public void mqttClient() throws Exception {
        //connect NAO
        this.nao = new NAO("johanus.local", 9559);
        // Connects to MQTT broker
        MqttClient client = new MqttClient (MQTT_HOST, MQTT_CLIENT_ID);
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setUserName (MQTT_USERNAME);
        connectOptions.setPassword (MQTT_PASSWORD.toCharArray());
        client.connect(connectOptions);

        // Prints true if connection is made
        System.out.print("Verbonden? ");
        System.out.println(client.isConnected());

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("connection lost...");
            }

            // Listens to arrived messages and prints the topic and message
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                System.out.println("Bericht ontvangen");
                System.out.print("Topic:");
                System.out.println(topic);
                System.out.print("Bericht: ");
                System.out.println(mqttMessage.toString());


                // Switch case that plays presentation according to buttong pressed on website
                switch (mqttMessage.toString()) {
                    case "SE":
                        nao.staan();
                        System.out.println("Software engineering presentation starting...");
                        nao.led("rood");
                        Thread.sleep(2000);
                        nao.zeg(nao.se());
                        nao.led("wit");
                        nao.staan();

                        break;
                    case "TI":
                        System.out.println("Technische Informatica presentation starting...");
                        nao.staan();
                        nao.led("geel");
                        nao.zeg(nao.ti());
                        nao.led("wit");
                        nao.staan();
                        break;
                    case "BIM":
                        System.out.println("Business Management presentation starting...");
                        nao.staan();
                        nao.led("paars");
                        nao.zeg(nao.bim());
                        nao.led("wit");
                        nao.staan();
                        break;
                    case "CS":
                        System.out.println("Cyber Security presentation starting...");
                        nao.staan();
                        nao.led("groen");
                        nao.zeg(nao.cs());
                        nao.led("wit");
                        nao.staan();
                        break;
                    case "GD":
                        System.out.println("Game Development presentation starting...");
                        nao.staan();
                        nao.led("blauw");
                        nao.zeg(nao.gd());
                        nao.led("wit");
                        nao.staan();
                        break;
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

        // Subscribed to topic
        client.subscribe("gritla/leerroute");
    }
}
