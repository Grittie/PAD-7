package src;

public class Main {

    public static void main(String[] args) throws Exception {
        NAO nao = new NAO();
        nao.connect("nao.local", 9559);
    }
}
