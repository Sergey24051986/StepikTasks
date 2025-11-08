import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.EventListener;
import java.util.EventObject;

public class Slider extends JComponent {

    private final EventListenerList listenerList = new EventListenerList();
    private static int sliderWidth;
    private static final int sliderHeight = 60;
    private static int x; // Начальное положение ползунка по x
    private static final int y = 5; // Положение ползунка по оси y
    private static int currentPosition;
    private static Balloons balloons;
    private static int pointNumber;
    private static int min;
    private static int max;
    SliderEvent sliderEvent = new SliderEvent(this);


    public Slider(int min, int max, int current) {
        sliderWidth = sliderHeight/2 + (max - min) * 2;
        currentPosition = current;
        pointNumber = (max - min) * 2;
        x = (current - min) * 2 + 1;
        Slider.min = min;
        Slider.max = max;
        setSize(sliderWidth, sliderHeight);

        balloons = new Balloons();
        Background background = new Background();

        balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
        background.setBounds(0, background.getHeight(), background.getWidth(), background.getHeight());


        add(balloons);
        add(background);

        addMouseWheelListener(new ForMouse());
        addMouseListener(new ForMouse());
    }

    public void addSliderListener(SliderListener e) {
        listenerList.add(SliderListener.class, e);
    }

    public void removeSliderListener(SliderListener e) {
        listenerList.remove(SliderListener.class, e);
    }

    protected void fireSlider() {
        for (SliderListener listener: listenerList.getListeners(SliderListener.class))
            listener.changeSliderCurrentPosition(sliderEvent);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        Slider.currentPosition = currentPosition;
        x = (currentPosition - min) * 2 + 1;
        balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
    }

    // Ползунок слайдера
    class Balloons extends JComponent {

        private final int ballonsWidth = 30;
        private final int ballonsHeight = 60;

        public Balloons() {
            addMouseMotionListener(new ForMouse());
            addMouseListener(new ForMouse());
            setSize(ballonsWidth, ballonsHeight);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setColor(Color.ORANGE);
            g2D.fillOval(3,sliderHeight/2,ballonsWidth - 10, ballonsHeight/2 - 10);
            g2D.setColor(Color.DARK_GRAY);
            g2D.drawOval(3,sliderHeight/2,ballonsWidth - 10, ballonsHeight/2 - 10);
            g2D.drawLine(ballonsHeight/4 - 2, ballonsHeight/2 - 1, ballonsHeight/4 - 2, 20);
            g2D.drawRect(0,0, 24, 20);

            if (currentPosition < 10)
                g2D.drawString(String.valueOf(currentPosition), 10,15);
            else if (currentPosition < 100)
                g2D.drawString(String.valueOf(currentPosition), 7,15);
            else
                g2D.drawString(String.valueOf(currentPosition), 3,15);
        }
    }
    // Рамка слайдера
    class Background extends JComponent {
        // Размер уменьшен, чтобы сдвинуть вниз и оставить место для индикатора
        private final int backgroundHeight = sliderHeight/2;
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
            g2D.drawRect(backgroundHeight/2, 3, pointNumber, backgroundHeight - 7);

            // Закрашиваем фон рабочего прямоугольника
            g2D.setColor(Color.LIGHT_GRAY);
            g2D.fillRect(backgroundHeight/2 - 1, 5, pointNumber + 3, backgroundHeight - 10);
        }
    }

    class ForMouse extends MouseAdapter {
        static int shiftXBalloons;
        static int shiftXWindow;

        // При вращении колесика мыши - ballons сдвигается на 1-о деление
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getWheelRotation() == -1) {
                if (currentPosition >= max) return;
                x += 2;
                currentPosition += 1;
            }  else {
                if (currentPosition <= min) return;
                x -= 2;
                currentPosition -= 1;
            }
            balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
            fireSlider();

        }

        /* При нажатии на правую кнопку мыши:
         * Запоминается позиция x если курсор в компоненте ballons
         * Ели курсор в Slider, то устанавливает ballons по координатам курсора
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1 && e.getComponent().getClass().equals(Balloons.class)) {
                shiftXBalloons = e.getX();
            }
            if (e.getButton() == 1 && e.getComponent().getClass().equals(Slider.class)) {
                x = e.getX() - 4;
                currentPosition = x/2 - 1;
                if (currentPosition > max | currentPosition < min) return;
                balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
                fireSlider();
            }
        }

        // Перетаскивает ballons по оси x
        @Override
        public void mouseDragged(MouseEvent e) {
            x = (int)(e.getLocationOnScreen().getX() - e.getComponent().getParent().getLocationOnScreen().getX() - shiftXBalloons);
            if (x < 3) x = 1;
            if (x > pointNumber + 1) x = pointNumber + 1;
            currentPosition = x/2 + min;
            balloons.setBounds(x, y, balloons.getWidth(), balloons.getHeight());
            fireSlider();
        }
    }

    interface SliderListener extends EventListener {
        void changeSliderCurrentPosition(SliderEvent e);
    }

    class SliderEvent extends EventObject {
        public SliderEvent(Object source) {
            super(source);
        }
    }
}
