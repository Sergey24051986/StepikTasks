import javax.swing.*;
import java.awt.*;

/// Пользовательский класс (собственной реализации)
public class MyJRadioButton extends JComponent {
    private Image img;
    private JRadioButton radioButton;

    public MyJRadioButton(Image img, JRadioButton radioButton) {
        setSize(500, 200);
        // Без установки PreferredSize в панели отображаться не будет
        setPreferredSize(new Dimension(500, 200));

        this.img = img;
        this.radioButton = radioButton;

        add(radioButton);
        radioButton.setOpaque(false);
        radioButton.setSize(getSize());
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(),null);
    }
    //------------------------------------------------------------------------------------------------------------------
} // Конец класса MyJRadioButton
