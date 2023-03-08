import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        NAO nao = new NAO();
        //nao.verbinden("localhost", 57586);
        nao.fysiekVerbinden();
        nao.staan();




        //nao.animationPath("/Applications/Choregraphe.app/Contents/Resources/share/choregraphe/libraries/box/Animation/Entertainment/Dances");
        new Thread(new NAO.PresenterenBeweging(nao)).start();
//        new Thread(new NAO.PresenterenTekst(nao)).start();
//        nao.staan();
    }
}
