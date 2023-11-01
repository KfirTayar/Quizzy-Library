package il.ac.hit.quizzy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuizFrame implements ActionListener {
    private List<IQuizQuestion> questions;
    private JFrame frame;
    private JLabel title;
    private JLabel question;
    private JLabel[] answerLabels;
    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonC;
    private JButton buttonD;
    private JButton buttonE;
    private int index;
    private int score;

    public List<IQuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<IQuizQuestion> questions) {
        this.questions = questions;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JLabel getQuestion() {
        return question;
    }

    public void setQuestion(JLabel question) {
        this.question = question;
    }

    public JLabel[] getAnswerLabels() {
        return answerLabels;
    }

    public void setAnswerLabels(JLabel[] answerLabels) {
        this.answerLabels = answerLabels;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public QuizFrame(List<IQuizQuestion> questions) {
        setQuestions(questions);
        setFrame(new JFrame());
        setTitle(new JLabel());
        setQuestion(new JLabel());
        setAnswerLabels(new JLabel[5]);
        setIndex(0);
        setScore(0);
        buttonA = new JButton();
        buttonB = new JButton();
        buttonC = new JButton();
        buttonD = new JButton();
        buttonE = new JButton();
    }

    public void initialize() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,700);
        frame.setLayout(null);
        frame.setResizable(false);

        buttonA.setBounds(100,300,100,50);
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(100,350,100,50);
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(100,400,100,50);
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(100,450,100,50);
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        buttonE.setBounds(100,500,100,50);
        buttonE.setFocusable(false);
        buttonE.addActionListener(this);
        buttonE.setText("E");

        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(buttonE);


        nextQuestion();

        frame.setVisible(true);
    }

    private void nextQuestion() {
        if(index >= questions.size()) {
            results();
        }
        else {
            buttonA.setEnabled(true);
            buttonB.setEnabled(true);
            buttonC.setEnabled(true);
            buttonD.setEnabled(true);
            buttonE.setEnabled(true);

            title.setText(questions.get(index).getTitle());
            title.setBounds(100,100,500,50);

            question.setText(questions.get(index).getQuestion());
            question.setBounds(100,200,500,50);

            for (int i = 0; i < questions.get(index).getAnswers().size(); i++) {
                answerLabels[i] = new JLabel();
                answerLabels[i].setText(questions.get(index).getAnswers().get(i).getAnswer());
            }

            answerLabels[0].setBounds(225,300,500,50);
            answerLabels[1].setBounds(225,350,500,50);
            answerLabels[2].setBounds(225,400,500,50);
            answerLabels[3].setBounds(225,450,500,50);
            answerLabels[4].setBounds(225,500,500,50);

            frame.add(title);
            frame.add(question);
            frame.add(answerLabels[0]);
            frame.add(answerLabels[1]);
            frame.add(answerLabels[2]);
            frame.add(answerLabels[3]);
            frame.add(answerLabels[4]);

            frame.revalidate();
            frame.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        buttonE.setEnabled(false);

        if(e.getSource()==buttonA) {
            if(questions.get(index).getAnswers().get(0).isCorrect()) {
                setScore(score+1);
            }
        }
        if(e.getSource()==buttonB) {
            if(questions.get(index).getAnswers().get(1).isCorrect()) {
                setScore(score+1);
            }
        }
        if(e.getSource()==buttonC) {
            if(questions.get(index).getAnswers().get(2).isCorrect()) {
                setScore(score+1);
            }
        }
        if(e.getSource()==buttonD) {
            if(questions.get(index).getAnswers().get(3).isCorrect()) {
                setScore(score+1);
            }
        }
        if(e.getSource()==buttonE) {
            if(questions.get(index).getAnswers().get(4).isCorrect()) {
                setScore(score+1);
            }
        }

        setIndex(index+1);

        frame.getContentPane().remove(title);
        frame.getContentPane().remove(question);
        frame.getContentPane().remove(answerLabels[0]);
        frame.getContentPane().remove(answerLabels[1]);
        frame.getContentPane().remove(answerLabels[2]);
        frame.getContentPane().remove(answerLabels[3]);
        frame.getContentPane().remove(answerLabels[4]);

        nextQuestion();
    }

    public void results() {
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLayout(null);
        frame.setResizable(false);
        JLabel result = new JLabel();
        result.setBounds(125,125, 50,50);
        result.setText("Score: " + score);
        frame.add(result);
        frame.setVisible(true);
    }
}