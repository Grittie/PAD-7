
package src;

import com.aldebaran.qi.Application;

public class NAO {

    private Application application;
    private LED led;

    public NAO(String hostname, int port) {

        String robotUrl = "tcp://" + hostname + ":" + port;

        // Create a new application
        this.application = new Application(new String[]{}, robotUrl);

        // Start your application
        this.application.start();

        this.led = new LED(this.application.session());

    }
    public void LED (String kleur) throws Exception {
        this.led.LED(kleur);
    }

}