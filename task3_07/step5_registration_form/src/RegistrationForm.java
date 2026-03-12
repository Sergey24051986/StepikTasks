import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Sun Feb 22 00:13:18 MSK 2026
 */


/**
 * @author Serge
 */
public class RegistrationForm extends JDialog {

    public RegistrationForm() {
        // При закрытии окно удаляется
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        initComponents();

        // Для выбора только одного из двух параметров
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(gasoline);
        buttonGroup.add(diesel);

        // Заполнение поля с годами
        for (int i = 2026; i > 1949; i--)
            yearBox.addItem(i + "");
    }

    /**
     * Для проверки заполненности всех полей!
     */
    private boolean check() {
        boolean res = true;
        for (Component c: getContentPane().getComponents()) {
            if (c.getClass().equals(JTextField.class)) {
                // Проверка текстовых полей
                JTextField textField = (JTextField) c;
                if (!textField.isEnabled())
                    continue;
                if (textField.getText().isEmpty()) {
                    res = false;
                    break;
                }
            } else if (c.getClass().equals(JComboBox.class)) {
                // Проверка выпадающих списков
                JComboBox comboBox = (JComboBox) c;
                if (comboBox.getSelectedItem() == null) {
                    res = false;
                    break;
                } else if (comboBox.getSelectedItem().toString().equals("-")) {
                    res = false;
                    break;
                }
            }
        }
        if (!gasoline.isSelected() && !diesel.isSelected()) res = false;
        if (!res) JOptionPane.showMessageDialog(this,"Не все данные указаны!");
        return res;
    }

    /**
     * Блокировка компонентов при выборе параметра
     */
    private void otherColor(ActionEvent e) {
        otherColorField.setEnabled(otherColor.isSelected());
        colorBox.setEnabled(!otherColor.isSelected());
    }

    public void setButtonSaveText(String newText) {
        save.setText(newText);
    }

    /**
     * Если возвращает -1 - произошла отмена, изменения не вносятся<br/>
     * Если возвращает 0 - было задействовано изменение записи<br/>
     * Если возвращает 1 - было задействовано внесение новой записи
     */
    private void save(ActionEvent e) {
        String str = "";
        if (!check()) return;

        for (Component c: getContentPane().getComponents()) {
            if (c.getClass().equals(JTextField.class)) {
                // Сбор данных с текстовых полей
                JTextField textField = (JTextField) c;
                if (!textField.isEnabled()) continue;
                str += textField.getText() + "; ";
            } else if (c.getClass().equals(JComboBox.class)) {
                // Сбор данных с выпадающих списков
                JComboBox comboBox = (JComboBox) c;
                if (!comboBox.isEnabled()) continue;
                str += comboBox.getSelectedItem().toString() + "; ";
            } else if (c.getClass().equals(JRadioButton.class)) {
                // Сбор данных с радио кнопки
                JRadioButton radioButton = (JRadioButton) c;
                if (radioButton.isSelected())
                    str += radioButton.getText() + "; ";
            }
        }
        str = str.substring(0, str.length() - 2);
        // Проверочное окно с данными
        InfoWindow infoWindow = new InfoWindow(this);
        int i = 0;
        ArrayList<String> list = new ArrayList<>(Arrays.asList(str.split("; ")));
        list.set(9, list.get(9) + list.remove(10));

        for (Component c: infoWindow.getContentPanel().getComponents()) {
            JLabel label = (JLabel) c;
            if (label.getText().isEmpty())
                label.setText(list.get(i++));
        }
        infoWindow.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        infoWindow.pack();
        infoWindow.setVisible(true);

        // Настройка значения поля answer
        if (infoWindow.getAnswer() == 1 && save.getText().equals("Зарегистрировать автомобиль")) {
            answer = new String[]{"1", str};
            dispose();
        } else if (infoWindow.getAnswer() == 1 && save.getText().equals("Сохранить изменения!")) {
            answer = new String[]{"0", str};
            dispose();
        }
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setTitle(String newText) {
        mainTitle.setText(newText);
    }

    private void initComponents() {
        answer = new String[]{"-1", ""};
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        mainTitle = new JLabel();
        mainDataTitle = new JLabel();
        carBrand = new JLabel();
        carBrandBox = new JComboBox();
        carModel = new JLabel();
        carModelBox = new JComboBox();
        vin = new JLabel();
        vinField = new JTextField();
        year = new JLabel();
        yearBox = new JComboBox<>();
        color = new JLabel();
        colorBox = new JComboBox<>();
        otherColor = new JCheckBox();
        otherColorField = new JTextField();
        mileage = new JLabel();
        mileageField = new JTextField();
        generation = new JLabel();
        generationBox = new JComboBox();
        engineTitle = new JLabel();
        engineModel = new JLabel();
        engineModelBox = new JComboBox();
        engineCapacity = new JLabel();
        engineCapacityField = new JTextField();
        cm3 = new JLabel();
        engineConfig = new JLabel();
        engineConfigBox = new JComboBox<>();
        cylindersNum = new JLabel();
        cylindersNumBox = new JComboBox<>();
        engineType = new JLabel();
        engineTypeBox = new JComboBox<>();
        gasoline = new JRadioButton();
        diesel = new JRadioButton();
        ecoClass = new JLabel();
        ecoClassBox = new JComboBox<>();
        timingBeltDrive = new JLabel();
        timingBeltDriveBox = new JComboBox<>();
        timingType = new JLabel();
        timingTypeBox = new JComboBox<>();
        power = new JLabel();
        powerField = new JTextField();
        transmissionTitle = new JLabel();
        transmissionType = new JLabel();
        transmissionTypeBox = new JComboBox<>();
        gearsNum = new JLabel();
        gearsNumBox = new JComboBox<>();
        drive = new JLabel();
        driveBox = new JComboBox<>();
        otherDataTitle = new JLabel();
        bodyType = new JLabel();
        bodyTypeBox = new JComboBox<>();
        doorsNum = new JLabel();
        doorsNumBox = new JComboBox<>();
        wheel = new JLabel();
        wheelBox = new JComboBox<>();
        save = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 10,hidemode 3,gap 10 10",
            // columns
            "[fill]" +
            "[182,fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[fill]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- mainTitle ----
        mainTitle.setText("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u043d\u043e\u0432\u043e\u0433\u043e \u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0438\u043b\u044f");
        mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainTitle.setFont(new Font("Inter", Font.BOLD, 20));
        contentPane.add(mainTitle, "cell 1 0 6 1");

        //---- mainDataTitle ----
        mainDataTitle.setText("\u041e\u0441\u043d\u043e\u0432\u043d\u044b\u0435 \u0434\u0430\u043d\u043d\u044b\u0435");
        mainDataTitle.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 14));
        contentPane.add(mainDataTitle, "cell 2 1");

