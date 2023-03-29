package src;

import com.aldebaran.qi.Application;

public class NAO {
    private Application application;
    private SynchSpeech synchSpeech;
    private MovementTalking movementTalking;

    public NAO(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[] {}, robotUrl);
        // Start your application
        this.application.start();

        this.synchSpeech = new SynchSpeech(this.application.session());
        this.movementTalking = new MovementTalking(this.application.session());
    }

    public void intro() throws Exception {
        this.synchSpeech.intro();
    }
    public void bim() throws Exception {
        this.synchSpeech.BIMpresentatie();
    }
    public void ti() throws Exception {
        this.synchSpeech.ITpresentatie();
    }
    public void se() throws Exception {
        this.synchSpeech.SEpresentatie();
    }
    public void staan() throws  Exception{
        this.movementTalking.staan();
    }

    public void wijsNaarBord() throws Exception {
        this.movementTalking.wijsNaarBord();
    }

    public void animationPath(String body_language) throws Exception {
        this.movementTalking.animationPath(body_language);
    }
}
