

import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.*;

import java.util.Scanner;


public class NAO {

    private String naam;
    public float movementSpeed = 0.5f;

    private Application application;

    public void verbinden(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;    // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }

    public void fysiekVerbinden() {
        String robotUrl = "tcp://nao.local:" +  9559;    // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }



    public void zeg(String tekst) throws Exception {
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());  // Create an ALTextToSpeech object and link it to your current session
        tts.say(tekst); // Make your robot say something
    }

    public void zitten() throws Exception {
        ALRobotPosture movement = new ALRobotPosture(this.application.session());
        movement.goToPosture("Sit",this.movementSpeed);
    }

    public void staan() throws Exception {
        ALRobotPosture movement = new ALRobotPosture(this.application.session());
        movement.goToPosture("StandInit",this.movementSpeed);



    }

    public void footState() throws Exception{
        ALMotion motion = new ALMotion(application.session());      //DONT WORK
        motion.wbEnable(true);
        motion.wbFootState("Plane", "Legs");


    }
    public void animation() throws Exception {
        ALAnimationPlayer alAnimationPlayer = new ALAnimationPlayer(this.application.session()); //DONT WORK
        alAnimationPlayer.runTag("explain");

    }

    public void handOpen() throws Exception {
        ALMotion alMotion = new ALMotion(this.application.session());
        alMotion.openHand("LHand");
        alMotion.openHand("RHand");
    }
    public String watKanIk(){
        return """
                Crouch,
                LyingBack,
                LyingBelly,
                Sit,
                SitRelax,
                Stand,
                StandInit,
                StandZero""";
    }

    public void allesWatJeWil(String naamMovement) throws Exception {
        ALRobotPosture movement = new ALRobotPosture(this.application.session());
        movement.goToPosture(naamMovement,this.movementSpeed);

    }
    public void lopen(Long hoelang) throws Exception {
        //grootte stappen
        ALMotion alMotion = new ALMotion(this.application.session());
        alMotion.moveToward(1f, // x is stap vooruit,
                0f, // y is zijwaardsstappen   q
                0f); // rondje stappen
        Thread.sleep(hoelang);
        alMotion.stopMove();
    }
    public void metersLopen() throws Exception {
        //meters vooruit/achteruit
        ALMotion alMotion = new ALMotion(this.application.session());
        alMotion.walkTo(-0.1f,0f,0f);
    }








}

