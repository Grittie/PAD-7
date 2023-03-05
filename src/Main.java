import com.aldebaran.qi.helper.proxies.ALMotion;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        NAO nao = new NAO();
        Scanner scanner = new Scanner(System.in);
        nao.verbinden("localhost",50109);   // verbinden met de virtuele robot in choreogrape
        System.out.println(nao.watKanIk());
        System.out.println();
        System.out.print("Wat wil je dat ik doe: ");
        String movement = scanner.nextLine();
        nao.allesWatJeWil(movement);
    }
}
