import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EasyMovement1 extends JFrame {
    private static final int width = 1015;
    private static final int height = 990;

    public EasyMovement1() throws IOException{
        super("EasyMovement1");
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
                new EasyMovement1();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static class Image extends JComponent {
        private final BufferedImage img;
        private int x = 0;
        private int y = 0;

        Image() throws IOException {
            img = ImageIO.read(new File("EasyMovement/Image/Smile.png"));
            setSize(img.getWidth(), img.getHeight());
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
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
                    img.setX(img.getX() - img.getWidth() < 0 ? 0 : img.getX() - 50);
                    break;
                case 38:
                    img.setY(img.getY() - img.getHeight() < 0 ? 0 : img.getY() - 50);
                    break;
                case 39:
                    img.setX(img.getX() + img.getWidth() > width - img.getWidth() ? img.getX() : img.getX() + 50);
                    break;
                case 40:
                    img.setY(img.getY() + img.getHeight() > height - img.getHeight() ? img.getY() : img.getY() + 50);
                    break;
            }
            img.setBounds(img.getX(), img.getY(), img.getWidth(), img.getHeight());
        }
    }
}
