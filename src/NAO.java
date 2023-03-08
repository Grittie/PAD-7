package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class NAO {
    private String name;
    private Application application;

    public void connect(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[] {}, robotUrl);
        // Start your application
        application.start();

    }

    public void stand() throws Exception {
        ALRobotPosture robotPosture = new ALRobotPosture(application.session());
        robotPosture.goToPosture("StandInit", 0.5f);
    }

    public void sit() throws Exception {
        ALRobotPosture robotPosture = new ALRobotPosture(application.session());
        robotPosture.goToPosture("StandInit", 0.5f);
    }

    public void say(String tekst) throws Exception {
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());
        // Make your robot say something
        tts.say(tekst);
    }

    public void idle() throws Exception {
        ALRobotPosture robotPosture = new ALRobotPosture(application.session());
        robotPosture.goToPosture("LyingBack", 0.5f);
    }
}
