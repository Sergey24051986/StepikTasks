import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class DVD extends JFrame implements Runnable{

    private static Picture picture;
    private static boolean moveUp = false;
    private static boolean moveDown = true;
    private static boolean moveRight = true;
    private static boolean moveLeft = false;

    public DVD() throws IOException {
        super("Task 3.2 step 6 - DVD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        getContentPane().setBackground(Color.black);
        setLayout(null);

        picture = new Picture();
        add(picture);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DVD dvd;
            try {
                dvd = new DVD();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Thread th = new Thread(dvd);
            th.start();
        });
    }

    @Override
    public void run() {

        while (true) {
            picture.setBounds(picture.getPX(), picture.getPY(), picture.getWidth(), picture.getHeight());
            int xSpeed = 1;
            int ySpeed = 2;

            // Движение вниз вправо
            if (moveDown & moveRight) {
                picture.setPX(picture.getPX() + xSpeed);
                picture.setPY(picture.getPY() + ySpeed);
            }
            //Движение вверх вправо
            if (moveRight & moveUp) {
                picture.setPX(picture.getPX() + xSpeed);
                picture.setPY(picture.getPY() - ySpeed);
            }
            // Движение влево вверх
            if (moveLeft & moveUp) {
                picture.setPX(picture.getPX() - xSpeed);
                picture.setPY(picture.getPY() - ySpeed);
            }
            // Движение влево вниз
            if (moveDown & moveLeft) {
                picture.setPX(picture.getPX() - xSpeed);
                picture.setPY(picture.getPY() + ySpeed);
            }
            // Вправо
            // Если вниз и вправо до низа
            if (picture.getPY() >= getHeight() - picture.getHeight() - 20 & moveRight & moveDown) {
                moveDown = false;
                moveUp = true;
                moveLeft = false;
                picture.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }
            // Если вниз и вправо до правого края
            if (picture.getPX() >= getWidth() - picture.getWidth() - 10 & moveRight & moveDown) {
                moveUp = false;
                moveLeft = true;
                moveRight = false;
                picture.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }
            // Если вверх и вправо до правого края
            if (picture.getPX() >= getWidth() - picture.getWidth() - 10 & moveUp & moveRight) {
                moveLeft = true;
                moveRight = false;
                moveDown = false;
                picture.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }
            // Если вверх и вправо до верха
            if (picture.getPY() <= -10 & moveUp & moveRight) {
                moveUp = false;
                moveDown = true;
                moveLeft = false;
                picture.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }
            // Влево
            // Если вниз и влево до низа
            if (picture.getPY() >= getHeight() - picture.getHeight() - 20 & moveDown & moveLeft) {
                moveRight = false;
                moveUp = true;
                moveDown = false;
                picture.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }
            // Если вниз и влево до левого края
            if (picture.getPX() <= 0 & moveDown & moveLeft) {
                moveRight = true;
                moveUp = false;
                moveLeft = false;
                picture.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }
            // Если вверх и влево до верха
            if (picture.getPY() <= -10 & moveUp & moveLeft) {
                moveUp = false;
                moveDown = true;
                moveRight = false;
                picture.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }
            // Если вверх и влево до левого края
            if (picture.getPX() <= 0 & moveUp & moveLeft) {
                moveLeft = false;
                moveDown = false;
                moveRight = true;
                picture.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
