import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
public class JRadioButtonTask extends JFrame {


    private String path;
    private String text;
    private Answer answer;
    private String[] seasons;
    private ArrayList<MyJRadioButton> buttons;

    {
        path = "task3_06/step7_jradiobutton/Image/";
        seasons = new String[]{"Winter", "Spring", "Summer", "Autumn"};
        buttons = new ArrayList<>();
    }

    public JRadioButtonTask() {
        super("JRadioButton");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        ButtonGroup buttonGroup = new ButtonGroup();

        for (String season : seasons) {
            JRadioButton jRadioButton = new JRadioButton(season);
            jRadioButton.setFont(new Font("Caliber", Font.BOLD | Font.ITALIC, 50));
            jRadioButton.setForeground(Color.white);
            Image image = new ImageIcon(path + season + ".jpg").getImage();
            MyJRadioButton button = new MyJRadioButton(image, jRadioButton);
            buttonGroup.add(jRadioButton);
            buttons.add(button);
        }

        answer = new Answer(new ImageIcon(path + "abstract.jpg").getImage());
        answer.getMyButton().addActionListener(e -> {
            Enumeration<AbstractButton> buttons = buttonGroup.getElements();
            while (buttons.hasMoreElements()) {
                AbstractButton b = buttons.nextElement();
                if (b.isSelected()) text = b.getText();
            }
            answer.getLabel().setText("Ответ: " + text);
        });

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.orange);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(buttons.get(0));
        panel1.add(buttons.get(1));

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.orange);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.add(buttons.get(2));
        panel2.add(buttons.get(3));

        add(panel1);
        add(panel2);
        add(answer);

        pack();
        setVisible(true);
    }

    static void main() {
        SwingUtilities.invokeLater(JRadioButtonTask::new);
    }
}
