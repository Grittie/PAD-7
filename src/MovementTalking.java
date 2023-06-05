

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
    public void say(String tekst) throws Exception {          // Create an ALAnimatedSpeech object and link it to your current session
        ALAnimatedSpeech alAnimatedSpeech = new ALAnimatedSpeech(this.session);
        alAnimatedSpeech.say("\\rspd=85\\"+tekst);

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
    public void waitingloop() throws Exception {            // a loop that make the robot random moves
        for (int i = 0; i < 10; i++) {                      // it can be used in a thread so it can stopped easy
            this.animationRandom("undiscovered");
            this.animationRandom("you");
        }
    }
    public void music() throws Exception {                  // method to load a music file, and play it
        ALAudioPlayer alAudioPlayer = new ALAudioPlayer(this.session);
        int mFile = alAudioPlayer.loadFile("/opt/aldebaran/www/apps/pad7-032137/muziek/waitingSound.wav");
        System.out.println(mFile);
        alAudioPlayer.setVolume(mFile,0.3f);
        alAudioPlayer.play(mFile);


    }
    public void stopmusic() throws Exception {      // method to stop all the music from playing
        ALAudioPlayer alAudioPlayer = new ALAudioPlayer(this.session);
        alAudioPlayer.stopAll();
    }





}

