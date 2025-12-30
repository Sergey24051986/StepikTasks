//import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
//import java.io.*;

public class TrafficLights extends Canvas {
    private static BufferedImage img;

    public static void painTrafficLights() {
        img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setBackground(Color.GRAY);// отрисовка фона
        g2.clearRect(0,0,800,800);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);// Сглаживание
        g2.setColor(Color.black);
        g2.fillRoundRect(320, 140, 160, 410, 50, 50);// Корпус светофора
        g2.fillArc(350, 116, 100, 20, 18, 160);// Шляпа верх
        g2.fillRoundRect(350, 123, 100, 10, 10, 10);// Шляпа низ
        g2.fillRoundRect(230, 170, 80, 80, 10, 10);// Левый верхний треугольник (л.в.)
        g2.fillRoundRect(230, 300, 80, 80, 10, 10);// Левый средний треугольник (л.с.)
        g2.fillRoundRect(230, 430, 80, 80, 10, 10);// Левый нижний треугольник (л.н.)
        g2.fillRoundRect(490, 170, 80, 80, 10, 10);// Правый верхний треугольник (п.в.)
        g2.fillRoundRect(490, 300, 80, 80, 10, 10);// Правый средний треугольник (п.с.)
        g2.fillRoundRect(490, 430, 80, 80, 10, 10);// Правый нижний треугольник (п.н.)
        g2.fillRoundRect(370, 560, 60, 100, 10, 10);// Нижний прямоугольник
        g2.setColor(Color.white);
        g2.fillArc(344, 150, 111, 130, 0, 180);// Верхний козырек(верхний полукруг)
        g2.fillArc(344, 280, 111, 130, 0, 180);// Средний козырек(верхний полукруг)
        g2.fillArc(344, 410, 111, 130, 0, 180);// Нижний козырек(верхний полукруг)
        g2.setColor(Color.black);
        g2.fillArc(345, 162, 110, 115, 0, 180); // Верхний козырек(нижний полукруг)
        g2.fillArc(345, 292, 110, 115, 0, 180); // Средний козырек(нижний полукруг)
        g2.fillArc(345, 422, 110, 115, 0, 180); // Нижний козырек(нижний полукруг)
        g2.setColor(Color.RED);
        g2.fillOval(350, 170, 100, 100); // Красный круг
        g2.setColor(Color.YELLOW);
        g2.fillOval(350, 300, 100, 100); // Желтый круг
        g2.setColor(Color.green);
        g2.fillOval(350, 430, 100, 100); // Зеленый круг
        g2.setColor(Color.gray);
        g2.fillPolygon(new int[]{220, 220, 315}, new int[]{165, 260, 260}, 3); // Треугольник убирающий часть (л.в.)прямоугольника
        g2.fillPolygon(new int[]{220, 220, 315}, new int[]{295, 390, 390}, 3); // Треугольник убирающий часть (л.с.)прямоугольника
        g2.fillPolygon(new int[]{220, 220, 315}, new int[]{425, 520, 520}, 3); // Треугольник убирающий часть (л.н.)прямоугольника
        g2.fillPolygon(new int[]{580, 580, 485}, new int[]{165, 260, 260}, 3); // Треугольник убирающий часть (п.в.)прямоугольника
        g2.fillPolygon(new int[]{580, 580, 485}, new int[]{295, 390, 390}, 3); // Треугольник убирающий часть (п.с.)прямоугольника
        g2.fillPolygon(new int[]{580, 580, 485}, new int[]{425, 520, 520}, 3); // Треугольник убирающий часть (п.н.)прямоугольника
        // Контуры
        g2.setColor(Color.black);
        g2.drawRoundRect(320, 140, 160, 410, 50, 50);// Корпус светофора
        g2.drawArc(350, 116, 100, 20, 25, 130);// Шляпа верх
        g2.drawRoundRect(350, 123, 100, 10, 10, 10);// Шляпа низ
        g2.drawArc(344, 150, 111, 130, 0, 180);// Верхний козырек(верхний полукруг)
        g2.drawArc(344, 280, 111, 130, 0, 180);// Средний козырек(верхний полукруг)
        g2.drawArc(344, 410, 111, 130, 0, 180);// Нижний козырек(верхний полукруг)
        g2.drawArc(345, 162, 110, 115, 0, 180); // Верхний козырек(нижний полукруг)
        g2.drawArc(345, 292, 110, 115, 0, 180); // Средний козырек(нижний полукруг)
        g2.drawArc(345, 422, 110, 115, 0, 180); // Нижний козырек(нижний полукруг)
        g2.drawOval(350, 170, 100, 100); // Красный круг
        g2.drawOval(350, 300, 100, 100); // Желтый круг
        g2.drawOval(350, 430, 100, 100); // Зеленый круг
        g2.dispose();
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame();
        painTrafficLights();
        frame.add(new TrafficLights());
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        //Для записи в файл
        //ImageIO.write(img, "png", new File("task3_01/step6_traffic_lights/Image/TrafficLights.png"));
    }
}
