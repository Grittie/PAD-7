package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class Main {

    public static void main(String[] args) throws Exception {
        String robotUrl = "tcp://nao.local:9559";
        // Create a new application
        Application application = new Application(args, robotUrl);
        // Start your application
        application.start();
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(application.session());
        // Make your robot say something
        tts.say("Skedadle skedoodle!");
    }
}