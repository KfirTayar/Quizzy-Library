package il.ac.hit.quizzy;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO{
    private static SimpleCSVQuizFilesDAO instance = null;

    // Create private constructor just for the class itself
    private SimpleCSVQuizFilesDAO(){
    }

    // Implement singleton pattern
    public static IQuizFilesDAO getInstance() {
        if (instance == null){
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    // Save current quiz
    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {

        IQuiz quizForSaving = (IQuiz) quiz;

        if (quizForSaving == null) {
            throw new QuizException("Invalid quiz type");
        }

        try (FileWriter writer = new FileWriter(fileName)) {

            // Append the name of the quiz to the file
            writer.append("Quiz name:\n");
            writer.append(quizForSaving.getName() + "\n");
            writer.append("Quiz type:\n");
            writer.append(quizForSaving.getTypeOfTheQuiz() + "\n");
            writer.append(("\n"));

            // Append the questions of the quiz to the file
            writer.append("Quiz questions:\n");
            for(IQuizQuestion builder: quizForSaving.getQuestions()) {
                writer.append("Title:\n");
                writer.append(builder.getTitle() + "\n");
                writer.append("Question:\n");
                writer.append(builder.getQuestion() + "\n");

                // Append the answers for each question to the file
                writer.append("Answers:\n");
                for (QuizAnswer answer : builder.getAnswers()) {
                    writer.append(answer.getAnswer() + "," + answer.isCorrect() + "\n");
                }
                writer.append(("\n"));
            }
            System.out.println("The Quiz was saved!\n");

        } catch (IOException e) {
            throw new QuizException("Error: the quiz was not saved!" + e.getMessage());
        }
    }

    // Load saved quiz
    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String prevLine;
            String currLine;
            String type = null;
            String name = null;
            List<String> titles = new ArrayList<>();
            List<String> questions = new ArrayList<>();
            List<String> answers = new ArrayList<>();
            List<Boolean> isCorrect = new ArrayList<>();

            // Iterate line by line at the file and match each variable by his previous title
            prevLine = reader.readLine();
            while((currLine = reader.readLine()) != null){

                if ("Quiz name:".equals(prevLine)) {
                    name = currLine;
                }
                if ("Quiz type:".equals(prevLine)) {
                    type = currLine;
                }
                if ("Title:".equals(prevLine)) {
                    titles.add(currLine);
                }
                if ("Question:".equals(prevLine)) {
                    questions.add(currLine);
                }
                if ("Answers:".equals(prevLine)) {

                    // We assume that there are 5 answers to each question
                    for (int i = 0; i < 5; i++){
                        if (i == 0){
                            String[] q = currLine.split(",");
                            answers.add(q[0]);
                            isCorrect.add(Boolean.parseBoolean(q[1]));
                        }
                        else{
                            String[] q = reader.readLine().split(",");
                            answers.add(q[0]);
                            isCorrect.add(Boolean.parseBoolean(q[1]));
                        }
                    }
                }
                prevLine = currLine;
            }

            // Create the quiz by his type
            IQuiz quizForLoading = switch (type) {
                case "TERMINAL" -> new QuizFactory().createQuiz(QuizType.TERMINAL);
                case "GUI" -> new QuizFactory().createQuiz(QuizType.GUI);
                default -> throw new QuizException("The quiz type are invalid!");
            };

            // Set the name of the quiz
            quizForLoading.setName(name);

            // Create the questions of the quiz using the builders
            List<IQuizQuestion> builders = new ArrayList<>();

            int idxAnswer = 0;
            for (int i = 0; i < titles.size(); i++) {
                IQuizQuestionBuilder builder = new QuizQuestion.Builder();
                builder.setTitle(titles.get(i));
                builder.setQuestion(questions.get(i));

                for (int j = 0; j < 5; j++){
                    builder.addAnswer(answers.get(idxAnswer), isCorrect.get(idxAnswer));
                    idxAnswer += 1;
                }
                IQuizQuestion question = builder.create();
                builders.add(question);
            }

            // Add each question that created to the quiz
            for (IQuizQuestion question: builders) {
                quizForLoading.addQuestion(question);
            }

            System.out.println("Loading the quiz...\n");
            return quizForLoading;

        } catch (IOException e) {
            throw new QuizException("Error: the quiz was not load!" + e.getMessage());
        }
    }
}
