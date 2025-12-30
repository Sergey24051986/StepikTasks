import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Пользовательский класс Spinner (собственной реализации)
public class Spinner extends JComponent {

    private Shape shape;
    private Color topButtonColor;
    private Color bottomButtonColor;
    private Color triangleIlluminationUp;
    private Color triangleIlluminationDown;
    private Color contourIlluminationUp;
    private Color contourIlluminationDown;
    private final Border border;
    private final TopButton topButton;
    ;
    private final JTextField textField;
    private final BackGround backGround;
    private final BottomButton bottomButton;
    private final ChangeBookCountEvent event;
    private final ArrayList<SpinnerListener> spinnerListeners;

    //--------------------------------------------------------------------------------------------------------------
    {
        topButtonColor = new Color(238, 238, 238);
        bottomButtonColor = new Color(238, 238, 238);
        triangleIlluminationUp = Color.BLACK;
        contourIlluminationUp = new Color(238, 238, 238);
        ;
        triangleIlluminationDown = Color.BLACK;
        contourIlluminationDown = new Color(238, 238, 238);
        ;
        spinnerListeners = new ArrayList<>();
        border = new Border();
        topButton = new TopButton();
        bottomButton = new BottomButton();
        backGround = new BackGround();
        textField = new JTextField();
        event = new ChangeBookCountEvent(this);
    }

    //--------------------------------------------------------------------------------------------------------------
    public Spinner() {
        topButton.addMouseListener(new ForMouseInSpinner());
        bottomButton.addMouseListener(new ForMouseInSpinner());

        textField.setText("0");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setOpaque(false);

        addComponentListener(new Resize());

        add(textField);
        add(border);
        add(bottomButton);
        add(topButton);
        add(backGround);

    }

    //--------------------------------------------------------------------------------------------------------------
    public void addSpinnerListener(SpinnerListener e) {
        spinnerListeners.add(e);
    }

    //--------------------------------------------------------------------------------------------------------------
    public void removeSpinnerListener(SpinnerListener e) {
        spinnerListeners.remove(e);
    }

    //--------------------------------------------------------------------------------------------------------------
    protected void fireSpinner() {
        for (SpinnerListener listener : spinnerListeners)
            listener.changeBookCount(event);
    }

    //--------------------------------------------------------------------------------------------------------------
    public String getCount() {
        return textField.getText();
    }

    //--------------------------------------------------------------------------------------------------------------
    public void setCount(String str) {
        textField.setText(str);
    }

