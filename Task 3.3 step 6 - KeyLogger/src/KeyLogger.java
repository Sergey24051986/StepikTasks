import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class KeyLogger extends JFrame {

    private static PrintWriter writer;
    private static JLabel text;
    private static String lineForWindow = "<html>";
    private static String lineForWriter = "";
    private static volatile boolean flag = false;

    public KeyLogger() throws IOException {
        int width = 640;
        int height = 355;

        writer = new PrintWriter("Task 3.3 step 6 - KeyLogger/Text.txt");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);

        setUndecorated(true);
        setLayout(null);
        addKeyListener(new ClickAction());

        //Компонент для отображения введенного текста
        text = new JLabel();
        text.setBounds(30, 10, 600, 300);
        text.setFont(new Font("", Font.BOLD | Font.ITALIC, 25));
        add(text);

        //Инфо окно с указаниями по работе
        JLabel info = new JLabel("<html>Для выхода нажмите Esc<br/>Можно удалять символы<br/>Можно перейти на новую строку<html>");
        info.setBounds(width - 200, height - 100, 200, 100);
        info.setFont(new Font("", Font.BOLD | Font.ITALIC, 10));
        add(info);

        //С помощью потока отслеживаем завершение и выводим сообщение
        Thread infoThread = new Thread(() -> {
            while (true) {
                if (flag) {
                    info.setText("Сохраняю в файл!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.exit(0);
                }
            }
        });
        infoThread.start();

        // Установка картинки для фона
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = img.createGraphics();
        g2D.drawImage(ImageIO.read(new File("Task 3.3 step 6 - KeyLogger/Image/Background.jpg")), 0, 0, width, height, null);
        JLabel background = new JLabel(new ImageIcon(img));
        background.setBounds(0, 0, width, height);
        add(background);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new KeyLogger();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static class ClickAction extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 27) {
                writer.print(lineForWriter);
                writer.close();
                flag = true;
            }

            if (e.getKeyCode() == 8) {
                if (lineForWindow.endsWith("<br/>")) {
                    lineForWindow = lineForWindow.substring(0, lineForWindow.length() - 5);
                } else if (!lineForWindow.equals("<html>")) {
                    lineForWindow = lineForWindow.substring(0, lineForWindow.length() - 1);
                }
                lineForWriter = !lineForWriter.isEmpty() ? lineForWriter.substring(0, lineForWriter.length() - 1) : lineForWriter;
                text.setText(lineForWindow);
            }

            if (e.getKeyCode() == 10) {
                lineForWindow += "<br/>";
                lineForWriter += "\n";
            }

            if (e.getKeyCode() >= 32 & e.getKeyCode() <= 122 | e.getKeyCode() >= 1040 & e.getKeyCode() <= 1103 | e.getKeyCode() == 222 | e.getKeyCode() == 0) {
                lineForWindow += e.getKeyChar();
                lineForWriter += e.getKeyChar();
                text.setText(lineForWindow);
            }
        }
    }
}
