import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.desktop.QuitEvent;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {


        NAO nao = new NAO("localhost", 59679);
        Questions questions = new Questions(nao);
        CreateImage createImage = new CreateImage();
        long[] results = {10,10,10,150,10};

        createImage.staafDiagram(results);
        String hoogste = createImage.staafDiagram(results);
        questions.parseJson(hoogste);

    }
}
