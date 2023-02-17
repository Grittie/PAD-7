package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

public class Main {

    public static void main(String[] args) throws Exception {
        Robottesten nao = new Robottesten();
        // Create an ALTextToSpeech object and link it to your current session
//        ALTextToSpeech tts = new ALTextToSpeech(NAO.session());
        // Make your robot say something
//        tts.say("Hello Mats!");
        nao.verbinden("localhost",50155);   // verbinden met de virtuele robot in choreograph
//        nao.fysiekVerbinden();                         // verbinden met onze fysieke robot
        nao.zeg("");
    }
}