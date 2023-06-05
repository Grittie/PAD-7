
import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALSystem;

public class NAO {
    private Application application;
    // Use presentatie etc class so it can use their methodes, so we have only have to make one NAO object in main
    private Presentations presentations;
    private MovementTalking movementTalking;
    private LED led;

    public NAO(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[] {}, robotUrl);
        // Start your application
        this.application.start();

        //make object and give the session into the args of the constructor
        this.movementTalking = new MovementTalking(this.application.session());
        this.led = new LED(this.application.session());
    }

    public void setName(String name) throws Exception {
        ALSystem alSystem = new ALSystem(this.application.session());
        alSystem.setRobotName(name);
    }
    //Methodes that make the NAO move
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