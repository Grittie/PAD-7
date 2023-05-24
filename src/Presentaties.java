

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
        return "^start(animations/Stand/Gestures/Hey_1)Welkom bij de Business ijTie presentatie, oftewel BIM." + "^start(animations/Stand/Gestures/Explain_3)Hier leer je hoe ieceetee het bedrijfsleven kan verbeteren ";
    }
    public String ITpresentatie() throws Exception {
        return "^start(animations/Stand/Gestures/Hey_1) Welkom bij de Technische informatica presentatie, oftewel tee ie.  " +
                        "^start(animations/Sit/BodyTalk/BodyTalk_1) Hier leer je bijvoorbeeld hoe je mij kan programeeren! ";
    }
    public String SEpresentatie() throws Exception {

       return "^start(animations/Stand/Gestures/Hey_1)Welkom bij de Software engineer presentatie!, oftewel essee.  " +
                        "^start(animations/Sit/BodyTalk/BodyTalk_10) Hier leer je om applicaties te maken! ";
    }
    public String GDpresentatie() throws Exception {

       return "^start(animations/Stand/Gestures/Hey_1)Welkom bij Game development, GeeDee.  " +
                        "^start(animations/Sit/BodyTalk/BodyTalk_4) Hier leer je om de vetste games te maken! ";
    }
    public String CSpresentatie() throws Exception {

       return "^start(animations/Stand/Gestures/Hey_1)Welkom bij Cyber Security!, oftewel Cee Es.  " +
                        "^start(animations/Sit/BodyTalk/BodyTalk_6) Hier leer je alles over de veiligheid van het internet!";
    }


}
