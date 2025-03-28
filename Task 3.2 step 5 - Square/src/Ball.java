import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ball extends JComponent {


    public Ball() throws IOException {
        BufferedImage circle = new BufferedImage(120,140,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = circle.createGraphics();
        // Улучшение текстур
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // Рисование
        g2D.setColor(Color.ORANGE);
        g2D.fillOval(0, 0, 100, 100);
        g2D.setColor(Color.black);
        g2D.drawOval(0, 0, 100, 100);

        // Запись в файл
        ImageIO.write(circle, "png", new File("Task 3.2 step 5 - Square/Image/Circle.png"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Ball();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
