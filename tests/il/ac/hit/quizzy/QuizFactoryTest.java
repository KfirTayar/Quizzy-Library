package il.ac.hit.quizzy;

import static org.junit.jupiter.api.Assertions.*;

class QuizFactoryTest {

    private QuizFactory sampleFactory;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // Create a sample factory for testing
        sampleFactory = new QuizFactory();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        // // Delete all the definitions of the factory
        sampleFactory = null;
    }

    @org.junit.jupiter.api.Test
    void createQuiz() throws QuizException {
        // Create a sample GUI quiz
        IQuiz sampleGuiQuiz = sampleFactory.createQuiz(QuizType.GUI);
        // Check if sampleGuiQuiz is not null and instance of GUIQuiz class
        assertNotNull(sampleGuiQuiz);
        assertTrue(sampleGuiQuiz instanceof GUIQuiz);

        // Create a sample terminal quiz
        IQuiz sampleTerminalQuiz = sampleFactory.createQuiz(QuizType.TERMINAL);
        // Check if sampleTerminalQuiz is not null and instance of TerminalQuiz class
        assertNotNull(sampleTerminalQuiz);
        assertTrue(sampleTerminalQuiz instanceof TerminalQuiz);
    }
}
