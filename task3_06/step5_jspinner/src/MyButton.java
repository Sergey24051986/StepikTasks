import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Пользовательский класс MyButton (собственной реализации)
public class MyButton extends JButton {
    // Проблема расположения JButton в JFrame из-за прозрачности (надо сделать прозрачной!) - setOpaque(false)
    private Color backGroundColor;
    private Color illuminationColor;
    private Color textColor;

    //------------------------------------------------------------------------------------------------------------------
    {
        backGroundColor = new Color(238, 238, 238);
        illuminationColor = Color.GRAY;
        textColor = Color.BLACK;
    }

    //------------------------------------------------------------------------------------------------------------------
    public MyButton() {
        setBorderPainted(false);
        setOpaque(false);
        addMouseListener(new ForMouseInButton());
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        // Закраска фона кнопки-----------------------------------------------------------------------------------------
        g2D.setColor(backGroundColor);
        g2D.setStroke(new BasicStroke(3));
        g2D.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        // Отрисовка рамки кнопки---------------------------------------------------------------------------------------
        g2D.setColor(illuminationColor);
        g2D.drawRoundRect(
                (int) (getWidth() * 0.01), (int) (getHeight() * 0.03),
                (int) (getWidth() * 0.98), (int) (getHeight() * 0.92), 10, 10);

        // Отрисовка текста---------------------------------------------------------------------------------------------
        g2D.setColor(textColor);
        g2D.setFont(new Font("", Font.ITALIC, (int) (getHeight() * 0.5)));
        g2D.drawString("Ответить", (int) (getWidth() * 0.08), (int) (getHeight() * 0.65));
        //--------------------------------------------------------------------------------------------------------------
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30).contains(x, y);
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс (слушатели для мыши)
    class ForMouseInButton extends MouseAdapter {
        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            // Для изменения цвета кнопки при нажатии
            super.mousePressed(e);
            backGroundColor = Color.DARK_GRAY;
        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseReleased(MouseEvent e) {
            // Для возврата первоначального цвета при отпускании кнопки
            super.mousePressed(e);
            backGroundColor = new Color(238, 238, 238);
            ;
        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseEntered(MouseEvent e) {
            // Для подсвечивания при наведении на кнопку
            super.mousePressed(e);
            illuminationColor = Color.CYAN;
            textColor = Color.CYAN;

        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseExited(MouseEvent e) {
            // Для возврата обычного цвета при выходе курсора с кнопки
            super.mousePressed(e);
            illuminationColor = Color.GRAY;
            textColor = Color.BLACK;
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса ForMouseInButton
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} // Конец класса MyButton
