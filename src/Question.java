package src;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

import org.eclipse.paho.client.mqttv3.*;

public class Question {
    private Session session;

    static private MQTT mqtt;

    static private MQTT getMqtt() {
        if (mqtt == null) {
            mqtt = new MQTT();
        }
        return mqtt;
    }

    static private ALTextToSpeech textToSpeech;

    public Question(Session session) throws Exception {
        this.session = session;
        this.textToSpeech = new ALTextToSpeech(this.session);
    }

    Question() throws MqttSecurityException, MqttException, InterruptedException, CallError {
        MqttClient client = MQTT.getMqttClient();
        MqttConnectOptions mqttConnectOptions = MQTT.getMqttConnectOptions();

        MQTT.connect();
        client.subscribe("gritla/leerroute");

        listen();
        remind(10);
    }

    public void askQuestion(){}

    /**
     * Reminds the quiz taker to give answer after a certain amount of time
     * @param sec The time in seconds
     * @throws InterruptedException
     * @throws CallError
     */
    public void remind(int sec) throws InterruptedException, CallError {
        Thread.sleep(sec * 1000);
        // textToSpeech.say("Je kan antwoord geven door op 1 van deze knoppen te drukken");
        System.out.println("Je kan antwoord geven door op 1 van deze knoppen te drukken");
    }

    /**
     * Listen to the mqtt database for button presses
     * @throws MqttException
     */
    public void listen() throws MqttException {

        getMqtt();
        MQTT.getMqttClient().setCallback(new MqttCallback() {
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
            public void deliveryComplete(IMqttDeliveryToken token) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'deliveryComplete'");
            }
        });
    }
}
