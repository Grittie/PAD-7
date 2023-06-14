import org.eclipse.paho.client.mqttv3.*;

/**
 * The Introduction class handles the introduction sequence and MQTT communication for the study choice bot.
 */
public class Introduction {
    private static NAO nao;
    private static MQTT mqtt;
    private boolean isPressed = false;
    private static MqttClient mqttClient;

    /**
     * Retrieves the MQTT instance.
     *
     * @return the MQTT instance
     */
    static private MQTT getMqtt() {
        if (mqtt == null) {
            mqtt = new MQTT();
        }
        return mqtt;
    }

    /**
     * Constructs an Introduction object with the provided NAO instance.
     *
     * @param nao the NAO instance
     * @throws Exception if an error occurs during initialization
     */
    Introduction(NAO nao) throws Exception {
        Introduction.nao = nao;
        this.mqttClient = MQTT.getMqttClient();
        MqttConnectOptions mqttConnectOptions = MQTT.getMqttConnectOptions();
        listen();
        MQTT.connect();

        String payload = "reset";
        int qos = 0;
        boolean retained = false;
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        message.setRetained(retained);
        this.mqttClient.publish("gritla/intro", message);
        System.out.println("resetting buttons... ");

        System.out.println("connected to mqtt broker");
        this.mqttClient.subscribe("gritla/intro");
    }

    /**
     * Sets up the MQTT listener and callback.
     *
     * @throws MqttException if an error occurs during setup
     */
    private void listen() throws MqttException {
        getMqtt();
        MQTT.getMqttClient().setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                try {
                    throw throwable;
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("connection lost...");
            }

            // Listens to arrived messages and performs actions based on the received message
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                if (mqttMessage.toString().equals("Start")) {
                    System.out.println("starting intro...");
                    introductionStart();
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method
                // 'deliveryComplete'");
            }
        });
    }

    /**
     * Starts the introduction sequence.
     *
     * @throws Exception if an error occurs during the sequence
     */
    public void introductionStart() throws Exception {
        System.out.println("NAO standing...");
        nao.say();

        nao.say("Hallo, ik ben de studie keuze bot. Ik zal een demonstratie geven van een studie keuze tjek.");
        Thread.sleep(500);
        nao.say("Ik ga een paar vragen stellen. je kan hier op reageren met de knoppen voor je.");
        Thread.sleep(500);
        nao.say("Na dat de quiz over is kan je een QR code scannen.");
        Thread.sleep(500);

        System.out.println("Introduction finished");
        isPressed = true;
    }

    /**
     * Executes the introduction sequence and waits until a button is pressed.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     * @throws MqttException         if an error occurs during MQTT communication
     */
    public void introductionSequence() throws InterruptedException, MqttException {
        while (!isPressed) {
            Thread.sleep(100);
        }

        isPressed = false;

        System.out.println("Closing connection...");
        mqttClient.disconnect();
    }
}
