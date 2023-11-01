package il.ac.hit.quizzy;

import java.util.LinkedList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {

    private String title;
    private String question;
    private List<QuizAnswer> answers = new LinkedList<>();

    protected QuizQuestion() {}

    public String getTitle() {return title;}

    public String getQuestion() {return question;}

    public List<QuizAnswer> getAnswers() {return answers;}

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public void setAnswerList(List<QuizAnswer> answers) {
        this.answers = answers;
    }

    public static class Builder implements IQuizQuestionBuilder {
        private String title;
        private String question;
        private List<QuizAnswer> answers = new LinkedList<>();

        public String getTitle() {
            return title;
        }

        public String getQuestion() {
            return question;
        }

        public List<QuizAnswer> getAnswers() {
            return answers;
        }

        @Override
        public IQuizQuestionBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        @Override
        public IQuizQuestionBuilder setQuestion(String question) {
            this.question = question;
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String answer, boolean correct) {
            this.answers.add(new QuizAnswer(answer, correct));
            return this;
        }

        @Override
        public IQuizQuestion create() {
            IQuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setTitle(title);
            quizQuestion.setQuestion(question);
            quizQuestion.setAnswerList(answers);
            return quizQuestion;
        }
    }
}