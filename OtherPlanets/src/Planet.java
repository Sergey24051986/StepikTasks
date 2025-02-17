import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Planet extends JComponent {
    BufferedImage img;
    private int planetWidth;
    private int planetHeight;
    private int planetX;
    private int planetY;
    // Разные радиусы для x и y, для вращения по овалу
    private final int planetRX;
    private final int planetRY;
    private static double widthDifference = 1;
    private static double heightDifference = 1;
    private double planetT = 0;

    Planet(String name, int planetRX, int planetRY) throws IOException {
        img = ImageIO.read(new File("OtherPlanets/Image/" + name + ".png"));
        this.planetRX = planetRX;
        this.planetRY = planetRY;

        // Для масштабирования
        planetWidth = (int) (img.getWidth() / widthDifference);
        planetHeight = (int) (img.getHeight() / heightDifference);
        setSize(planetWidth, planetHeight);
    }

    public int getPlanetX() {
        return planetX;
    }

    public int getPlanetY() {
        return planetY;
    }

    public int getPlanetRX() {
        return planetRX;
    }

    public int getPlanetRY() {
        return planetRY;
    }

    public double getPlanetT() {
        return planetT;
    }

    public void setPlanetX(int newXPlanet) {
        this.planetX = newXPlanet;
    }

    public void setPlanetY(int newYPlanet) {
        this.planetY = newYPlanet;
    }

    public void setPlanetT(double tPlanet) {
        this.planetT = tPlanet;
    }

    public static void setWidthDifference(double widthDifference) {
        Planet.widthDifference = widthDifference;
    }

    public static void setHeightDifference(double heightDifference) {
        Planet.heightDifference = heightDifference;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Для масштабирования
        planetWidth = (int) (img.getWidth() / widthDifference);
        planetHeight = (int) (img.getHeight() / heightDifference);
        g.drawImage(img, 0, 0, planetWidth, planetHeight, null);
    }
}
