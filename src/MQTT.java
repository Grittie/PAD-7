import org.eclipse.paho.client.mqttv3.*;

/**
 * The MQTT class handles the MQTT client connection and subscription.
 */
public class MQTT {
    // MQTT broker credentials
    private static final String MQTT_HOST = "tcp://mqtt.hva-robots.nl:1883";
    private static final String MQTT_CLIENT_ID = "gritla_0";
    private static final String MQTT_USERNAME = "gritla";
    private static final String MQTT_PASSWORD = "D6G9E1b95x8h3LaGFtxA";

    private static MqttClient client;
    private static MqttConnectOptions connectOptions;

    /**
     * Retrieves the MQTT client instance.
     *
     * @return the MQTT client instance
     * @throws MqttException if an error occurs while creating the client
     */
    public static MqttClient getMqttClient() throws MqttException {
        if (client == null) {
            client = new MqttClient(MQTT_HOST, MQTT_CLIENT_ID);
        }
        return client;
    }

    /**
     * Retrieves the MQTT connection options.
     *
     * @return the MQTT connection options
     */
    public static MqttConnectOptions getMqttConnectOptions() {
        if (connectOptions == null) {
            connectOptions = new MqttConnectOptions();
        }
        return connectOptions;
    }

    /**
     * Connects to the MQTT broker.
     *
     * @throws MqttSecurityException if there is a security-related error
     * @throws MqttException         if an error occurs while connecting
     */
    public static void connect() throws MqttSecurityException, MqttException {
        connectOptions.setUserName(MQTT_USERNAME);
        connectOptions.setPassword(MQTT_PASSWORD.toCharArray());
        getMqttClient().connect(getMqttConnectOptions());
    }

    /**
     * Subscribes to a specific MQTT topic.
     *
     * @param topic the topic to subscribe to
     * @throws MqttException if an error occurs while subscribing
     */
    public static void subscribe(String topic) throws MqttException {
        getMqttClient().subscribe(topic);
    }

    /**
     * Publish messager to connected mqtt broker
     * 
     * @param payload The mesage you want to send
     * @param topic   The topic in the broker
     * @param qos     How sure do you want to be that the message reaches the broker
     * @param retain  Should the broker rember this message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public static void publish(String payload, String topic, int qos, boolean retain)
            throws MqttPersistenceException, MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        message.setRetained(retain);
        getMqttClient().publish(topic, message);
    }

    /**
     * Disconnect from the mqtt broker
     * 
     * @throws MqttException
     */
    public static void disconnect() throws MqttException {
        getMqttClient().disconnect();
    }

    // Function that runs the MQTT Client
    public void mqttClient() throws Exception {
        // connect NAO
        NAO nao = new NAO("johanus.local", 9559);
        // Connects to MQTT broker
        MqttClient client = new MqttClient(MQTT_HOST, MQTT_CLIENT_ID);
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setUserName(MQTT_USERNAME);
        connectOptions.setPassword(MQTT_PASSWORD.toCharArray());
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
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

        // Subscribed to topic
        client.subscribe("gritla/leerroute");
    }
}
