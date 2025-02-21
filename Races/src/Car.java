import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Car extends JComponent {
    private final String name;
    private int xCar = 0;
    private int yCar = 0;
    private final BufferedImage img;

    Car(String name) throws IOException {
        this.name = name;
        img = ImageIO.read(new File("Races/Image/" + name + ".png"));
        setSize(img.getWidth(),img.getHeight());
    }

    public String getName() {
        return name;
    }

    public int getXCar() {
        return xCar;
    }

    public int getYCar() {
        return yCar;
    }

    public void setXCar(int newXCar) {
        this.xCar = newXCar;
    }

    public void setYCar(int newYCar) {
        this.yCar = newYCar;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
