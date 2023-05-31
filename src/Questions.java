package src;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.eclipse.paho.client.mqttv3.*;

public class Questions {
    static private final int REMINDER_DELAY = 10;

    static private JSONParser parser;
    static private MQTT mqtt;
    static private NAO nao;
    static private Scores scores;
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

    Questions(NAO nao) throws Exception {
        Questions.nao = nao;
        MqttClient client = MQTT.getMqttClient();
        MqttConnectOptions mqttConnectOptions = MQTT.getMqttConnectOptions();
        MQTT.connect();
        client.subscribe("gritla/answer");
        listen();
    }

    /**
     * Listen to the mqtt database for button presses.
     * 
     * @throws MqttException
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

            // Listens to arrived messages and prints the topic and message
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                isPressed = true;
                switch (mqttMessage.toString()) {
                    case "Yes":
                        nao.say("Je hebt JA geantwoord");
                        System.out.println(answers.get(0));
                        score[0] += (int) (long) ((JSONObject) answers.get(0)).get("score-back-end");
                        score[1] += (int) (long) ((JSONObject) answers.get(0)).get("score-front-end");
                        score[2] += (int) (long) ((JSONObject) answers.get(0)).get("score-robot-ui");
                        score[3] += (int) (long) ((JSONObject) answers.get(0)).get("score-robot-technical");
                        score[4] += (int) (long) ((JSONObject) answers.get(0)).get("score-ict-ondernemer");

                        break;
                    case "Maybe":
                        nao.say("Je hebt MISSCHIEN geantwoord");
                        System.out.println(answers.get(1));
                        score[0] += (int) (long) ((JSONObject) answers.get(1)).get("score-back-end");
                        score[1] += (int) (long) ((JSONObject) answers.get(1)).get("score-front-end");
                        score[2] += (int) (long) ((JSONObject) answers.get(1)).get("score-robot-ui");
                        score[3] += (int) (long) ((JSONObject) answers.get(1)).get("score-robot-technical");
                        score[4] += (int) (long) ((JSONObject) answers.get(1)).get("score-ict-ondernemer");

                        break;
                    case "No":
                        nao.say("Je hebt NEE geantwoord");
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
                JSONObject questionObj = (JSONObject) question; // Cast to JSONObject

                String questionValue = (String) questionObj.get("question");
                answers = (ArrayList<String>) questionObj.get("answers"); // Cast to ArrayList<String>
                Thread reminder = new Thread(new Reminder(10));

                System.out.println(questionValue);
                nao.say(questionValue);
                reminder.start();

                while (!isPressed) {
                    Thread.sleep(100);
                }
                isPressed = false;
                if (reminder.isAlive()) {
                    reminder.interrupt();
                }
            }

            System.out.println(Arrays.toString(score));
            scores.storeResults(score);
            nao.say("Dankjewel voor je antwoorden.");
            Thread.sleep(1000);
            nao.say("Ik zal nu een image voor je genereren.");
            Thread.sleep(1000);

            // Send score array to CreateImage class
            CreateImage createImage = new CreateImage();
            createImage.staafDiagram(score);

            System.out.println("closing");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Reminder implements Runnable {

        static private int time;
        static private String question;

        /**
         * Reminds the quiz taker to give answer after a certain amount of time.
         * This class is intended to run in parallel as a Thread.
         * 
         * @param time
         */
        Reminder(int time, String question) {
            Reminder.time = time;
            Reminder.question = question;
        }

        /**
         * Reminds the quiz taker to give answer after a certain amount of time
         * @throws CallError
         */
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(time * 1000L);
                    nao.say(question);
                    System.out.println("Reminder: " + question);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
