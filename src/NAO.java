package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;


public class NAO {

    private Application application;

    public void verbinden(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }

    public void fysiekVerbinden() {
        String robotUrl = "tcp://localhost:62627";
        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }

    public void dab() throws Exception {
        ALMotion motion = new ALMotion(application.session());
        String[] limbs = {"LShoulderPitch", "LShoulderRoll", "LElbowYaw", "LElbowRoll", "LWristYaw", "RShoulderPitch", "RShoulderRoll", "RElbowYaw", "RElbowRoll", "RWristYaw", "HeadPitch"};
        double[] limbsAngels = {0.2f, 0.0f, 0.0f, -2.0f, 0.0f, 1.9f, -2.0f, 1.0f, 0.0f, 0.0f, 1.0f};
        float fractionMaxSpeed = 0.1f;
        for (int i = 0; i < limbsAngels.length; i++) {
            motion.setAngles(limbs[i], limbsAngels[i], fractionMaxSpeed);
        }
    }

    public void zeg(String tekst) throws Exception {
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());
        // Make your robot say something
        tts.say(tekst);
    }
}