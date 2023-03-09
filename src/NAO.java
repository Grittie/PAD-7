package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;
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
        ALMotion motion = new ALMotion(application.session());
        String[] limbs = { "LShoulderPitch", "LShoulderRoll", "LElbowYaw", "LElbowRoll", "LWristYaw", "RShoulderPitch",
                "RShoulderRoll", "RElbowYaw", "RElbowRoll", "RWristYaw", "HeadPitch" };
        double[] limbsAngels = { 1f, 0.4f, 0.0f, -2.0f, 0.0f, 1f, -0.4f, 0.0f, 2.0f, 0.0f };

        float fractionMaxSpeed = 0.1f;
        for (int i = 0; i < limbsAngels.length; i++) {
            motion.setAngles(limbs[i], limbsAngels[i], fractionMaxSpeed);
        }

    }
}
