import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
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
    private final VisibleArea visibleArea;
    private final JTextField infoWidthShape;
    private final JTextField infoHeightShape;
    private final JPanel panel;

    public ImageEditor () throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        visibleArea = new VisibleArea();
        addMouseListener(new MoveVisibleArea());
        addMouseMotionListener(new MoveVisibleArea());
        addMouseWheelListener(new MoveVisibleArea());
        addComponentListener(new Resizes());

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
        infoWidthShape = new JTextField("" + widthOfArea);
        infoHeightShape = new JTextField("" + heightOfArea);

        // Привязка событий
        buttonRight.addActionListener(e -> {
            x += accuracy;
            repaint();
        });
        buttonLeft.addActionListener(e -> {
            x -= accuracy;
            repaint();
        });
        buttonUp.addActionListener(e -> {
            y -= accuracy;
            repaint();
        });
        buttonDown.addActionListener(e -> {
            y += accuracy;
            repaint();
        });
        buttonIncreaseWidth.addActionListener(e -> {
            widthOfArea += accuracy;
            infoWidthShape.setText("" + widthOfArea);
            repaint();
        });
        buttonReductionWidth.addActionListener(e -> {
            widthOfArea -= accuracy;
            infoWidthShape.setText("" + widthOfArea);
            repaint();
        });
        buttonIncreaseHeight.addActionListener(e -> {
            heightOfArea += accuracy;
            infoHeightShape.setText("" + heightOfArea);
            repaint();
        });
        buttonReductionHeight.addActionListener(e -> {
            heightOfArea -= accuracy;
            infoHeightShape.setText("" + heightOfArea);
            repaint();
        });
        buttonIncreaseAccuracy.addActionListener(e -> {
            accuracy += 1;
            infoAccuracy.setText("" + accuracy);
            repaint();
        });
        buttonReductionAccuracy.addActionListener(e -> {
            accuracy -= 1;
            infoAccuracy.setText("" + accuracy);
            repaint();
        });
        buttonOvalShape.addActionListener(e -> {
            ovalShape = true;
            rectangleShape = false;
            repaint();
        });
        buttonRectangleShape.addActionListener(e -> {
            ovalShape = false;
            rectangleShape = true;
            repaint();
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
            repaint();
        });
        buttonSave.addActionListener(e -> {
            try {
                saveImg();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonLoading.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("Task 3.2 step 8 - OtherPlanets/ImageEditor/Image/Original/");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Изображения в формате JPG и PNG", "jpg", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(ImageEditor.this);
            try {
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    origImg = ImageIO.read(new File(chooser.getSelectedFile().getPath()));
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            visibleArea.setBounds(0,0,origImg.getWidth(),origImg.getHeight());
        });

        add(visibleArea);

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(buttonRight);
        panel.add(buttonLeft);
        panel.add(buttonUp);
        panel.add(buttonDown);
        panel.add(buttonIncreaseWidth);
        panel.add(buttonReductionWidth);
        panel.add(buttonIncreaseHeight);
        panel.add(buttonReductionHeight);
        panel.add(buttonIncreaseAccuracy);
        panel.add(buttonReductionAccuracy);
        panel.add(buttonSave);
        panel.add(buttonOvalShape);
        panel.add(buttonRectangleShape);
        panel.add(buttonSetNewParameters);
        panel.add(buttonLoading);
        panel.add(infoAllText);
        panel.add(infoFinalWidth);
        panel.add(infoFinalHeight);
        panel.add(infoAccuracy);
        panel.add(infoWidthShape);
        panel.add(infoHeightShape);

        add(panel);

        buttonRight.setBounds(230,45,100,30);//1420
        buttonLeft.setBounds(10,45,100,30);//1200
        buttonUp.setBounds(120,10,100,30);//1310
        buttonDown.setBounds(120,80,100,30);//1310
        buttonReductionAccuracy.setBounds(110,40,120,40);//1300

        buttonIncreaseWidth.setBounds(230,165,100,30);//1420
        buttonReductionWidth.setBounds(10,165,100,30);//1200
        buttonIncreaseHeight.setBounds(120,130,100,30);//1310
        buttonReductionHeight.setBounds(120,200,100,30);//1310
        buttonIncreaseAccuracy.setBounds(110,160,120,40);//1300

        buttonLoading.setBounds(40,280,130,40);//1230
        buttonSave.setBounds(170,280,130,40);//1360
        buttonOvalShape.setBounds(40,320,130,40);//1230
        buttonRectangleShape.setBounds(170,320,130,40);//1360
        buttonSetNewParameters.setBounds(60,530,200,30);//1250

        infoAllText.setBounds(10,365,300,300);//1200
        infoAccuracy.setBounds(290,370,50,20);//1480
        infoWidthShape.setBounds(290,400,50,20);//1480
        infoHeightShape.setBounds(290,430,50,20);//1480
        infoFinalWidth.setBounds(290,460,50,20);//1480
        infoFinalHeight.setBounds(290,490,50,20);//1480

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
    public void saveImg() throws IOException {
        int correctWidth;
        int correctHeight;
        File path = null;

        JFileChooser chooser = new JFileChooser("Task 3.2 step 8 - OtherPlanets/ImageEditor/Image/Processed/");
        int returnVal =  chooser.showSaveDialog(ImageEditor.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = new File(chooser.getSelectedFile().getPath() + ".png");
        }
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
        if (path != null) {
            ImageIO.write(finalImg, "png", path);
            JOptionPane.showMessageDialog(ImageEditor.this, new String[]{"Файл с названием " + chooser.getSelectedFile().getName() + " сохранен!", "по пути " + chooser.getSelectedFile().getPath()});
        } else {
            JOptionPane.showMessageDialog(ImageEditor.this, "Вы не указали имя файла, для сохранения!");
        }
    }

    // Отображение области на экране (в два раза меньше, чтобы помещалось!)
    static class VisibleArea extends JComponent {
        
        public VisibleArea() {
            setName("VisibleArea");
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
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

    class MoveVisibleArea implements MouseListener, MouseMotionListener, MouseWheelListener {
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1 & ImageEditor.this.getContentPane().getComponentAt(e.getX(), e.getY()).getName().equals("VisibleArea")) {
                x = (double) e.getX() * 2 - (double) widthOfArea /2;
                y = (double)e.getY() * 2 - (double) heightOfArea /2;
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            if (e.getButton() == 0 & ImageEditor.this.getContentPane().getComponentAt(e.getX(), e.getY()).getName().equals("VisibleArea")) {
                x = (double) e.getX() * 2 - (double) widthOfArea /2;
                y = (double)e.getY() * 2 - (double) heightOfArea /2;
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {}

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (ImageEditor.this.getContentPane().getComponentAt(e.getX(), e.getY()).getName().equals("VisibleArea")) {
                widthOfArea += e.getWheelRotation() * -10;
                heightOfArea += e.getWheelRotation() * -10;
                infoWidthShape.setText("" + widthOfArea);
                infoHeightShape.setText("" + heightOfArea);
                repaint();

            }
        }
    }

    class Resizes extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            //System.out.println(getWidth());
            panel.setBounds(getWidth() - 400,0,400,600);
        }
    }
}
