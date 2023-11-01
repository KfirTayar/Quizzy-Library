package il.ac.hit.quizzy;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TerminalQuiz implements Cloneable, IQuiz {
    private String mName;
    private LinkedList<IQuizQuestion> mQuestionsList = new LinkedList<>();
    private String mTypeOfTheQuiz = "TERMINAL";
    private int index;
    private int score;
    private String chosenAnswer;
    private Scanner scanner;

    // Constructor
    public TerminalQuiz(String iName) {
        setName(iName);
        setIndex(0);
        setScore(0);
        setChosenAnswer(new String());
        setScanner(new Scanner(System.in));
    }

    // Methods
    @Override
    public void start(){
        System.out.println("Starting terminal quiz: " + mName + "\n");
        for (IQuizQuestion question: mQuestionsList) {
            nextQuestion();
        }
        scanner.close();
        System.out.println("Score: " + score);
    }

    public void nextQuestion() {
        if (index >= mQuestionsList.size()) {
            System.out.println("Score: " + score);
        } else {
            System.out.println(mQuestionsList.get(index).getTitle());
            System.out.println(mQuestionsList.get(index).getQuestion());
            System.out.println("A." + mQuestionsList.get(index).getAnswers().get(0).getAnswer());
            System.out.println("B." + mQuestionsList.get(index).getAnswers().get(1).getAnswer());
            System.out.println("C." + mQuestionsList.get(index).getAnswers().get(2).getAnswer());
            System.out.println("D." + mQuestionsList.get(index).getAnswers().get(3).getAnswer());
            System.out.println("E." + mQuestionsList.get(index).getAnswers().get(4).getAnswer());

            System.out.println("\nEnter Your answer:");
            setChosenAnswer(scanner.nextLine());

            System.out.println("Your Answer: " + chosenAnswer + "\n");
            checkAnswer();
        }
    }

    public void checkAnswer() {
        if (Objects.equals(chosenAnswer, "A") || Objects.equals(chosenAnswer, "a")) {
            if (mQuestionsList.get(index).getAnswers().get(0).isCorrect()) {
                setScore(score+1);
            }
        }
        if (Objects.equals(chosenAnswer, "B") || Objects.equals(chosenAnswer, "b")) {
            if (mQuestionsList.get(index).getAnswers().get(1).isCorrect()) {
                setScore(score+1);
            }
        }
        if (Objects.equals(chosenAnswer, "C") || Objects.equals(chosenAnswer, "c")) {
            if (mQuestionsList.get(index).getAnswers().get(2).isCorrect()) {
                setScore(score+1);
            }
        }
        if (Objects.equals(chosenAnswer, "D") || Objects.equals(chosenAnswer, "d")) {
            if (mQuestionsList.get(index).getAnswers().get(3).isCorrect()) {
                setScore(score+1);
            }
        }
        if (Objects.equals(chosenAnswer, "E") || Objects.equals(chosenAnswer, "e")) {
            if (mQuestionsList.get(index).getAnswers().get(4).isCorrect()) {
                setScore(score+1);
            }
        }
        setIndex(index+1);
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
    public void setIndex(int index) {this.index = index;}
    public int getIndex() {return index;}
    public void setScore(int score) {this.score = score;}
    public int getScore() {return score;}
    public void setScanner(Scanner scanner) {this.scanner = scanner;}
    public Scanner getScanner() {return scanner;}
    public void setChosenAnswer(String chosenAnswer) {this.chosenAnswer = chosenAnswer;}
    public String getChosenAnswer() {return chosenAnswer;}


    @Override
    // Rewrite the clone func
    protected Object clone() {
        TerminalQuiz clonedQuiz = new TerminalQuiz(this.mName);

        // clone the needed attributes
        clonedQuiz.mQuestionsList = new LinkedList<>();
        clonedQuiz.mTypeOfTheQuiz = this.mTypeOfTheQuiz;
        clonedQuiz.index = this.index;
        clonedQuiz.score = this.score;
        clonedQuiz.chosenAnswer = this.chosenAnswer;

        return clonedQuiz;
    }
}