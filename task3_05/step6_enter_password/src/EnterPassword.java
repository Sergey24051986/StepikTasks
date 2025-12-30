import javax.swing.*;

public class EnterPassword {

    public static void main(String[] args) {
        String[] optional1 = {"Да", "Нет"};
        String[] optional2 = {"Да", "Отмена"};
        int question = JOptionPane.showOptionDialog(null, "Добрый день, желаете зарегистрироваться,", "Окно регистрации", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optional1, optional1[0]);
        if (question != 0) System.exit(0);

        JTextField login = new JTextField();
        while (true) {
            question = JOptionPane.showOptionDialog(null, new Object[]{"Введите логин:", login}, "Регистрация", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optional2, null);
            if (question != 0) System.exit(0);

            if (login.getText().matches("[^ ]{6,}+")) break;
            else
                JOptionPane.showMessageDialog(null, "Логин должен быть больше 5 символов и не должен содержать пробелов!", "Подсказка!", JOptionPane.INFORMATION_MESSAGE);
        }

        JPasswordField password1 = new JPasswordField();
        password1.setSize(300, 20);
        password1.setEchoChar('*');
        String[] message = {"Пароль должен быть больше 8 символов, не должен ", "содержать  пробелов  и  должен содержать  хотя  бы ", "одну цифру и хотя бы одну букву."};

        while (true) {
            question = JOptionPane.showOptionDialog(null, new Object[]{"Введите пароль:", password1}, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optional2, null);
            if (question != 0) System.exit(0);

            if (new String(password1.getPassword()).matches("(?=.*[0-9])(?=.*[a-zA-Zа-яА-Я])\\S{9,}")) break;
            else JOptionPane.showMessageDialog(null, message, "Подсказка!", JOptionPane.INFORMATION_MESSAGE);
        }
        JPasswordField password2 = new JPasswordField();
        password2.setEchoChar('*');

        while (true) {
            question = JOptionPane.showOptionDialog(null, new Object[]{"Повторите введенный пароль:", password2}, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optional2, null);
            if (question != 0) System.exit(0);

            if (new String(password1.getPassword()).equals(new String(password2.getPassword()))) break;
            else
                JOptionPane.showMessageDialog(null, "Пароли не совпадают, попробуйте ещё раз!", "Подсказка!", JOptionPane.INFORMATION_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, login.getText() + " вы успешно зарегистрировались!", "Поздравляем!", JOptionPane.INFORMATION_MESSAGE);
    }
}