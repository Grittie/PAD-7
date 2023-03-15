package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.*;


public class MovementTalking {

    private String naam;
    public float movementSpeed = 0.3f; // speed of the movements

    private Application application;


    //Thead waarin de robot dingen kan zeggen
    static class PresenterenTekst implements Runnable {
        private MovementTalking movementTalking;
        public PresenterenTekst(MovementTalking movementTalking){ this.movementTalking = movementTalking;}

        @Override
        public void run() {
            try {
                movementTalking.zeg("Ik ga jullie de leerroutes presenteren!");
                Thread.sleep(1000);
                movementTalking.zeg("Alleen is dit nog de eerste sprint review");
                Thread.sleep(1000);
                movementTalking.zeg("Daarom kan ik nog niet alles, en heb ik ook wat moeite met de taal");
                Thread.sleep(1000);
                movementTalking.zeg("Maar op het bord kan je een mooie presentatie zien die gemaakt is!");
                Thread.sleep(1000);
                movementTalking.zeg("Dit was mijn aller eerste presentaite!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Thread om de robot te laten bewegen, er zijn 2 threads aangemaakt zodat de robot kan praten en kan bewegen
    static class PresenterenBeweging implements Runnable {
        private MovementTalking movementTalking;
        public PresenterenBeweging(MovementTalking movementTalking) {this.movementTalking = movementTalking;};

        @Override
        public void run() {
            try {
                for (int i = 0; i < 2; i++) { // Loop om de robot verschillende animaties te doen
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
        ALRobotPosture movement = new ALRobotPosture(this.application.session()); // Create an ALRobotPosture object and link it to current application
        movement.goToPosture("Stand",this.movementSpeed); // let the robot stand in the preset "Stand"
    }

    public void animationPath(String path) throws Exception {
        ALAnimationPlayer alAnimationPlayer = new ALAnimationPlayer(this.application.session()); // Create an ALAnimationPLayer object and link it
        alAnimationPlayer.runTag(path); // the runTag choose a random animation within a tag
    }

    public void wijsNaarBord() throws Exception {
        ALMotion alMotion = new ALMotion(this.application.session()); // Create an alMotion object and link it
        String[] gevrichten = {"LElbowYaw", "LShoulderRoll", "HeadYaw", "RElbowRoll", "RElbowYaw", "RShoulderPitch"}; // Gets the robots limbs
        double[] hoeken = {-4f,5f,1.5f, 5f, 1f, 0.8f}; // the radians that the limbs need to reach
            for (int j = 0; j < gevrichten.length; j++) {
                alMotion.setAngles(gevrichten[j],hoeken[j],movementSpeed); // sets al the radians to the limbs
            }
            Thread.sleep(100);
    }

    //connect to the nao
    public void fysiekVerbinden() {
        String robotUrl = "tcp://nao.local:" +  9559;    // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }


}

