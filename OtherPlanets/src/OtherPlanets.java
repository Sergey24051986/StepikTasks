import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OtherPlanets extends JFrame {

    private static int width = 960;
    private static int height = 520;
    private static double widthDifference = 1;
    private static double heightDifference = 1;
    private static JComponent backGround;
    // Размер планет оптимизирован под данные размеры сторон экрана
    private static final int startWidth = 1920;
    private static final int startHeight = 1040;

    OtherPlanets () throws IOException {
        super("OtherPlanets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(null);

        backGround = new BackGround();
        Planet sun = new Planet("Sun", 0, 0);
        Planet mercury = new Planet("Mercury", 110, 55);
        Planet venus = new Planet("Venus", 180, 90);
        Planet earth = new Planet("Earth", 260, 130);
        Planet mars = new Planet("Mars", 330, 165);
        Planet jupiter = new Planet("Jupiter", 410, 205);
        Planet saturn = new Planet("Saturn", 550, 275);
        Planet uranium = new Planet("Uranium", 680, 340);
        Planet neptune = new Planet("Neptune", 760, 380);

        getLayeredPane().add(neptune);
        getLayeredPane().add(uranium);
        getLayeredPane().add(saturn);
        getLayeredPane().add(jupiter);
        getLayeredPane().add(mars);
        getLayeredPane().add(earth);
        getLayeredPane().add(venus);
        getLayeredPane().add(mercury);
        getLayeredPane().add(sun);
        getLayeredPane().setLayer(sun, 1);
        getLayeredPane().add(backGround);

        // Для масштабирования
        addComponentListener(new Resizes());

        ActionListener action = e -> {
            setCoordinatesOfPlanet(mercury, -0.005, 2);
            setCoordinatesOfPlanet(venus, 0.007, 3);
            setCoordinatesOfPlanet(earth,0.006, 4);
            setCoordinatesOfPlanet(mars, 0.008, 5);
            setCoordinatesOfPlanet(jupiter,0.005, 6);
            setCoordinatesOfPlanet(saturn,0.004, 7);
            setCoordinatesOfPlanet(uranium,-0.007, 8);
            setCoordinatesOfPlanet(neptune,0.009, 9);
            putInCenter(sun);
        };
        
        Timer timer = new Timer(10,action);
        timer.start();

        setVisible(true);
    }

    // Для определения центра, учитывая все размеры
    public void putInCenter(Planet planet) {
        planet.setPlanetX((int) (width / 2 - (planet.getWidth() / widthDifference) / 2 + planet.getPlanetRX() / widthDifference));
        planet.setPlanetY((int) (height / 2 - (planet.getHeight() / heightDifference) / 2 + planet.getPlanetRY() / heightDifference));
        planet.setBounds(planet.getPlanetX(), planet.getPlanetY(), planet.getWidth(), planet.getHeight());
        repaint();
    }

    // Этот метод определяет куда нужно установить картинку при движении, учитывая измененье окна.
    public void setCoordinatesOfPlanet(Planet planet, double speed, int order) {
        // Держит угол в диапазоне чисел
        if (planet.getPlanetT() > 2 * Math.PI | planet.getPlanetT() < -2 * Math.PI) {
            planet.setPlanetT(0);
        }
        // В зависимости от положения по углу определяет приоритет отображения (если есть пересечение)
        if (planet.getPlanetT() < 0 & planet.getPlanetT() > -Math.PI) {
            getLayeredPane().setLayer(planet, 1);
        } else if (planet.getPlanetT() < -Math.PI & planet.getPlanetT() > -2 * Math.PI) {
            getLayeredPane().setLayer(planet, order);
        }else if (planet.getPlanetT() > 0 & planet.getPlanetT() < Math.PI) {
            getLayeredPane().setLayer(planet, order);
        } else {
            getLayeredPane().setLayer(planet, 1);
        }

        planet.setPlanetX((int) (width / 2 + ((planet.getPlanetRX() / widthDifference) * Math.cos(planet.getPlanetT())) - ((planet.getWidth() / widthDifference)/2)));
        planet.setPlanetY((int) (height / 2 + ((planet.getPlanetRY() / heightDifference) * Math.sin(planet.getPlanetT())) - ((planet.getHeight() / heightDifference)/2)));
        planet.setBounds(planet.getPlanetX(),planet.getPlanetY(),planet.getWidth(),planet.getHeight());
        planet.setPlanetT(planet.getPlanetT() + speed);
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            try {
                new OtherPlanets();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static class BackGround extends JComponent {
        BufferedImage backGroundImg;

        BackGround() throws IOException {
            backGroundImg = ImageIO.read(new File("OtherPlanets/Image/SpaceOrig.jpg"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(backGroundImg,0,0,width,height,null);
            g.drawLine(width / 2 , 0,width / 2 ,height);
            g.drawLine(0, height/ 2, width, height / 2);
        }
    }

    class Resizes extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            widthDifference = (double) startWidth / getWidth();
            heightDifference = (double) startHeight / getHeight();
            width = getWidth();
            height = getHeight();
            Planet.setWidthDifference(widthDifference);
            Planet.setHeightDifference(heightDifference);
            backGround.setBounds(0,0,getWidth(),getHeight());
        }
    }
}
