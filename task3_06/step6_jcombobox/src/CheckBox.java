import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Пользовательский класс CheckBox (собственной реализации)
public class CheckBox extends JComponent {

    private Color shadow;
    private boolean isTick;
    private final Tick tick;
    private final String text;
    private final CheckBoxEvent event;
    private final ArrayList<CheckBoxListener> listeners;

    //------------------------------------------------------------------------------------------------------------------
    {
        isTick = false;
        tick = new Tick();
        shadow = new Color(238, 238, 238);
        listeners = new ArrayList<>();
        event = new CheckBoxEvent(this);
    }

    //------------------------------------------------------------------------------------------------------------------
    public CheckBox(String text) {
        setSize(110, 20);
        this.text = text;
        addMouseListener(new ForMouseInCheckBox());
    }

    //------------------------------------------------------------------------------------------------------------------
    public void addCheckBoxListener(CheckBoxListener listener) {
        listeners.add(listener);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Double(5, 5, 10, 10, 5, 5).contains(x, y);
    }

    //------------------------------------------------------------------------------------------------------------------
    public boolean isTick() {
        return isTick;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void fireCheckBox() {
        for (CheckBoxListener listener : listeners)
            listener.changeToCheckBox(event);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2D.setColor(shadow);
        g2D.fillRoundRect(5, 6, 10, 7, 5, 5);

        g2D.setColor(Color.WHITE);
        g2D.fillOval(7, 8, 7, 10);

        g2D.setStroke(new BasicStroke(1.5F));
        g2D.setColor(Color.BLACK);
        g2D.drawRoundRect(5, 5, 10, 10, 5, 5);

        g2D.setFont(new Font("Calibre", Font.ITALIC | Font.BOLD, 12));
        g2D.drawString(text, 20, 15);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void removeCheckBoxListener(CheckBoxListener listener) {
        listeners.remove(listener);
    }
    //------------------------------------------------------------------------------------------------------------------

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс CheckBox (событие в CheckBox)
    class CheckBoxEvent extends EventObject {
        public CheckBoxEvent(Object source) {
            super(source);
        }
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс CheckBox (слушатели событий в CheckBox)
    interface CheckBoxListener extends EventListener {
        void changeToCheckBox(CheckBoxEvent e);
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс CheckBox (слушатели для мыши)
    class ForMouseInCheckBox extends MouseAdapter {
        private int count = 0;

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            // Добавляет галочку
            if (e.getButton() == 1) {
                if (count == 0) {
                    add(tick);
                    repaint();
                    count++;
                    isTick = true;
                    fireCheckBox();
                } else {
                    remove(tick);
                    repaint();
                    isTick = false;
                    count = 0;
                    fireCheckBox();
                }
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseEntered(MouseEvent e) {
            // Для появления тени при наведении на рамку
            shadow = Color.lightGray;
            repaint();
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseExited(MouseEvent e) {
            // Для исчезновения тени при выходе курсора с рамки
            shadow = new Color(238, 238, 238);
            repaint();
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса ForMouseInCheckBox

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для CheckBox (галочка)
    class Tick extends JComponent {
        //--------------------------------------------------------------------------------------------------------------
        public Tick() {
            setSize(20, 20);
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2D.setColor(Color.BLACK);
            g2D.setStroke(new BasicStroke(1.5F));
            Path2D tick = new Path2D.Double();
            tick.moveTo(4, 4);
            tick.curveTo(4, 4, 8, 8, 8, 12);
            tick.curveTo(8, 12, 14, 6, 19, 3);
            g2D.draw(tick);
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса Tick
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} // Конец класса CheckBox
/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////