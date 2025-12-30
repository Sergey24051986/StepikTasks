import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

/// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Пользовательский класс ElementsList (собственной реализации)
public class ElementsList extends JComponent {

    private int height;
    private int startCount;
    private int startWidth;
    private int startHeight;
    private int positionIndicatorDown;
    private int positionIndicatorUp;
    private boolean isOpen;
    private Thread thread;
    private Scrollbar scrollbar;
    private String selectedElement;
    private final Border border;
    private final ArrayList<JLabel> elementList;
    private final ArrayList<SelectedElementListener> listeners;
    private final ElementsListEvent event;

    //------------------------------------------------------------------------------------------------------------------
    {
        height = 0;
        startCount = 1;
        selectedElement = "";
        border = new Border();
        thread = new Thread();
        listeners = new ArrayList<>();
        event = new ElementsListEvent(this);
        positionIndicatorDown = Integer.MAX_VALUE;
    }

    //------------------------------------------------------------------------------------------------------------------
    public ElementsList(String[] arr) {
        elementList = new ArrayList<>();

        for (String str : arr) {
            elementList.add(new JLabel(str));
        }
        add(border);
        addComponentListener(new Resize());
    }

    //------------------------------------------------------------------------------------------------------------------
    public void addSelectedElementListener(SelectedElementListener e) {
        listeners.add(e);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Double(4, 0, startWidth - 4, height * 0.94, 10, 10)
                .contains(x, y);
    }

    //------------------------------------------------------------------------------------------------------------------
    protected void elementsDown() {
        if (elementList.size() > 8 && positionIndicatorUp < (int) (startHeight * 0.2) - startHeight * 0.95) {
            positionIndicatorUp += startHeight;
            putElements();
        } else putElements(0);
    }

