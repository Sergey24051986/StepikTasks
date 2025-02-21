import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JComponent {
    private String info = "";

    public InfoWindow() {
        setSize(500, 200);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String newInfo) {
        this.info = newInfo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font("",Font.BOLD,30));
        g2.drawString(info, 100, 100);
    }
}
