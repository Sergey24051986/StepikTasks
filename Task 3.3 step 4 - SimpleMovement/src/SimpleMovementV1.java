import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// С использованием JComponent
public class SimpleMovementV1 extends JFrame {
    private static final int width = 1015;
    private static final int height = 990;

    public SimpleMovementV1() throws IOException{
        super("SimpleMovementV1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLayout(null);

        Image smile = new Image();
        add(smile);

        ImageMove smileMove = new ImageMove(smile);
        addKeyListener(smileMove);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new SimpleMovementV1();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static class Image extends JComponent {
        private final BufferedImage img;
        private int x = 0;
        private int y = 0;

        public Image() throws IOException {
            img = ImageIO.read(new File("Task 3.3 step 4 - SimpleMovement/Image/Smile.png"));
            setSize(img.getWidth(), img.getHeight());
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }

    static class ImageMove extends KeyAdapter {
        private final Image img;

        ImageMove(Image image) {
            img = image;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 37:
                    img.x = img.x - img.getWidth() < 0 ? 0 : img.x - 50;
                    break;
                case 38:
                    img.y = img.y - img.getHeight() < 0 ? 0 : img.y - 50;
                    break;
                case 39:
                    img.x = img.x + img.getWidth() > width - img.getWidth() ? img.x : img.x + 50;
                    break;
                case 40:
                    img.y = img.y + img.getHeight() > height - img.getHeight() ? img.y : img.y + 50;
                    break;
            }
            img.setBounds(img.x, img.y, img.getWidth(), img.getHeight());
        }
    }
}
