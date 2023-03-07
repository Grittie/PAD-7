import com.aldebaran.qi.helper.proxies.ALMotion;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        NAO nao = new NAO();
        Scanner scanner = new Scanner(System.in);
        nao.verbinden("localhost",55180);   // verbinden met de virtuele robot in choreogrape
        nao.metersLopen();
    }
}
