package src;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.*;

public class MovementTalking {

    private String naam;
    private static final float MOVEMENT_SPEED = 0.3f; // speed of the movements

    private Session session;

    public MovementTalking(Session session) {
        this.session = session;
    }


    //Thead waarin de robot dingen kan zeggen
    static class await implements Runnable{
        @Override
        public void run() {
            System.out.println("await thread");

        }
    }
    static class PresenterenTekst implements Runnable {
        private NAO movementTalking;
        public PresenterenTekst(NAO movementTalking){ this.movementTalking = movementTalking;}

        @Override
        public void run() {
            try {
                movementTalking.say("Welkom bij de Open dag");
                Thread.sleep(1000);
                movementTalking.say("Er zijn op deze opleiding 3 verschillende leerroutes");
                Thread.sleep(1000);
                movementTalking.say("Wil je daar meer over weten?");
                Thread.sleep(1000);
                movementTalking.say("Druk dan op een van deze 3 knoppen! ");


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Thread om de robot te laten bewegen, er zijn 2 threads aangemaakt zodat de robot kan praten en kan bewegen
    static class PresenterenBeweging implements Runnable {
        private NAO movementTalking;
        public PresenterenBeweging(NAO movementTalking) {this.movementTalking = movementTalking;};

        @Override
        public void run() {
            try {
                for (int i = 0; i < 2; i++) { // Loop om de robot verschillende animaties te doen
                    movementTalking.animationRandom("explain");
                    movementTalking.animationRandom("body language");
                    movementTalking.animationRandom("explain");
                    movementTalking.wijsNaarBord();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void say(String tekst) throws Exception {
        ALTextToSpeech tts = new ALTextToSpeech(this.session);// Create an ALTextToSpeech object and link it to your current session
        ALAnimatedSpeech alAnimatedSpeech = new ALAnimatedSpeech(this.session);
        alAnimatedSpeech.say(tekst);
    }


    public void staan() throws Exception {
        ALRobotPosture movement = new ALRobotPosture(this.session); // Create an ALRobotPosture object and link it to current application
        movement.goToPosture("Stand",this.MOVEMENT_SPEED); // let the robot stand in the preset "Stand"
    }

    public void animationRandom(String path) throws Exception {
        ALAnimationPlayer alAnimationPlayer = new ALAnimationPlayer(this.session); // Create an ALAnimationPLayer object and link it
        alAnimationPlayer.runTag(path); // the runTag choose a random animation within a tag
    }
    public void animationPath(String path) throws Exception {
        ALAnimationPlayer alAnimationPlayer = new ALAnimationPlayer(this.session); // Create an ALAnimationPLayer object and link it
        alAnimationPlayer.run(path); // the run makes do the robot do a specific animtion.
    }

    public void wijsNaarBord() throws Exception {
        ALMotion alMotion = new ALMotion(this.session); // Create an alMotion object and link it
        String[] gevrichten = {"LElbowYaw", "LShoulderRoll", "HeadYaw", "RElbowRoll", "RElbowYaw", "RShoulderPitch"}; // Gets the robots limbs
        double[] hoeken = {-4f,5f,1.5f, 5f, 1f, 0.8f}; // the radians that the limbs need to reach
            for (int j = 0; j < gevrichten.length; j++) {
                alMotion.setAngles(gevrichten[j],hoeken[j], MOVEMENT_SPEED); // sets al the radians to the limbs
            }
            Thread.sleep(100);
    }


}

