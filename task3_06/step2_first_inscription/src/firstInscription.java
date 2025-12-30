import javax.swing.*;
import java.awt.*;

public class firstInscription extends JFrame {

    public firstInscription() {
        super("firstInscription");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(null);

        JLabel label = new JLabel("Моя первая надпись!");
        label.setFont(new Font("", Font.ITALIC, 50));
        label.setBounds(800 / 2 - 500 / 2, 500 / 2 - 50, 500, 50);

        add(label);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(firstInscription::new);
    }
}
