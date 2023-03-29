package src;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;
import com.aldebaran.qi.helper.proxies.ALSpeakingMovement;

public class SynchSpeech {
    private Session session;

    public SynchSpeech(Session session) {
        this.session = session;
    }

    /**
     * Methode waarin makkelijk de bewegingen worden aangestuurd wanneer de robot wat specefieks zegt.
     * Met de ALAnimatedSpeech module kan je in een string een animatie starten en de robot blijft doorpraten.
     * @throws Exception
     */
    public void intro() throws Exception {
        ALAnimatedSpeech alAnimatedSpeech = new ALAnimatedSpeech(this.session);
        alAnimatedSpeech.say("Hallo! ^start(animations/Stand/Gestures/Hey_1). " +
                "Dit is het begin van mijn presentatie! " +
                "Ik ga jullie^start(animations/Stand/Gestures/Explain_10) uitleggen wat de verschillende leerroutes inhouden. " +
                "Als je meer over een leerroute wilt weten, klik dan op een van de knoppen! ",
                "contextual");

    }

    /**
     * 3 methodes waarin de leerroutes worden uitgelegd
     * @throws Exception
     */
    public void BIMpresentatie() throws Exception {
        ALAnimatedSpeech alAnimatedSpeech = new ALAnimatedSpeech(this.session);
        alAnimatedSpeech.say("Welkom bij de Business IT presentatie, ^start(animations/Stand/Gestures/Hey_1) oftewel BIM.  " +
                        "Hier leer je hoe ICT het bedrijfsleven kan verbeteren ",
                "contextual");
    }
    public void ITpresentatie() throws Exception {
        ALAnimatedSpeech alAnimatedSpeech = new ALAnimatedSpeech(this.session);
        alAnimatedSpeech.say("Welkom bij de Technische informatica presentatie, ^start(animations/Stand/Gestures/Hey_1) oftewel TI.  " +
                        "^start(animations/Sit/BodyTalk/BodyTalk_10) Hier leer je bijvoorbeeld hoe je mij kan programeren! ",
                "contextual");
    }
    public void SEpresentatie() throws Exception {
        ALAnimatedSpeech alAnimatedSpeech = new ALAnimatedSpeech(this.session);
        alAnimatedSpeech.say("Welkom bij de Software engineer presentatie!, ^start(animations/Stand/Gestures/Hey_1) oftewel TI.  " +
                        "^start(animations/Sit/BodyTalk/BodyTalk_10) Hier leer je om applicaties te maken! ",
                "contextual");
    }
}
