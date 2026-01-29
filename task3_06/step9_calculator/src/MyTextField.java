import javax.swing.*;
import java.awt.*;
/**
 * Пользовательский класс обертка для <code>JTextField</code><br/>
 * Сделан для изменения рамки на закругленную
 */
public class MyTextField extends JComponent {
    private int width;
    private int height;
    private Color borderColor;
    private Color background;
    private final JTextField textField;
    //------------------------------------------------------------------------------------------------------------------
    {
        borderColor = Color.BLACK;
        background = Color.white;
    }
    //------------------------------------------------------------------------------------------------------------------
    public MyTextField(JTextField tF) {
        textField = tF;
        textField.setBorder(null);
        width = (int) this.textField.getPreferredSize().getWidth();
        height = (int) this.textField.getPreferredSize().getHeight() + 30;

        setSize(width, height);
        setPreferredSize(new Dimension(width, height));

        textField.setBounds(5, 5, getWidth() - 9, getHeight() - 9);
        add(textField);
    }
    //------------------------------------------------------------------------------------------------------------------
    public JTextField getTextField() {
        return textField;
    }
    //------------------------------------------------------------------------------------------------------------------
    public void setBackground(Color background) {
        this.background = background;
        textField.setBackground(background);
    }
    //------------------------------------------------------------------------------------------------------------------
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.setColor(background);
        g2D.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 10, 10);

        g2D.setColor(borderColor);
        g2D.setStroke(new BasicStroke(1.5F));
        g2D.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 10, 10);
    }

} // конец класса MyTextField


