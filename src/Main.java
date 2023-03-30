package src;

import com.aldebaran.qi.Application;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws Exception {

        NAO nao = new NAO("localhost",53722 );//9559
        nao.staan();

//        Thread praten = new Thread(new MovementTalking.PresenterenTekst(nao));
//        Thread bewegen = new Thread(new MovementTalking.PresenterenBeweging(nao));
//        Thread await = new Thread(new MovementTalking.await());
//
//        praten.start();
//        bewegen.start();
//        await.join();

        nao.zeg("animatie 1");
        nao.animationPath("animations/Stand/Gestures/Explain_1");
        Thread.sleep(500);

        nao.zeg("animatie 10");
        nao.animationPath("animations/Stand/Gestures/Explain_10");
        Thread.sleep(500);

        nao.zeg("animatie 11");
        nao.animationPath("animations/Stand/Gestures/Explain_11");
        Thread.sleep(500);

        nao.zeg("animaite 2");
        nao.animationPath("animations/Stand/Gestures/Explain_2");
        Thread.sleep(500);

        nao.zeg("animaite 3");
        nao.animationPath("animations/Stand/Gestures/Explain_3");
        Thread.sleep(500);

        nao.zeg("animatie 4");
        nao.animationPath("animations/Stand/Gestures/Explain_4");
        Thread.sleep(500);

        nao.zeg("animatie 5");
        nao.animationPath("animations/Stand/Gestures/Explain_5");
        Thread.sleep(500);

        nao.zeg("animatie 6");
        nao.animationPath("animations/Stand/Gestures/Explain_6");
        Thread.sleep(500);

        nao.zeg("animaite 7");
        nao.animationPath("animations/Stand/Gestures/Explain_7");
        Thread.sleep(500);

        nao.zeg("animatie 8");
        nao.animationPath("animations/Stand/Gestures/Explain_8");

    }
}