    //--------------------------------------------------------------------------------------------------------------
    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }
    //--------------------------------------------------------------------------------------------------------------

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Spinner (для слушателя изменения размера компонента)
    class Resize extends ComponentAdapter {
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void componentResized(ComponentEvent e) {
            setSize(getSize());
            border.setBounds(0, 0, getWidth(), getHeight());
            topButton.setBounds((int) (getWidth() * 0.65), 2, (int) (getWidth() * 0.35), getHeight() / 2);
            bottomButton.setBounds((int) (getWidth() * 0.65), getHeight() / 2, (int) (getWidth() * 0.35), (int) (getHeight() * 0.5));
            textField.setBounds((int) (getWidth() * 0.15), (int) (getHeight() * 0.15), (int) (getWidth() * 0.47), (int) (getHeight() * 0.7));
            textField.setFont(new Font("", Font.PLAIN, (int) (getHeight() * 0.5)));
            backGround.setBounds(0, 0, getWidth(), getHeight());
            shape = new RoundRectangle2D.Double(2, 2, getWidth() - 4, getHeight() - 4, 30, 30);
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса Resize

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Spinner (задний фон)
    class BackGround extends JComponent {
        //----------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2D.setColor(Color.white);
            g2D.fill(shape); // Общий фон, прокладка между основным окном
            g2D.dispose();
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса BackGround

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Spinner (рамка)
    class Border extends JComponent {
        //----------------------------------------------------------------------------------------------------------
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

            g2D.setColor(Color.GRAY);
            g2D.setStroke(new BasicStroke(3));
            g2D.draw(shape); // Общий контур

            int x = (int) (getWidth() * 0.65);
            int y = getHeight() / 2;

            g2D.drawLine(x, 4, x, getHeight() - 4);                           // Вертикальная линия
            g2D.drawLine(x, y, getWidth() - 4, y);                            // Горизонтальная линия
            g2D.dispose();
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса Border

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Spinner (верхняя кнопка)
    class TopButton extends JComponent {
        //----------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

            // Закраска нижнего левого треугольника-----------------------------------------------------------------
            g2D.setColor(topButtonColor);
            g2D.fillPolygon(new int[]{1, 0, getWidth()}, new int[]{0, getHeight(), getHeight()}, 3);

            // Закраска арки кнопки---------------------------------------------------------------------------------
            Path2D path = new Path2D.Double();                    // арка кнопки
            path.moveTo(0, 0);                                    // начальная точка

            // три последующие точи---------------------------------------------------------------------------------
            path.curveTo(
                    getWidth() * 0.15, 0,
                    getWidth(), -getHeight() * 0.5,
                    getWidth(), getHeight());
            g2D.fill(path);                                       // отрисовка

            // Закраска треугольника--------------------------------------------------------------------------------
            g2D.setColor(Color.BLACK);
            g2D.fillPolygon(
                    new int[]{(int) (getWidth() * 0.35), (int) (getWidth() * 0.6), (int) (getWidth() * 0.47)},
                    new int[]{(int) (getHeight() * 0.65), (int) (getHeight() * 0.65), (int) (getHeight() * 0.35)}, 3);

            // Подсветка кнопки-------------------------------------------------------------------------------------
            g2D.setColor(contourIlluminationUp);
            Path2D path1 = new Path2D.Double();                   // арка подсветки
            path1.moveTo(getWidth() * 0.1, getHeight() * 0.112);  // начальная точка
            // три последующие точи
            path1.curveTo(
                    getWidth() * 0.16, getHeight() * 0.12,
                    getWidth() * 1.1, -getHeight() * 0.2,
                    getWidth() * 0.85, getHeight());
            g2D.draw(path1);                                      // отрисовка

            // нижний контур подсветки------------------------------------------------------------------------------
            g2D.drawPolyline(
                    new int[]{(int) (getWidth() * 0.1), (int) (getWidth() * 0.1), (int) (getWidth() * 0.85)},
                    new int[]{(int) (getHeight() * 0.1), (int) (getHeight() * 0.85), (int) (getHeight() * 0.85)}, 3);

            // треугольник подсветки--------------------------------------------------------------------------------
            g2D.setColor(triangleIlluminationUp);
            g2D.drawPolygon(
                    new int[]{(int) (getWidth() * 0.35), (int) (getWidth() * 0.6), (int) (getWidth() * 0.47)},
                    new int[]{(int) (getHeight() * 0.65), (int) (getHeight() * 0.65), (int) (getHeight() * 0.35)}, 3);

            g2D.dispose();
            //------------------------------------------------------------------------------------------------------
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Spinner (нижняя кнопка)
    class BottomButton extends JComponent {
        //----------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

            // Закраска верхнего левого треугольника-----------------------------------------------------------------
            g2D.setColor(bottomButtonColor);
            g2D.fillPolygon(new int[]{0, getWidth(), 0}, new int[]{getHeight(), 0, 0}, 3);

            // Закраска арки кнопки---------------------------------------------------------------------------------
            Path2D path = new Path2D.Double();                    // арка кнопки
            path.moveTo(0, getHeight());                          // начальная точка
            // три последующие точи
            path.curveTo(
                    getWidth() * 0.16, getHeight(),
                    getWidth(), getHeight() * 1.5,
                    getWidth(), 0);
            g2D.fill(path);                                       // отрисовка

            // Закраска треугольника--------------------------------------------------------------------------------
            g2D.setColor(Color.BLACK);
            g2D.fillPolygon(
                    new int[]{(int) (getWidth() * 0.35), (int) (getWidth() * 0.6), (int) (getWidth() * 0.47)},
                    new int[]{(int) (getHeight() * 0.35), (int) (getHeight() * 0.35), (int) (getHeight() * 0.65)}, 3);

            // Подсветка кнопки-------------------------------------------------------------------------------------
            g2D.setColor(contourIlluminationDown);
            Path2D path1 = new Path2D.Double();                 // арка подсветки
            path1.moveTo(getWidth() * 0.1, getHeight() * 0.9);  // начальная точка
            // три последующие точи
            path1.curveTo(
                    getWidth() * 0.15, getHeight() * 0.8,
                    getWidth(), getHeight() * 1.2,
                    getWidth() * 0.88, getHeight() * 0.1);
            g2D.draw(path1);                                    // отрисовка

            // верхний контур подсветки------------------------------------------------------------------------------
            g2D.drawPolyline(
                    new int[]{(int) (getWidth() * 0.1), (int) (getWidth() * 0.1), (int) (getWidth() * 0.85)},
                    new int[]{(int) (getHeight() * 0.85), (int) (getHeight() * 0.1), (int) (getHeight() * 0.1)}, 3);

            // треугольник подсветки--------------------------------------------------------------------------------
            g2D.setColor(triangleIlluminationDown);
            g2D.drawPolygon(
                    new int[]{(int) (getWidth() * 0.35), (int) (getWidth() * 0.6), (int) (getWidth() * 0.47)},
                    new int[]{(int) (getHeight() * 0.35), (int) (getHeight() * 0.35), (int) (getHeight() * 0.65)}, 3);

            g2D.dispose();
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Spinner (слушатели для мыши)
    class ForMouseInSpinner extends MouseAdapter {
        private CountUpThread countUpThread;
        private CountDownThread countDownThread;

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            // Для изменения числа и цвета кнопки при нажатии
            if (e.getComponent().getClass().equals(TopButton.class)) {
                if (textField.getText().matches("-*[0-9]+")) {
                    int count = Integer.parseInt(textField.getText());
                    count++;
                    textField.setText(String.valueOf(count));
                }
                topButtonColor = Color.DARK_GRAY;
                repaint();
                fireSpinner();
                countUpThread = new CountUpThread();
                countUpThread.start();
            }
            if (e.getComponent().getClass().equals(BottomButton.class)) {
                if (textField.getText().matches("-*[0-9]+")) {
                    int count = Integer.parseInt(textField.getText());
                    count--;
                    textField.setText(String.valueOf(count));
                }
                bottomButtonColor = Color.DARK_GRAY;
                repaint();
                fireSpinner();
                countDownThread = new CountDownThread();
                countDownThread.start();
            }
        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseReleased(MouseEvent e) {
            // Для возврата первоначального цвета при отпускании кнопки
            if (e.getComponent().getClass().equals(TopButton.class)) {
                countUpThread.interrupt();
                topButtonColor = new Color(238, 238, 238);
                repaint();
            }
            if (e.getComponent().getClass().equals(BottomButton.class)) {
                countDownThread.interrupt();
                bottomButtonColor = new Color(238, 238, 238);
                repaint();
            }
        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseEntered(MouseEvent e) {
            // Для подсвечивания при наведении на кнопку
            if (e.getComponent().getClass().equals(TopButton.class)) {
                triangleIlluminationUp = Color.CYAN;
                contourIlluminationUp = Color.CYAN;
                repaint();
            }
            if (e.getComponent().getClass().equals(BottomButton.class)) {
                triangleIlluminationDown = Color.CYAN;
                contourIlluminationDown = Color.CYAN;
                repaint();
            }
        }

        //----------------------------------------------------------------------------------------------------------
        @Override
        public void mouseExited(MouseEvent e) {
            // Для возврата обычного цвета при выходе курсора с кнопки
            if (e.getComponent().getClass().equals(TopButton.class)) {
                triangleIlluminationUp = Color.BLACK;
                contourIlluminationUp = new Color(238, 238, 238);
                repaint();
            }
            if (e.getComponent().getClass().equals(BottomButton.class)) {
                triangleIlluminationDown = Color.BLACK;
                contourIlluminationDown = new Color(238, 238, 238);
                repaint();
            }
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса ForMouseInSpinner

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Spinner (поток для увеличения числа при удержании кнопки мышы)
    class CountUpThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    return;
                }
                if (textField.getText().matches("-*[0-9]+")) {
                    int count = Integer.parseInt(textField.getText());
                    count++;
                    textField.setText(String.valueOf(count));
                    fireSpinner();
                }
            }
        }
    } // Конец класса CountUpThread

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Spinner (поток для уменьшения числа при удержании кнопки мышы)
    class CountDownThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    return;
                }
                if (textField.getText().matches("-*[0-9]+")) {
                    int count = Integer.parseInt(textField.getText());
                    count--;
                    textField.setText(String.valueOf(count));
                    fireSpinner();
                }
            }
        }
    } // Конец класса CountDownThread

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Spinner (событие изменения числа в Spinner)
    class ChangeBookCountEvent extends EventObject {
        public ChangeBookCountEvent(Object source) {
            super(source);
        }
    } // Конец класса ChangeBookCountEvent

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний интерфейс Spinner (слушатель)
    interface SpinnerListener extends EventListener {
        void changeBookCount(ChangeBookCountEvent e);
    } // Конец интерфейса SpinnerListener
} // Конец класса Spinner
