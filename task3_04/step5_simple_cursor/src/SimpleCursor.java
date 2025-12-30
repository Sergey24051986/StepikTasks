import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class SimpleCursor extends JFrame {

    public SimpleCursor() {
        super("Simple Cursor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLayout(null);

        JLabel label = new JLabel();
        label.setBounds(350, 150, 200, 50);
        label.setText("         Н а в е д и  на  м е н я !");
        label.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        label.setBorder(new EtchedBorder());
        add(label);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCursor::new);
    }
}
