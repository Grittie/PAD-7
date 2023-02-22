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
//        nao.verbinden("localhost",55470);   // verbinden met de virtuele robot in choreograph
        nao.fysiekVerbinden();
// verbinden met onze fysieke robot
//        Thread.sleep(2000);
        nao.zeg("Op een vrijdag in de kroeg\n" +
                "Ergens in Amsterdam\n" +
                "Zat aan de bar met een glas een oude wijze man\n" +
                "Hij zei dat die nog maar een paar dagen had\n" +
                "Dus pak het leven, pak alles en ga er mee op pad\n" +
                "En hij zei\n" +
                "\"Leef, alsof het je laatste dag is\"\n" +
                "\"Leef, alsof de morgen niet bestaat\"\n" +
                "\"Leef, alsof het nooit echt af is\"\n" +
                "En \"leef, pak alles wat je kan");

    }
}