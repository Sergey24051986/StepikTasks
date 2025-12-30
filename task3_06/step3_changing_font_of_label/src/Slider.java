import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.EventListener;
import java.util.EventObject;

public class Slider extends JComponent {

    private int x; // Начальное положение ползунка по x
    private int min;
    private int max;
    private int currentPosition;
    private final Balloons balloons;
    private final int y = 5; // Положение ползунка по оси y
    private final int pointNumber;
    private final int sliderWidth;
    private final int sliderHeight = 60;
    private final EventListenerList listenerList = new EventListenerList();
    private final SliderEvent sliderEvent = new SliderEvent(this);

    //------------------------------------------------------------------------------------------------------------------
    public Slider(int min, int max, int current) {
        sliderWidth = sliderHeight / 2 + (max - min) * 2;
        currentPosition = current;
        pointNumber = (max - min) * 2;
        x = (current - min) * 2 + 1;
        this.min = min;
        this.max = max;
        setSize(sliderWidth, sliderHeight);

        balloons = new Balloons();
        Background background = new Background();

        balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
        background.setBounds(0, background.getHeight(), background.getWidth(), background.getHeight());


        add(balloons);
        add(background);

        addMouseWheelListener(new ForMouseInSlider());
        addMouseListener(new ForMouseInSlider());
    }

    //------------------------------------------------------------------------------------------------------------------
    public void addSliderListener(SliderListener e) {
        listenerList.add(SliderListener.class, e);
    }

    //------------------------------------------------------------------------------------------------------------------
    public void removeSliderListener(SliderListener e) {
        listenerList.remove(SliderListener.class, e);
    }

    //------------------------------------------------------------------------------------------------------------------
    protected void fireSlider() {
        for (SliderListener listener : listenerList.getListeners(SliderListener.class))
            listener.changeSliderCurrentPosition(sliderEvent);
    }

    //------------------------------------------------------------------------------------------------------------------
    public int getCurrentPosition() {
        return currentPosition;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        x = (currentPosition - min) * 2 + 1;
        balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
    }
    //------------------------------------------------------------------------------------------------------------------

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Slider (Ползунок слайдера)
    class Balloons extends JComponent {

        private final int ballonsWidth = 30;
        private final int ballonsHeight = 60;

        //--------------------------------------------------------------------------------------------------------------
        public Balloons() {
            addMouseMotionListener(new ForMouseInSlider());
            addMouseListener(new ForMouseInSlider());
            setSize(ballonsWidth, ballonsHeight);
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setColor(Color.ORANGE);
            g2D.fillOval(3, sliderHeight / 2, ballonsWidth - 10, ballonsHeight / 2 - 10);
            g2D.setColor(Color.DARK_GRAY);
            g2D.drawOval(3, sliderHeight / 2, ballonsWidth - 10, ballonsHeight / 2 - 10);
            g2D.drawLine(ballonsHeight / 4 - 2, ballonsHeight / 2 - 1, ballonsHeight / 4 - 2, 20);
            g2D.drawRect(0, 0, 24, 20);

            if (currentPosition < 10)
                g2D.drawString(String.valueOf(currentPosition), 10, 15);
            else if (currentPosition < 100)
                g2D.drawString(String.valueOf(currentPosition), 7, 15);
            else
                g2D.drawString(String.valueOf(currentPosition), 3, 15);
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса Balloons

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Slider (задний фон)
    class Background extends JComponent {
        // Размер уменьшен, чтобы сдвинуть вниз и оставить место для индикатора
        private final int backgroundHeight = sliderHeight / 2;

        public Background() {
            setSize(sliderWidth, backgroundHeight);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Закрашиваем фон полукругов
            g2D.setColor(Color.LIGHT_GRAY);
            g2D.fillArc(3, 3, backgroundHeight - 7, backgroundHeight - 7, 90, 180);
            g2D.fillArc(pointNumber + 2, 3, backgroundHeight - 7, backgroundHeight - 7, 90, -180);

            // Рисуем контуры слайдера
            g2D.setColor(Color.GRAY);
            g2D.setStroke(new BasicStroke(3));
            g2D.drawArc(3, 3, backgroundHeight - 7, backgroundHeight - 7, 90, 180);
            g2D.drawArc(pointNumber + 2, 3, backgroundHeight - 7, backgroundHeight - 7, 90, -180);
            g2D.drawRect(backgroundHeight / 2, 3, pointNumber, backgroundHeight - 7);

            // Закрашиваем фон рабочего прямоугольника
            g2D.setColor(Color.LIGHT_GRAY);
            g2D.fillRect(backgroundHeight / 2 - 1, 5, pointNumber + 3, backgroundHeight - 10);
        }
    } // Конец класса Background

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Slider (слушатели для мыши)
    class ForMouseInSlider extends MouseAdapter {
        static int shiftXBalloons;
        static int shiftXWindow;

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            // При вращении колесика мыши - ballons сдвигается на 1-о деление
            if (e.getWheelRotation() == -1) {
                if (currentPosition >= max) return;
                x += 2;
                currentPosition += 1;
            } else {
                if (currentPosition <= min) return;
                x -= 2;
                currentPosition -= 1;
            }
            balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
            fireSlider();
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            /* При нажатии на правую кнопку мыши:
             * Запоминается позиция x если курсор в компоненте ballons
             * Ели курсор в Slider, то устанавливает ballons по координатам курсора
             */
            if (e.getButton() == 1 && e.getComponent().getClass().equals(Balloons.class)) {
                shiftXBalloons = e.getX();
            }
            if (e.getButton() == 1 && e.getComponent().getClass().equals(Slider.class)) {
                x = e.getX() - 4;
                currentPosition = x / 2 - 1;
                if (currentPosition > max | currentPosition < min) return;
                balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
                fireSlider();
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseDragged(MouseEvent e) {
            // Перетаскивает ballons по оси x
            x = (int) (e.getLocationOnScreen().getX() - e.getComponent().getParent().getLocationOnScreen().getX() - shiftXBalloons);
            if (x < 3) x = 1;
            if (x > pointNumber + 1) x = pointNumber + 1;
            currentPosition = x / 2 + min;
            balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
            fireSlider();
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса ForMouseInSlider

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс ElementsList (слушатель ElementsList выбранного элемента)
    interface SliderListener extends EventListener {
        void changeSliderCurrentPosition(SliderEvent e);
    } // Конец интерфейса SliderListener

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс ElementsList (событие ElementsList компонента)
    class SliderEvent extends EventObject {
        public SliderEvent(Object source) {
            super(source);
        }
    } // Конец класса SliderEvent
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} // Конец класса Slider
