
import com.aldebaran.qi.Application;

public class NAO {
    private Application application;
    // Use presentatie etc class so it can use their methodes, so we have only have to make one NAO object in main
    private Presentaties presentaties;
    private MovementTalking movementTalking;
    private LED led;

    public NAO(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[] {}, robotUrl);
        // Start your application
        this.application.start();

        //make object and give the session into the args of the constructor
        this.presentaties = new Presentaties(this.application.session());
        this.movementTalking = new MovementTalking(this.application.session());
        this.led = new LED(this.application.session());
    }
    //Methodes that make the NAO move

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
    public String gd() throws Exception {
        return this.presentaties.GDpresentatie();
    }
    public String cs() throws Exception {
        return this.presentaties.CSpresentatie();
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

    public void say(String text) throws Exception {
        this.movementTalking.say(text);
    }
    public void led(String kleur) throws Exception {
        this.led.LED(kleur);
    }
    public void waitingloop() throws Exception {
        this.movementTalking.waitingloop();
    }
    public void music() throws Exception {
        this.movementTalking.music();
    }
    public void stopmusic() throws Exception {
        this.movementTalking.stopmusic();
    }
}