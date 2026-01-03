import javax.swing.*;
import java.awt.*;

/// Пользовательский класс (собственной реализации)
public class Answer extends JComponent {
    private Image img;
    private JLabel label;
    private MyButton myButton;
    //------------------------------------------------------------------------------------------------------------------
    public Answer(Image img) {
        setSize(500, 200);
        // Без установки PreferredSize в панели отображаться не будет
        setPreferredSize(new Dimension(500, 200));

        myButton = new MyButton("Ответить");
        myButton.setBounds(10, 20, 150, 60);

        label = new JLabel("Ответ: ");
        label.setFont(new Font("Caliber", Font.BOLD | Font.ITALIC, 30));
        label.setBounds(20, getHeight() - 100, 300, 100);
        label.setForeground(new Color(220, 80, 0));
        add(label);
        add(myButton);
        this.img = img;
    }
    //------------------------------------------------------------------------------------------------------------------
    public JLabel getLabel() {
        return label;
    }
    //------------------------------------------------------------------------------------------------------------------
    public MyButton getMyButton() {
        return myButton;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
    //------------------------------------------------------------------------------------------------------------------
} // Конец класса Answer
