import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.eclipse.paho.client.mqttv3.*;

public class Questions {
    static private final int REMINDER_DELAY = 10;

    private static JSONParser parser;
    private static MQTT mqtt;
    private static NAO nao;
    private Scores scores;
    private long[] score = new long[5];
    private boolean isPressed = false;
    private ArrayList answers;

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

    /**
     * Running this constructor without parameters is for debug purposes only
     * 
     * @throws Exception
     */
    Questions() throws Exception {
        System.out.println("Connecting to Questions MQTT...");
        MQTT.connect();
        listen();
        MQTT.subscribe("gritla/answer");
    }

    Questions(NAO nao) throws Exception {
        Questions.nao = nao;
        System.out.println("Connecting to Questions MQTT...");
        MQTT.connect();
        listen();
        MQTT.subscribe("gritla/answer");
    }

    /**
     * Listen to the mqtt broker for button presses.
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
                    e.printStackTrace();
                }
                System.out.println("connection lost...");
            }

            // Listens to arrived messages and prints the topic and message
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                System.out.println("listening for message");
                isPressed = true;
                switch (mqttMessage.toString()) {
                    case "Yes":
                        nao.say("Je hebt JA geantwoord");
                        nao.led("groen");
                        System.out.println(answers.get(0));
                        awardPoints(0);

                        break;
                    case "Maybe":
                        nao.say("Je hebt MISSCHIEN geantwoord");
                        nao.led("geel");
                        System.out.println(answers.get(1));
                        awardPoints(1);

                        break;
                    case "No":
                        nao.say("Je hebt NEE geantwoord");
                        nao.led("rood");
                        System.out.println(answers.get(2));
                        awardPoints(2);

                        break;
                    default:
                        System.out.println("This is not a valid input");
                        break;
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    /**
     * Adds the appropriate points to `score[]
     * 
     * @param index
     */
    private void awardPoints(int index) {
        score[0] += (int) (long) ((JSONObject) answers.get(index)).get("score-back-end");
        score[1] += (int) (long) ((JSONObject) answers.get(index)).get("score-front-end");
        score[2] += (int) (long) ((JSONObject) answers.get(index)).get("score-robot-ui");
        score[3] += (int) (long) ((JSONObject) answers.get(index)).get("score-robot-technical");
        score[4] += (int) (long) ((JSONObject) answers.get(index)).get("score-ict-ondernemer");
    }

    /**
     * This function parses and asks all questions
     */
    public void askAllQuestions() {
        try {

            Object obj = getJsonParser().parse(new FileReader("./config/questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray questions = (JSONArray) jsonObject.get("questions");

            for (Object question : questions) {
                JSONObject questionObj = (JSONObject) question; // Cast to JSONObject

                String questionValue = (String) questionObj.get("question");
                answers = (ArrayList<String>) questionObj.get("answers"); // Cast to ArrayList<String>
                Thread reminder = new Thread(new Reminder(REMINDER_DELAY, questionValue));
                Thread waiting = new Thread(new Waiting());

                System.out.println(questionValue);
                nao.say(questionValue);
                MQTT.publish("test", "gritla/led", 0, false);
                Thread.sleep(10);
                waiting.start();
                reminder.start();

                while (!isPressed) {
                    Thread.sleep(100);
                }
                isPressed = false;
                if (reminder.isAlive()) {
                    reminder.interrupt();
                }
                if (waiting.isAlive()) {
                    waiting.interrupt();
                }
                nao.stopmusic();
                waiting.interrupt();
            }

            System.out.println(Arrays.toString(score));
            Scores.storeResults(score);
            nao.say("Dankjewel voor je antwoorden.");
            nao.led("blauw");
            Thread.sleep(1000);
            nao.say("Ik zal nu een image voor je genereren.");
            Thread.sleep(1000);

            // Send score array to CreateImage class

            CreateImage createImage = new CreateImage();
            String highest = createImage.barChart(score);
            createImage.barChart(score);

            nao.say("Het startprofiel: " + highest
                    + "lijkt mij het best geschikt voor jou, ik ga jou een presentatie nu geven!");
            Presentations presentations = new Presentations();
            nao.say(presentations.parseJson(highest));

            // Closing mqtt connection
            MQTT.disconnect();
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
         * 
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

    /**
     * Thread to play music and move while waiting on answer from player
     */
    static class Waiting implements Runnable {

        @Override
        public void run() {
            try {
                nao.music();
                nao.waitingloop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
