public class Main {
    public static void main(String[] args) throws Exception {

        NAO nao = new NAO("169.254.130.214", 9559);
        while (true) {
            Introduction introduction = new Introduction(nao);
            introduction.introductionSequence();

            Questions questions = new Questions(nao);
            questions.askAllQuestions();

            Scores scores = new Scores();
            scores.getResults();
        }
    }
}
