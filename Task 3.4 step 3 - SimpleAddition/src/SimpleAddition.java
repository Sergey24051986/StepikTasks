import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SimpleAddition extends JFrame {
    private boolean isEmpty = true;
    private boolean isBurst = false;
    private final JComponent infoWindow;

    public SimpleAddition(){
        super("Simple Addition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        infoWindow = new InfoWindow();
        infoWindow.setBounds(getWidth() - infoWindow.getWidth() - 20, getHeight() - infoWindow.getHeight() - 45, infoWindow.getWidth(), infoWindow.getHeight());
        add(infoWindow);

        addMouseListener(new Put());
        addKeyListener(new Mode());
        addComponentListener(new Resize());

        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleAddition::new);
    }

    class Component extends JComponent implements Runnable {
        private int x = 100;
        private int y = 100;
        private int width = 0;
        private int height = 0;
        private int indicator = 0;
        private final Color color;
        private final boolean isEmpty;
        private final boolean isBurst;

        public Component(boolean isEmpty, boolean isBurst) {
            setSize(200, 200);
            color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
            this.isEmpty = isEmpty;
            this.isBurst = isBurst;
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setStroke(new BasicStroke(2));

            if (isEmpty) { // Только контур круга
                g2D.setColor(color);
                g2D.drawOval(x, y, width, height);
            } else { // Заполненный круг
                g2D.setColor(color);
                g2D.fillOval(x, y, width, height);
            }
        }

        @Override
        public void run() {
            while (true) {
                if (!isBurst) {
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
                SimpleAddition.this.repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class Put extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1) {
                Component circle = new Component(isEmpty, isBurst);
                circle.setBounds(e.getX() - 110, e.getY() - 130, 200, 200);
                SimpleAddition.this.getLayeredPane().add(circle, 0);
                Thread th = new Thread(circle);
                th.start();
            }
        }
    }

    class Mode extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // Смена режима: "Пустые шары" или "Закрашенные шары"
            if (e.getKeyCode() == 49 | e.getKeyCode() == 97) {
                isEmpty = !isEmpty;
            }
            // Смена режима: "Надуваются и сдуваются" или "Лопаются"
            if (e.getKeyCode() == 50 | e.getKeyCode() == 98) {
                isBurst = !isBurst;
            }
        }
    }
    class InfoWindow extends JComponent {
        private final int width = 550;
        private final int height = 150;

        public InfoWindow() {
            setSize(width, height);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2D.setFont(new Font("", Font.ITALIC | Font.BOLD, 15));
            String infoMode1 = isEmpty ? "Пустые шары" : "Закрашенные шары";
            String infoMode2 = !isBurst ? "Надуваются и сдуваются" : "Лопаются";
            g2D.setColor(Color.black);
            g2D.drawString("Режим: ", 10, 20);
            g2D.drawString("Режим: ", 10, 40);
            g2D.drawString("Управление: ", 10, 80);
            g2D.drawString("Цифра 1 - Смена режима: \"Пустые шары\" или \"Закрашенные шары\"", 10, 100);
            g2D.drawString("Цифра 2 - Смена режима: \"Надуваются и сдуваются\" или \"Лопаются\"", 10, 120);
            g2D.drawString("Левая кнопка мыши - добавить компонент", 10, 140);
            g2D.drawRect(0, 0, width - 1, height - 1);
            g2D.setColor(Color.ORANGE);
            g2D.drawString(infoMode1, 70, 20);
            g2D.drawString(infoMode2, 70, 40);
            SimpleAddition.this.repaint();
        }
    }

    class Resize extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            infoWindow.setBounds(getWidth() - infoWindow.getWidth() - 20, getHeight() - infoWindow.getHeight() - 45, infoWindow.getWidth(), infoWindow.getHeight());
        }
    }
}
