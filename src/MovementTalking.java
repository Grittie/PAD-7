

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.*;

public class MovementTalking {
    private static final float MOVEMENT_SPEED = 0.3f; // speed of the movements. to high has a risk of the nao falling.
    private static final float MUSIC_VOLUME = 0.3f; // 0.3 so the music isn't louder then the say.
    private static final float SAY_SPEED = 85; // 85% is nicer then 100% to listen.
    private Session session;

    public MovementTalking(Session session) {
        this.session = session;
    }

    public void say(String tekst) throws Exception {          // Create an ALAnimatedSpeech object and link it to your current session
        ALAnimatedSpeech alAnimatedSpeech = new ALAnimatedSpeech(this.session);
        alAnimatedSpeech.say("\\rspd="+SAY_SPEED+"\\"+tekst); //set speed to 85%, 100% is too fast. The alAnimatedSpeech.say can be buggy

    }

    public void stay() throws Exception {
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
    public void waitingloop() throws Exception {            // a loop that make the robot random moves
        for (int i = 0; i < 10; i++) {                      // its used in a thread so it can be interrupted
            this.animationRandom("undiscovered");
            this.animationRandom("you");
        }
    }
    public void music() throws Exception {                  // method to load a music file, and play it
        ALAudioPlayer alAudioPlayer = new ALAudioPlayer(this.session);
        int mFile = alAudioPlayer.loadFile("/opt/aldebaran/www/apps/pad7-032137/muziek/waitingSound.wav");
        System.out.println(mFile);
        alAudioPlayer.setVolume(mFile,MUSIC_VOLUME); //
        alAudioPlayer.play(mFile);
    }
    public void stopmusic() throws Exception {      // method to stop all the music from playing
        ALAudioPlayer alAudioPlayer = new ALAudioPlayer(this.session);
        alAudioPlayer.stopAll();
    }
}

