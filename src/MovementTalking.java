package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.*;


public class MovementTalking {

    private String naam;
    public float movementSpeed = 0.3f;

    private Application application;

    static class PresenterenTekst implements Runnable {
        private MovementTalking movementTalking;
        public PresenterenTekst(MovementTalking movementTalking){ this.movementTalking = movementTalking;}

        @Override
        public void run() {
            try {
                movementTalking.zeg("Dit is mijn presentatie! Alles goed met jullie? Met mij wel!");
                Thread.sleep(1000);
                movementTalking.zeg("Kamal is druk! en ik mag ik bierie?");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class PresenterenBeweging implements Runnable {
        private MovementTalking movementTalking;
        public PresenterenBeweging(MovementTalking movementTalking) {this.movementTalking = movementTalking;};

        @Override
        public void run() {
            try {
                for (int i = 0; i < 2; i++) {
                    movementTalking.animationPath("explain");
                    movementTalking.animationPath("body language");
                    movementTalking.animationPath("explain");
                    movementTalking.wijsNaarBord();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void zeg(String tekst) throws Exception {
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());  // Create an ALTextToSpeech object and link it to your current session
        tts.say(tekst); // Make your robot say something
    }


    public void staan() throws Exception {
        ALRobotPosture movement = new ALRobotPosture(this.application.session());
        movement.goToPosture("StandInit",this.movementSpeed);
        System.out.println(movement.getPostureFamily());

    }

    public void animationPath(String path) throws Exception {
        ALAnimationPlayer alAnimationPlayer = new ALAnimationPlayer(this.application.session());
        alAnimationPlayer.runTag(path);
    }

    public void wijsNaarBord() throws Exception {
        ALMotion alMotion = new ALMotion(this.application.session());
        String[] gevrichten = {"LElbowYaw", "LShoulderRoll", "HeadYaw", "RElbowRoll", "RElbowYaw", "RShoulderPitch"};
        double[] hoeken = {-4f,5f,1.5f, 5f, 1f, 0.8f};
            for (int j = 0; j < gevrichten.length; j++) {
                alMotion.setAngles(gevrichten[j],hoeken[j],movementSpeed);
            }
            Thread.sleep(100);
    }

    public void getAngles(String onderdeel) throws Exception {
        ALMotion alMotion = new ALMotion(this.application.session());
        System.out.println(alMotion.getAngles(onderdeel,true));
    }
}

