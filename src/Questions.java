package src;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Questions {
    public static void main(String[] args) {

    }

    public void parseJson() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./config/questions.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray questions = (JSONArray)jsonObject.get("questions");
            Iterator iterator = questions.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
