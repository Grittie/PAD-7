public class Main {

    public static void main(String[] args) throws Exception {
        MovementTalking movementTalking = new MovementTalking();

        movementTalking.staan();
        new Thread(new MovementTalking.PresenterenBeweging(movementTalking)).start();
        new Thread(new MovementTalking.PresenterenTekst(movementTalking)).start();
        movementTalking.staan();

    }
}
