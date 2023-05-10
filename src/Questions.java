package src;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import com.aldebaran.qi.CallError;
import org.eclipse.paho.client.mqttv3.*;

public class Questions {
    static private Session session;
    static private ALTextToSpeech textToSpeech;
    static private JSONParser parser;
    static private MQTT mqtt;
    static private long[] score = new long[5];
    static private boolean isPressed = false;

    static private ArrayList answers;

    static private JSONParser getJsonParser() {
        if (parser == null) {
            parser = new JSONParser();
        }
        return parser;
    }

    static private MQTT getMqtt() {
        if (mqtt == null) {
            mqtt = new MQTT();
        }
        return mqtt;
    }

    // public Questions(Session session) throws Exception {
    public Questions() throws Exception {
        // this.session = session;
        // this.textToSpeech = new ALTextToSpeech(this.session);
        MqttClient client = MQTT.getMqttClient();
        MqttConnectOptions mqttConnectOptions = MQTT.getMqttConnectOptions();
        MQTT.connect();
        client.subscribe("gritla/answer");
        listen();
    }

    /**
     * Reminds the quiz taker to give answer after a certain amount of time
     *
     * @param sec The time in seconds
     * @throws InterruptedException
     * @throws CallError
     */
    public void remind(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
        // textToSpeech.say("Je kan antwoord geven door op 1 van deze knoppen te
        // drukken");
        System.out.println("Je kan antwoord geven door op 1 van deze knoppen te drukken");
    }

    /**
     * Listen to the mqtt database for button presses
     *
     * @throws MqttException
     */
    public void listen() throws MqttException {
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

            // Listens to arrived messages and prints the topic and message
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                isPressed = true;
                switch (mqttMessage.toString()) {
                    case "Yes":
                        // System.out.println(answers.get(0));
                        System.out.println(((JSONObject) answers.get(0)).get("score-back-end"));
                         score[0] += (int) (long) ((JSONObject) answers.get(0)).get("score-back-end");
                         score[1] += (int) (long) ((JSONObject) answers.get(0)).get("score-front-end");
                         score[2] += (int) (long) ((JSONObject) answers.get(0)).get("score-robot-ui");
                         score[3] += (int) (long) ((JSONObject) answers.get(0)).get("score-robot-technical");
                         score[4] += (int) (long) ((JSONObject) answers.get(0)).get("score-ict-ondernemer");

                        break;
                    case "Maybe":
                        System.out.println(answers.get(1));

                         score[0] += (int) (long) ((JSONObject) answers.get(1)).get("score-back-end");
                         score[1] += (int) (long) ((JSONObject) answers.get(1)).get("score-front-end");
                         score[2] += (int) (long) ((JSONObject) answers.get(1)).get("score-robot-ui");
                         score[3] += (int) (long) ((JSONObject) answers.get(1)).get("score-robot-technical");
                         score[4] += (int) (long) ((JSONObject) answers.get(1)).get("score-ict-ondernemer");

                        break;
                    case "No":
                        System.out.println(answers.get(2));

                         score[0] += (int) (long) ((JSONObject) answers.get(2)).get("score-back-end");
                         score[1] += (int) (long) ((JSONObject) answers.get(2)).get("score-front-end");
                         score[2] += (int) (long) ((JSONObject) answers.get(2)).get("score-robot-ui");
                         score[3] += (int) (long) ((JSONObject) answers.get(2)).get("score-robot-technical");
                         score[4] += (int) (long) ((JSONObject) answers.get(2)).get("score-ict-ondernemer");

                        break;
                    default:
                        System.out.println("This is not a valid input");
                        break;
                }

                // System.out.println(mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'deliveryComplete'");
            }
        });
    }

    public void parseJson() {
        try {
            Object obj = getJsonParser().parse(new FileReader("./config/questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray questions = (JSONArray) jsonObject.get("questions");
            Iterator iterator = questions.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void askAllQuestions() {
        try {
            Object obj = getJsonParser().parse(new FileReader("./config/questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray questions = (JSONArray) jsonObject.get("questions");
            for (Object question : questions) {
                String questionValue = (String) ((JSONObject) question).get("question");
                answers = (ArrayList) ((JSONObject) question).get("answers");

                System.out.println(questionValue);

                while (!isPressed) {
                    Thread.sleep(100); // anders is er geen tijd om te lluisteren naar MQTT
                }
                isPressed = false;
            }

            System.out.println("closing");
            System.out.println(Arrays.toString(score));

            CreateImage createImage = new CreateImage();
            createImage.staafDiagram(score);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
