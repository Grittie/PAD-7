package src;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALLeds;

public class LED {
    private Session session;


    public LED(Session session) {
        this.session = session;
    }

    public void LED(String kleur) throws Exception {
        ALLeds alLeds = new ALLeds(this.session);
        String kleurtest = null;
        switch (kleur) {
            case "blauw":
                kleurtest = "blue";
            case "groen":
                kleurtest = "green";
            case "rood":
                kleurtest = "red";
            case "paars":
                kleurtest = "magenta";
            case "geel":
                kleurtest = "yellow";
        }
        alLeds.fadeRGB("AllLeds", kleurtest, 1f);


        Thread.sleep(1000);
    }

}
