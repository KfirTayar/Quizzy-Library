package il.ac.hit.quizzy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GUIQuizTest {

    private GUIQuiz sampleGuiQuiz;

    @BeforeEach
    void setUp() {
        // Create a sample GUIQuiz for testing
        sampleGuiQuiz = new GUIQuiz("Quiz Name");
    }

    @AfterEach
    void tearDown() {
        // Delete all the definitions of the quiz
        sampleGuiQuiz = null;
    }

    @Test
    void setAndGetName() {
        // Set a new name to the quiz and check if the new name has been updated using getName
        sampleGuiQuiz.setName("New Quiz Name");
        assertEquals("New Quiz Name", sampleGuiQuiz.getName());
    }


    @Test
    void addAndGetQuestion() {
        // Add a new question and check if the quiz includes only a single question using getQuestions
        IQuizQuestion question = new QuizQuestion.Builder()
                .setTitle("We Love Canada")
                .setQuestion("Canada starts with…?")
                .addAnswer("C", true)
                .addAnswer("A", false)
                .addAnswer("B", false)
                .addAnswer("D", false)
                .addAnswer("E", false)
                .create();

        sampleGuiQuiz.addQuestion(question);

        assertEquals(1, sampleGuiQuiz.getQuestions().size());
        // Check if the name of the title is correctly saved
        assertEquals("We Love Canada", sampleGuiQuiz.getQuestions().get(0).getTitle());
    }

    @Test
    void getTypeOfTheQuiz() {
        // Check if the type of the quiz is correctly saved
        assertEquals("GUI", sampleGuiQuiz.getTypeOfTheQuiz());
    }
}
