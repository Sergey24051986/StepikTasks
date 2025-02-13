import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Planet extends JComponent {
    private final BufferedImage img;
    private int planetX;
    private int planetY;
    private final int planetR;
    private double planetT = 0;;

    public Planet(int planetX, int planetY, int planetR, String fileName) throws Exception {
        this.planetX = planetX;
        this.planetY = planetY;
        this.planetR = planetR;
        img = ImageIO.read(new File("Earth/Image/" + fileName + ".png"));
        setSize(img.getWidth(), img.getHeight());
    }

    public int getPlanetX() {
        return planetX;
    }

    public int getPlanetY() {
        return planetY;
    }

    public int getPlanetR() {
        return planetR;
    }

    public double getPlanetT() {
        return planetT;
    }

    public void setPlanetX(int planetX) {
        this.planetX = planetX;
    }

    public void setPlanetY(int planetY) {
        this.planetY = planetY;
    }

    public void setPlanetT(double planetT) {
        this.planetT = planetT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
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
        BufferedImage earthImgOrig = ImageIO.read(new File ("earth/Image/EarthOrig.jpg")).getSubimage(90,90,1050,1000); // Берем только часть изображения
        Graphics2D earthOvalG2D = earthImgOrigOval.createGraphics();
        earthOvalG2D.setClip(new Ellipse2D.Double(5,-8,1020,1020));// устанавливаем круглую форму отрисовки
        earthOvalG2D.drawImage(earthImgOrig,0,0,null); // рисуем в earthImgOrigOval часть оригинального изображения попавшего в круглую форму
        earthG2D.drawImage(earthImgOrigOval,0,0,earthWidth,earthHeight,null); // Масштабируем изображение до нужных размеров
        ImageIO.write(earthImgFinal,"png",new File("Earth/Image/Earth.png"));// Записываем в файл, для дальнейшего использования
    }
}
