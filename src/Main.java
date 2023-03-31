package src;

public class Main {

    public static void main(String[] args) throws Exception {
        NAO nao = new NAO("nao.local", 9559);
        nao.LED("blauw");
    }
}