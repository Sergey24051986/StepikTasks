//import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
//import java.io.IOException;

public class Snowman extends Canvas {
    private static BufferedImage img;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public static void paintSnowman() {
        img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = img.createGraphics();
        // Сглаживание
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setBackground(Color.white);
        g2D.clearRect(0,0,800,800);
        // Ось
        //g2D.drawLine(400,0,400,800);
        // Нижний овал
        g2D.setColor(new Color(51,51,51));
        g2D.setStroke(new BasicStroke(5));
        g2D.drawOval(225,430,350,300);
        //  Правая рука
        g2D.setStroke(new BasicStroke(10));
        g2D.drawPolyline(new int[]{500,680,710,715,695,735,735,695,715,710,680,500,500},new int[]{400,380,410,405,380,380,370,365,345,340,365,380,400},13);
        g2D.setColor(Color.white);
        g2D.fillPolygon(new int[]{500,680,710,715,695,735,735,695,715,710,680,500,500},new int[]{400,380,410,405,380,380,370,365,345,340,365,380,400},13);
        // Средний овал
        g2D.setColor(new Color(51,51,51));
        g2D.drawOval(275,300,250,200);
        g2D.setColor(Color.white);
        g2D.fillOval(275,300,250,200);
        //Пуговицы
        g2D.setStroke(new BasicStroke(3));
        g2D.setColor(new Color(51,51,51));
        g2D.drawOval(415,370,15,15);
        g2D.drawOval(415,400,15,15);
        g2D.drawOval(415,430,15,15);
        // Голова
        g2D.setStroke(new BasicStroke(10));
        g2D.setColor(new Color(51,51,51));
        g2D.drawOval(325,170,150,150);
        g2D.setColor(Color.white);
        g2D.fillOval(325,170,150,150);
        // Лицо
        g2D.setStroke(new BasicStroke(3));
        g2D.setColor(new Color(51,51,51));
        //Рот
        g2D.drawOval(420,285,15,15);
        g2D.drawOval(445,280,10,10);
        g2D.drawOval(395,285,15,15);
        g2D.drawOval(375,280,10,10);
        // Глаза
        g2D.drawOval(430,220,15,15);
        g2D.drawOval(380,220,15,15);
        // Нос
        g2D.setStroke(new BasicStroke(6));
        g2D.drawPolyline(new int[]{415,510,425,415},new int[]{250,250,265,250},4);
        g2D.setColor(Color.white);
        g2D.fillPolygon(new int[]{415,510,425},new int[]{250,250,265},3);
        //  Левая рука
        g2D.setStroke(new BasicStroke(10));
        g2D.setColor(new Color(51,51,51));
        g2D.drawPolyline(new int[]{300,150,120,115,135,95,95,135,115,120,150,300,300},new int[]{400,380,410,405,380,380,370,365,345,340,365,380,400},13);
        g2D.setColor(Color.white);
        g2D.fillPolygon(new int[]{300,150,120,115,135,95,95,135,115,120,150,300,300},new int[]{400,380,410,405,380,380,370,365,345,340,365,380,400},13);
        // Шляпа (овал)
        g2D.setColor(new Color(51,51,51));
        g2D.drawOval(300,170,200,30);
        g2D.setColor(Color.white);
        g2D.fillOval(300,170,200,30);
        // Шляпа (прямоугольник)
        g2D.setColor(new Color(51,51,51));
        g2D.drawRoundRect(350,70,100,100,10,10);
        g2D.setColor(Color.white);
        g2D.fillRoundRect(350,70,100,100,10,10);
        // Линия на шляпе
        g2D.setColor(new Color(51,51,51));
        g2D.setStroke(new BasicStroke(5));
        g2D.drawLine(350,140,450,140);
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        paintSnowman();
        frame.add(new Snowman());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setVisible(true);
        //Для записи в файл
        //ImageIO.write(img, "png", new File("Task 3.01/step 7 - Snowman/Image/Snowman.png"));
    }
}
