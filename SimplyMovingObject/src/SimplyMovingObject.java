import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SimplyMovingObject extends JFrame{
    private int mode1 = 0;
    private int mode2 = 0;
    private final JComponent infoWindow;

    SimplyMovingObject(){
        super("Simply Moving Object");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        infoWindow = new InfoWindow();
        infoWindow.setBounds(getWidth() - infoWindow.getWidth() - 20, getHeight() - infoWindow.getHeight() - 45, infoWindow.getWidth(), infoWindow.getHeight());
        add(infoWindow);

        addMouseListener(new Put());
        addMouseWheelListener(new Put());
        addMouseMotionListener(new MoveObject());
        addKeyListener(new Mode());
        addComponentListener(new Resize());

        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimplyMovingObject::new);
    }

    class Component extends JComponent {

        private int x = 100, y = 100;
        private int width = 0;
        private int height = 0;
        private int indicator = 0;
        private final Color color;
        private final int mode;
        private int shiftX;
        private int shiftY;

        Component(int mode) {
            setSize(200, 200);
            setName("Component");
            color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
            Timer timer = new Timer(30, new WaveAction());
            timer.start();
            this.mode = mode;

        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setStroke(new BasicStroke(2));

            if (mode == 0) { // Только контур круга
                g2D.setColor(color);
                g2D.drawOval(x, y, width, height);
            } else { // Заполненный круг
                g2D.setColor(color);
                g2D.fillOval(x, y, width, height);
            }
        }

        class WaveAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (mode2 == 0) {
                    // Увеличиваются, а потом уменьшаются
                    if (indicator > 190) {
                        indicator = 0;
                    }

                    if (indicator < 95) {
                        x--;
                        y--;
                        width += 2;
                        height += 2;
                    } else {
                        width -= 2;
                        height -= 2;
                        x++;
                        y++;
                    }
                } else {
                    // Лопающиеся
                    if (indicator > 95) {
                        width = 0;
                        height = 0;
                        x = 100;
                        y = 100;
                        indicator = 0;
                    }
                    x--;
                    y--;
                    width += 2;
                    height += 2;
                }
                indicator++;
                SimplyMovingObject.this.repaint();
            }
        }
    }

    class Put extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            // При нажатии на левую кнопку мыши устанавливает объект
            if (e.getButton() == 1) {
                Component info = new Component(mode1);
                info.setBounds(e.getX() - 110, e.getY() - 130, 200, 200);
                SimplyMovingObject.this.getLayeredPane().add(info, 0);

            }
            // При нажатии на среднюю кнопку мыши удаляет объект
            if (e.getButton() == 2) {
                // Проверка, чтобы не удалял ContentPain
                if (getLayeredPane().getComponentAt(e.getX(), e.getY()).getName().equals("Component")) {
                    getLayeredPane().remove(getLayeredPane().getComponentAt(e.getX(), e.getY()));
                }
            }
            // При нажатии на правую кнопку мыши вычисляет поправку для правильного перемещения объекта
            if (e.getButton() == 3) {
                if (getLayeredPane().getComponentAt(e.getX(), e.getY()).getName().equals("Component")) {
                    Component component = (Component) SimplyMovingObject.this.getLayeredPane().getComponentAt(e.getX(), e.getY());
                    component.shiftX = e.getX() - component.getX();
                    component.shiftY = e.getY() - component.getY();
                }
            }
        }
    }

    class MoveObject extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {
                if (getLayeredPane().getComponentAt(e.getX(), e.getY()).getName().equals("Component")) {
                    Component component = (Component) SimplyMovingObject.this.getLayeredPane().getComponentAt(e.getX(), e.getY());
                    component.setBounds(e.getX() - component.shiftX, e.getY() - component.shiftY, 200, 200);
                    SimplyMovingObject.this.repaint();
                }
            }
        }
    }

    class Mode extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // Отображаются полые шары
            if (e.getKeyCode() == 49) {
                mode1 = 0;
            }
            // Отображаются заполненные шары
            if (e.getKeyCode() == 50) {
                mode1 = 1;
            }
            // Шары надуваются и сдуваются
            if (e.getKeyCode() == 51) {
                mode2 = 0;
            }
            // Шары лопаются
            if (e.getKeyCode() == 52) {
                mode2 = 1;
            }
        }
    }

    class InfoWindow extends JComponent {
        private final int width = 550;
        private final int height = 250;

        InfoWindow() {
            setSize(width, height);
        }

        @Override
        protected void paintComponent(Graphics g) {
            //g.setFont(new Font("", Font.ITALIC, 10));
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2D.setFont(new Font("", Font.ITALIC | Font.BOLD, 15));


            String infoMode1 = mode1 == 0 ? "Пустые шары" : "Закрашенные шары";
            String infoMode2 = mode2 == 0 ? "Надуваются и сдуваются" : "Лопаются";

            g2D.setColor(Color.black);

            g2D.drawString("Режим: ", 10, 20);
            g2D.drawString("Режим: ", 10, 40);
            g2D.drawString("Управление: ", 10, 80);
            g2D.drawString("Кнопка 1 - Режим: \"Пустые шары\"", 10, 100);
            g2D.drawString("Кнопка 2 - Режим: \"Закрашенные шары\"", 10, 120);
            g2D.drawString("Кнопка 3 - Режим: \"Надуваются и сдуваются\"", 10, 140);
            g2D.drawString("Кнопка 4 - Режим: \"Лопаются\"", 10, 160);

            g2D.drawString("Левая кнопка мыши - добавить компонент", 10, 180);
            g2D.drawString("Средняя кнопка мыши (нажатие колесика) - удалить компонент", 10, 200);
            g2D.drawString("Правая кнопка мыши (удержание кнопки) - перемещение компонента", 10, 220);

            g2D.drawRect(0, 0, width - 1, height - 1);

            g2D.setColor(Color.ORANGE);
            g2D.drawString(infoMode1, 70, 20);
            g2D.drawString(infoMode2, 70, 40);

            SimplyMovingObject.this.repaint();
        }
    }

    class Resize extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            infoWindow.setBounds(getWidth() - infoWindow.getWidth() - 20, getHeight() - infoWindow.getHeight() - 45, infoWindow.getWidth(), infoWindow.getHeight());
        }
    }
}
