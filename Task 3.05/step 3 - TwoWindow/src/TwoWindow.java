import javax.swing.*;

public class TwoWindow{
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Напишите свое имя!");
        JOptionPane.showMessageDialog(null, name);
        SwingUtilities.invokeLater(TwoWindow::new);
    }
}
