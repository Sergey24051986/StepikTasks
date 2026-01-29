import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;

/**
 * <code>UI</code> - программы <code>Calculator</code><br/>
 * Настроено взаимодействие кнопок и <code>textField</code><br/>
 * Для расчетов исполь зовать класс <code>Calculator</code>
 * @see Calculator
 */
class CalculatorUI extends JFrame {
    private JTextField textField;
    //------------------------------------------------------------------------------------------------------------------
    public CalculatorUI() {
        super("Calculator");
        setSize(250, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout());

        MyTextField myTextField = new MyTextField(new JTextField(20));
        textField = myTextField.getTextField();
        textField.setFont(new Font("Dialog", Font.BOLD, 20));
        textField.addKeyListener(new KeyButtonListener());
        textField.setCaretColor(Color.WHITE);
        add(myTextField);

        String[] buttonsText = {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "C", "0", "=", "/"};
        
        for (String s: buttonsText) {
            MyButton button = new MyButton(s);
            // Если "=", то слушателя не присоединяем, так как к "=" нужно присоединять класс для расчета!
            if (!s.equals("="))
                button.addActionListener(new ButtonPress());
            add(button);
        }

        setVisible(true);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void addActionListener(ActionListener actionListener) {
        // Метод для присоединения класса с расчетами к кнопке "="
        for (Component com: getContentPane().getComponents()) {
            if (com.getClass().getName().contains("Button")) {
                JButton b = (JButton) com;
                if (b.getText().equals("=")) {
                    b.addActionListener(actionListener);
                }
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public synchronized void addKeyListener(KeyListener l) {
        // Метод для присоединения слушателя клавиатуры
        super.addKeyListener(l);
        for (Component com: getContentPane().getComponents()) {
            if (com.getClass().getName().contains("Button")) {
                com.addKeyListener(l);
            }
        }
        textField.addKeyListener(l);
    }
    //------------------------------------------------------------------------------------------------------------------
    public String getText() {
        return textField.getText();
    }
    //------------------------------------------------------------------------------------------------------------------
    public void setText(String text) {
        textField.setText(text);
    }
    //------------------------------------------------------------------------------------------------------------------
    public JTextField getTextField() {
        return textField;
    }
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Внутренний класс для взаимодействия между <code>textField</code> и кнопками<br/>
     * Используется диспетчеризация
     */
    class ButtonPress implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().getClass().getName().contains("Button")) {
                // Для начала возвращаем фокус в textField, чтобы всегда реагировало на клавиатуру
                textField.requestFocus();
                JButton b = (JButton) e.getSource();
                String text = textField.getText();

                // Если нажата кнопка "С", то очищает текстовое поле
                if (b.getText().equals("C")) {
                    textField.setText("");
                    return;
                }
                // Если в текстовом поле больше 16 символов, то выход
                if (text.length() > 16)
                    return;

                if (text.isEmpty()) {
                    // Если текстовое поле пустое, принимает только цифру
                    if (b.getText().matches("[0-9]"))
                        textField.setText(b.getText());
                } else {
                    // Если уже есть цифры и одно арифметическое действие, то только заменит текущее действие
                    if (text.matches(".+[+\\-/*]")) {
                        if (b.getText().matches("[+\\-/*]")) {
                            try {
                                textField.setText(textField.getText(0, text.length()-1) + b.getText());
                            } catch (BadLocationException ex) {
                                throw new RuntimeException(ex);
                            }
                            return;
                        }
                    }
                    // Если есть цифры, арифметическое действие и опять цифра, то принимает только цифру
                    if (text.matches(".+[+\\-/*]+.+")) {
                        if (b.getText().matches("[0-9]"))
                            textField.setText(text + b.getText());
                        return;
                    }

                    if (text.matches("[^=]+"))
                        textField.setText(text + b.getText());
                }
            }
        }
    } // Конец класса ButtonPress
    /**
     * Внутренний класс для обработки событий клавиатуры<br/>
     * Поглощает все кроме нужных знаков и цифр
     */
    class KeyButtonListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            String text = textField.getText();
            String ch = e.getKeyChar() + "";

            // Если в текстовом поле больше 16 символов, то выход
            if (text.length() > 16) {
                e.consume();
                return;
            }

            if (text.isEmpty()) {
                // Если текстовое поле пустое, принимает только цифру
                if (!ch.matches("[0-9]"))
                    e.consume();
            } else {
                // Если не пустое - то цифру и знаки
                if (!ch.matches("[0-9]")) {
                    e.consume();
                    // Если уже есть цифры и одно арифметическое действие, то только заменит текущее действие
                    if (text.matches(".+([+\\-/*]+)")) {
                        //System.out.println("I'm here!");
                        if (ch.matches("[0-9+\\-*/]")) {
                            e.consume();
                            try {
                                textField.setText(textField.getText(0, text.length() - 1) + ch);
                            } catch (BadLocationException ex) {
                                throw new RuntimeException(ex);
                            }
                            return;
                        }
                    }
                    // Если есть цифры, арифметическое действие и опять цифра, то принимает только цифру
                    if (text.matches(".+([+\\-/*]+).+")) {
                        System.out.println("I'm here!");
                        if (!ch.matches("[0-9]"))
                            e.consume();
                        return;
                    }
                    // Если есть одна цифра
                    if (ch.matches("[+\\-/*]"))
                        textField.setText(textField.getText() + ch);
                }
            }
        }
    } // Конец класса KeyButtonListener

} // Конец класса CalculatorUI

