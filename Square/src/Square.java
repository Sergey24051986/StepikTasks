import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Square extends JFrame {

    private static Picture picture;

    public Square() throws IOException {
        super("Square");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        addComponentListener(new Resizes());

        picture = new Picture();
        add(picture);

        MoveAroundSquare moveAroundSquare = new MoveAroundSquare();

        Timer timer = new Timer(1,moveAroundSquare);
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Square();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    class MoveAroundSquare implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            picture.setBounds(picture.getPX(), picture.getPY(), picture.getWidth(), picture.getHeight());
            if(picture.getPY() <= 1) picture.setPX(picture.getPX() + 6);
            if(picture.getPX() >= getWidth() - picture.getWidth()) picture.setPY(picture.getPY() + 6);
            if(picture.getPY() >= getHeight() - picture.getHeight()) picture.setPX(picture.getPX() - 6);
            if(picture.getPX() <= 1) picture.setPY(picture.getPY() - 6);
        }
    }

    static class Resizes extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            picture.setPX(0);
            picture.setPY(0);
        }
    }
}
