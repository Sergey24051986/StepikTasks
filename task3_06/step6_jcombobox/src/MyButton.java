import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Пользовательский класс MyButton (собственной реализации)
public class MyButton extends JButton {

    // Проблема расположения JButton в JFrame из-за прозрачности (надо сделать прозрачной!) - setOpaque(false)
    private Font font;
    private String text;
    private Color shadow;
    private Color backGroundColor;
    private final JLabel label;

    //------------------------------------------------------------------------------------------------------------------
    {
        label = new JLabel("Ответ");
        font = new Font("Dialog", Font.BOLD, 12);
        shadow = new Color(238, 238, 238);
        backGroundColor = new Color(238, 238, 238);
    }

    //------------------------------------------------------------------------------------------------------------------
    public MyButton() {
        setBorderPainted(false);
        addMouseListener(new ForMouseInMyButton());
    }

    //------------------------------------------------------------------------------------------------------------------
    public MyButton(String text) {
        this.text = text;
        setLayout(null);
        add(label);

        setBorderPainted(false);
        addMouseListener(new ForMouseInMyButton());
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Font getFont() {
        return font;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        g2D.clearRect(0, 0, getWidth(), getHeight());

        // Закраска фона кнопки-----------------------------------------------------------------------------------------
        g2D.setColor(backGroundColor);
        g2D.setStroke(new BasicStroke(1.5F));
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        // Тень при наведении-------------------------------------------------------------------------------------------
        g2D.setColor(shadow);
        g2D.setStroke(new BasicStroke(2F));
        g2D.drawRoundRect(4, 4, getWidth() - 8, getHeight() - 8, 8, 8);

        // Отрисовка рамки кнопки---------------------------------------------------------------------------------------
        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(1.5F));
        g2D.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 10, 10);

        g2D.setFont(font);
        var fm = g2D.getFontMetrics(font);

        int textWidth = fm.stringWidth(text);
        int textAscent = fm.getAscent();
        // Получаем положение крайнего левого символа на базовой линии
        // getWidth() и getHeight() возвращают ширину и высоту этого компонента
        int textX = getWidth() / 2 - textWidth / 2;
        int textY = getHeight() / 2 + textAscent / 2;
        g.drawString(text, textX, textY);

    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Double(2, 2, getWidth() - 4, getHeight() - 4, 10, 10).contains(x, y);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс (слушатели для мыши)
    class ForMouseInMyButton extends MouseAdapter {
        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            // Для изменения цвета кнопки при нажатии
            backGroundColor = Color.lightGray;
        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseReleased(MouseEvent e) {
            // Для возврата первоначального цвета при отпускании кнопки
            backGroundColor = new Color(238, 238, 238);
            ;
        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseEntered(MouseEvent e) {
            // Для подсвечивания при наведении на кнопку
            shadow = Color.lightGray;
        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseExited(MouseEvent e) {
            // Для возврата обычного цвета при выходе курсора с кнопки
            shadow = new Color(238, 238, 238);
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса ForMouseInMyButton
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} // Конец класса MyButton
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////