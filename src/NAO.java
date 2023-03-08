

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.*;


public class NAO {

    private String naam;
    public float movementSpeed = 0.3f;

    private Application application;

    static class PresenterenTekst implements Runnable {
        private NAO nao;
        public PresenterenTekst(NAO nao){ this.nao = nao;}

        @Override
        public void run() {
            try {
                nao.zeg("Dit is mijn presentatie! Alles goed met jullie? Met mij wel");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class PresenterenBeweging implements Runnable {
        private NAO nao;
        public PresenterenBeweging(NAO nao) {this.nao = nao;};

        @Override
        public void run() {
            try {
                nao.wijsNaarBord();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

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
        movement.goToPosture("StandZero",this.movementSpeed);
        System.out.println(movement.getPostureFamily());

    }

    public void footState() throws Exception{
        ALMotion motion = new ALMotion(application.session());      //DONT WORK
        motion.wbEnable(true);
        motion.wbFootState("Plane", "Legs");


    }
    public void zwaaien() throws Exception {
        ALAnimationPlayer alAnimationPlayer = new ALAnimationPlayer(this.application.session()); //DONT WORK
        alAnimationPlayer.run("animations/Stand/Gestures/Hey_1");
    }

    public void animationPath(String path) throws Exception {
        ALAnimationPlayer alAnimationPlayer = new ALAnimationPlayer(this.application.session()); //DONT WORK
        alAnimationPlayer.run(path);

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
        alMotion.walkTo(0f,0f,-1f);
    }

    public void wijsNaarBord() throws Exception {
        ALMotion alMotion = new ALMotion(this.application.session());
        String[] gevrichten = {"LElbowYaw", "LShoulderRoll", "HeadYaw", "RElbowRoll", "RElbowYaw", "RShoulderPitch"};
        double[] hoeken = {-4f,5f,1.5f, 5f, 1f, 0.8f};
            for (int j = 0; j < gevrichten.length; j++) {
                alMotion.setAngles(gevrichten[j],hoeken[j],movementSpeed);
            }
            Thread.sleep(100);

//        alMotion.setAngles("HeadYaw",0f,1f);
    }

    public void kamalMethode() throws Exception {
        ALMotion alMotion = new ALMotion(this.application.session());
        String[] gevrichten = {"RElbowRoll", "RElbowYaw", "RShoulderPitch", "LElbowRoll", "LElbowYaw", "LShoulderPitch"};
        double[] hoeken = { 5f, 1f, 0.8f, -5f, -1f, -0.8f};
        for (int i = 0; i < gevrichten.length; i++) {
            alMotion.setAngles(gevrichten[i],hoeken[i],movementSpeed);
        }

    }
    public void getAngles(String onderdeel) throws Exception {
        ALMotion alMotion = new ALMotion(this.application.session());
        System.out.println(alMotion.getAngles(onderdeel,true));
    }

    public void eyes() throws Exception {
        ALLeds alLeds = new ALLeds(this.application.session());
        alLeds.randomEyes(10f);

    }

}

