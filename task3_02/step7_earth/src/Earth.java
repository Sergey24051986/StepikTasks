import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Earth extends JFrame implements Runnable{

    private final static int width = 1300;
    private final static int height = 1000;
    private final Planet planet;

    public Earth() throws Exception {
        super("Task 3.2 step 7 - Earth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);// Убираем менеджер компоновки для возможности частичного наложения
        setSize(width, height);
        setResizable(false);

        Background background = new Background();
        background.setBounds(0,0, getWidth(), getHeight());

        planet = new Planet(200, 425, 400, "Earth");

        add(planet);
        add(background);

        setVisible(true);
    }
    @Override
    public void run() {
        while (true) {
            planet.setPlanetX(600 + (int) (planet.getPlanetR() * Math.cos(planet.getPlanetT())));
            planet.setPlanetY(450 + (int) (planet.getPlanetR() * Math.sin(planet.getPlanetT())));
            planet.setBounds(planet.getPlanetX(), planet.getPlanetY(), planet.getWidth(), planet.getHeight());
            planet.setPlanetT(planet.getPlanetT() + 0.001);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            Earth earth;
            try {
                //Planet.paintEarth();
                //Earth.paintSpace();
                earth = new Earth();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Thread th = new Thread(earth);
            th.start();
        });

    }

    static class Background extends JComponent {
        private final BufferedImage background;

        public Background() throws IOException {
            background = ImageIO.read(new File("task3_02/step7_earth/Image/Space.png"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }
    }

    // Подготовка изображения Солнца и фона с космосом
    public static void paintSpace () throws IOException {
        final int sunWidth = 400, sunHeight = 250; // Нужные размеры солнца
        // Конечное обработанное изображение солнца в виде круга
        BufferedImage sunImgFinal = new BufferedImage(sunWidth,sunHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D sunG2D = sunImgFinal.createGraphics();
        // Здесь берется только часть изображения помещающегося в круг
        BufferedImage sunImgOrigOval = new BufferedImage(2560,1600,BufferedImage.TYPE_INT_ARGB);
        // Первоначальное оригинальное изображение солнца
        BufferedImage sunImgOrig = ImageIO.read(new File ("task3_02/step7_earth/Image/SunOrig.jpg")).getSubimage(400,0,1800,1600); // Берем только часть изображения
        Graphics2D sunOvalG2D = sunImgOrigOval.createGraphics();
        sunOvalG2D.setClip(new Ellipse2D.Double(135,60,1525,1525));// устанавливаем круглую форму отрисовки
        sunOvalG2D.drawImage(sunImgOrig,0,0,null);
        sunG2D.drawImage(sunImgOrigOval,0,0,400,250,null);
        //Космос + Солнце
        // Конечное изображение в которое рисуются нужные по размеру компоненты
        BufferedImage spaceImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        // Первоначальное оригинальное изображение космоса
        BufferedImage spaceImgOrig = ImageIO.read(new File ("task3_02/step7_earth/Image/SpaceOrig.jpg"));
        Graphics2D g2D = spaceImg.createGraphics();
        g2D.drawImage(spaceImgOrig,0,0,width,height,null);// Масштабируем и рисуем космос
        g2D.drawImage(sunImgFinal,500,350,null);// Добавляем обработанное солнце
        ImageIO.write(spaceImg,"png",new File("task3_02/step7_earth/Image/Space.png"));// Записываем готовое изображение, для дальнейшей работы
    }

}