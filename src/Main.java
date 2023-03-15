package src;

public class Main {

    public static void main(String[] args) throws Exception {
        MovementTalking nao = new MovementTalking();

        nao.fysiekVerbinden();
        nao.staan();

        new Thread(new MovementTalking.PresenterenBeweging(nao)).start();
        new Thread(new MovementTalking.PresenterenTekst(nao)).start();
    }
}
