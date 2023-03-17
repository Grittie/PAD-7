package src;

public class Main {

    public static void main(String[] args) throws Exception {
        SynchSpeech nao = new SynchSpeech();
        nao.choreographe(50555);
        nao.animitedSpeech();
    }
}
