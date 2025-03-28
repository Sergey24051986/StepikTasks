import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Square extends JFrame implements Runnable{

    private final BufferedImage img;
    private static int x = 0;
    private static int y = 1;
    private final Picture picture;

    public Square() throws IOException {
        super("Task 3.2 step 5 - Square");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setSize(800,800);
        setLayout(null);
        addComponentListener(new Resizes());

        img = ImageIO.read(new File("Task 3.2 step 5 - Square/Image/Circle.png"));
        picture = new Picture();
        add(picture);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Square square;
            try {
                square = new Square();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Thread th = new Thread(square);
            th.start();
        });
    }

    @Override
    public void run() {
        while (true) {
            picture.setBounds(x, y, picture.getWidth(), picture.getHeight());
            if (y <= 1) x += 1;
            if (x >= getWidth() - picture.getWidth() + 2) y += 1;
            if (y >= getHeight() - picture.getHeight()) x -= 1;
            if (x <= 1) y -= 1;

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Resizes extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            x = 0;
            y = 1;
        }
    }

    class Picture extends JComponent {
        public Picture() {
            setSize(img.getWidth(), img.getHeight());
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }
}
