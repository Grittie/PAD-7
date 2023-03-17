package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;

public class SynchSpeech {

    private Application application;

    public void fysiekVerbinden() {
        String robotUrl = "tcp://nao.local:" +  9559;    // Create a new application
        this.application = new Application(new String[]{}, robotUrl);
        // Start your application
        application.start();
    }
    public void choreographe(int port) {
        String robotUrl = "tcp://" + "localhost" + ":" + port;
        // Create a new application
        this.application = new Application(new String[] {}, robotUrl);
        // Start your application
        application.start();
    }

    public void animitedSpeech() throws Exception {
        ALAnimatedSpeech alAnimatedSpeech = new ALAnimatedSpeech(this.application.session());
        alAnimatedSpeech.say("Hoi!. ^start(animations/Stand/Gestures/Hey_1) Ik ben NAO de Robot, Ik ga jullie wat ^start(animations/Stand/Gestures/Enthusiastic_4) vertellen over de verschillende leerroutes");

    }
}
