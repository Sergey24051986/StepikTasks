import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Feb 27 22:15:41 MSK 2026
 */



/**
 * @author Serge
 */
public class CarBase extends JFrame {
    public CarBase() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        carBase = new TreeSet();
        base = new File("task3_07/step5_registration_form/src/carbase.txt");

        if (base.exists()) {
            try {
                Scanner in = new Scanner(base);
                while (in.hasNextLine())
                    carBase.add(in.nextLine());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        initComponents();
        edit.setEnabled(false);
        removeNote.setEnabled(false);
        open.setEnabled(false);

        updateListModel();

        setVisible(true);
    }

    private void carBaeListValueChanged(ListSelectionEvent e) {
        edit.setEnabled(true);
        removeNote.setEnabled(true);
        open.setEnabled(true);
    }

    private void open(ActionEvent e) {
        InfoWindow infoWindow = new InfoWindow(this);
        infoWindow.setTitle("Просмотр данных");

        int i = 0;
        ArrayList<String> list = new ArrayList<>(Arrays.asList(fullData[carBaseList.getSelectedIndex()].split("; ")));
        list.set(9, list.get(9) + list.remove(10));

        for (Component c: infoWindow.getContentPanel().getComponents()) {
            JLabel label = (JLabel) c;
            if (label.getText().isEmpty())
                label.setText(list.get(i++));
        }
        infoWindow.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        infoWindow.pack();
        infoWindow.setVisible(true);
        updateListModel();
    }

    private void edit(ActionEvent e) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setTitle("Внесите необходимые изменения!");
        registrationForm.setButtonSaveText("Сохранить изменения!");

        int i = 0;
        ArrayList<String> list = new ArrayList<>(Arrays.asList(fullData[carBaseList.getSelectedIndex()].split("; ")));
        String fuel = "";
        if (list.contains("бензиновый")) {
            fuel = "бензиновый";
            list.remove("бензиновый");
        } else {
            fuel = "дизельный";
            list.remove("дизельный");
        }

        for (Component c: registrationForm.getContentPane().getComponents()) {
            if (c.getClass().equals(JTextField.class)) {
                // Проверка текстовых полей
                JTextField textField = (JTextField) c;
                if (!textField.isEnabled())
                    continue;
                textField.setText(list.get(i++));
            } else if (c.getClass().equals(JComboBox.class)) {
                // Проверка выпадающих списков
                JComboBox comboBox = (JComboBox) c;
                comboBox.setSelectedItem(list.get(i++));
            } else if (c.getClass().equals(JRadioButton.class)) {
                // Установка радио кнопки
                JRadioButton radioButton = (JRadioButton) c;
                if (radioButton.getText().equals(fuel))
                    radioButton.setSelected(true);
            }
        }
        registrationForm.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        registrationForm.pack();
        registrationForm.setVisible(true);

        check(registrationForm.getAnswer());
    }

    private void check(String[] data) {
        String answer = data[0];
        String str = data[1];

        if (answer.equals("1")) {
            carBase.add(str);
            JOptionPane.showMessageDialog(this, "Запись добавлена!");
        } else if (answer.equals("0")) {
            carBase.remove(fullData[carBaseList.getSelectedIndex()]);
            carBase.add(str);
            JOptionPane.showMessageDialog(this, "Изменения сохранены!");
        }
        updateListModel();
    }

    private void updateListModel() {
        // Массив сокращенных строк для визуального отображения
        String[] frontData = new String[carBase.size()];
        // Массив с полными данными
        fullData = new String[carBase.size()];

        int i = 0;
        for (String s: carBase) {
            fullData[i] = s;
            String[] data = s.split("; ");
            frontData[i++] = data[0] + " " + data[1] + " " + data[6] + " " + data[3] + " VIN: " + data[2];
        }
        // Создание нового ListModel и внесение его в JList
        ListModel<String> bigData = new AbstractListModel<String>() {
            public int getSize() {
                return carBase.size();
            }

            public String getElementAt(int i) {
                return frontData[i];
            }
        };
        carBaseList.setModel(bigData);
        // Перезапись содержимого файла новым списком
        try {
            Files.write(Paths.get("task3_07/step5_registration_form/src/carbase.txt"), carBase, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        // Выключение кнопок
        edit.setEnabled(false);
        removeNote.setEnabled(false);
        open.setEnabled(false);
    }

    private void addNote(ActionEvent e) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        registrationForm.pack();
        registrationForm.setVisible(true);
        check(registrationForm.getAnswer());
    }

    private void removeNote(ActionEvent e) {
        carBase.remove(fullData[carBaseList.getSelectedIndex()]);
        System.out.println(carBase);
        updateListModel();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        addNote = new JButton();
        open = new JButton();
        edit = new JButton();
        removeNote = new JButton();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        carBaseList = new JList();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "insets 0,hidemode 3,gap 5 5",
                // columns
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]",
                // rows
                "[fill]"));

            //---- addNote ----
            addNote.setText("\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0437\u0430\u043f\u0438\u0441\u044c");
            addNote.addActionListener(e -> addNote(e));
            panel1.add(addNote, "cell 1 0");

            //---- open ----
            open.setText("\u041e\u0442\u043a\u0440\u044b\u0442\u044c");
            open.addActionListener(e -> open(e));
            panel1.add(open, "cell 17 0");

            //---- edit ----
            edit.setText("\u0420\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c");
            edit.addActionListener(e -> edit(e));
            panel1.add(edit, "cell 18 0");

            //---- removeNote ----
            removeNote.setText("\u0423\u0434\u0430\u043b\u0438\u0442\u044c");
            removeNote.addActionListener(e -> removeNote(e));
            panel1.add(removeNote, "cell 19 0");
        }
        contentPane.add(panel1, BorderLayout.SOUTH);

        //---- label1 ----
        label1.setText("\u0421\u043f\u0438\u0441\u043e\u043a \u0437\u0430\u0440\u0435\u0433\u0435\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u044b\u0445 \u0430\u0432\u0442\u043e\u043c\u043e\u0431\u0438\u043b\u0435\u0439");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 18));
        contentPane.add(label1, BorderLayout.NORTH);

        //======== scrollPane1 ========
        {

            //---- carBaseList ----
            carBaseList.addListSelectionListener(e -> carBaeListValueChanged(e));
            scrollPane1.setViewportView(carBaseList);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JButton addNote;
    private JButton open;
    private JButton edit;
    private JButton removeNote;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JList carBaseList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private SortedSet<String> carBase;
    private String[] fullData;
    private File base;

    static void main() {
        SwingUtilities.invokeLater(CarBase::new);
    }
}
