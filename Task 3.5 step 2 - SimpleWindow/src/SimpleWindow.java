import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SimpleWindow extends JFrame {
    public SimpleWindow() {
        super("SimpleWindow");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setOpacity(0.1f);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    JOptionPane.showMessageDialog(null, "Серёга");
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(SimpleWindow::new);
    }
}
