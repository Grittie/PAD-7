

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALLeds;

public class LED {
    private Session session;

    /**
     *
     * @param session
     */

    public LED(Session session) {
        this.session = session;
    }

    /**
     *
     * @param kleur
     * @throws Exception
     */
    public void LED(String kleur) throws Exception {
        ALLeds alLeds = new ALLeds(this.session);
        String kleurtest = "red";
        switch (kleur) {
            case "blauw":
                kleurtest = "blue";
                break;
            case "groen":
                kleurtest = "green";
                break;
            case "rood":
                kleurtest = "red";
                break;
            case "paars":
                kleurtest = "magenta";
                break;
            case "geel":
                kleurtest = "yellow";
                break;
            case "wit":
                kleurtest = "white";
        }
        alLeds.fadeRGB("AllLeds", kleurtest, 1f);
    }

}
