import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class ConfirmationWindow {

    public static void main(String[] args) {
        int count = 0;
        Object[] options = new Object[] {"Да", "Нет"};
        int question1 = JOptionPane.showOptionDialog(null, "Женское сердце бьется быстрее мужского?", "Вопрос первый", YES_NO_OPTION, QUESTION_MESSAGE, null, options, options[1]);
        System.out.println(question1);
        if (question1 == 0) {
            JOptionPane.showMessageDialog(null, new String[] {"Правильно. Женское сердце немного меньше ",
                    "мужского и потому бьется быстрее. У мужчин оно ",
                    "совершает в среднем 60-70 ударов в минуту, а у ",
                    "женщин - 80-90."});
            count++;
        } else if (question1 == 1) {
            JOptionPane.showMessageDialog(null, new String[] {"Вы ошиблись. Женское сердце немного меньше ",
                    "мужского и потому бьется быстрее. У мужчин оно ",
                    "совершает в среднем 60-70 ударов в минуту, а у ",
                    "женщин - 80-90."});
        }

        int question2 = JOptionPane.showOptionDialog(null, "(2 + 2) - 2 * 2 = 4?", "Вопрос второй", YES_NO_OPTION, QUESTION_MESSAGE, null, options, options[1]);
        if (question2 == 0) {
            JOptionPane.showMessageDialog(null, "Не верно: (2 + 2) - 2 * 2 = 0");
        } else if (question2 == 1) {
            JOptionPane.showMessageDialog(null, "Верно: (2 + 2) - 2 * 2 = 0");
            count++;
        }

        int question3 = JOptionPane.showOptionDialog(null, new String[] {"Правда ли, что утенок считает мамой того,", "кого увидел первым после рождения?"}, "Вопрос второй", YES_NO_OPTION, QUESTION_MESSAGE, null, options, options[1]);
        if (question3 == 0) {
            JOptionPane.showMessageDialog(null, new String[] {"Да. Это явление называется импринтинг,", "или запечатление, или синдром утенка."});
            count++;
        } else if (question3 == 1) {
            JOptionPane.showMessageDialog(null, new String[] {"Не правильно! Утенок действительно считает мамой",
                    "того кого увидел первым после рождения.",
                    " ",
                    "Это явление называется импринтинг или запечатление,",
                    "или синдром утенка."});
        }

        switch(count) {
            case 0:
                JOptionPane.showMessageDialog(null, new String[] {"К сожалению вы не ответили правильно не на один", "вопрос, но вы открыли для себя что-то новое!"});
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Один правильный ответ, не самый худший вариант!");
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Два правильных ответа, больше половины между прочим! Неплохо!");
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Не одной ошибки поздравляю!");
                break;
        }
    }

}
