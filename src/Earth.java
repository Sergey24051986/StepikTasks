import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Earth extends JFrame {

    private static int x = 200, y = 425;
    private static final int R = 400;
    private static double t = 0;;
    private final static int width = 1300, height = 1000;

    public Earth() throws IOException {
        super("Earth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Planet earth = new Planet();
        Background background = new Background();
        setLayout(null);// Убираем менеджер компоновки для возможности частичного наложения
        setSize(width, height);
        background.setBounds(0,0, getWidth(), getHeight());

        ActionListener action = e -> {
            x = 600 + (int) (R * Math.cos(t));
            y = 450 + (int) (R * Math.sin(t));
            earth.setBounds(x,y,100,100);
            t += 0.005;
        };

        add(earth);
        add(background);
        setResizable(false);
        setVisible(true);

        Timer timer = new Timer(10,action);
        timer.start();
    }

    public static void main(String[] args) throws IOException {
        new Earth();
    }

    static class Planet extends JComponent {
        private final BufferedImage planet;

        public Planet () throws IOException {
            planet = ImageIO.read(new File("Image/Earth/Earth.png"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(planet,0,0,null);
        }
    }

    static class Background extends JComponent {
        private final BufferedImage background;

        public Background() throws IOException {
            background = ImageIO.read(new File("Image/Earth/Space.png"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }
    }

    // Подготовка изображения Земли
    public static void paintEarth () throws IOException {
        final int earthWidth = 100, earthHeight = 100; // Нужные размеры земли
        // Конечное обработанное изображение земли в виде круга
        BufferedImage earthImgFinal = new BufferedImage(earthWidth,earthHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D earthG2D = earthImgFinal.createGraphics();
        // Здесь берется только часть изображения помещающегося в круг
        BufferedImage earthImgOrigOval = new BufferedImage(1100,1100,BufferedImage.TYPE_INT_ARGB);
        // Первоначальное оригинальное изображение земли
        BufferedImage earthImgOrig = ImageIO.read(new File ("Image/EarthOrig.jpg")).getSubimage(90,90,1050,1000); // Берем только часть изображения
        Graphics2D earthOvalG2D = earthImgOrigOval.createGraphics();
        earthOvalG2D.setClip(new Ellipse2D.Double(5,-8,1020,1020));// устанавливаем круглую форму отрисовки
        earthOvalG2D.drawImage(earthImgOrig,0,0,null); // рисуем в earthImgOrigOval часть оригинального изображения попавшего в круглую форму
        earthG2D.drawImage(earthImgOrigOval,0,0,earthWidth,earthHeight,null); // Масштабируем изображение до нужных размеров
        ImageIO.write(earthImgFinal,"png",new File("Image/Earth.png"));// Записываем в файл, для дальнейшего использования
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
        BufferedImage sunImgOrig = ImageIO.read(new File ("Image/SunOrig.jpg")).getSubimage(400,0,1800,1600); // Берем только часть изображения
        Graphics2D sunOvalG2D = sunImgOrigOval.createGraphics();
        sunOvalG2D.setClip(new Ellipse2D.Double(135,60,1525,1525));// устанавливаем круглую форму отрисовки
        sunOvalG2D.drawImage(sunImgOrig,0,0,null);
        sunG2D.drawImage(sunImgOrigOval,0,0,400,250,null);
        //Космос + Солнце
        // Конечное изображение в которое рисуются нужные по размеру компоненты
        BufferedImage spaceImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        // Первоначальное оригинальное изображение космоса
        BufferedImage spaceImgOrig = ImageIO.read(new File ("Image/SpaceOrig.jpg"));
        Graphics2D g2D = spaceImg.createGraphics();
        g2D.drawImage(spaceImgOrig,0,0,width,height,null);// Масштабируем и рисуем космос
        g2D.drawImage(sunImgFinal,500,350,null);// Добавляем обработанное солнце
        ImageIO.write(spaceImg,"png",new File("Image/Space.png"));// Записываем готовое изображение, для дальнейшей работы
    }
}


