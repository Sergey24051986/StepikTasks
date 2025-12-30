import javax.swing.*;
import java.awt.*;

public class JSpinner extends JFrame {
    //------------------------------------------------------------------------------------------------------------------
    private String text;
    private JLabel label;
    private javax.swing.JSpinner jSpinner;

    //------------------------------------------------------------------------------------------------------------------
    public JSpinner() {
        super("JSpinner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        System.out.println(getLayout());
        setLayout(null);

        Spinner spinner = new Spinner();
        spinner.setBounds(100, 10, 100, 55); //130, 55
        spinner.addSpinnerListener(e -> {
            jSpinner.setValue(Integer.parseInt(spinner.getCount()));
        });

        MyButton button = new MyButton();
        button.setBounds(150, 100, 130, 40);
        button.addActionListener(e -> {
            text = "Ответ: " + spinner.getCount();
            label.setText(text);
        });

        jSpinner = new javax.swing.JSpinner();
        jSpinner.setBounds(250, 10, 100, 55);
        jSpinner.addChangeListener(e -> {
            spinner.setCount(String.valueOf(jSpinner.getValue()));
        });

        text = "Ответ: ";
        label = new JLabel(text);
        label.setBounds(150, 160, 150, 30);
        label.setFont(new Font("", Font.ITALIC, 20));

        add(label);
        add(button);
        add(spinner);
        add(jSpinner);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JSpinner::new);
    }
}
