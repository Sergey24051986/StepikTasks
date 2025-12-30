import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyTextField extends JComponent {

    private Color color;
    private final JTextField textField;

    //------------------------------------------------------------------------------------------------------------------
    {
        color = Color.BLACK;
        textField = new JTextField();
    }

    //------------------------------------------------------------------------------------------------------------------
    public MyTextField(String text) {
        textField.setBorder(null);
        textField.setText(text);

        add(textField);
        addComponentListener(new Resize());
    }

    //------------------------------------------------------------------------------------------------------------------
    public JTextField getTextField() {
        return textField;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        if (textField.isEnabled())
            color = Color.BLACK;
        else color = Color.GRAY;

        g2D.setColor(color);
        g2D.setStroke(new BasicStroke(1.5F));
        g2D.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 10, 10);
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для MyTextField (для слушателя изменения размера компонента)
    class Resize extends ComponentAdapter {
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void componentResized(ComponentEvent e) {
            textField.setBounds(5, 5, getWidth() - 9, getHeight() - 9);
        }
        //--------------------------------------------------------------------------------------------------------------
    } // конец класса Resize
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} // конец класса MyTextField