/**
 * Класс принимает в методе <code>calculation</code> строку<br/>
 * Внутри метода расчет и возврат строки с результатом
 */
class Calculator {
    //------------------------------------------------------------------------------------------------------------------
    public String calculation(String text) {
        // Если выражение не соответствует определенному виду, то выход
        if (!text.matches("([\\-]?)([0-9]+[.]?[0-9]*)([+\\-*/]+)([0-9]+[.]?[0-9]*)")) {
            System.out.println("Exit");
            return text;
        }
        int c = 1;
        // Проверка если в выражении первым стоит минус
        if (text.charAt(0) == '-') {
            text = text.substring(1);
            c = -1;
        }
        // Выражение для проверки - дробное или целое
        String regex = "[\\-]?[0-9]+[.]?[0]*";
        // Создается массив, в котором разделитель - это любое арифметическое действие
        String[] nums = text.split("[+\\-*/]");
        // Если первым был минус, то домножит на -1 и сделает первое число отрицательным
        Double a = Double.parseDouble(nums[0]) * c;
        Double b = Double.parseDouble(nums[1]);

        if (text.contains("+")) {
            // Проверяет, дробное или не дробное число получается в результате
            if ((a + b + "").matches(regex)) {
                // Если не дробное, возвращает без точки
                return (long) (a + b) + "";
            }
            // Если дробное, то с точкой
            return ((a + b) + "");
        }
        if (text.contains("-")) {
            if ((a - b + "").matches(regex)) {
                return (long) (a - b) + "";
            }
            return (a - b) + "";
        }
        if (text.contains("*")) {
            if ((a * b + "").matches(regex)) {
                return (long) (a * b) + "";
            }
            return (a * b) + "";
        }
        // По умолчанию, если деление "/"
        if (nums[1].equals("0")) {
            return "Error! / by zero";
        }
        if ((a / b + "").matches(regex)) {
            return (long) (a / b) + "";
        }
        return (a / b) + "";

    }

} // Конец класса Calculator

class CalculatorApp {
    static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalculatorUI calculatorUI = new CalculatorUI();
                Calculator calculator = new Calculator();
                calculatorUI.addActionListener(e -> {
                    calculatorUI.getTextField().requestFocus();
                    calculatorUI.setText(calculator.calculation(calculatorUI.getText()));
                });
                calculatorUI.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        if (e.getKeyCode() == 10) {
                            calculatorUI.setText(calculator.calculation(calculatorUI.getText()));
                        }
                    }
                });
            }
        });
    }
}