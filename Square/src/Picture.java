import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture extends JComponent {
    private static BufferedImage img;
    private int pX = 0,  pY = 0;

    public Picture() throws IOException {
        paintOval();
        img = ImageIO.read(new File("Square/Image/Circle.png"));
        setSize(img.getWidth(), img.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public int getPX() {
        return pX;
    }

    public int getPY() {
        return pY;
    }

    public void setPX(int newRX) {
        pX = newRX;
    }

    public void setPY(int newRY) {
        pY = newRY;
    }

    // Метод для рисования изображения в img
    public static void paintOval() throws IOException {
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

        // Запись в файл в виде круга
        //ImageIO.write(circle, "png", new File("Square/Image/Circle.png"));
    }
}
