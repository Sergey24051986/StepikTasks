import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Пользовательский класс Scrollbar (собственной реализации)
public class Scrollbar extends JComponent {

    private int sliderHeight;
    private Thread thread;
    private Slider slider;
    private double sector;
    private double remainder = 0;
    private double amendment = 0;
    private final int comNum;
    private final ElementsList elementsList;
    private final ScrollbarEvent scrollbarEvent;
    private final ArrayList<ScrollbarListener> listeners;

    //------------------------------------------------------------------------------------------------------------------
    {
        listeners = new ArrayList<>();
        scrollbarEvent = new ScrollbarEvent(this);
    }

    //------------------------------------------------------------------------------------------------------------------
    public Scrollbar(ElementsList elementsList) {
        comNum = elementsList.getElementList().size();
        this.elementsList = elementsList;
        addComponentListener(new Resize());
        addMouseListener(new ForMouseInScrollbar());
        addMouseWheelListener(new ForMouseInScrollbar());
        addMouseMotionListener(new ForMouseInScrollbar());
    }

    //------------------------------------------------------------------------------------------------------------------
    public void sliderUp() {
        int y = (int) (slider.getLocation().getY());
        amendment += remainder;

        if (y + sliderHeight + sector + amendment <= getHeight() && y + sliderHeight + sector + amendment * 2 > getHeight()) {
            slider.setBounds(0, getHeight() - sliderHeight, getWidth(), sliderHeight);
        } else if (y + sliderHeight + sector + amendment <= getHeight())
            slider.setBounds(0, y += (int) (sector) + (int) (amendment), getWidth(), sliderHeight);
        else if (y + sliderHeight + sector + amendment > getHeight())
            slider.setBounds(0, getHeight() - sliderHeight, getWidth(), sliderHeight);

        if (amendment >= 1) amendment = amendment - (int) amendment;
        fireScrollbar();
    }

    //------------------------------------------------------------------------------------------------------------------
    public void sliderDown() {
        int y = (int) (slider.getLocation().getY());
        amendment += remainder;

        if (y - sector - amendment >= 0 && y - (int) sector * 2 - (int) (amendment) < 0)
            slider.setBounds(0, 0, getWidth(), sliderHeight);
        else if (y - sector >= 0)
            slider.setBounds(0, y -= (int) sector + (int) (amendment), getWidth(), sliderHeight);
        else if (y - sector - amendment < 0) {
            slider.setBounds(0, 0, getWidth(), sliderHeight);
        }

        if (amendment >= 1) amendment = amendment - (int) amendment;
        fireScrollbar();
    }

