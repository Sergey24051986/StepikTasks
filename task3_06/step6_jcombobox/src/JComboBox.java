import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JComboBox extends JFrame {

    //------------------------------------------------------------------------------------------------------------------
    private int count1;
    private final ComboBox box;
    private final CheckBox checkBox;
    private final MyTextField textField;
    private final MyButton button;
    private final JLabel label1;
    //------------------------------------------------------------------------------------------------------------------
    private int count2;
    private final JLabel label2;
    private final JButton jButton;
    private final JCheckBox jCheckBox;
    private final JTextField jTextField;
    private final javax.swing.JComboBox jComboBox;

    //------------------------------------------------------------------------------------------------------------------
    {
        String[] colors = new String[]{"Черный", "Белый", "Бирюзовый", "Желтый", "Сомон", "Небесный", "Циан"};
        //--------------------------------------------------------------------------------------------------------------
        box = new ComboBox(colors);
        checkBox = new CheckBox("Свой вариант");
        textField = new MyTextField("Placeholder");
        button = new MyButton("Ответить");
        label1 = new JLabel("Ответ: ");
        //--------------------------------------------------------------------------------------------------------------
        count2 = 1;
        jComboBox = new javax.swing.JComboBox<>(colors);
        jCheckBox = new JCheckBox("Свой вариант");
        jTextField = new JTextField("Placeholder");
        jButton = new JButton("Ответить");
        label2 = new JLabel("Ответ: ");
        //--------------------------------------------------------------------------------------------------------------
    }

    //------------------------------------------------------------------------------------------------------------------
    public JComboBox() {
        super("JComboBox");
        //getContentPane().setBackground(Color.BLUE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 250);
        setLocation(600, 300);
        setLayout(null);

        // Родные компоненты
        //--------------------------------------------------------------------------------------------------------------
        jComboBox.setBounds(200, 20, 130, 30);
        jCheckBox.setBounds(210, 60, 110, 15);
        jCheckBox.addItemListener(e -> {
            jTextField.setEnabled(jCheckBox.isSelected());
            if (!jCheckBox.isSelected()) jTextField.setText("Placeholder");
            count2 = 1;
        });
        jTextField.setBounds(200, 100, 130, 30);
        jTextField.setFont(new Font("Caliber", Font.PLAIN, 12));
        jTextField.setEnabled(false);
        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                while (count2-- > 0)
                    jTextField.setText("");
            }
        });
        jButton.setBounds(215, 140, 100, 30);
        jButton.addActionListener(e -> {
            if (jCheckBox.isSelected()) {
                label2.setText("Ответ: " + jTextField.getText());
            } else {
                label2.setText("Ответ: " + jComboBox.getSelectedItem());
            }

        });
        label2.setBounds(200, 170, 200, 30);
        label2.setFont(new Font("", Font.ITALIC, 14));

        add(jComboBox);
        add(jCheckBox);
        add(jTextField);
        add(jButton);
        add(label1);
        //--------------------------------------------------------------------------------------------------------------

        // Созданные компоненты
        //--------------------------------------------------------------------------------------------------------------
        box.setBounds(20, 20, 130, 30);
        checkBox.setLocation(30, 60);
        checkBox.addCheckBoxListener(e -> {
            textField.getTextField().setEnabled(checkBox.isTick());
            repaint();
            if (!checkBox.isTick()) textField.getTextField().setText("Placeholder");
            count1 = 1;
        });
        textField.setBounds(20, 100, 130, 30);
        textField.getTextField().setEnabled(false);
        textField.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                while (count1-- > 0)
                    textField.getTextField().setText("");
            }
        });
        button.setBounds(35, 140, 100, 30);
        button.addActionListener(e -> {
            if (checkBox.isTick()) {
                label1.setText("Ответ: " + textField.getTextField().getText());
            } else {
                label1.setText("Ответ: " + box.getSelectedItem());
            }
        });
        label1.setBounds(20, 170, 200, 30);
        label1.setFont(new Font("", Font.ITALIC, 14));

        add(box);
        add(checkBox);
        add(textField);
        add(button);
        add(label2);
        //--------------------------------------------------------------------------------------------------------------
        setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JComboBox::new);
    }
    //------------------------------------------------------------------------------------------------------------------
}