        //---- carBrand ----
        carBrand.setText("\u041c\u0430\u0440\u043a\u0430 \u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0438\u043b\u044f:");
        contentPane.add(carBrand, "cell 1 2");

        //---- carBrandBox ----
        carBrandBox.setEditable(true);
        contentPane.add(carBrandBox, "cell 2 2");

        //---- carModel ----
        carModel.setText("\u041c\u043e\u0434\u0435\u043b\u044c \u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0438\u043b\u044f:");
        contentPane.add(carModel, "cell 4 2");

        //---- carModelBox ----
        carModelBox.setEditable(true);
        contentPane.add(carModelBox, "cell 6 2");

        //---- vin ----
        vin.setText("VIN");
        contentPane.add(vin, "cell 1 3");

        //---- vinField ----
        vinField.setColumns(20);
        contentPane.add(vinField, "cell 2 3");

        //---- year ----
        year.setText("\u0413\u043e\u0434 \u0432\u044b\u043f\u0443\u0441\u043a\u0430:");
        contentPane.add(year, "cell 4 3");

        //---- yearBox ----
        yearBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-"
        }));
        contentPane.add(yearBox, "cell 6 3");

        //---- color ----
        color.setText("\u0426\u0432\u0435\u0442");
        contentPane.add(color, "cell 1 4");

        //---- colorBox ----
        colorBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "Aqua (\u043c\u043e\u0440\u0441\u043a\u0430\u044f \u0432\u043e\u043b\u043d\u0430)",
            "Black (\u0447\u0435\u0440\u043d\u044b\u0439)",
            "Blue (\u0433\u043e\u043b\u0443\u0431\u043e\u0439)",
            "Fuchsia (\u0444\u0443\u043a\u0441\u0438\u043d)",
            "Gray (\u0441\u0435\u0440\u044b\u0439)",
            "Green (\u0437\u0435\u043b\u0435\u043d\u044b\u0439)",
            "Lime (\u044f\u0440\u043a\u043e-\u0437\u0435\u043b\u0435\u043d\u044b\u0439)",
            "Maroon (\u0442\u0435\u043c\u043d\u043e-\u0431\u043e\u0440\u0434\u043e\u0432\u044b\u0439)",
            "Navy (\u0442\u0435\u043c\u043d\u043e-\u0441\u0438\u043d\u0438\u0439)",
            "Olive (\u043e\u043b\u0438\u0432\u043a\u043e\u0432\u044b\u0439)",
            "Purple (\u0444\u0438\u043e\u043b\u0435\u0442\u043e\u0432\u044b\u0439)",
            "Red (\u043a\u0440\u0430\u0441\u043d\u044b\u0439)",
            "Silver (\u0441\u0435\u0440\u0435\u0431\u0440\u044f\u043d\u044b\u0439)",
            "Teal (\u0441\u0435\u0440\u043e-\u0437\u0435\u043b\u0435\u043d\u044b\u0439)",
            "White (\u0431\u0435\u043b\u044b\u0439)",
            "Yellow (\u0436\u0435\u043b\u0442\u044b\u0439)"
        }));
        contentPane.add(colorBox, "cell 2 4");

        //---- otherColor ----
        otherColor.setText("\u0414\u0440\u0443\u0433\u043e\u0439 \u0446\u0432\u0435\u0442");
        otherColor.addActionListener(e -> otherColor(e));
        contentPane.add(otherColor, "cell 4 4");

        //---- otherColorField ----
        otherColorField.setEnabled(false);
        otherColorField.setColumns(20);
        contentPane.add(otherColorField, "cell 6 4");

        //---- mileage ----
        mileage.setText("\u041f\u0440\u043e\u0431\u0435\u0433:");
        contentPane.add(mileage, "cell 1 5");
        contentPane.add(mileageField, "cell 2 5");

        //---- generation ----
        generation.setText("\u041f\u043e\u043a\u043e\u043b\u0435\u043d\u0438\u0435:");
        contentPane.add(generation, "cell 4 5");

        //---- generationBox ----
        generationBox.setEditable(true);
        contentPane.add(generationBox, "cell 6 5");

        //---- engineTitle ----
        engineTitle.setText("\u0414\u0432\u0438\u0433\u0430\u0442\u0435\u043b\u044c");
        engineTitle.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 14));
        contentPane.add(engineTitle, "cell 2 6");

        //---- engineModel ----
        engineModel.setText("\u041c\u043e\u0434\u0435\u043b\u044c \u0434\u0432\u0438\u0433\u0430\u0442\u0435\u043b\u044f:");
        contentPane.add(engineModel, "cell 1 7");

        //---- engineModelBox ----
        engineModelBox.setEditable(true);
        contentPane.add(engineModelBox, "cell 2 7");

        //---- engineCapacity ----
        engineCapacity.setText("\u0420\u0430\u0431\u043e\u0447\u0438\u0439 \u043e\u0431\u044a\u0435\u043c \u0434\u0432\u0438\u0433\u0430\u0442\u0435\u043b\u044f:");
        contentPane.add(engineCapacity, "cell 4 7");

        //---- engineCapacityField ----
        engineCapacityField.setColumns(20);
        contentPane.add(engineCapacityField, "cell 6 7");

        //---- cm3 ----
        cm3.setText("(\u0441\u043c3)");
        cm3.setFont(new Font("Inter", Font.ITALIC, 13));
        contentPane.add(cm3, "cell 8 7");

        //---- engineConfig ----
        engineConfig.setText("\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f \u0434\u0432\u0438\u0433\u0430\u0442\u0435\u043b\u044f:");
        contentPane.add(engineConfig, "cell 1 8");

        //---- engineConfigBox ----
        engineConfigBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "R",
            "V",
            "B (\u041e\u043f\u043f\u043e\u0437\u0438\u0442\u043d\u044b\u0439)",
            "VR",
            "W",
            "\u0421\u043e \u0432\u0441\u0442\u0440\u0435\u0447\u043d\u044b\u043c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0435\u043c \u043f\u043e\u0440\u0448\u043d\u0435\u0439",
            "\u0417\u0432\u0435\u0437\u0434\u043e\u043e\u0431\u0440\u0430\u0437\u043d\u044b\u0439",
            "Y"
        }));
        contentPane.add(engineConfigBox, "cell 2 8");

        //---- cylindersNum ----
        cylindersNum.setText("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0446\u0438\u043b\u0438\u043d\u0434\u0440\u043e\u0432:");
        contentPane.add(cylindersNum, "cell 4 8");

        //---- cylindersNumBox ----
        cylindersNumBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18"
        }));
        contentPane.add(cylindersNumBox, "cell 6 8");

        //---- engineType ----
        engineType.setText("\u0422\u0438\u043f \u0434\u0432\u0438\u0433\u0430\u0442\u0435\u043b\u044f:");
        contentPane.add(engineType, "cell 1 9");

        //---- engineTypeBox ----
        engineTypeBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "\u0430\u0442\u043c\u043e\u0441\u0444\u0435\u0440\u043d\u044b\u0439",
            "\u0442\u0443\u0440\u0431\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u044b\u0439"
        }));
        contentPane.add(engineTypeBox, "cell 2 9");

        //---- gasoline ----
        gasoline.setText("\u0431\u0435\u043d\u0437\u0438\u043d\u043e\u0432\u044b\u0439");
        contentPane.add(gasoline, "cell 4 9");

        //---- diesel ----
        diesel.setText("\u0434\u0438\u0437\u0435\u043b\u044c\u043d\u044b\u0439");
        contentPane.add(diesel, "cell 6 9");

        //---- ecoClass ----
        ecoClass.setText("\u042d\u043a\u043e\u043b\u043e\u0433\u0438\u0447\u0435\u0441\u043a\u0438\u0439 \u043a\u043b\u0430\u0441\u0441:");
        contentPane.add(ecoClass, "cell 1 10");

        //---- ecoClassBox ----
        ecoClassBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "Euro-1",
            "Euro-2",
            "Euro-3",
            "Euro-4",
            "Euro-5",
            "Euro-6"
        }));
        contentPane.add(ecoClassBox, "cell 2 10");

        //---- timingBeltDrive ----
        timingBeltDrive.setText("\u041f\u0440\u0438\u0432\u043e\u0434 \u0413\u0420\u041c:");
        contentPane.add(timingBeltDrive, "cell 4 10");

        //---- timingBeltDriveBox ----
        timingBeltDriveBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "\u0440\u0435\u043c\u0435\u043d\u044c",
            "\u0446\u0435\u043f\u044c"
        }));
        contentPane.add(timingBeltDriveBox, "cell 6 10");

        //---- timingType ----
        timingType.setText("\u0422\u0438\u043c \u0441\u0438\u0441\u0442\u0435\u043c\u044b \u0413\u0420\u041c:");
        contentPane.add(timingType, "cell 1 11");

        //---- timingTypeBox ----
        timingTypeBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "OHV (Over Head Valve)",
            "SOHC (Single Overhead Camshaft)",
            "DOHC (Double Overhead Camshaft)"
        }));
        contentPane.add(timingTypeBox, "cell 2 11");

        //---- power ----
        power.setText("\u041c\u043e\u0449\u043d\u043e\u0441\u0442\u044c (\u043b.\u0441.)");
        contentPane.add(power, "cell 4 11");
        contentPane.add(powerField, "cell 6 11");

        //---- transmissionTitle ----
        transmissionTitle.setText("\u0422\u0440\u0430\u043d\u0441\u043c\u0438\u0441\u0441\u0438\u044f");
        transmissionTitle.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 14));
        contentPane.add(transmissionTitle, "cell 2 12");

        //---- transmissionType ----
        transmissionType.setText("\u0422\u0438\u043f:");
        contentPane.add(transmissionType, "cell 1 13");

        //---- transmissionTypeBox ----
        transmissionTypeBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "\u041c\u041a\u041f\u041f (\u043c\u0435\u0445\u0430\u043d\u0438\u0447\u0435\u0441\u043a\u0430\u044f)",
            "\u0410\u041a\u041f\u041f (\u0430\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0430\u044f)",
            "\u0420\u041a\u041f\u041f (\u0440\u043e\u0431\u043e\u0442\u0438\u0437\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u0430\u044f)",
            "CVT (\u0432\u0430\u0440\u0438\u0430\u0442\u043e\u0440)"
        }));
        contentPane.add(transmissionTypeBox, "cell 2 13");

        //---- gearsNum ----
        gearsNum.setText("\u041a\u043e\u043b\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043f\u0435\u0440\u0435\u0434\u0430\u0447:");
        contentPane.add(gearsNum, "cell 4 13");

        //---- gearsNumBox ----
        gearsNumBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        }));
        contentPane.add(gearsNumBox, "cell 6 13");

        //---- drive ----
        drive.setText("\u041f\u0440\u0438\u0432\u043e\u0434:");
        contentPane.add(drive, "cell 1 14");

        //---- driveBox ----
        driveBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "FWD (\u043f\u0435\u0440\u0435\u0434\u043d\u0438\u0439)",
            "RWD (\u0437\u0430\u0434\u043d\u0438\u0439)",
            "AWD (\u043f\u043e\u043b\u043d\u044b\u0439)"
        }));
        contentPane.add(driveBox, "cell 2 14");

        //---- otherDataTitle ----
        otherDataTitle.setText("\u0414\u0440\u0443\u0433\u0438\u0435 \u0434\u0430\u043d\u043d\u044b\u0435");
        otherDataTitle.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 14));
        contentPane.add(otherDataTitle, "cell 2 15");

        //---- bodyType ----
        bodyType.setText("\u0422\u0438\u043f \u043a\u0443\u0437\u043e\u0432\u0430:");
        contentPane.add(bodyType, "cell 1 16");

        //---- bodyTypeBox ----
        bodyTypeBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "\u0412\u043d\u0435\u0434\u043e\u0440\u043e\u0436\u043d\u0438\u043a",
            "\u041a\u0430\u0431\u0440\u0438\u043e\u043b\u0435\u0442",
            "\u041a\u0443\u043f\u0435",
            "\u041b\u0438\u0444\u0442\u0431\u0435\u043a",
            "\u041c\u0438\u043d\u0438\u0432\u0435\u043d",
            "\u041f\u0438\u043a\u0430\u043f",
            "\u0421\u0435\u0434\u0430\u043d",
            "\u0423\u043d\u0438\u0432\u0435\u0440\u0441\u0430\u043b",
            "\u0424\u0443\u0440\u0433\u043e\u043d",
            "\u0425\u0430\u0442\u0447\u0431\u044d\u043a"
        }));
        contentPane.add(bodyTypeBox, "cell 2 16");

        //---- doorsNum ----
        doorsNum.setText("\u041a\u043e\u043b\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0434\u0432\u0435\u0440\u0435\u0439:");
        contentPane.add(doorsNum, "cell 4 16");

        //---- doorsNumBox ----
        doorsNumBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "1",
            "2",
            "3",
            "4",
            "5"
        }));
        contentPane.add(doorsNumBox, "cell 6 16");

        //---- wheel ----
        wheel.setText("\u0420\u0430\u0441\u043f\u043e\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u0440\u0443\u043b\u044f:");
        contentPane.add(wheel, "cell 1 17");

        //---- wheelBox ----
        wheelBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "\u043b\u0435\u0432\u043e\u0435",
            "\u043f\u0440\u0430\u0432\u043e\u0435"
        }));
        contentPane.add(wheelBox, "cell 2 17");

        //---- save ----
        save.setText("\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0438\u043b\u044c");
        save.addActionListener(e -> save(e));
        contentPane.add(save, "cell 2 19");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel mainTitle;
    private JLabel mainDataTitle;
    private JLabel carBrand;
    private JComboBox carBrandBox;
    private JLabel carModel;
    private JComboBox carModelBox;
    private JLabel vin;
    private JTextField vinField;
    private JLabel year;
    private JComboBox<String> yearBox;
    private JLabel color;
    private JComboBox<String> colorBox;
    private JCheckBox otherColor;
    private JTextField otherColorField;
    private JLabel mileage;
    private JTextField mileageField;
    private JLabel generation;
    private JComboBox generationBox;
    private JLabel engineTitle;
    private JLabel engineModel;
    private JComboBox engineModelBox;
    private JLabel engineCapacity;
    private JTextField engineCapacityField;
    private JLabel cm3;
    private JLabel engineConfig;
    private JComboBox<String> engineConfigBox;
    private JLabel cylindersNum;
    private JComboBox<String> cylindersNumBox;
    private JLabel engineType;
    private JComboBox<String> engineTypeBox;
    private JRadioButton gasoline;
    private JRadioButton diesel;
    private JLabel ecoClass;
    private JComboBox<String> ecoClassBox;
    private JLabel timingBeltDrive;
    private JComboBox<String> timingBeltDriveBox;
    private JLabel timingType;
    private JComboBox<String> timingTypeBox;
    private JLabel power;
    private JTextField powerField;
    private JLabel transmissionTitle;
    private JLabel transmissionType;
    private JComboBox<String> transmissionTypeBox;
    private JLabel gearsNum;
    private JComboBox<String> gearsNumBox;
    private JLabel drive;
    private JComboBox<String> driveBox;
    private JLabel otherDataTitle;
    private JLabel bodyType;
    private JComboBox<String> bodyTypeBox;
    private JLabel doorsNum;
    private JComboBox<String> doorsNumBox;
    private JLabel wheel;
    private JComboBox<String> wheelBox;
    private JButton save;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private SortedSet<String> carBase;
    private String[] answer;

    static void main() {
        SwingUtilities.invokeLater(RegistrationForm::new);
    }
}
