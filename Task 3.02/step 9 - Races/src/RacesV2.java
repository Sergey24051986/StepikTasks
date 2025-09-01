import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.State.*;

// Анимация с помощью Thread
public class RacesV2 extends JFrame implements Runnable {

    private final List<Car> carList = new ArrayList<>();
    protected final Thread races;
    private static int count = 3;
    private static String info = "";

    public RacesV2() throws IOException {
        super("Task 3.2 step 9 - RacesV2");
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

        JButton startRace = new JButton("Stat");
        startRace.setBounds(getWidth() - 120, 0, 100, 50);
        add(startRace);

        startRace.addActionListener(new StartButtonAction());

        InfoWindow infoWindow = new InfoWindow();
        infoWindow.setBounds(getWidth() / 2 - infoWindow.getWidth() / 2, getHeight() / 2 - infoWindow.getHeight() / 2, infoWindow.getWidth(), infoWindow.getHeight());
        add(infoWindow);

        races = new Thread(RacesV2.this);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new RacesV2();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            if (count >= 0) {
                if (count == 0) info = "                GO!";
                else info = "                 " + count;
                count--;
            } else {
                info = "";
                boolean flag = true;

                for (Car car: carList) {
                    if (car.xCar >= getWidth() - car.getWidth() - 15) {
                        info = "The winner is " + car.name;
                        flag = false;
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
            repaint();

            try {
                if (info.isEmpty()) Thread.sleep(5);
                else Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // Событие нажатие кнопки
    class StartButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (info.contains("winner") | races.getState() == NEW) {
                Collections.shuffle(carList);
                int yPosition = 0;
                count = 3;

                for (Car car : carList) {
                    add(car);
                    car.setBounds(0, yPosition, car.getWidth(), car.getHeight());
                    repaint();
                    car.yCar = yPosition;
                    yPosition += 200;
                }
                for (Car car : carList) {
                    car.xCar = 0;
                }
                if (!races.isAlive()) races.start();
            }
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
}