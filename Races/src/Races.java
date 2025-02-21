import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Races extends JFrame {

    private final List<Car> carList = new ArrayList<>();
    private int count = 3;
    private final InfoWindow info;
    private final Timer moveCarsTimer;
    private final Timer countDownTimer;

    public Races() throws IOException {
        super("Races");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        info = new InfoWindow();
        info.setBounds(getWidth() / 2 - info.getWidth() / 2, getHeight() / 2 - info.getHeight() / 2, info.getWidth(), info.getHeight());


        JButton startRace = new JButton("Stat");
        startRace.setBounds(getWidth() - 120, 0, 100, 50);
        startRace.addActionListener(new StartButtonAction());

        moveCarsTimer = new Timer(1, new MoveCars());
        countDownTimer = new Timer(1000, new CountDownAction());

        add(startRace);
        add(info);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Races();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Событие нажатия кнопки
    class StartButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Collections.shuffle(carList);
            int yPosition = 0;

            for (Car car: carList) {
                add(car);
                car.setBounds(0,yPosition,car.getWidth(),car.getHeight());
                repaint();
                car.setYCar(yPosition);
                yPosition += 200;
            }

            for (Car car: carList) {
                car.setXCar(0);
                count = 3;
            }
            countDownTimer.start();
        }
    }

    // Событие - отсчет времени до старта!
    class CountDownAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (count == 0) {
                info.setInfo("                GO!");
                moveCarsTimer.start();
            } else if(count < 0) {
                info.setInfo("");
                countDownTimer.stop();
            } else {
                info.setInfo("                 " + count);
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
                if (car.getXCar() >= getWidth() - car.getWidth()) {
                    flag = false;
                    info.setInfo("The winner is " + car.getName());
                    repaint();
                    moveCarsTimer.stop();
                }
            }
            if (flag) {
                for (Car car : carList) {
                    int speed = new Random().nextInt(5);
                    car.setXCar(car.getXCar() + speed);
                    car.setBounds(car.getXCar(), car.getYCar(), car.getWidth(), car.getHeight());
                }
            }
        }
    }

}



