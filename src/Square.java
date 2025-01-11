import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class Square extends Component{

    private static int x = 0, y = 0;
    private static BufferedImage oval;
    private final static int speedX = 6, speedY = 6;
    private static double widthCurrent, heightCurrent; // Текущие ширина, высота окна

    public Square() {
        paintOval();
        if(y <= 1) x += speedX;
        if(x >= widthCurrent - 119) y += speedY;
        if(y >= heightCurrent - 141) x -= speedX;
        if(x <= 1) y -= speedY;
    }

    public static void paintOval() {
        oval = new BufferedImage(110,110,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = oval.createGraphics();
        g2D.setBackground(Color.white);
        g2D.clearRect(0,0,150,150);
        // Улучшение текстур
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // Рисование
        g2D.setColor(Color.ORANGE);
        g2D.fillOval(0, 0, 100, 100);
        g2D.setColor(Color.black);
        g2D.drawOval(0, 0, 100, 100);
    }
    public void paint(Graphics g) {
        g.drawImage(oval, x, y,null);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,800);
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);

        ActionListener action = e -> {
                f.add(new Square());
                f.setVisible(true);
        };
        Timer timer = new Timer(1,action);
        timer.start();

        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                heightCurrent = f.getSize().getHeight();
                widthCurrent = f.getSize().getWidth();
                x = 0; y = 0;
            }
        });
    }
}
