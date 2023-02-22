package src;

public class Main {

    public static void main(String[] args) throws Exception {
        Robottesten nao = new Robottesten();
        //nao.verbinden("localhost",55470);   // verbinden met de virtuele robot in choreograph
        nao.fysiekVerbinden();
        // verbinden met onze fysieke robot
        nao.zeg("Op een vrijdag in de kroeg");

    }
}