import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.awt.image.*;

public class ChoosingCustomPermission {
    public static String showSelectedItem(String... args) {
        JRadioButton[] button = new JRadioButton[args.length];
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < args.length; i++) {
            button[i] = new JRadioButton(args[i]);
        }

        for (int i = 0; i < args.length; i++) {
            buttonGroup.add(button[i]);
            panel.add(button[i]);
        }
        JOptionPane.showMessageDialog(null, panel);

        String result = "";
        Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton b = buttons.nextElement();
            if (b.isSelected()) result = b.getText();
        }
        return result;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String resolution = ChoosingCustomPermission.showSelectedItem("800x600", "1024x768", "1200x600", "1280x1024", "1680x1050", "1920x1080");
            int width = Integer.parseInt(resolution.split("x")[0]);
            int height = Integer.parseInt(resolution.split("x")[1]);

            BufferedImage flowers = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            try {
                flowers.createGraphics().drawImage(ImageIO.read(new File("task3_05/step7_choosing_custom_permission/Image/flowers.jpg")), 0, 0, width, height, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);

            JLabel background = new JLabel(new ImageIcon(flowers));
            frame.add(background);

            frame.setVisible(true);
        });

    }
}
