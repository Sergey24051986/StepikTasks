import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// С использованием JLabel
public class SimpleMovementV2 extends JFrame{
    private static final int width = 1015;
    private static final int height = 990;
    private static int x = 0;
    private static int y = 0;
    private static JLabel img;

    public SimpleMovementV2() {
        super("SimpleMovementV2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLayout(null);

        img = new JLabel(new ImageIcon("Task 3.3 step 4 - SimpleMovement/Image/Smile.png"));
        img.setBounds(x, y, 50, 50);
        add(img);

        ImageMove smileMove = new ImageMove();
        addKeyListener(smileMove);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleMovementV2::new);
    }

    static class ImageMove extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 37:
                    x -= x - 50 < 0 ? 0 : 50;
                    break;
                case 38:
                    y -= y - 50 < 0 ? 0 : 50;
                    break;
                case 39:
                    x += x + 50 > width - 50 ? 0 : 50;
                    break;
                case 40:
                    y += y + 50 > height - 50 ? 0 : 50;
                    break;
            }
            img.setBounds(x, y, img.getWidth(), img.getHeight());
        }
    }
}


