import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

// Анимация с помощью Timer
public class RacesV1 extends JFrame {

    private final List<Car> carList = new ArrayList<>();
    private int count = 3;
    private final Timer moveCarsTimer;
    private final Timer countDownTimer;
    private static String info = "";

    public RacesV1() throws IOException {
        super("Task 3.2 step 9 - RacesV1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 1000);
        setResizable(false);
        setLayout(null);

        Car astonMartin = new Car("AstonMartin");
        carList.add(astonMartin);
        Car bmw = new Car("BMW");
        carList.add(bmw);
        Car ford = new Car("Ford");
        carList.add(ford);
        Car jaguar = new Car("Jaguar");
        carList.add(jaguar);
        Car porsche = new Car("Porsche");
        carList.add(porsche);

        InfoWindow infoWindow = new InfoWindow();
        infoWindow.setBounds(getWidth() / 2 - infoWindow.getWidth() / 2, getHeight() / 2 - infoWindow.getHeight() / 2, infoWindow.getWidth(), infoWindow.getHeight());

        JButton startRace = new JButton("Stat");
        startRace.setBounds(getWidth() - 120, 0, 100, 50);
        startRace.addActionListener(new StartButtonAction());

        moveCarsTimer = new Timer(1, new MoveCars());
        countDownTimer = new Timer(1000, new CountDownAction());

        add(startRace);
        add(infoWindow);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new RacesV1();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    // Событие нажатие кнопки
    class StartButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!countDownTimer.isRunning() & !moveCarsTimer.isRunning()) {
                Collections.shuffle(carList);
                int yPosition = 0;

                for (Car car : carList) {
                    add(car);
                    car.setBounds(0, yPosition, car.getWidth(), car.getHeight());
                    repaint();
                    car.yCar = yPosition;
                    yPosition += 200;
                }
                for (Car car : carList) {
                    car.xCar = 0;
                    count = 3;
                }
                countDownTimer.start();
            }
        }
    }
    // Событие - отсчет времени до старта!
    class CountDownAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (count == 0) {
                info = "                GO!";
                moveCarsTimer.start();
            } else if(count < 0) {
                info = "";
                countDownTimer.stop();
            } else {
                info = "                 " + count;
            }
            count--;
            repaint();
        }
    }
    // Событие - передвижение машин!
    class MoveCars implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean flag = true;

            for (Car car: carList) {
                if (car.xCar >= getWidth() - car.getWidth() - 15) {
                    info = "The winner is " + car.name;
                    moveCarsTimer.stop();
                    flag = false;
                    repaint();
                }
            }
            if (flag) {
                for (Car car : carList) {
                    int speed = new Random().nextInt(5);
                    car.xCar = car.xCar + speed;
                    car.setBounds(car.xCar, car.yCar, car.getWidth(), car.getHeight());
                }
            }
        }
    }
    // Информационное окно (в нем посередине отображаются цифры и победитель)
    static class InfoWindow extends JComponent {
        public InfoWindow() {
            setSize(500, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(new Font("",Font.BOLD,30));
            g2.drawString(info, 100, 100);
        }
    }
    // Для сознания экземпляров машин
    static class Car extends JComponent {
        private final String name;
        private int xCar = 0;
        private int yCar = 0;
        private final BufferedImage img;

        public Car(String name) throws IOException {
            this.name = name;
            img = ImageIO.read(new File("Task 3.02/step 9 - Races/Image/" + name + ".png"));
            setSize(img.getWidth(),img.getHeight());
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }
}