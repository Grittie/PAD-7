import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALFaceDetection;

public class Idle {
    Application application;

    public Idle(Application application) {
        this.application = application;
    }

    /**
     * This function starts the idle animation.
     * You can choose if it tracks faces.
     *
     * @param tracking
     * @throws Exception
     */
    public void startIdleAnimation(boolean tracking) throws Exception {
        ALMotion motion = new ALMotion(application.session());
        ALFaceDetection faceDetection = new ALFaceDetection(application.session());
        faceDetection.setRecognitionEnabled(false);

        String[] limbs = { "LShoulderPitch", "LShoulderRoll", "LElbowYaw", "LElbowRoll", "LWristYaw", "RShoulderPitch",
                "RShoulderRoll", "RElbowYaw", "RElbowRoll", "RWristYaw", "HeadPitch" };
        double[] limbsAngels = { 1f, 0.4f, 0.0f, -2.0f, 0.0f, 1f, -0.4f, 0.0f, 2.0f, 0.0f };

        float fractionMaxSpeed = 0.1f;
        for (int i = 0; i < limbsAngels.length; i++) {
            motion.setAngles(limbs[i], limbsAngels[i], fractionMaxSpeed);
        }
        faceDetection.setTrackingEnabled(tracking);
    }
}
