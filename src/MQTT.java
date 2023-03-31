package src;

import org.eclipse.paho.client.mqttv3.*;

public class MQTT {
    // MQTT broker credentials
    public static String MQTT_HOST = "tcp://mqtt.hva-robots.nl:1883";
    public static String MQTT_CLIENT_ID = "gritla_0";
    public static String MQTT_USERNAME = "gritla";
    public static String MQTT_PASSWORD = "D6G9E1b95x8h3LaGFtxA";

    // Function that runs the MQTT Client
    public static void mqttClient() throws Exception {
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
                        System.out.println("Software engineering presentation starting...");
                        break;
                    case "TI":
                        System.out.println("Technische Informatica presentation starting...");
                        break;
                    case "BIM":
                        System.out.println("Business Management presentation starting...");
                        break;
                    case "CS":
                        System.out.println("Cyber Security presentation starting...");
                        break;
                    case "GD":
                        System.out.println("Game Development presentation starting...");
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
