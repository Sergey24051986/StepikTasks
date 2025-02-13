import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Earth extends JFrame {

    private final static int width = 1300;
    private final static int height = 1000;

    public Earth() throws Exception {
        super("Earth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);// Убираем менеджер компоновки для возможности частичного наложения
        setSize(width, height);
        setResizable(false);

        Background background = new Background();
        background.setBounds(0,0, getWidth(), getHeight());

        Planet earth = new Planet(200, 425, 400, "Earth");
        MoveInCircle moveInCircle = new MoveInCircle(earth);

        add(earth);
        add(background);

        Timer timer = new Timer(10,moveInCircle);
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new Earth();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    static class Background extends JComponent {
        private final BufferedImage background;

        public Background() throws IOException {
            background = ImageIO.read(new File("Earth/Image/Space.png"));
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
        BufferedImage sunImgOrig = ImageIO.read(new File ("Earth/Image/SunOrig.jpg")).getSubimage(400,0,1800,1600); // Берем только часть изображения
        Graphics2D sunOvalG2D = sunImgOrigOval.createGraphics();
        sunOvalG2D.setClip(new Ellipse2D.Double(135,60,1525,1525));// устанавливаем круглую форму отрисовки
        sunOvalG2D.drawImage(sunImgOrig,0,0,null);
        sunG2D.drawImage(sunImgOrigOval,0,0,400,250,null);
        //Космос + Солнце
        // Конечное изображение в которое рисуются нужные по размеру компоненты
        BufferedImage spaceImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        // Первоначальное оригинальное изображение космоса
        BufferedImage spaceImgOrig = ImageIO.read(new File ("Earth/Image/SpaceOrig.jpg"));
        Graphics2D g2D = spaceImg.createGraphics();
        g2D.drawImage(spaceImgOrig,0,0,width,height,null);// Масштабируем и рисуем космос
        g2D.drawImage(sunImgFinal,500,350,null);// Добавляем обработанное солнце
        ImageIO.write(spaceImg,"png",new File("Earth/Image/Space.png"));// Записываем готовое изображение, для дальнейшей работы
    }

    static class MoveInCircle implements ActionListener {
        private final Planet planet;

        MoveInCircle(Planet planet) {
            this.planet = planet;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            planet.setPlanetX(600 + (int) (planet.getPlanetR() * Math.cos(planet.getPlanetT())));
            planet.setPlanetY(450 + (int) (planet.getPlanetR() * Math.sin(planet.getPlanetT())));
            planet.setBounds(planet.getPlanetX(), planet.getPlanetY(), planet.getWidth(), planet.getHeight());
            planet.setPlanetT(planet.getPlanetT() + 0.005);
        }
    }
}