    //------------------------------------------------------------------------------------------------------------------
    protected void elementsUp() {

        if (elementList.size() > 8 && positionIndicatorDown > height) {
            positionIndicatorUp -= startHeight;
            putElements();
        } else {
            putElements(scrollbar.getHeight() - scrollbar.getSliderHeight() - 0.4);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    protected void fireElementListener() {
        for (SelectedElementListener l : listeners)
            l.selectedElement(event);
    }

    //------------------------------------------------------------------------------------------------------------------
    public ArrayList<JLabel> getElementList() {
        return elementList;
    }

    //------------------------------------------------------------------------------------------------------------------
    public String getSelectedElement() {
        return selectedElement;
    }

    //------------------------------------------------------------------------------------------------------------------
    public boolean isOpen() {
        return isOpen;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void openCloseList() {
        if (thread.getState() != Thread.State.TIMED_WAITING) {
            thread = new OpenCloseTreed();
            thread.start();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setClip(new RoundRectangle2D.Double(0, 0, startWidth, (int) (startHeight * 8.4), 10, 10));//0.96
        g2D.setColor(Color.white);
        g2D.fillRoundRect(2, 0, startWidth - 4, (int) (height * 0.95), 10, 10);
    }

    //------------------------------------------------------------------------------------------------------------------
    protected void putElements() {
        int position = 0;

        for (JLabel element : elementList) {
            element.setBounds(4, positionIndicatorUp + position, startWidth, startHeight);
            position += startHeight;
        }
        positionIndicatorDown = position + positionIndicatorUp;

    }

    //------------------------------------------------------------------------------------------------------------------
    protected void putElements(double mouseYPosition) { //int sliderHeight
        double n = (getHeight() - startHeight * 8) / (double) (height - scrollbar.getSliderHeight()) * 1.065;
        double y = mouseYPosition * n;
        positionIndicatorUp = (int) (startHeight * 0.2 - y);
        int position = 0;

        for (JLabel element : elementList) {
            element.setBounds(4, positionIndicatorUp + position, startWidth, startHeight);
            position += startHeight;
        }
        positionIndicatorDown = positionIndicatorUp + position;
    }

    //------------------------------------------------------------------------------------------------------------------
    public void removeSelectedElementListener(SelectedElementListener e) {
        listeners.remove(e);
    }
    //------------------------------------------------------------------------------------------------------------------

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для ElementsList (для слушателя изменения размера компонента)
    class Resize extends ComponentAdapter {
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void componentResized(ComponentEvent e) {
            // Цикл делается один раз, при создании ComboBox для определения и сохранения первоначальных размеров
            // Так как componentResized срабатывает при изменении размера компонента, то и добавление компонентов
            // делается тоже в этом цикле - один раз.
            // Все это делается здесь, а не в конструкторе, потому что все размеры берутся из setBounds без
            // менеджера компоновки.------------------------------------------------------------------------------------
            while (startCount-- > 0) {
                startWidth = getWidth();
                startHeight = getHeight();
                // верхняя позиция элементов со сдвигом вниз из-за сдвинутого вверх окна ElementsList
                positionIndicatorUp = (int) (startHeight * 0.2);

                // Если элементов больше восьми, то добавляет Scrollbar
                if (elementList.size() > 8) {
                    scrollbar = new Scrollbar(ElementsList.this);
                    scrollbar.setVisible(false);
                    add(scrollbar);
                }

                // Добавляются в ElementsList Элементов в виде JLabel---------------------------------------------------
                int pposition = positionIndicatorUp;
                if (!elementList.isEmpty()) {
                    for (JLabel element : elementList) {
                        element.setBounds(4, pposition, startWidth, startHeight);
                        element.setBackground(Color.ORANGE);
                        add(element);
                        element.addMouseListener(new ForMouseInElementsList());
                        element.addMouseWheelListener(new ForMouseInElementsList());
                        element.setVisible(false);
                        pposition += startHeight;
                    }
                }
            }
            // Установка размера компонента ElementsList----------------------------------------------------------------
            int currentHeight;
            if (elementList.isEmpty()) {
                currentHeight = startHeight + (int) (startHeight * 0.35);
            } else {
                currentHeight = startHeight * elementList.size() + (int) (startHeight * 0.35);
            }
            setSize(startWidth, currentHeight);
            //----------------------------------------------------------------------------------------------------------
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец внутреннего класса Resize

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс для ElementList (рамка)
    class Border extends JComponent {
        // Рамка делается отдельно, для возможности установки поверх добавленных элементов и последующего ограничения
        // вхождения курсора (contains) по ее границам------------------------------------------------------------------
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2D.setColor(Color.black);
            g2D.setStroke(new BasicStroke(1.5F));
            g2D.drawRoundRect(2, 0, getWidth() - 4, (int) (height * 0.95), 10, 10);
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец внутреннего класса Border

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс ElementsList (слушатели для мыши)
    class ForMouseInElementsList extends MouseAdapter {
        private JLabel label;

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mousePressed(MouseEvent e) {
            // При нажатии левой кнопки мыши устанавливает selectedElement и сообщает слушателям
            if (e.getButton() == 1) {
                selectedElement = label.getText();
                openCloseList();
                fireElementListener();
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseEntered(MouseEvent e) {
            // Для подсвечивания при наведении на кнопку
            label = (JLabel) e.getComponent();
            label.setOpaque(true);
            repaint();
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseExited(MouseEvent e) {
            // Для возврата обычного цвета при выходе курсора с кнопки
            label.setOpaque(false);
            repaint();
        }

        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            // Элементы и ползунок сдвигаются при вращении колесика мыши
            if (e.getWheelRotation() == 1) {
                if (elementList.size() > 8) {
                    elementsUp();
                    scrollbar.sliderUp();
                }
            } else if (e.getWheelRotation() == -1) {
                if (elementList.size() > 8) {
                    elementsDown();
                    scrollbar.sliderDown();
                }
            }
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец внутреннего класса ForMouseInElementsList

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс ElementsList (событие ElementsList компонента)
    class ElementsListEvent extends EventObject {
        public ElementsListEvent(Object source) {
            super(source);
        }
    } // Конец внутреннего класса ElementsListEvent

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс ElementsList (поток для плавного открывания ElementsList)
    class OpenCloseTreed extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Создается коэффициент, зависящий от количества отображаемых в ElementsList элементов
                // если его не добавлять, то рамка будет отображаться не корректно!
                int k = (int) (0.7 * elementList.size());
                // Если высота height достигла нужного размера----------------------------------------------------------
                if (height >= getHeight() + k || height >= startHeight * 8.6) {
                    // Не было открытой до этого
                    if (!isOpen) {
                        for (Component component : getComponents()) {
                            // Если количество элементов больше восьми, добавляет Scrollbar
                            if (elementList.size() > 8) {
                                scrollbar.setBounds(getWidth() - 20, 0,
                                        20, (int) (height * 0.95));
                            }
                            // делаем все компоненты видимыми
                            component.setVisible(true);
                        }
                        // Устанавливается флаг, что окно открыто
                        isOpen = true;
                        break;
                    } else {
                        // Если ElementsList уже открыт, то удаляем из ElementsList все компоненты
                        for (Component component : getComponents()) {
                            if (!component.getClass().equals(Border.class))
                                component.setVisible(false);
                        }
                    }
                }
                //------------------------------------------------------------------------------------------------------
                if (!isOpen) height++;
                else height--;

                border.setBounds(0, 0, getWidth(), height);
                // Если высота height достигла нужного размера (уменьшилась до 0)---------------------------------------
                if (height == 0) {
                    // Устанавливается флаг, что окно закрыто
                    isOpen = false;
                    break;
                }
                //------------------------------------------------------------------------------------------------------
            }
        }
        //--------------------------------------------------------------------------------------------------------------
    } // Конец внутреннего класса OpenCloseTreed

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Внутренний класс ElementsList (слушатель ElementsList выбранного элемента)
    interface SelectedElementListener extends EventListener {
        void selectedElement(ElementsListEvent e);
    } // Конец внутреннего класса SelectedElementListener
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} // Конец класса ElementsList
