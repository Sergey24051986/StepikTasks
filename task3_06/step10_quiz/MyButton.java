import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;
import java.util.Scanner;

/// Пользовательский класс (собственной реализации)
public class MyButton extends JButton {
    private int width;
    private int height;
    private Font font;
    private String text;
    private Color shadow;
    private Color background;
    private Color background0;
    private Color borderColor;
    private int retreat;
    //------------------------------------------------------------------------------------------------------------------
    {
        font = new Font("Dialog", Font.BOLD, 20);
        shadow = new Color(255, 255, 255, 150);
        background = new Color(255, 255, 255, 150);
        background0 = background;
        borderColor = Color.BLACK;
        width = 397;
        height = 80;
        retreat = 30;
    }
    //------------------------------------------------------------------------------------------------------------------
    public MyButton() {
        this.text = "";
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        setOpaque(false);
        setBorderPainted(false);

        addMouseListener(new ForMouseInMyButton());
        addComponentListener(new Resize());
    }
    //------------------------------------------------------------------------------------------------------------------
    public MyButton(String text) {
        this.text = text;
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        setOpaque(false);
        setBorderPainted(false);

        addMouseListener(new ForMouseInMyButton());
        addComponentListener(new Resize());
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
    public String getText() {
        return text;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void setText(String text) {
        this.text = text;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        int curve = 30;
        // Закраска фона кнопки-----------------------------------------------------------------------------------------
        g2D.setColor(background);
        g2D.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, curve, curve);

        // Тень при наведении-------------------------------------------------------------------------------------------
        g2D.setColor(shadow);
        g2D.setStroke(new BasicStroke(3F));
        g2D.drawRoundRect(4, 4, getWidth() - 8, getHeight() - 8, curve, curve);

        // Отрисовка рамки кнопки---------------------------------------------------------------------------------------
        g2D.setColor(borderColor);
        g2D.setStroke(new BasicStroke(1.5F));
        g2D.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, curve, curve);

        // Установка по центру и отрисовка текста-----------------------------------------------------------------------
        g2D.setFont(font);
        var fm = g2D.getFontMetrics(font);
        var sc = new Scanner(text);
        // Если весь текст длиннее чем ширина кнопки, то обрабатывает с переносом
        if (fm.stringWidth(text) >= width) {
            int y = retreat;
            String word = "";
            while (sc.hasNext()) {
                var newWord = sc.next();
                if (fm.stringWidth(word + newWord + 20) >= width) {
                    g2D.drawString(word, 20, y);
                    y += retreat;
                    word = "";
                }
                word += newWord + " ";
            }
            g2D.drawString(word, 20, y);
        } else {
            // В данном случае текст короче, отрисовка по центру
            int textWidth = fm.stringWidth(text);
            int textAscent = fm.getAscent();
            // Получаем положение крайнего левого символа на базовой линии
            // getWidth() и getHeight() возвращают ширину и высоту этого компонента
            int textX = getWidth() / 2 - textWidth / 2;
            int textY = getHeight() / 2 + textAscent / 2;
            g.drawString(text, textX, textY);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Double(2, 2, getWidth() - 4, getHeight() - 4, 10, 10).contains(x, y);
    }
    //------------------------------------------------------------------------------------------------------------------
    /// Внутренний класс (слушатель изменения размера)
    class Resize extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            width = getWidth();
            height = getHeight();
            font = new Font("Dialog", Font.BOLD, (int) (height * 0.25));
            retreat = (int) (height * 0.35);
            repaint();
        }
    }
    /// Внутренний класс (слушатели для мыши)
    class ForMouseInMyButton extends MouseAdapter {
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            // Для изменения цвета кнопки при нажатии
            background = new Color(128, 128, 128, 100);
        }
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseReleased(MouseEvent e) {
            // Для возврата первоначального цвета при отпускании кнопки
            background = new Color(255, 255, 255, 150);
        }
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseEntered(MouseEvent e) {
            // Для подсвечивания при наведении на кнопку
            shadow = Color.GRAY;
        }
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseExited(MouseEvent e) {
            // Для возврата обычного цвета при выходе курсора с кнопки
            shadow = new Color(255, 255, 255, 150);
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса ForMouseInMyButton

} // Конец класса MyButton
