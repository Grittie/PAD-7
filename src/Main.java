package src;

public class Main {

    public static void main(String[] args) throws Exception {
        NAO nao = new NAO();
        Dab dab = new Dab();
//        nao.verbinden("nao.local.",9559);   // verbinden met de virtuele robot in choreograph
        nao.verbinden("localhost",53130);   // verbinden met de virtuele robot in choreograph
//        nao.fysiekVerbinden();
        nao.zeg("Op een vrijdag in de kroeg");
        dab.dab();
        Thread.sleep(100);
    }
}
