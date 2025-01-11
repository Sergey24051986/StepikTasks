import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class DVD extends JComponent {

    private static BufferedImage dvd;
    private static Color color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
    private static boolean moveUp = false, moveDown = true, moveRight = true, moveLeft = false;
    private static int x = 0, y = 0;
    private final static int speedX = 4, speedY = 8; // Изменение скорости
    private static double widthCurrent, heightCurrent, kW = 1, kH = 1; // Текущие ширина, высота окна и коэффициент изменения размера окна

    public DVD() {
        paintDVD();
        if(moveDown & moveRight) {x += speedX; y += speedY;}// Движение вниз вправо
        if(moveRight & moveUp) {x += speedX; y -= speedY;}// Движение вверх вправо
        if(moveLeft & moveUp) {x -= speedX; y -= speedY;}// Движение влево вверх
        if(moveDown & moveLeft) {x -= speedX; y += speedY;}// Движение влево вниз
        changeMove();
    }

    public void paintComponent(Graphics g) {

        if(widthCurrent < 500 | heightCurrent < 500) {
            var g2D = (Graphics2D) g;
            g2D.drawImage(dvd,x,y, (int)(512/kW), (int)(512/kH), null);
        }else g.drawImage(dvd,x,y,null);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("DVD");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000,1000);
        f.getContentPane().setBackground(Color.black);
        f.add(new DVD());
        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                heightCurrent = f.getHeight();
                widthCurrent = f.getWidth();
                if(widthCurrent < 500 | heightCurrent < 500) {
                    kW = 1000/widthCurrent;
                    kH = 1000/heightCurrent;
                }else {kW = 1; kH = 1;}

            }
        });
        f.setVisible(true);

        ActionListener action  = e -> {
            f.add(new DVD());
            f.setVisible(true);
        };
        Timer timer = new Timer(1,action);
        timer.start();
    }

    public static void paintDVD() {
        dvd = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dvd.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setBackground(Color.black);
        g.clearRect(0, 0, 512, 512);

        g.setColor(color);
        g.fillPolygon(new int[]{402, 323, 342, 387, 375, 410}, new int[]{269, 269, 189, 189, 241, 231}, 6);
        g.fillPolygon(new int[]{114, 35, 54, 99, 87, 130}, new int[]{269, 269, 189, 189, 241, 231}, 6);

        g.drawLine(63, 153, 234, 153);
        g.drawLine(234, 153, 258, 228);
        g.drawLine(258, 228, 320, 153);
        g.drawLine(320, 153, 423, 153);

        Path2D path1 = new Path2D.Double();// Правая D, внешний полукруг
        path1.moveTo(423, 153);
        path1.curveTo(518, 150, 518, 273, 402, 269);
        g.fill(path1);
        g.draw(path1);

        g.drawLine(402, 269, 323, 269);
        g.drawLine(323, 269, 342, 189);
        g.drawLine(342, 189, 387, 189);
        g.drawLine(387, 189, 375, 241);

        Path2D path2 = new Path2D.Double();
        path2.moveTo(375, 241);
        path2.curveTo(455, 250, 455, 180, 414, 179);

        g.drawLine(345, 179, 241, 296);
        g.drawLine(241, 296, 201, 189);

        Path2D path3 = new Path2D.Double();// Левая D, внешний полукруг
        path3.moveTo(135, 153);
        path3.curveTo(230, 150, 230, 273, 114, 269);
        g.fill(path3);
        g.draw(path3);

        g.drawLine(114, 269, 35, 269);
        g.drawLine(35, 269, 54, 189);
        g.drawLine(54, 189, 99, 189);
        g.drawLine(99, 189, 87, 241);

        Path2D path4 = new Path2D.Double();
        path4.moveTo(87, 241);
        path4.curveTo(167, 250, 167, 180, 126, 179);

        g.drawLine(57, 179, 63, 153);
        g.fillOval(20, 297, 444, 60);
        g.drawOval(20, 297, 444, 60);

        g.fillPolygon(new int[]{424, 420, 345, 241, 201, 135, 234, 258, 320, 423}, new int[]{153, 179, 179, 296, 189, 153, 153, 228, 153, 153}, 10);
        g.fillPolygon(new int[]{63, 136, 140, 57, 63}, new int[]{153, 153, 179, 179, 153}, 5);

        g.setColor(Color.black);
        g.fill(path2);
        g.fill(path4);
        g.drawLine(130, 179, 57, 179);
        g.drawLine(418, 179, 347, 179);
        g.setFont(new Font("V I D E O", Font.BOLD, 50));
        g.drawString("V I D E O", 140, 345);
    }

    public static void changeMove() {
        // Вправо
        if(y >= heightCurrent - 400/kH & moveRight & moveDown) { // Если вниз и вправо до низа 600
            moveDown = false;
            moveUp = true;
            moveLeft = false;
            color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
            new DVD();
        }
        if(x >= widthCurrent - 510/kW & moveRight & moveDown) { // Если вниз и вправо до правого края 490
            moveUp = false;
            moveLeft = true;
            moveRight = false;
            color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
            new DVD();
        }
        if(x >= widthCurrent - 510/kW & moveUp & moveRight) { // Если вверх и вправо до правого края 490
            moveLeft = true;
            moveRight = false;
            moveDown = false;
            color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
            new DVD();
        }
        if(y <= -154/kH & moveUp & moveRight) { // Если вверх и вправо до верха
            moveUp = false;
            moveDown = true;
            moveLeft = false;
            color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
            new DVD();
        }
        // Влево
        if(y >= heightCurrent - 400/kH & moveDown & moveLeft) { // Если вниз и влево до низа 600
            moveRight = false;
            moveUp = true;
            moveDown = false;
            color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
            new DVD();
        }
        if(x <= -15/kW & moveDown & moveLeft) { // Если вниз и влево до левого края
            moveRight = true;
            moveUp = false;
            moveLeft = false;
            color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
            new DVD();
        }
        if(y <= -154/kH & moveUp & moveLeft) { // Если вверх и влево до верха
            moveUp = false;
            moveDown = true;
            moveRight = false;
            color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
            new DVD();
        }
        if(x <= -15/kH & moveUp & moveLeft) { // Если вверх и влево до левого края
            moveLeft = false;
            moveDown = false;
            moveRight = true;
            color = new Color((int)(Math.random() * 100), (int)(Math.random() * 100), (int)(Math.random() * 10));
            new DVD();
        }
    }
}
