package src;

public class Main {
    public static void main(String[] args) throws Exception {
        NAO nao = new NAO("johanus.local", 9559);
        nao.staan();
        nao.say("Hallo ik ben de Studie Keuze bot. Ik zal jullie een korte demonstratie geven van een studie keuze check.");
        Thread.sleep(1000);
        Questions questions = new Questions(nao);
        questions.askAllQuestions();
    }
}
