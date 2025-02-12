import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DVD extends JFrame {

    private static Picture picture;
    private static boolean moveUp = false;
    private static boolean moveDown = true;
    private static boolean moveRight = true;
    private static boolean moveLeft = false;
    private static final int xSpeed = 4;
    private static final int ySpeed = 8;

    public DVD() throws IOException {
        super("DVD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        getContentPane().setBackground(Color.black);
        setLayout(null);

        picture = new Picture();
        add(picture);

        Move move = new Move();
        Timer timer = new Timer(1,move);
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new DVD();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    class Move implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            picture.setBounds(picture.getPX(), picture.getPY(), picture.getWidth(), picture.getHeight());

            // Для записи кадров для gif
//            try {
//                new ImagesRecord(picture);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }

            // Движение вниз вправо
            if(moveDown & moveRight) {
                picture.setPX(picture.getPX() + xSpeed);
                picture.setPY(picture.getPY() + ySpeed);
            }
            //Движение вверх вправо
            if(moveRight & moveUp) {
                picture.setPX(picture.getPX() + xSpeed);
                picture.setPY(picture.getPY() - ySpeed);
            }
            // Движение влево вверх
            if(moveLeft & moveUp) {
                picture.setPX(picture.getPX() - xSpeed);
                picture.setPY(picture.getPY() - ySpeed);
            }
            // Движение влево вниз
            if(moveDown & moveLeft) {
                picture.setPX(picture.getPX() - xSpeed);
                picture.setPY(picture.getPY() + ySpeed);
            }
            // Вправо
            // Если вниз и вправо до низа
            if(picture.getPY() >= getHeight() - picture.getHeight() & moveRight & moveDown) {
                moveDown = false;
                moveUp = true;
                moveLeft = false;
                picture.setColor(new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10)));
                repaint();
            }
            // Если вниз и вправо до правого края
            if(picture.getPX() >= getWidth() - picture.getWidth() & moveRight & moveDown) {
                moveUp = false;
                moveLeft = true;
                moveRight = false;
                picture.setColor(new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10)));
                repaint();
            }
            // Если вверх и вправо до правого края
            if(picture.getPX() >= getWidth() - picture.getWidth() & moveUp & moveRight) {
                moveLeft = true;
                moveRight = false;
                moveDown = false;
                picture.setColor(new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10)));
                repaint();
            }
            // Если вверх и вправо до верха
            if(picture.getPY() <= 0 & moveUp & moveRight) {
                moveUp = false;
                moveDown = true;
                moveLeft = false;
                picture.setColor(new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10)));
                repaint();
            }
            // Влево
            // Если вниз и влево до низа
            if(picture.getPY() >= getHeight() - picture.getHeight() & moveDown & moveLeft) {
                moveRight = false;
                moveUp = true;
                moveDown = false;
                picture.setColor(new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10)));
                repaint();
            }
            // Если вниз и влево до левого края
            if(picture.getPX() <= 0 & moveDown & moveLeft) {
                moveRight = true;
                moveUp = false;
                moveLeft = false;
                picture.setColor(new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10)));
                repaint();
            }
            // Если вверх и влево до верха
            if(picture.getPY() <= 0 & moveUp & moveLeft) {
                moveUp = false;
                moveDown = true;
                moveRight = false;
                picture.setColor(new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10)));
                repaint();
            }
            // Если вверх и влево до левого края
            if(picture.getPX() <= 0 & moveUp & moveLeft) {
                moveLeft = false;
                moveDown = false;
                moveRight = true;
                picture.setColor(new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10)));
                repaint();
            }
        }
    }
}
