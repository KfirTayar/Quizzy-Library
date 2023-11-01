package il.ac.hit.quizzy;

public class QuizAnswer {
    private String mAnswer;
    private boolean mCorrect;

    public QuizAnswer(String answer, boolean isCorrect) {
        setAnswer(answer);
        setCorrect(isCorrect);
    }

    public boolean isCorrect() {
        return mCorrect;
    }

    public void setCorrect(boolean correct) {
        this.mCorrect = correct;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        this.mAnswer = answer;
    }
}