    //------------------------------------------------------------------------------------------------------------------
    public void addScrollbarListener(ScrollbarListener e) {
        listeners.add(e);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void removeScrollbarListener(ScrollbarListener e) {
        listeners.remove(e);
    }

    //------------------------------------------------------------------------------------------------------------------
    public int getSliderHeight() {
        return sliderHeight;
    }

    //------------------------------------------------------------------------------------------------------------------
    protected void fireScrollbar() {
        for (ScrollbarListener l : listeners)
            l.changePosition(scrollbarEvent);
    }
    //------------------------------------------------------------------------------------------------------------------

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Scrollbar (для слушателя изменения размера компонента)
    class Resize extends ComponentAdapter {
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void componentResized(ComponentEvent e) {
            setSize(getSize());

            int maxSize = (int) (getHeight() * 0.9);
            int minSize = (int) (getHeight() * 0.2);
            int lengthReducing = (int) (maxSize * 0.1);

            sliderHeight = maxSize - lengthReducing * (comNum - 8);
            sector = (getHeight() - sliderHeight) / (double) (comNum - 8);

            if (sliderHeight < minSize) {
                sector = (getHeight() - minSize) / (double) (comNum - 8);
                sliderHeight = minSize;
            }

            remainder = sector - (int) sector;
            slider = new Slider();
            slider.setBounds(0, 0, getWidth(), sliderHeight);
            add(slider);
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса Resize

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Scrollbar (ползунок)
    class Slider extends JComponent {

        private static Color color;

        //--------------------------------------------------------------------------------------------------------------
        public Slider() {
            color = Color.lightGray;
            setSize(getWidth(), sliderHeight);

            addMouseListener(new ForMouseInSlider());
            addMouseWheelListener(new ForMouseInSlider());
            addMouseMotionListener(new ForMouseInSlider());
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2D.setColor(color);
            g2D.fillRoundRect((int) (getWidth() * 0.1), (int) (getHeight() * 0.05),
                    (int) (getWidth() * 0.8), (int) (getHeight() * 0.95), 10, 10);
        }
        //--------------------------------------------------------------------------------------------------------------

        /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /// Внутренний класс Slider (слушатели для мыши)
        class ForMouseInSlider extends MouseAdapter {
            private static int firstY;

            //----------------------------------------------------------------------------------------------------------
            @Override
            public void mousePressed(MouseEvent e) {
                firstY = e.getY();
            }

            //----------------------------------------------------------------------------------------------------------
            @Override
            public void mouseEntered(MouseEvent e) {
                // Изменение цвета при наведении
                color = Color.gray;
                repaint();
            }

            //----------------------------------------------------------------------------------------------------------
            @Override
            public void mouseExited(MouseEvent e) {
                // Возврат цвета при выходе из компонента
                color = Color.lightGray;
                repaint();
            }

            //----------------------------------------------------------------------------------------------------------
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // При вращении сдвигает вверх или вниз
                if (e.getWheelRotation() == -1) {
                    sliderDown();
                    elementsList.elementsDown();
                } else {
                    elementsList.elementsUp();
                    sliderUp();
                }
            }

            //----------------------------------------------------------------------------------------------------------
            @Override
            public void mouseDragged(MouseEvent e) {
                int y = (int) (getLocation().getY() + e.getY() - firstY);

                if (y < 0) {
                    slider.setBounds(0, 0, getWidth(), sliderHeight);
                } else if (y > getParent().getHeight() - sliderHeight) {
                    elementsList.putElements(getParent().getHeight() - getSliderHeight() - 0.4);
                    slider.setBounds(0, getParent().getHeight() - sliderHeight, getWidth(), sliderHeight);
                } else {
                    elementsList.putElements(y);
                    slider.setBounds(0, y, getWidth(), sliderHeight);
                }
            }
            //--------------------------------------------------------------------------------------------------------------
        }
    } // Конец класса Slider

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Scrollbar (слушатели для мыши)
    class ForMouseInScrollbar extends MouseAdapter {
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            int n = 7;
            if (slider.getLocation().getY() < e.getY()) {
                thread = new ElementsUpThread();
                while (n-- >= 0) {
                    sliderUp();
                    elementsList.elementsUp();
                }
            } else {
                thread = new ElementsDownThread();
                while (n-- >= 0) {
                    sliderDown();
                    elementsList.elementsDown();
                }
            }
            thread.start();
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseReleased(MouseEvent e) {
            thread.interrupt();
        }

        //--------------------------------------------------------------------------------------------------------------
        public void mouseWheelMoved(MouseWheelEvent e) {
            // При вращении сдвигает вверх или вниз
            if (e.getWheelRotation() == -1) {
                sliderDown();
                elementsList.elementsDown();
            } else {
                elementsList.elementsUp();
                sliderUp();
            }
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса ForMouseInScrollbar

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Scrollbar (поток для перемещения элементов вверх при удержании кнопки)
    class ElementsUpThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    return;
                }
                sliderUp();
                elementsList.elementsUp();
            }
        }
    } // Конец класса ElementsUpThread

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Scrollbar (поток для перемещения элементов вниз при удержании кнопки)
    class ElementsDownThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    return;
                }
                sliderDown();
                elementsList.elementsDown();
            }
        }
    } // Конец класса ElementsDownThread

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Scrollbar (событие Scrollbar компонента)
    class ScrollbarEvent extends EventObject {
        public ScrollbarEvent(Object source) {
            super(source);
        }
    } // Конец класса ScrollbarEvent

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Scrollbar (слушатель Scrollbar)
    interface ScrollbarListener extends EventListener {
        void changePosition(ScrollbarEvent e);
    } // Конец класса ScrollbarListener
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} // Конец класса Scrollbar
