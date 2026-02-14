import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

class QuizUI extends JFrame {
    private Panel panel;
    private Quiz quiz;
    private MyButton[] buttons;
    private int numberOfQuestion;
    private int numberOfQuestions;
    private int countCorrectAnswer;
    private MyButton continuation;
    private MyButton newGame;
    private String[] correctAnswer;
    private QuestionField questionField;
    private MyButton fullScreen;
    //-----------------------------------------------------------------------------------
    {
        buttons = new MyButton[4];
        correctAnswer = new String[2];
        questionField = new QuestionField();
        continuation = new MyButton("Продолжить...");
        newGame = new MyButton("Начать сначала?");
        fullScreen = new MyButton("Развернуть");
    }
    //-----------------------------------------------------------------------------------
    public QuizUI() throws IOException, URISyntaxException {
        setSize(960, 540);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        addComponentListener(new Resize());

        panel = new Panel();
        panel.add(questionField);
        setContentPane(panel);


        fullScreen.setSize(150, 50);
        fullScreen.addActionListener(new ButtonPress());
        getLayeredPane().add(fullScreen, JLayeredPane.PALETTE_LAYER);

        for (int i = 0; i < 4; i++) {
            buttons[i] = new MyButton("Ответ№ " + (i + 1));
            buttons[i].addActionListener(new ButtonPress());
            panel.add(buttons[i]);
        }
        panel.add(continuation);
        panel.add(newGame);

        continuation.addActionListener(new ButtonPress());
        newGame.addActionListener(new ButtonPress());
        newGame.setVisible(false);

        setVisible(true);
    }
    //---------------------------------------------------------------------------------------------
    /**
     * Метод для инициализации загружаемого <code>Quiz</code> и загрузки первого вопроса<br/>
     * Также используется для перезагрузки <code>Quiz</code>
     * @param quiz
     */
    public void downloadQuiz(Quiz quiz) {
        this.quiz = quiz;
        numberOfQuestion = 1;
        countCorrectAnswer = 0;
        // Не больше 10 вопросов за одну викторину
        numberOfQuestions = quiz.getQuestions().size() < 11 ? quiz.getQuestions().size() : 10;
        continuation.setVisible(false);

        int buttonNumber = 0;
        // Из первой строки берем сам вопрос
        questionField.setText(quiz.getQuestions().getFirst().getFirst());
        for (int i = 1; i <= buttons.length; i++) {
            buttons[buttonNumber++].setText(quiz.getQuestions().getFirst().get(i));
        }
        // Создаем данные, для корректного ответа и расшифровки (в массиве)
        correctAnswer = quiz.getQuestions().getFirst().getLast().split("/");
    }
    //---------------------------------------------------------------------------------------------
    public void nextQuestion() {
        if (numberOfQuestion == 10)
            return;

        int buttonNumber = 0;
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                questionField.setText(quiz.getQuestions().get(numberOfQuestion).get(i));
            } else if (i == 5) {
                correctAnswer = quiz.getQuestions().get(numberOfQuestion).get(i).split("/");
            } else {
                buttons[buttonNumber++].setText(quiz.getQuestions().get(numberOfQuestion).get(i));
            }
        }
        numberOfQuestion++;
        continuation.setVisible(false);
        repaint();
    }
    //---------------------------------------------------------------------------------------------
    public void finish() {
        for (Component c : panel.getComponents()) {
            panel.isFinish = true;
            c.setVisible(false);
            repaint();
        }
    }
    //---------------------------------------------------------------------------------------------
    /// Внутренний класс (слушатель изменения размера)
    class Resize extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            int widthQF = (int) (getWidth() * 0.83);
            int heightQF = (int) (getHeight() * 0.37);
            questionField.setSize(widthQF, heightQF);
            questionField.setPreferredSize(new Dimension(widthQF, heightQF));

            int widthB = (int) (getWidth() * 0.413);
            int heightB = (int) (getHeight() * 0.15);
            for (MyButton b: buttons) {
                b.setSize(widthB, heightB);
                b.setPreferredSize(new Dimension(widthB, heightB));
            }
            repaint();
        }
    } // Конец класса Resize
    /**
     * Создаем панель, которой будем заменять ContentPane<br/>
     * В ней делаем возможность установки и смены фонового изображения<br/>
     * По умолчанию фоновое изображение уже установлено<br/>
     * Так же можно отключить фоновое изображение методом <code>setImage</code><br/>
     */
    class Panel extends JPanel {
        private Image img;
        private Boolean isSet;
        private boolean isFinish;
        private ArrayList<Image> images;
        //-----------------------------------------------------------------------------------------
        public Panel() throws IOException, URISyntaxException {
            isSet = true;
            isFinish = false;
            File imgFolder = new File(getClass().getResource("/resources/image").toURI());

                images = new ArrayList<>();
            for (String img: imgFolder.list()) {
                Image i = ImageIO.read(getClass().getResource("/resources/image/" + img));
                images.add(i);
            }

            img = images.get(new Random().nextInt(images.size()));
            setLayout(new FlowLayout());
        }
        //-----------------------------------------------------------------------------------------
        // Метод для изменения фонового изображения
        public void setBackgroundImage(Image newImg) {
            img = newImg;
        }
        //-----------------------------------------------------------------------------------------
        // Метод для возможности отключения фонового изображения
        public void setImage(Boolean newIsSet) {
            isSet = newIsSet;
        }
        //-----------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            if (isSet)
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

            g2D.setColor(new Color(255, 255, 255, 150));
            g2D.fillOval((int) (getWidth() * 0.88), (int) (getHeight() * 0.06), (int) (getWidth() * 0.1), (int) (getHeight() * 0.1));

            g2D.setColor(Color.BLACK);
            g2D.setFont(new Font("", Font.BOLD, (int) (getHeight() * 0.04)));
            g2D.drawString(numberOfQuestion + " / " + numberOfQuestions, (int) (getWidth() * 0.9), (int) (getHeight() * 0.12));

            // Блок работает если викторина завершилась!
            if (isFinish) {
                g2D.setColor(new Color(255, 255, 255, 150));
                g2D.fillRoundRect((int) (getWidth() * 0.11), (int) (getHeight() * 0.4),
                                  (int) (getWidth() * 0.8), (int) (getHeight() * 0.2), 30, 30);

                g2D.setColor(Color.BLACK);
                g2D.setStroke(new BasicStroke(1.5F));
                g2D.drawRoundRect((int) (getWidth() * 0.11), (int) (getHeight() * 0.4),
                                  (int) (getWidth() * 0.8), (int) (getHeight() * 0.2), 30, 30);

                Font font = new Font("Dialog", Font.BOLD, (int) (getHeight() * 0.04));
                String text = "Викторина завершена! Вы ответили правильно на " + countCorrectAnswer + " из " + 10 + " вопросов.";

                // Отрисовка текста по центру
                g2D.setFont(font);
                var fm = g2D.getFontMetrics(font);
                int textWidth = fm.stringWidth(text);
                int textAscent = fm.getAscent();
                // Получаем положение крайнего левого символа на базовой линии
                // getWidth() и getHeight() возвращают ширину и высоту этого компонента
                int textX = getWidth() / 2 - textWidth / 2;
                int textY = getHeight() / 2 + textAscent / 2;
                g.drawString(text, textX, textY);
            }
        }

    } // Конец класса Panel
    /**
     * Поле для отображения текста вопроса<br/>
     * Можно менять текст и менять параметры текста
     */
    class QuestionField extends JComponent {
        private Font font;
        private String text;
        private int width;
        private int height;
        private int retreat;
        //-----------------------------------------------------------------------------------------
        {
            width = 800;
            height = 200;
            retreat = 30;
            font = new Font("Dialog", Font.BOLD, 20);
            text = "Вопрос";
        }
        //-----------------------------------------------------------------------------------------
        public QuestionField() {
            setSize(width, height);
            setPreferredSize(new Dimension(width, height));
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    width = getWidth();
                    height = getHeight();
                    font = new Font("Dialog", Font.BOLD, (int) (height * 0.1));
                    retreat = (int) (height * 0.11);
                    repaint();
                }
            });
        }
        //-----------------------------------------------------------------------------------------
        @Override
        public Font getFont() {
            return font;
        }
        //-----------------------------------------------------------------------------------------
        @Override
        public void setFont(Font font) {
            this.font = font;
        }
        //-----------------------------------------------------------------------------------------
        public void setText(String text) {
            this.text = text;
        }
        //-----------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g.create();
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2D.setColor(new Color(255, 255, 255, 150));
            g2D.fillRoundRect(2, getHeight() / 2, getWidth() - 4, getHeight() / 2 - 4, 30, 30);

            g2D.setColor(Color.BLACK);
            g2D.setStroke(new BasicStroke(2));
            g2D.drawRoundRect(2, getHeight() / 2, getWidth() - 4, getHeight() / 2 - 4, 30, 30);

            // Установка по центру и отрисовка текста-------------------------------------------------------------------
            g2D.setFont(font);
            var fm = g2D.getFontMetrics(font);
            var sc = new Scanner(text);
            // Если весь текст длиннее чем ширина кнопки, то обрабатывает с переносом
            if (fm.stringWidth(text) >= width) {
                int y = getHeight()/2 + retreat;
                String word = "";
                while (sc.hasNext()) {
                    var newWord = sc.next();
                    if (fm.stringWidth(word + newWord + 20) >= width) {
                        g2D.drawString(word, 20, y);
                        y += retreat;
                        word = "";
                    }
                    word += newWord + " ";
                }
                int textWidth = fm.stringWidth(word);
                int textAscent = fm.getAscent();
                // Получаем положение крайнего левого символа на базовой линии
                // getWidth() и getHeight() возвращают ширину и высоту этого компонента
                int textX = getWidth() / 2 - textWidth / 2;
                g.drawString(word, textX, y);
            } else {
                // В данном случае текст короче, отрисовка по центру
                int textWidth = fm.stringWidth(text);
                int textAscent = fm.getAscent();
                // Получаем положение крайнего левого символа на базовой линии
                // getWidth() и getHeight() возвращают ширину и высоту этого компонента
                int textX = getWidth() / 2 - textWidth / 2;
                int textY = getHeight() - getHeight() / 4 + textAscent / 2;
                g.drawString(text, textX, textY);
            }
        }

    } // Конец класса QuestionField
    /**
     * Класс для обработки события нажатия любой кнопки
     */
    class ButtonPress implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MyButton button = (MyButton) e.getSource();
            //----------------------------------------------------------------------------------------------------------
            // Если выбрана одна из кнопок с вариантами
            if (!continuation.isVisible() && !button.getText().equals("Развернуть") && !button.getText().equals("Свернуть")) {
                if (button.getText().contains(correctAnswer[0])) {
                    questionField.setText("Да, это правильный ответ. " + correctAnswer[1]);
                    countCorrectAnswer++;
                } else {
                    questionField.setText("Увы, это не правильный ответ. Правильный вариант: " + correctAnswer[0] + " - " + correctAnswer[1]);
                }
                continuation.setVisible(true);
                repaint();
            }
            //----------------------------------------------------------------------------------------------------------
            // Если выбрана кнопка "Продолжить..."
            if (button.getText().equals("Продолжить...")) {
                if (numberOfQuestions == 0) {
                    return;
                }
                if (numberOfQuestion == 10) {
                    finish();
                    newGame.setVisible(true);
                }
                else nextQuestion();
            }
            //----------------------------------------------------------------------------------------------------------
            // Если выбрана кнопка "Начать сначала?"
            if (button.getText().equals("Начать сначала?")) {
                panel.isFinish = false;
                for (Component c : panel.getComponents()) {
                    c.setVisible(true);
                }
                continuation.setVisible(false);
                newGame.setVisible(false);
                try {
                    downloadQuiz(new Quiz(new File(getClass().getResource("/resources/text/questions.txt").toURI())));
                } catch (FileNotFoundException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //----------------------------------------------------------------------------------------------------------
            // Если выбрана кнопка "Развернуть"
            if (button.getText().equals("Развернуть")) {
                QuizUI.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                fullScreen.setText("Свернуть");
                panel.revalidate();
                return;
            }
            //----------------------------------------------------------------------------------------------------------
            // Если выбрана кнопка "Свернуть"
            if (button.getText().equals("Свернуть")) {
                QuizUI.this.setExtendedState(JFrame.NORMAL);
                fullScreen.setText("Развернуть");
                panel.revalidate();
            }
        }

    }  // Конец класса ButtonPress

} // Конец класса QuizUI

/**
 * Класс для обработки текстового файла с вопросами и подготовка их к передаче в интерфейс
 */
class Quiz {
    private ArrayList<ArrayList<String>> questions;
    public Quiz(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        questions = new ArrayList<>();
        // Заполнение списка текстом из файла
        while (in.hasNextLine()) {
            // Создается отдельный список для хранения 1-ого вопроса с ответами
            ArrayList<String> question = new ArrayList<>();

            for (int i = 0; i < 6; i++) {
                question.add(in.nextLine());
            }
            in.nextLine();
            // Добавление вопроса в список со всеми вопросами
            questions.add(question);
        }
        Collections.shuffle(questions);
    }

    public ArrayList<ArrayList<String>> getQuestions() {
        return questions;
    }

    public int size() {
        return questions.size();
    }

}// Конец класса Quiz

class QuizApp {
    public QuizApp() throws URISyntaxException, IOException {
        File file = new File(getClass().getResource("/resources/text/questions.txt").toURI());
        QuizUI quizUI = new QuizUI();
        Quiz quiz = new Quiz(file);
        quizUI.downloadQuiz(quiz);
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            try {
                new QuizApp();
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
