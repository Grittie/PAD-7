package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;

public class Dab {

    private Application application;

    public void dab() throws Exception {
        ALMotion motion = new ALMotion(this.application.session());
        ALRobotPosture robotPosture = new ALRobotPosture(this.application.session());
        String[] limbs = {"LShoulderPitch", "LShoulderRoll", "LElbowYaw", "LElbowRoll", "LWristYaw", "RShoulderPitch", "RShoulderRoll", "RElbowYaw", "RElbowRoll", "RWristYaw", "HeadPitch"};
        double[] limbsAngels = {0.2f, 0.0f, 0.0f, -2.0f, 0.0f, 1.9f, -2.0f, 1.0f, 0.0f, 0.0f, 1.0f};
        float fractionMaxSpeed = 0.1f;
        for (int i = 0; i < limbsAngels.length; i++) {
            motion.setAngles(limbs[i], limbsAngels[i], fractionMaxSpeed);
        }
        Thread.sleep(10000);
        robotPosture.goToPosture("StandInit", 0.2f);
    }
}