import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DirectLine extends JFrame {

    private static Picture picture;

    public DirectLine() throws IOException {
        super("DirectLine");
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        setLayout(null);

        picture = new Picture();
        add(picture);

        MoveDownDiagonally moveDownDiagonally = new MoveDownDiagonally();

        Timer timer = new Timer(10,moveDownDiagonally);
        timer.start();

        setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            try {
                new DirectLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    class MoveDownDiagonally implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            picture.setBounds(picture.getPX(), picture.getPY(), picture.getWidth(), picture.getHeight());
            picture.setPX(picture.getPX() + 2);
            picture.setPY(picture.getPY() + 2);

            if(picture.getPX() > getWidth() | picture.getPY() > getHeight()) {
                picture.setPX(-200);
                picture.setPY(-200);
            }
        }
    }
}
