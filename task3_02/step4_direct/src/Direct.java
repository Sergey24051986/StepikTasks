import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Direct extends JFrame implements Runnable {
    private final BufferedImage img;
    private double x = -200;
    private double y = -200;
    private final Picture picture;
    public Direct() throws IOException {
        super("Task 3.2 step 4 - Direct");
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);

        img = ImageIO.read(new File("task3_02/step4_direct/Image/Rocket.png"));
        picture = new Picture();
        add(picture);

        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Direct direct;
            try {
                direct = new Direct();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Thread th = new Thread(direct);
            th.start();
        });

    }

    public void run () {
        while (true) {
            picture.setBounds((int)x, (int)y, picture.getWidth(), picture.getHeight());
            x += 0.3;
            y += 0.3;

            if (x > getWidth() | y > getHeight()) {
               x = -200;
               y = -200;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.print(e);
            }
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
