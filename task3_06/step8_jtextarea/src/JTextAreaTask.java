import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JTextAreaTask extends JFrame {
    private JTextField textField;
    private JTextArea textArea;
    private JButton button;

    {
        textField = new JTextField(17);
        textArea = new JTextArea(10, 17);
        button = new JButton("Записать");
    }

    public JTextAreaTask() {
        super("JTextArea");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(250, 300);
        setLayout(new FlowLayout());
        setResizable(false);

        textArea.setLineWrap(true);
        button.addActionListener(e -> {
            textArea.append(textField.getText() + "\n");
        });

        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == 10)
                    textArea.append(textField.getText() + "\n");
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == 10)
                    textArea.append(textField.getText() + "\n");
            }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(textField);
        add(button);
        add(scrollPane);

        setVisible(true);
    }
}

class JTextAreaTaskApp {
    static void main() {
        SwingUtilities.invokeLater(JTextAreaTask::new);
    }
}
