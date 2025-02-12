import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture extends JComponent {
    private BufferedImage img;
    private int pX = 0,  pY = 0;
    private static Color color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));

    public Picture() throws IOException {
        paintDVD();
        setSize(img.getWidth(), img.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            paintDVD();
            img = ImageIO.read(new File("DVD/Image/DVD.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(img, 0, 0, null);
    }

    public int getPX() {
        return pX;
    }

    public int getPY() {
        return pY;
    }

    public Color getColor() {
        return color;
    }

    public void setPX(int newRX) {
        pX = newRX;
    }

    public void setPY(int newRY) {
        pY = newRY;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    // Метод для рисования изображения в img
    public void paintDVD() throws IOException {
        BufferedImage dvd = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dvd.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setBackground(Color.black);
        g.clearRect(0, 0, 512, 512);

        g.setColor(color);
        g.fillPolygon(new int[]{402, 323, 342, 387, 375, 410}, new int[]{269, 269, 189, 189, 241, 231}, 6);
        g.fillPolygon(new int[]{114, 35, 54, 99, 87, 130}, new int[]{269, 269, 189, 189, 241, 231}, 6);

        g.drawLine(63, 153, 234, 153);
        g.drawLine(234, 153, 258, 228);
        g.drawLine(258, 228, 320, 153);
        g.drawLine(320, 153, 423, 153);

        Path2D path1 = new Path2D.Double();// Правая D, внешний полукруг
        path1.moveTo(423, 153);
        path1.curveTo(518, 150, 518, 273, 402, 269);
        g.fill(path1);
        g.draw(path1);

        g.drawLine(402, 269, 323, 269);
        g.drawLine(323, 269, 342, 189);
        g.drawLine(342, 189, 387, 189);
        g.drawLine(387, 189, 375, 241);

        Path2D path2 = new Path2D.Double();
        path2.moveTo(375, 241);
        path2.curveTo(455, 250, 455, 180, 414, 179);

        g.drawLine(345, 179, 241, 296);
        g.drawLine(241, 296, 201, 189);

        Path2D path3 = new Path2D.Double();// Левая D, внешний полукруг
        path3.moveTo(135, 153);
        path3.curveTo(230, 150, 230, 273, 114, 269);
        g.fill(path3);
        g.draw(path3);

        g.drawLine(114, 269, 35, 269);
        g.drawLine(35, 269, 54, 189);
        g.drawLine(54, 189, 99, 189);
        g.drawLine(99, 189, 87, 241);

        Path2D path4 = new Path2D.Double();
        path4.moveTo(87, 241);
        path4.curveTo(167, 250, 167, 180, 126, 179);

        g.drawLine(57, 179, 63, 153);
        g.fillOval(20, 297, 444, 60);
        g.drawOval(20, 297, 444, 60);

        g.fillPolygon(new int[]{424, 420, 345, 241, 201, 135, 234, 258, 320, 423}, new int[]{153, 179, 179, 296, 189, 153, 153, 228, 153, 153}, 10);
        g.fillPolygon(new int[]{63, 136, 140, 57, 63}, new int[]{153, 153, 179, 179, 153}, 5);

        g.setColor(Color.black);
        g.fill(path2);
        g.fill(path4);
        g.drawLine(130, 179, 57, 179);
        g.drawLine(418, 179, 347, 179);
        g.setFont(new Font("V I D E O", Font.BOLD, 50));
        g.drawString("V I D E O", 140, 345);
        g.dispose();

        // Запись в файл
        BufferedImage dvdCopy = new BufferedImage(480 , 225, BufferedImage.TYPE_INT_ARGB);
        Graphics2D dvdCopyG2D = dvdCopy.createGraphics();
        dvdCopyG2D.drawImage(dvd, -15, -145, null);
        ImageIO.write(dvdCopy, "png", new File("DVD/Image/DVD.png"));

        img = ImageIO.read(new File("DVD/Image/DVD.png"));
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("DVD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Picture dvd = new Picture();
        frame.add(dvd);;
        frame.setSize(dvd.getSize());
        frame.setVisible(true);
    }
}
