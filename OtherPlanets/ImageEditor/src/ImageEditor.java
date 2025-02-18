import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageEditor extends JFrame {

    private static double x = 0;
    private static double y = 0;
    private static int widthOfArea = 500;
    private static int heightOfArea = 500;
    private static int accuracy = 10;
    private static int finalWidth = 100;
    private static int finalHeight = 100;
    private static BufferedImage origImg;
    private static BufferedImage ovalImg;
    private static boolean ovalShape = true;
    private static boolean rectangleShape = false;
    private static JTextField infoSave;
    private static JTextField infoLoading;

    ImageEditor () throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Image image = new Image();
        JButton buttonRight = new JButton("Вправо");
        JButton buttonLeft = new JButton("Влево");
        JButton buttonUp = new JButton("Вверх");
        JButton buttonDown = new JButton("Вниз");
        JButton buttonIncreaseWidth = new JButton("Ширина (+)");
        JButton buttonReductionWidth = new JButton("Ширина (-)");
        JButton buttonIncreaseHeight = new JButton("Высота (+)");
        JButton buttonReductionHeight = new JButton("Высота (-)");
        JButton buttonIncreaseAccuracy = new JButton("Точность (+)");
        JButton buttonReductionAccuracy = new JButton("Точность (-)");
        JButton buttonSave = new JButton("Сохранить");
        JButton buttonOvalShape = new JButton("Овал");
        JButton buttonRectangleShape = new JButton("Прямоугольник");
        JButton buttonLoading = new JButton("Загрузить");
        JButton buttonSetNewParameters = new JButton("Сохранить параметры");

        InfoAllText infoAllText = new InfoAllText();
        JTextField infoFinalWidth = new JTextField("" + finalWidth);
        JTextField infoFinalHeight = new JTextField("" + finalHeight);
        JTextField infoAccuracy = new JTextField("" + accuracy);
        JTextField infoWidthShape = new JTextField("" + widthOfArea);
        JTextField infoHeightShape = new JTextField("" + heightOfArea);
        infoSave = new JTextField("File name");
        infoLoading = new JTextField("File name");

        // Привязка событий
        buttonRight.addActionListener(e -> {
            x += accuracy;
            this.repaint();
        });
        buttonLeft.addActionListener(e -> {
            x -= accuracy;
            this.repaint();
        });
        buttonUp.addActionListener(e -> {
            y -= accuracy;
            this.repaint();
        });
        buttonDown.addActionListener(e -> {
            y += accuracy;
            this.repaint();
        });
        buttonIncreaseWidth.addActionListener(e -> {
            widthOfArea += accuracy;
            infoWidthShape.setText("" + widthOfArea);
            this.repaint();
        });
        buttonReductionWidth.addActionListener(e -> {
            widthOfArea -= accuracy;
            infoWidthShape.setText("" + widthOfArea);
            this.repaint();
        });
        buttonIncreaseHeight.addActionListener(e -> {
            heightOfArea += accuracy;
            infoHeightShape.setText("" + heightOfArea);
            this.repaint();
        });
        buttonReductionHeight.addActionListener(e -> {
            heightOfArea -= accuracy;
            infoHeightShape.setText("" + heightOfArea);
            this.repaint();
        });
        buttonIncreaseAccuracy.addActionListener(e -> {
            accuracy += 1;
            infoAccuracy.setText("" + accuracy);
            this.repaint();
        });
        buttonReductionAccuracy.addActionListener(e -> {
            accuracy -= 1;
            infoAccuracy.setText("" + accuracy);
            this.repaint();
        });
        buttonOvalShape.addActionListener(e -> {
            ovalShape = true;
            rectangleShape = false;
            this.repaint();
        });
        buttonRectangleShape.addActionListener(e -> {
            ovalShape = false;
            rectangleShape = true;
            this.repaint();
        });
        buttonSetNewParameters.addActionListener(e -> {
            String newFinalWidth = infoFinalWidth.getText();
            String newFinalHeight = infoFinalHeight.getText();
            String newAccuracy = infoAccuracy.getText();
            String newWidthShape = infoWidthShape.getText();
            String newHeightShape = infoHeightShape.getText();
            finalWidth = Integer.parseInt(newFinalWidth);
            finalHeight = Integer.parseInt(newFinalHeight);
            accuracy = Integer.parseInt(newAccuracy);
            widthOfArea = Integer.parseInt(newWidthShape);
            heightOfArea = Integer.parseInt(newHeightShape);
            this.repaint();
        });
        buttonSave.addActionListener(e -> {
            try {
                saveImg();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonLoading.addActionListener(e -> {
            try {
                origImg = ImageIO.read(new File ("OtherPlanets/ImageEditor/Image/Original/" + infoLoading.getText() + ".jpg"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            image.setBounds(0,0,origImg.getWidth(),origImg.getHeight());
        });

        add(image);
        add(buttonRight);
        add(buttonLeft);
        add(buttonUp);
        add(buttonDown);
        add(buttonIncreaseWidth);
        add(buttonReductionWidth);
        add(buttonIncreaseHeight);
        add(buttonReductionHeight);
        add(buttonIncreaseAccuracy);
        add(buttonReductionAccuracy);
        add(buttonSave);
        add(buttonOvalShape);
        add(buttonRectangleShape);
        add(buttonSetNewParameters);
        add(buttonLoading);

        add(infoAllText);
        add(infoFinalWidth);
        add(infoFinalHeight);
        add(infoAccuracy);
        add(infoWidthShape);
        add(infoHeightShape);
        add(infoLoading);
        add(infoSave);

        //image.setBounds(0,0,origImg.getWidth(),origImg.getHeight());
        buttonRight.setBounds(1420,45,100,30);
        buttonLeft.setBounds(1200,45,100,30);
        buttonUp.setBounds(1310,10,100,30);
        buttonDown.setBounds(1310,80,100,30);
        buttonReductionAccuracy.setBounds(1300,40,120,40);

        buttonIncreaseWidth.setBounds(1420,165,100,30);
        buttonReductionWidth.setBounds(1200,165,100,30);
        buttonIncreaseHeight.setBounds(1310,130,100,30);
        buttonReductionHeight.setBounds(1310,200,100,30);
        buttonIncreaseAccuracy.setBounds(1300,160,120,40);

        buttonLoading.setBounds(1230,240,130,40);
        buttonSave.setBounds(1230,280,130,40);
        buttonOvalShape.setBounds(1230,320,130,40);
        buttonRectangleShape.setBounds(1360,320,130,40);
        buttonSetNewParameters.setBounds(1250,530,200,30);

        infoAllText.setBounds(1200,365,300,300);
        infoAccuracy.setBounds(1480,370,50,20);
        infoWidthShape.setBounds(1480,400,50,20);
        infoHeightShape.setBounds(1480,430,50,20);
        infoFinalWidth.setBounds(1480,460,50,20);
        infoFinalHeight.setBounds(1480,490,50,20);
        infoLoading.setBounds(1360,240,130,42);
        infoSave.setBounds(1360,280,130,45);

        setSize(1600,1000);
        setVisible(true);


    }

    // Сохранение области оригинального изображения (для сохранения качества!)
    public static void shapeImg () {
        ovalImg = new BufferedImage(origImg.getWidth(),origImg.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D shapeG2 = ovalImg.createGraphics();
        shapeG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if(ovalShape) {
            shapeG2.setClip(new Ellipse2D.Double(x, y, widthOfArea, heightOfArea));
        }
        if(rectangleShape) {
            shapeG2.setClip(new Rectangle2D.Double(x, y, widthOfArea, heightOfArea));
        }
        shapeG2.drawImage(origImg,0,0,null);
    }

    // Масштабирование и сохранение области изображения в указанные размеры
    public static void saveImg () throws IOException {
        int correctWidth;
        int correctHeight;
        File path = new File("OtherPlanets/ImageEditor/Image/Processed/" + infoSave.getText() + ".png");
        //File path = new File("Image/Processed/" + infoSave.getText() + ".png");
        double differenceSides = (double) origImg.getWidth() / origImg.getHeight();

        if (differenceSides > 0) {
            correctWidth = (int) (finalWidth * differenceSides);
            correctHeight = finalHeight;
        } else {
            correctHeight = (int) (finalHeight / differenceSides);
            correctWidth = finalWidth;
        }
        shapeImg();
        BufferedImage finalImg = new BufferedImage(correctWidth,correctHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D finalG2 = finalImg.createGraphics();
        finalG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        finalG2.drawImage(ovalImg,0,0,correctWidth,correctHeight,null);
        ImageIO.write(finalImg,"png",path);
    }

    // Отображение области на экране (в два раза меньше, чтобы помещалось!)
    static class Image extends JComponent {

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            if(ovalShape) {
                g2.setClip(new Ellipse2D.Double(x / 2, y / 2, (double) widthOfArea / 2, (double) heightOfArea / 2));
            }
            if(rectangleShape) {
                g2.setClip(new Rectangle2D.Double(x / 2, y / 2, (double) widthOfArea / 2, (double) heightOfArea / 2));
            }
            g2.drawImage(origImg,0,0,origImg.getWidth()/2,origImg.getHeight()/2,null);
        }
    }

    // Компонент с информационным текстом
    static class InfoAllText extends JComponent {

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(new Font("",Font.BOLD,15));
            g2.drawString("Точность (шаг сдвига области):",30,20);
            g2.drawString("Ширина области отображения:",30,50);
            g2.drawString("Высота области отображения:",30,80);
            g2.drawString("Ширина конечного изображения:",30,110);
            g2.drawString("Высота конечного изображения:",30,140);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                try {
                    new ImageEditor();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        });
    }
}
