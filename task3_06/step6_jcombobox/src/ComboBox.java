import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;

/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Пользовательский класс ComboBox (собственной реализации)
public class ComboBox extends JComponent {

    private int startCount;
    private int startWidth;
    private int startHeight;
    private Shape shape;
    private Color buttonColor;
    private Color triangleIllumination;
    private Color contourIllumination;
    private final JLabel label;
    private final Border border;
    private final Button button;
    private final BackGround backGround;
    private final ElementsList elementList;

    //------------------------------------------------------------------------------------------------------------------
    {
        startCount = 1;
        label = new JLabel();
        border = new Border();
        button = new Button();
        backGround = new BackGround();
        buttonColor = new Color(238, 238, 238);
        triangleIllumination = Color.BLACK;
        contourIllumination = new Color(238, 238, 238);
    }

    //------------------------------------------------------------------------------------------------------------------
    public ComboBox(String... args) {
        elementList = new ElementsList(args);

        add(label);
        add(border);
        add(button);
        add(backGround);
        add(elementList);

        label.setFont(new Font("Dialog", Font.BOLD, 12));
        elementList.addSelectedElementListener(e -> {
            label.setText(elementList.getSelectedElement());
        });

        button.addMouseListener(new ForMouseInComboBox());
        addComponentListener(new Resize());
    }

    //------------------------------------------------------------------------------------------------------------------
    public void addElement(Object o) {
        elementList.getElementList().add(new JLabel(o + ""));
    }

    //------------------------------------------------------------------------------------------------------------------
    public String getSelectedItem() {
        return elementList.getSelectedElement();
    }
    //------------------------------------------------------------------------------------------------------------------

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для ComboBox (для слушателя изменения размера компонента)
    class Resize extends ComponentAdapter {
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void componentResized(ComponentEvent e) {
            while (startCount-- > 0) {
                startWidth = getWidth();
                startHeight = Math.max(getHeight(), 30);

                getParent().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (elementList.isOpen() && !getParent().getComponentAt(e.getX(), e.getY()).equals(ComboBox.this))
                            elementList.openCloseList();
                    }
                });
            }
            // Кнопка---------------------------------------------------------------------------------------------------
            int buttonWidth = 20;
            button.setBounds(startWidth - buttonWidth, 1, buttonWidth, startHeight - 2);
            // Определение размера ComboBox-----------------------------------------------------------------------------
            int elementSize = (int) (startHeight * 0.6);
            int size = Math.min(elementList.getElementList().size(), 8);
            int finalHeight;

            if (elementList.getElementList().isEmpty()) {
                finalHeight = startHeight + elementSize + 1;
            } else {
                finalHeight = startHeight + elementSize * size + 1;
            }
            setSize(startWidth, finalHeight);
            //----------------------------------------------------------------------------------------------------------

            shape = new RoundRectangle2D.Double(2, 2, startWidth - 4, startHeight - 4, 10, 10);
            backGround.setBounds(0, 0, startWidth, startHeight);
            border.setBounds(0, 0, startWidth, startHeight);
            label.setBounds(5, 5, getWidth() - buttonWidth - 5, startHeight - 9);
            elementList.setBounds(0, (int) (startHeight * 0.8), startWidth, elementSize);
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса Resize

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для ComboBox (задний фон)
    class BackGround extends JComponent {
        //----------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

            g2D.setColor(Color.white);
            g2D.fill(shape); // Общий фон, прокладка между основным окном
            g2D.dispose();
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса BackGround

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для ComboBox (рамка)
    class Border extends JComponent {
        //----------------------------------------------------------------------------------------------------------
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Общий контур
            g2D.setColor(Color.black);
            g2D.setStroke(new BasicStroke(1.5F));
            g2D.draw(shape);

            // Вертикальная линия
            int x = (int) (startWidth * 0.8);
            //g2D.drawLine(x, 3, x, startHeight - 3);
            g2D.dispose();
        }
        //----------------------------------------------------------------------------------------------------------
    } // Конец класса Border

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для ComboBox (кнопка)
    class Button extends JComponent {
        //----------------------------------------------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

            // Закраска фона кнопки-------------------------------------------------------------------------------------
            g2D.setColor(buttonColor);
            g2D.fillRoundRect(0, 2, getWidth() - 3, startHeight - 1, 10, 10);
            g2D.fillRect(0, 2, getWidth() - 5, startHeight - 1);

            // Закраска треугольника------------------------------------------------------------------------------------
            g2D.setColor(triangleIllumination);
            g2D.fillPolygon(
                    new int[]{getWidth() / 2 - 5, getWidth() / 2 + 5, getWidth() / 2},
                    new int[]{getHeight() / 2 - 2, getHeight() / 2 - 2, getHeight() / 2 + 3}, 3);

            // Рисуем левую линию рамки---------------------------------------------------------------------------------
            g2D.setColor(Color.BLACK);
            g2D.setStroke(new BasicStroke(1.5F));
            g2D.drawLine(2, 2, 2, getHeight());

            // прямоугольная линия подсветки при наведении--------------------------------------------------------------
            g2D.setColor(contourIllumination);
            g2D.drawRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 5, 5);
            //----------------------------------------------------------------------------------------------------------
            g2D.dispose();
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса Button

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс ComboBox (слушатели для мыши)
    class ForMouseInComboBox extends MouseAdapter {
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            // Изменяет цвет кнопки и открывает список
            if (e.getButton() == 1) {
                buttonColor = Color.darkGray;
                repaint();
                elementList.openCloseList();
                grabFocus();

            }
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseReleased(MouseEvent e) {
            // Для возврата первоначального цвета при отпускании кнопки
            if (e.getComponent().getClass().equals(Button.class)) {
                buttonColor = new Color(238, 238, 238);
                repaint();
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseEntered(MouseEvent e) {
            // Для изменения цвета подсветки при наведении курсора на кнопку
            if (e.getComponent().getClass().equals(Button.class)) {
                contourIllumination = Color.green;
                triangleIllumination = Color.green;
                repaint();
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseExited(MouseEvent e) {
            // Для возврата обычного цвета при выходе курсора с кнопки
            if (e.getComponent().getClass().equals(Button.class)) {
                contourIllumination = new Color(238, 238, 238);
                triangleIllumination = Color.BLACK;
                repaint();
            }
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец класса ForMouseInComboBox
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} // Конец класса ComboBox
