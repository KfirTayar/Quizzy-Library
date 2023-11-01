package il.ac.hit.quizzy;

import java.util.List;

public interface IQuizQuestion {

    public abstract void setTitle(String title);
    public abstract void setQuestion(String question);
    public abstract void setAnswerList(List<QuizAnswer> answers);
    public abstract String getTitle();
    public abstract String getQuestion();
    public abstract List<QuizAnswer> getAnswers();

}
