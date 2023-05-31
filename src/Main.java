import org.eclipse.paho.client.mqttv3.MqttClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.desktop.QuitEvent;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {

        NAO nao = new NAO("169.254.9.34", 9559);

//        nao.staan();
//        nao.say("Hallo ik ben de Studie Keuze bot. Ik zal jullie een korte demonstratie geven van een studie keuze check.");
//        Thread.sleep(1000);
//        nao.say("Ik zal je een paar vragen stellen. Jij kan hier op antwoorden met Ja, Nee of Misschien door te drukken op de knoppen voor je!");
//        nao.say("Daarna zal ik een QR code maken met mijn advies die je dan met je telefoon kan scannen!");
//        Thread.sleep(1000);

        Questions questions = new Questions(nao);
        questions.askAllQuestions();

        Scores scores = new Scores();
        scores.getResults();
    }
}
