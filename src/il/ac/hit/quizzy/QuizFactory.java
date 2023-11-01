package il.ac.hit.quizzy;

public class QuizFactory {

    private GUIQuiz guiQuizObject;
    private TerminalQuiz terminalQuizObject;

    // Constructor
    public QuizFactory() {
        guiQuizObject = new GUIQuiz("GUIQuiz");
        terminalQuizObject = new TerminalQuiz("TerminalQuiz");
    }

    // Implement factory and prototype patterns
    public IQuiz createQuiz(QuizType type) throws QuizException {

        IQuiz quiz = switch (type.name()) {
            case "TERMINAL" -> (IQuiz) terminalQuizObject.clone();
            case "GUI" -> (IQuiz) guiQuizObject.clone();
            default -> throw new QuizException("The quiz type are invalid!");
        };

        return quiz;

    }
}