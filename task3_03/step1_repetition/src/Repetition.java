import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Repetition extends JFrame {
    boolean isSave;

    public Repetition(boolean isSave) throws IOException {
        super("Task 3.3 step 1 - Repetition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLayout(null);
        this.isSave = isSave;
        BufferedImage img = ImageIO.read(new File("task3_03/step1_repetition/Image/Grass.png"));
        BufferedImage save = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < getHeight(); y += 50) {
            for (int x = 0; x < getWidth(); x += 50) {
                Graphics2D g2D = save.createGraphics();
                JLabel grass = new JLabel(new ImageIcon(img));
                grass.setBounds(x, y, 50, 50);
                add(grass);
                g2D.drawImage(img, x, y, null);
            }
        }
        if (isSave) ImageIO.write(save,"png", new File("Task 3.03/step 1 - Repetition/Image/Repetition.png"));
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                try {
                    new Repetition(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        });
    }
}
