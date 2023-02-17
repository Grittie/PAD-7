package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;


public class Robottesten {

    private String naam;

    private Application application;

    public void verbinden(String hostname, int port) {
        String robotUrl = "tcp://" + hostname + ":" + port;    // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }

    public void fysiekVerbinden() {
        String robotUrl = "tcp://padrick.local:" +  9559;    // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }



    public void zeg(String tekst) throws Exception {
        // Create an ALTextToSpeech object and link it to your current session
        ALTextToSpeech tts = new ALTextToSpeech(this.application.session());
        // Make your robot say something
        tts.say(tekst);
    }





}
