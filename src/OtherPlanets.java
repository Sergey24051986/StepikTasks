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

    OtherPlanets () throws IOException {
        super("OtherPlanets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(null);

        // Размер планет оптимизирован под данные размеры сторон экрана
        final int startWidth = 1920;
        final int startHeight = 1040;

        BackGround backGround = new BackGround();
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
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                widthDifference = (double) startWidth / getWidth();
                heightDifference = (double) startHeight / getHeight();
                width = getWidth();
                height = getHeight();

                backGround.setBounds(0,0,getWidth(),getHeight());
                sun.putInCenter();
                sun.setBounds(sun.getXPlanet(), sun.getYPlanet(), sun.getWidth(), sun.getHeight());
            }
        });

        ActionListener action = e -> {
            setCoordinatesOfPlanet(mercury, -0.005, 2);
            setCoordinatesOfPlanet(venus, 0.007, 3);
            setCoordinatesOfPlanet(earth,0.006, 4);
            setCoordinatesOfPlanet(mars, 0.008, 5);
            setCoordinatesOfPlanet(jupiter,0.005, 6);
            setCoordinatesOfPlanet(saturn,0.004, 7);
            setCoordinatesOfPlanet(uranium,-0.007, 8);
            setCoordinatesOfPlanet(neptune,0.009, 9);
        };
        Timer timer = new Timer(10,action);
        timer.start();

        setVisible(true);
    }

    // Этот метод определяет куда нужно установить картинку при движении, учитывая измененье окна.
    public void setCoordinatesOfPlanet(Planet planet, double speed, int order) {
        // Держит угол в диапазоне чисел
        if (planet.getTPlanet() > 2 * Math.PI | planet.getTPlanet() < -2 * Math.PI) {
            planet.setTPlanet(0);
        }

        // В зависимости от положения по углу определяет приоритет отображения (если есть пересечение)
        if (planet.getTPlanet() < 0 & planet.getTPlanet() > -Math.PI) {
            getLayeredPane().setLayer(planet, 1);
        } else if (planet.getTPlanet() < -Math.PI & planet.getTPlanet() > -2 * Math.PI) {
            getLayeredPane().setLayer(planet, order);
        }else if (planet.getTPlanet() > 0 & planet.getTPlanet() < Math.PI) {
            getLayeredPane().setLayer(planet, order);
        } else {
            getLayeredPane().setLayer(planet, 1);
        }

        planet.setXPlanet((int) (width / 2 + ((planet.getRXPlanet() / widthDifference) * Math.cos(planet.getTPlanet())) - ((planet.getWidth() / widthDifference)/2)));
        planet.setYPlanet((int) (height / 2 + ((planet.getRYPlanet() / heightDifference) * Math.sin(planet.getTPlanet())) - ((planet.getHeight() / heightDifference)/2)));
        planet.setBounds(planet.getXPlanet(),planet.getYPlanet(),planet.getWidth(),planet.getHeight());
        planet.setTPlanet(planet.getTPlanet() + speed);
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
            backGroundImg = ImageIO.read(new File("Image/OtherPlanetsImg/SpaceOrig.jpg"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(backGroundImg,0,0,width,height,null);
            g.drawLine(width / 2 , 0,width / 2 ,height);
            g.drawLine(0, height/ 2, width, height / 2);
        }
    }

    static class Planet extends JComponent {
        BufferedImage img;
        private int planetWidth;
        private int planetHeight;
        private int xPlanet;
        private int yPlanet;
        // Разные радиусы для x и y, для вращения по овалу
        private final int rXPlanet;
        private final int rYPlanet;
        private double tPlanet = 0;

        Planet(String name, int rXPlanet, int rYPlanet) throws IOException {
            img = ImageIO.read(new File("Image/OtherPlanetsImg/" + name + ".png"));
            this.rXPlanet = rXPlanet;
            this.rYPlanet = rYPlanet;
            // Для масштабирования
            planetWidth = (int) (img.getWidth() / widthDifference);
            planetHeight = (int) (img.getHeight() / heightDifference);
            setSize(planetWidth, planetHeight);
        }

        public void setXPlanet(int newXPlanet) {
            this.xPlanet = newXPlanet;
        }

        public void setYPlanet(int newYPlanet) {
            this.yPlanet = newYPlanet;
        }

        public int getRXPlanet() {
            return rXPlanet;
        }

        public int getRYPlanet() {
            return rYPlanet;
        }

        public double getTPlanet() {
            return tPlanet;
        }

        public int getXPlanet() {
            return xPlanet;
        }

        public int getYPlanet() {
            return yPlanet;
        }

        public void setTPlanet(double tPlanet) {
            this.tPlanet = tPlanet;
        }
        // Для определения центра, учитывая все размеры
        public void putInCenter() {
            xPlanet = (int) (width / 2 - (img.getWidth() / widthDifference) / 2 + rXPlanet / widthDifference);
            yPlanet = (int) (height / 2 - (img.getHeight() / heightDifference) / 2 + rYPlanet / heightDifference);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // Для масштабирования
            planetWidth = (int) (img.getWidth() / widthDifference);
            planetHeight = (int) (img.getHeight() / heightDifference);
            g.drawImage(img, 0, 0, planetWidth, planetHeight, null);
        }
    }
}
