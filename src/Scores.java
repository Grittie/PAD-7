package src;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Scores {
    public static void storeResults(long[] score) {
        // Read existing JSON file
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader("./config/scores.json")) {
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

            // Get the first scores object in the array
            JSONObject scoresObject = (JSONObject) jsonArray.get(0);
            JSONObject scoreObject = (JSONObject) scoresObject.get("scores");

            // Update the score values
            scoreObject.put("score-back-end", (Long) scoreObject.get("score-back-end") + score[0]);
            scoreObject.put("score-front-end", (Long) scoreObject.get("score-front-end") + score[1]);
            scoreObject.put("score-robot-ui", (Long) scoreObject.get("score-robot-ui") + score[2]);
            scoreObject.put("score-robot-technical", (Long) scoreObject.get("score-robot-technical") + score[3]);
            scoreObject.put("score-ict-ondernemer", (Long) scoreObject.get("score-ict-ondernemer") + score[4]);

            // Write updated JSON array to file
            try (FileWriter fileWriter = new FileWriter("./config/scores.json")) {
                fileWriter.write(jsonArray.toJSONString());
                System.out.println("Scores successfully written to scores.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    void getResults() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("./config/scores.json"));
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject = (JSONObject) jsonArray.get(0); // Assuming there is only one object in the JSON array

            JSONObject scoresObject = (JSONObject) jsonObject.get("scores");
            Iterator<?> iterator = scoresObject.keySet().iterator();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                int score = Integer.parseInt(scoresObject.get(key).toString());
                System.out.println(key + ": " + score);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
