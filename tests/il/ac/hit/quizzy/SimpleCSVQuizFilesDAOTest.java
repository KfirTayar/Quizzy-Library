package il.ac.hit.quizzy;

import il.ac.hit.quizzy.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCSVQuizFilesDAOTest {

    private SimpleCSVQuizFilesDAO dao;
    private IQuiz sampleQuiz;
    private String fileName;

    @BeforeEach
    void setUp() throws QuizException {
        dao = (SimpleCSVQuizFilesDAO) SimpleCSVQuizFilesDAO.getInstance();
        fileName = "sampleQuiz.csv";

        // Create a quiz for testing
        sampleQuiz = createSampleQuiz();
    }

    @AfterEach
    void tearDown() {
        // Delete the file after the test
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void saveQuizToFileAndLoadQuizFromFile() {

        // Save the quiz to a file
        assertDoesNotThrow(() -> dao.saveQuizToFile(sampleQuiz, fileName));

        // Load the saved quiz from the file
        IQuiz loadedQuiz = assertDoesNotThrow(() -> dao.loadQuizFromFile(fileName));

        // Check if the loaded quiz is not null
        assertNotNull(loadedQuiz);

        // Check if the loaded quiz has the same name as the original quiz
        assertEquals(sampleQuiz.getName(), loadedQuiz.getName());

        // Check if the loaded quiz has the same type as the original quiz
        assertEquals(sampleQuiz.getTypeOfTheQuiz(), loadedQuiz.getTypeOfTheQuiz());

        // Check if the loaded quiz has the same number of questions as the original quiz
        assertEquals(sampleQuiz.getQuestions().size(), loadedQuiz.getQuestions().size());
    }


    private IQuiz createSampleQuiz() throws QuizException {
        // Create a quiz for testing
        IQuiz quiz = new QuizFactory().createQuiz(QuizType.GUI);
        quiz.setName("Sample Quiz");

        // Question 1
        IQuizQuestionBuilder builder1 = new QuizQuestion.Builder();
        builder1.setTitle("We Love Canada");
        builder1.setQuestion("Canada starts with…?");
        builder1.addAnswer("Canada starts with the letter 'A'.",false);
        builder1.addAnswer("Canada starts with the letter 'B'.",false);
        builder1.addAnswer("Canada starts with the letter 'C'.",true);
        builder1.addAnswer("Canada starts with the letter 'D'.",false);
        builder1.addAnswer("Canada starts with the letter 'E'.",false);
        IQuizQuestion question1 = builder1.create();

        // Question 2
        IQuizQuestionBuilder builder2 = new QuizQuestion.Builder();
        builder2.setTitle("We Love Australia");
        builder2.setQuestion("Australia starts with…?");
        builder2.addAnswer("Australia starts with the letter 'A'.",true);
        builder2.addAnswer("Australia starts with the letter 'B'.",false);
        builder2.addAnswer("Australia starts with the letter 'C'.",false);
        builder2.addAnswer("Australia starts with the letter 'D'.",false);
        builder2.addAnswer("Australia starts with the letter 'E'.",false);
        IQuizQuestion question2 = builder2.create();

        // Add questions to the quiz
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        return quiz;
    }
}
