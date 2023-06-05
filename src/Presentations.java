

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Presentations {
    public String parseJson(String name) {
        String work = null;
        try {
            Object obj = new JSONParser().parse(new FileReader("./config/presentations.json"));
            JSONObject jo = (JSONObject) obj;
            work = (String) jo.get(name);
            System.out.println(work);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return work;
    }
}
