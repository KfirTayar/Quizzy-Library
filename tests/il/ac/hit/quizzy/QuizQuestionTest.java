package il.ac.hit.quizzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizQuestionTest {

    private QuizQuestion sampleQuestion;

    @BeforeEach
    void setUp() {
        // Create a sample QuizQuestion for testing
        sampleQuestion = new QuizQuestion();
    }

    @AfterEach
    void tearDown() {
        // Delete all the definitions of the quiz
        sampleQuestion = null;
    }

    @Test
    void setAndGetTitle() {
        // Set the title of the question and check if the title has been updated using getTitle
        sampleQuestion.setTitle("We Love Canada");
        assertEquals("We Love Canada", sampleQuestion.getTitle());
    }

    @Test
    void setAndGetQuestion() {
        // Set the question and check if the question has been updated using getQuestion
        sampleQuestion.setQuestion("Canada starts with…?");
        assertEquals("Canada starts with…?", sampleQuestion.getQuestion());
    }

    @Test
    void setAndGetAnswers() {
        // Add answers to the sample question
        QuizAnswer answer1 = new QuizAnswer("C", true);
        QuizAnswer answer2 = new QuizAnswer("A", false);
        QuizAnswer answer3 = new QuizAnswer("B", false);
        QuizAnswer answer4 = new QuizAnswer("D", false);
        QuizAnswer answer5 = new QuizAnswer("E", false);
        sampleQuestion.setAnswerList(List.of(answer1, answer2, answer3, answer4, answer5));

        // Check if the question includes 5 answers
        assertEquals(5, sampleQuestion.getAnswers().size());
        // Check if the answer is in the right place and if it returns her right value (True)
        assertEquals("C", sampleQuestion.getAnswers().get(0).getAnswer());
        assertTrue(sampleQuestion.getAnswers().get(0).isCorrect());
        // Check if the answer is in the right place and if it returns her right value (False)
        assertEquals("A", sampleQuestion.getAnswers().get(1).getAnswer());
        assertFalse(sampleQuestion.getAnswers().get(1).isCorrect());
    }

}
