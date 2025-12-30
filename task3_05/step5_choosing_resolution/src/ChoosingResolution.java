import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ChoosingResolution {

    public static void main(String[] args) throws IOException {
        Object[] options = new String[]{"800x600", "1024x768", "1200x600", "1280x1024", "1680x1050", "1920x1080"};
        String resolution = (String) JOptionPane.showInputDialog(null, "Выберете разрешение", "Выбор разрешения", JOptionPane.PLAIN_MESSAGE, new JOptionPane().getIcon(), options, options[0]);

        int width = Integer.parseInt(resolution.split("x")[0]);
        int height = Integer.parseInt(resolution.split("x")[1]);

        JFrame frame = new JFrame("ChoosingResolution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = img.createGraphics();
        g2D.drawImage(ImageIO.read(new File("task3_05/step5_choosing_resolution/Image/ChoosingResolution.jpg")), 0, 0, width, height, null);

        JLabel label = new JLabel(new ImageIcon(img));
        frame.add(label);

        frame.setVisible(true);
    }
}