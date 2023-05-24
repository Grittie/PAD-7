import java.awt.desktop.QuitEvent;

public class Main {
    public static void main(String[] args) throws Exception {

        NAO nao = new NAO("padrick.local", 9559);
        Questions questions = new Questions(nao);
        questions.askAllQuestions();
    }
}
