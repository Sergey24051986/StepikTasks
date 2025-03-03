import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FastMovement extends JFrame{
    private static final int width = 1015;
    private static final int height = 990;
    private static int x = 0;
    private static int y = 0;
    private static JLabel img;

    public FastMovement() {
        super("FastMovement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLayout(null);

        img = new JLabel(new ImageIcon("FastMovement/Image/Smile.png"));
        img.setBounds(x, y, 50, 50);
        add(img);

        ImageMove smileMove = new ImageMove();
        addKeyListener(smileMove);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FastMovement::new);
    }


    static class ImageMove extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int speed = e.isShiftDown() ? 50 * 2 : 50;

            switch (e.getKeyCode()) {
                case 37:
                    x -= x - speed < 0 ? -(width - speed) : speed;
                    break;
                case 38:
                    y -= y - speed < 0 ? -(height - speed) : speed;
                    break;
                case 39:
                    x += x + speed > width - speed ? -(width - speed) : speed;
                    break;
                case 40:
                    y += y + speed > height - speed ? -(height - speed) : speed;
                    break;
            }
            img.setBounds(x, y, img.getWidth(), img.getHeight());
        }
    }
}
