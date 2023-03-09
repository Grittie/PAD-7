package src;

import com.aldebaran.qi.Application;

public class NAO {
    public Application application;

    public NAO(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;
        // Create a new application
        this.application = new Application(new String[] {}, robotUrl);
        // Start your application
        application.start();
    }
}
