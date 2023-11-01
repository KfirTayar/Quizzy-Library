package il.ac.hit.quizzy;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
public class GUIQuiz extends JFrame implements Cloneable, IQuiz {
    private String mName;
    private List<IQuizQuestion> mQuestionsList = new LinkedList<>();
    private String mTypeOfTheQuiz = "GUI";

    // Constructor
    public GUIQuiz(String iName) {
        setName(iName);
    }

    // Methods
    @Override
    public void start() {
        System.out.println("Starting GUI quiz: " + mName + "\n");
        SwingUtilities.invokeLater(() -> {
            QuizFrame frame = new QuizFrame(mQuestionsList);
            frame.initialize();
        });
    }
    @Override
    public void setName(String iName) {
        this.mName = iName;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        mQuestionsList.add(question);
    }

    @Override
    public List<IQuizQuestion> getQuestions() {
        return mQuestionsList;
    }

    @Override
    public String getTypeOfTheQuiz() {
        return mTypeOfTheQuiz;
    }

    @Override
    // Rewrite the clone func
    protected Object clone() {
        GUIQuiz clonedQuiz = new GUIQuiz(this.mName);

        // clone the needed attributes
        clonedQuiz.mQuestionsList = new LinkedList<>();
        clonedQuiz.mTypeOfTheQuiz = this.mTypeOfTheQuiz;

        return clonedQuiz;
    }
}