
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.CallError;
import org.eclipse.paho.client.mqttv3.*;

public class Questions {
    static private Session session;
    static private JSONParser parser;
    static private MQTT mqtt;

    static private NAO nao;
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
        this.nao = nao;
        MqttClient client = MQTT.getMqttClient();
        MqttConnectOptions mqttConnectOptions = MQTT.getMqttConnectOptions();
        MQTT.connect();
        client.subscribe("gritla/answer");
        listen();
    }

    /**
     * Listen to the mqtt database for button presses
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
                        nao.led("groen");
                        System.out.println(answers.get(0));
                        score[0] += (int) (long) ((JSONObject) answers.get(0)).get("score-back-end");
                        score[1] += (int) (long) ((JSONObject) answers.get(0)).get("score-front-end");
                        score[2] += (int) (long) ((JSONObject) answers.get(0)).get("score-robot-ui");
                        score[3] += (int) (long) ((JSONObject) answers.get(0)).get("score-robot-technical");
                        score[4] += (int) (long) ((JSONObject) answers.get(0)).get("score-ict-ondernemer");

                        break;
                    case "Maybe":
                        nao.say("Je hebt MISSCHIEN geantwoord");
                        nao.led("geel");
                        System.out.println(answers.get(1));
                        score[0] += (int) (long) ((JSONObject) answers.get(1)).get("score-back-end");
                        score[1] += (int) (long) ((JSONObject) answers.get(1)).get("score-front-end");
                        score[2] += (int) (long) ((JSONObject) answers.get(1)).get("score-robot-ui");
                        score[3] += (int) (long) ((JSONObject) answers.get(1)).get("score-robot-technical");
                        score[4] += (int) (long) ((JSONObject) answers.get(1)).get("score-ict-ondernemer");

                        break;
                    case "No":
                        nao.say("Je hebt NEE geantwoord");
                        nao.led("rood");
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


    public void parseJson(String name) {
        try {
            Object obj = new JSONParser().parse(new FileReader("./config/presentations.json"));
            JSONObject jo = (JSONObject) obj;
            String work = (String) jo.get(name);
            System.out.println(work);
            nao.say(work);


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
                Thread reminder = new Thread(new Reminder(10));
                Thread waiting = new Thread(new Waiting());

                System.out.println(questionValue);
                reminder.start();
                nao.say(questionValue);
                Thread.sleep(10);
                waiting.start();

                while (!isPressed) {
                    Thread.sleep(100); // anders is er geen tijd om te luisteren naar MQTT
                }
                isPressed = false;
                if (reminder.isAlive()) {
                    reminder.interrupt();
                }
                nao.stopmusic();
                waiting.interrupt();
            }

            System.out.println(Arrays.toString(score));
            nao.say("Dankjewel voor je antwoorden.");
            nao.led("blauw");
            Thread.sleep(1000);
            nao.say("Ik zal nu een image voor je genereren.");
            Thread.sleep(1000);

            // Send score array to CreateImage class
            CreateImage createImage = new CreateImage();
            String highest = createImage.staafDiagram(score);
            createImage.staafDiagram(score);

            nao.say("Het startprofiel: "+highest + "lijkt mij het best geschikt voor jou, ik ga jou een presentatie nu geven! ");
            this.parseJson(highest);
            System.out.println("closing");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Reminder implements Runnable {

        static private int time;

        Reminder(int time) {
            Reminder.time = time;
        }

        /**
         * Reminds the quiz taker to give answer after a certain amount of time
         *
         * @param sec The time in seconds
         * @throws CallError
         */
        @Override
        public void run() {
            try {
                Thread.sleep(time * 1000);
                nao.say("Je kan antwoord geven door op 1 van deze knoppen te drukken");
                System.out.println("Je kan antwoord geven door op 1 van deze knoppen te drukken");
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
