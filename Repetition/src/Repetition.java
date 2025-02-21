import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Repetition extends JFrame {

    Repetition() throws IOException {
        super("Repetition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        Grass grass = new Grass();
        add(grass);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Repetition();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    static class Grass extends JComponent {
        BufferedImage img;

        Grass() throws IOException {
            img = ImageIO.read(new File("Repetition/Image/Grass.png"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            //BufferedImage save = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
            //Graphics2D g2D = save.createGraphics();
            for (int y = 0; y < getHeight(); y += 50) {
                for (int x = 0; x < getWidth(); x += 50) {
                    g.drawImage(img, x, y, null);
                    //g2D.drawImage(img, x, y, null);
                }
            }
            //Для записи изображения (опционально)
//            try {
//                ImageIO.write(save,"png", new File("Repetition/Image/Repetition.png"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
    }

}
