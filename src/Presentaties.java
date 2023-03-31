package src;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;

public class Presentaties {
    private Session session;

    public Presentaties(Session session) {
        this.session = session;
    }

    /**
     * Methode waarin makkelijk de bewegingen worden aangestuurd wanneer de robot wat specefieks zegt.
     * Met de ALAnimatedSpeech module kan je in een string een animatie starten en de robot blijft doorpraten.
     * @throws Exception
     */
    public String intro() throws Exception {
        return "^start(animations/Stand/Gestures/Hey_1)Hallo! Dit is het begin van mijn presentatie! Ik ga jullie^start(animations/Stand/Gestures/Explain_10) uitleggen wat de verschillende leerroutes inhouden. Als je meer over een leerroute wilt weten, klik ^start(animations/Stand/Gestures/Explain_7) dan op een van de knoppen hieronder! ";

    }

    /**
     * 3 methodes waarin de leerroutes worden uitgelegd
     * @throws Exception
     */
    public String BIMpresentatie() throws Exception {
        return "^start(animations/Stand/Gestures/Hey_1)Welkom bij de Business IT presentatie, oftewel BIM." + "^start(animations/Stand/Gestures/Explain_3)Hier leer je hoe ICT het bedrijfsleven kan verbeteren ";
    }
    public String ITpresentatie() throws Exception {
        return "Welkom bij de Technische informatica presentatie, ^start(animations/Stand/Gestures/Hey_1) oftewel TI.  " +
                        "^start(animations/Sit/BodyTalk/BodyTalk_10) Hier leer je bijvoorbeeld hoe je mij kan programeren! ";
    }
    public String SEpresentatie() throws Exception {

       return "Welkom bij de Software engineer presentatie!, ^start(animations/Stand/Gestures/Hey_1) oftewel TI.  " +
                        "^start(animations/Sit/BodyTalk/BodyTalk_10) Hier leer je om applicaties te maken! ";
    }
}
