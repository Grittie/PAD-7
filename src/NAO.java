
import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALSystem;

public class NAO {
    private Application application;
    private MovementTalking movementTalking;
    private LED led;

    public NAO(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        this.application = new Application(new String[]{}, robotUrl); // Create a new application
        this.application.start(); // Start your application

        //make new object and give the session into the args of the constructor
        this.movementTalking = new MovementTalking(this.application.session());
        this.led = new LED(this.application.session());
    }

    public void setName(String name) throws Exception {             // Method to set a new name. Only use once
        ALSystem alSystem = new ALSystem(this.application.session());
        alSystem.setRobotName(name);
    }

    //Methodes that make the NAO move/talking
    public void say() throws Exception {
        this.movementTalking.stay();
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