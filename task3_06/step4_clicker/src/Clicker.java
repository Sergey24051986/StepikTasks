import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Clicker extends JFrame implements Runnable {

    private static volatile boolean running;
    private static Color color;
    private static boolean moveUp;
    private static boolean moveDown;
    private static boolean moveRight;
    private static boolean moveLeft;
    private static int x;
    private static int y;
    private static int clickCount;
    private static Oval oval;

    //------------------------------------------------------------------------------------------------------------------
    {
        x = 100;
        y = 100;
        clickCount = 0;
        moveUp = false;
        moveDown = true;
        moveRight = true;
        moveLeft = false;
        running = true;
        color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
    }

    //------------------------------------------------------------------------------------------------------------------
    public Clicker() {
        super("Clicker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 100, 1200, 900);
        setLayout(null);

        oval = new Oval();
        oval.setBounds(x, y, 200, 100);

        add(oval);
        addMouseListener(new ForMouseInClicker());

        setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void run() {
        // Движение от края до края с изменением направления
        while (true) {
            while (running) {
                oval.setBounds(x, y, oval.getWidth(), oval.getHeight());
                int xSpeed = 1;
                int ySpeed = 2;

                // Движение вниз вправо
                if (moveDown & moveRight) {
                    x += xSpeed;
                    y += ySpeed;
                }
                //Движение вверх вправо
                if (moveRight & moveUp) {
                    x += xSpeed;
                    y -= ySpeed;
                }
                // Движение влево вверх
                if (moveLeft & moveUp) {
                    x -= xSpeed;
                    y -= ySpeed;
                }
                // Движение влево вниз
                if (moveDown & moveLeft) {
                    x -= xSpeed;
                    y += ySpeed;
                }
                // Вправо
                // Если вниз и вправо до низа
                if (y >= getHeight() - oval.getHeight() - 20 & moveRight & moveDown) {
                    moveDown = false;
                    moveUp = true;
                    moveLeft = false;
                    color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                }
                // Если вниз и вправо до правого края
                if (x >= getWidth() - oval.getWidth() - 10 & moveRight & moveDown) {
                    moveUp = false;
                    moveLeft = true;
                    moveRight = false;
                    color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                }
                // Если вверх и вправо до правого края
                if (x >= getWidth() - oval.getWidth() - 10 & moveUp & moveRight) {
                    moveLeft = true;
                    moveRight = false;
                    moveDown = false;
                    color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                }
                // Если вверх и вправо до верха
                if (y <= -10 & moveUp & moveRight) {
                    moveUp = false;
                    moveDown = true;
                    moveLeft = false;
                    color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                }
                // Влево
                // Если вниз и влево до низа
                if (y >= getHeight() - oval.getHeight() - 20 & moveDown & moveLeft) {
                    moveRight = false;
                    moveUp = true;
                    moveDown = false;
                    color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                }
                // Если вниз и влево до левого края
                if (x <= 0 & moveDown & moveLeft) {
                    moveRight = true;
                    moveUp = false;
                    moveLeft = false;
                    color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                }
                // Если вверх и влево до верха
                if (y <= -10 & moveUp & moveLeft) {
                    moveUp = false;
                    moveDown = true;
                    moveRight = false;
                    color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                }
                // Если вверх и влево до левого края
                if (x <= 0 & moveUp & moveLeft) {
                    moveLeft = false;
                    moveDown = false;
                    moveRight = true;
                    color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                }

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для Clicker (перемещающееся поле)
    class Oval extends JComponent {
        //--------------------------------------------------------------------------------------------------------------
        public Oval() {
            setSize(200, 100);
            addMouseListener(new ForMouseInClicker());

        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2D.setColor(color);
            g2D.fillOval(1, 1, getWidth() - 2, getHeight() - 2);

            g2D.setColor(Color.ORANGE);
            g2D.fillOval(getWidth() / 2 - 15, getHeight() / 2 - 15, 30, 30);

            g2D.setColor(Color.black);
            g2D.drawOval(getWidth() / 2 - 15, getHeight() / 2 - 15, 30, 30);

            int x = 97 - ((int) Math.log10(clickCount) * 4);
            g2D.drawString("" + clickCount, x, 55);
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public boolean contains(int x, int y) {
            return new Ellipse2D.Double(0, 0, getWidth() - 2, getHeight() - 2).contains(x, y);
        }
        //--------------------------------------------------------------------------------------------------------------
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс Clicker (слушатели для мыши)
    class ForMouseInClicker extends MouseAdapter {
        private Color temp;

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1 && e.getComponent().getClass().equals(Oval.class)) {
                clickCount++;
                temp = color;
                color = Color.GRAY;
                Clicker.this.repaint();
            }

        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == 1 && e.getComponent().getClass().equals(Oval.class)) {
                color = temp;
                Clicker.this.repaint();
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseEntered(MouseEvent e) {
            running = !e.getComponent().getClass().equals(Oval.class);
        }
        //--------------------------------------------------------------------------------------------------------------
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Thread(new Clicker()).start();
        });
    }
    //------------------------------------------------------------------------------------------------------------------
}
