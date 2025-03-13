import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class SimpleCursor extends JFrame {

    SimpleCursor() {
        super("Simple Cursor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLayout(null);

        NewWindow newWindow = new NewWindow();
        newWindow.setBounds(350, 150, 200, 50);
        newWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        newWindow.setBorder(new EtchedBorder());
        add(newWindow);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCursor::new);
    }

    static class NewWindow extends JComponent {
        public void paintComponent(Graphics g) {
            g.drawString("Наведи на меня!", 50, 25);
        }
    }
}
