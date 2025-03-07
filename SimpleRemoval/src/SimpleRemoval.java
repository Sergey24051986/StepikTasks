import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class SimpleRemoval extends JFrame{
    int layout = 0;
    int mode = 0;

    SimpleRemoval(){
        super("SimpleRemoval");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        addMouseListener(new Put());

        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleRemoval::new);
    }

    class Component extends JComponent {

        int x = 100, y = 100;
        int a = 0, b = 0;
        int indicator = 0;
        Color color;
        int mode;

        Component(int mode) {
            setSize(200, 200);
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
                g2D.drawOval(x, y, a, b);
            } else { // Заполненный круг
                g2D.setColor(color);
                g2D.fillOval(x, y, a, b);
            }
        }

        class WaveAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*// Лопающиеся
                if (a > 200 | b > 200) {
                    a = 0; b = 0; x = 100; y = 100;
                }
                x--; y--; a+=2; b+=2;
                */
                // Увеличиваются, а потом уменьшаются
                if (indicator > 190) {
                    indicator = 0;
                }

                if (indicator < 95) {
                    x--; y--; a+=2; b+=2;
                } else {
                    a -= 2; b -= 2; x++; y++;
                }
                indicator++;
                SimpleRemoval.this.repaint();
            }
        }
    }

    class Put extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {

            if (e.getButton() == 1) {
                Component info = new Component(mode);
                info.setBounds(e.getX() - 110, e.getY() - 130, 200, 200);
                SimpleRemoval.this.getLayeredPane().add(info, layout);

            }
            if (e.getButton() == 3) {
                mode += mode < 1 ? 1 : -1;
            }

            if (e.getButton() == 2) {
                // Проверка, чтобы не удалял ContentPain
                if (getLayeredPane().getComponentAt(e.getX(), e.getY()).getName() == null) {
                    getLayeredPane().remove(getLayeredPane().getComponentAt(e.getX(), e.getY()));
                }
            }

        }
    }
}
