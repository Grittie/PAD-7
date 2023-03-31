package src;

import com.aldebaran.qi.Application;

public class NAO {
    private Application application;
    private Presentaties presentaties;
    private MovementTalking movementTalking;

    public NAO(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[] {}, robotUrl);
        // Start your application
        this.application.start();

        this.presentaties = new Presentaties(this.application.session());
        this.movementTalking = new MovementTalking(this.application.session());
    }

    public String intro() throws Exception {
        return this.presentaties.intro();
    }
    public String bim() throws Exception {
        return this.presentaties.BIMpresentatie();
    }
    public String ti() throws Exception {
        return this.presentaties.ITpresentatie();
    }
    public String se() throws Exception {
        return this.presentaties.SEpresentatie();
    }
    public void staan() throws  Exception{
        this.movementTalking.staan();
    }

    public void wijsNaarBord() throws Exception {
        this.movementTalking.wijsNaarBord();
    }

    public void animationRandom(String body_language) throws Exception {
        this.movementTalking.animationRandom(body_language);
    }
    public void animationPath(String path) throws Exception{
        this.movementTalking.animationPath(path);
    }

    public void zeg(String text) throws Exception {
        this.movementTalking.zeg(text);
    }

}
