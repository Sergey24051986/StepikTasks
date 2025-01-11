import javax.swing.*;
import java.awt.*;

public class TrafficLights extends Canvas
{
    public void paint(Graphics g0) {
        Graphics2D g = (Graphics2D)g0;
        g.setBackground(Color.GRAY);// отрисовка фона
        g.clearRect(0,0,800,800);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);// Сглаживание
        g.setColor(Color.black);
        g.fillRoundRect(320, 140, 160, 410, 50, 50);// Корпус светофора
        g.fillArc(350, 116, 100, 20, 18, 160);// Шляпа верх
        g.fillRoundRect(350, 123, 100, 10, 10, 10);// Шляпа низ
        g.fillRoundRect(230, 170, 80, 80, 10, 10);// Левый верхний треугольник (л.в.)
        g.fillRoundRect(230, 300, 80, 80, 10, 10);// Левый средний треугольник (л.с.)
        g.fillRoundRect(230, 430, 80, 80, 10, 10);// Левый нижний треугольник (л.н.)
        g.fillRoundRect(490, 170, 80, 80, 10, 10);// Правый верхний треугольник (п.в.)
        g.fillRoundRect(490, 300, 80, 80, 10, 10);// Правый средний треугольник (п.с.)
        g.fillRoundRect(490, 430, 80, 80, 10, 10);// Правый нижний треугольник (п.н.)
        g.fillRoundRect(370, 560, 60, 100, 10, 10);// Нижний прямоугольник
        g.setColor(Color.white);
        g.fillArc(344, 150, 111, 130, 0, 180);// Верхний козырек(верхний полукруг)
        g.fillArc(344, 280, 111, 130, 0, 180);// Средний козырек(верхний полукруг)
        g.fillArc(344, 410, 111, 130, 0, 180);// Нижний козырек(верхний полукруг)
        g.setColor(Color.black);
        g.fillArc(345, 162, 110, 115, 0, 180); // Верхний козырек(нижний полукруг)
        g.fillArc(345, 292, 110, 115, 0, 180); // Средний козырек(нижний полукруг)
        g.fillArc(345, 422, 110, 115, 0, 180); // Нижний козырек(нижний полукруг)
        g.setColor(Color.RED);
        g.fillOval(350, 170, 100, 100); // Красный круг
        g.setColor(Color.YELLOW);
        g.fillOval(350, 300, 100, 100); // Желтый круг
        g.setColor(Color.green);
        g.fillOval(350, 430, 100, 100); // Зеленый круг
        g.setColor(Color.gray);
        g.fillPolygon(new int[]{220, 220, 315}, new int[]{165, 260, 260}, 3); // Треугольник убирающий часть (л.в.)прямоугольника
        g.fillPolygon(new int[]{220, 220, 315}, new int[]{295, 390, 390}, 3); // Треугольник убирающий часть (л.с.)прямоугольника
        g.fillPolygon(new int[]{220, 220, 315}, new int[]{425, 520, 520}, 3); // Треугольник убирающий часть (л.н.)прямоугольника
        g.fillPolygon(new int[]{580, 580, 485}, new int[]{165, 260, 260}, 3); // Треугольник убирающий часть (п.в.)прямоугольника
        g.fillPolygon(new int[]{580, 580, 485}, new int[]{295, 390, 390}, 3); // Треугольник убирающий часть (п.с.)прямоугольника
        g.fillPolygon(new int[]{580, 580, 485}, new int[]{425, 520, 520}, 3); // Треугольник убирающий часть (п.н.)прямоугольника
        // Контуры
        g.setColor(Color.black);
        g.drawRoundRect(320, 140, 160, 410, 50, 50);// Корпус светофора
        g.drawArc(350, 116, 100, 20, 25, 130);// Шляпа верх
        g.drawRoundRect(350, 123, 100, 10, 10, 10);// Шляпа низ
        g.drawArc(344, 150, 111, 130, 0, 180);// Верхний козырек(верхний полукруг)
        g.drawArc(344, 280, 111, 130, 0, 180);// Средний козырек(верхний полукруг)
        g.drawArc(344, 410, 111, 130, 0, 180);// Нижний козырек(верхний полукруг)
        g.drawArc(345, 162, 110, 115, 0, 180); // Верхний козырек(нижний полукруг)
        g.drawArc(345, 292, 110, 115, 0, 180); // Средний козырек(нижний полукруг)
        g.drawArc(345, 422, 110, 115, 0, 180); // Нижний козырек(нижний полукруг)
        g.drawOval(350, 170, 100, 100); // Красный круг
        g.drawOval(350, 300, 100, 100); // Желтый круг
        g.drawOval(350, 430, 100, 100); // Зеленый круг
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new TrafficLights());
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
