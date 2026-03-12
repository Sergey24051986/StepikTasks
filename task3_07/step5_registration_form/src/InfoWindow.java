import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Wed Feb 25 15:59:17 MSK 2026
 */



/**
 * @author Serge
 */
public class InfoWindow extends JDialog {
    public InfoWindow(Window owner) {
        super(owner);
        initComponents();
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    private void ok(ActionEvent e) {
        answer = 1;
        dispose();
    }

    public int getAnswer() {
        return answer;
    }

    private void button1(ActionEvent e) {dispose();}

    public void setTitle(String text) {
        title.setText(text);
        okButton.setVisible(false);
    }

    public void initComponents() {
        answer = 0;
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        title = new JLabel();
        label1 = new JLabel();
        brand = new JLabel();
        model = new JLabel();
        label3 = new JLabel();
        vin = new JLabel();
        label6 = new JLabel();
        year = new JLabel();
        label8 = new JLabel();
        color = new JLabel();
        label10 = new JLabel();
        mileage = new JLabel();
        generation = new JLabel();
        label18 = new JLabel();
        label19 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        engine = new JLabel();
        label14 = new JLabel();
        capacity = new JLabel();
        config = new JLabel();
        type = new JLabel();
        fuel = new JLabel();
        label4 = new JLabel();
        ecoclass = new JLabel();
        label2 = new JLabel();
        timingBeltDrive = new JLabel();
        timingType = new JLabel();
        label5 = new JLabel();
        power = new JLabel();
        label7 = new JLabel();
        label9 = new JLabel();
        transmission = new JLabel();
        label11 = new JLabel();
        gearsNum = new JLabel();
        label15 = new JLabel();
        drive = new JLabel();
        bodyType = new JLabel();
        doorNum = new JLabel();
        label20 = new JLabel();
        wheel = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        button1 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets 10,hidemode 3,gap 10 10",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
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

                //---- title ----
                title.setText("\u041f\u0440\u043e\u0432\u0435\u0440\u043a\u0430 \u0434\u0430\u043d\u043d\u044b\u0445!");
                title.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 16));
                title.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(title, "cell 0 0 6 1");

                //---- label1 ----
                label1.setText("\u0410\u0432\u0442\u043e\u043c\u043e\u0431\u0438\u043b\u044c:");
                contentPanel.add(label1, "cell 0 1");

                //---- brand ----
                brand.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(brand, "cell 2 1 4 1");

                //---- model ----
                model.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(model, "cell 2 1 4 1");

                //---- label3 ----
                label3.setText("VIN \u043d\u043e\u043c\u0435\u0440:");
                contentPanel.add(label3, "cell 0 2");

                //---- vin ----
                vin.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(vin, "cell 2 2 4 1");

                //---- label6 ----
                label6.setText("\u0413\u043e\u0434 \u0432\u044b\u043f\u0443\u0441\u043a\u0430:");
                contentPanel.add(label6, "cell 0 3");

                //---- year ----
                year.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(year, "cell 2 3 4 1");

                //---- label8 ----
                label8.setText("\u0426\u0432\u0435\u0442:");
                contentPanel.add(label8, "cell 0 4");

                //---- color ----
                color.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(color, "cell 2 4 4 1");

                //---- label10 ----
                label10.setText("\u0421 \u043f\u0440\u043e\u0431\u0435\u0433\u043e\u043c (\u043a\u043c):");
                contentPanel.add(label10, "cell 0 5");

                //---- mileage ----
                mileage.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(mileage, "cell 2 5 4 1");

                //---- generation ----
                generation.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(generation, "cell 2 1 4 1");

                //---- label18 ----
                label18.setText("\u0422\u0438\u043f \u043a\u0443\u0437\u043e\u0432\u0430:");
                contentPanel.add(label18, "cell 0 6");

                //---- label19 ----
                label19.setText("\u041f\u043e\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u0440\u0443\u043b\u0435\u0432\u043e\u0433\u043e \u043a\u043e\u043b\u0435\u0441\u0430:");
                contentPanel.add(label19, "cell 0 7");

                //---- label12 ----
                label12.setText("\u0425\u0430\u0440\u0430\u043a\u0442\u0435\u0440\u0438\u0441\u0442\u0438\u043a\u0438 \u0434\u0432\u0438\u0433\u0430\u0442\u0435\u043b\u044f:");
                label12.setHorizontalAlignment(SwingConstants.CENTER);
                label12.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 14));
                contentPanel.add(label12, "cell 0 8 6 1");

                //---- label13 ----
                label13.setText("\u041c\u043e\u0434\u0435\u043b\u044c \u0434\u0432\u0438\u0433\u0430\u0442\u0435\u043b\u044f:");
                contentPanel.add(label13, "cell 0 9");

                //---- engine ----
                engine.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(engine, "cell 2 9 4 1");

                //---- label14 ----
                label14.setText("\u041e\u0431\u044a\u0435\u043c\u043e\u043c (cm3):");
                contentPanel.add(label14, "cell 0 10");

                //---- capacity ----
                capacity.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(capacity, "cell 2 10 4 1");

                //---- config ----
                config.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(config, "cell 2 9 4 1");

                //---- type ----
                type.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(type, "cell 2 9 4 1");

                //---- fuel ----
                fuel.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(fuel, "cell 2 9 4 1");

                //---- label4 ----
                label4.setText("\u042d\u043a\u043e\u043b\u043e\u0433\u0438\u0447\u0435\u0441\u043a\u0438\u0439 \u043a\u043b\u0430\u0441\u0441:");
                contentPanel.add(label4, "cell 0 11");

                //---- ecoclass ----
                ecoclass.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(ecoclass, "cell 2 11 4 1");

                //---- label2 ----
                label2.setText("\u041f\u0440\u0438\u0432\u043e\u0434 \u043a\u043b\u0430\u043f\u0430\u043d\u043e\u0432:");
                contentPanel.add(label2, "cell 0 12");

                //---- timingBeltDrive ----
                timingBeltDrive.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(timingBeltDrive, "cell 2 12 4 1");

                //---- timingType ----
                timingType.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(timingType, "cell 2 12 4 1");

                //---- label5 ----
                label5.setText("\u041c\u043e\u0449\u043d\u043e\u0441\u0442\u044c (\u043b.\u0441.):");
                contentPanel.add(label5, "cell 0 13");

                //---- power ----
                power.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(power, "cell 2 13 4 1");

                //---- label7 ----
                label7.setText("\u0422\u0440\u0430\u043d\u0441\u043c\u0438\u0441\u0441\u0438\u044f:");
                label7.setHorizontalAlignment(SwingConstants.CENTER);
                label7.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 14));
                contentPanel.add(label7, "cell 0 14 6 1");

                //---- label9 ----
                label9.setText("\u041a\u043e\u0440\u043e\u0431\u043a\u0430 \u043f\u0435\u0440\u0435\u0434\u0430\u0447:");
                contentPanel.add(label9, "cell 0 15");

                //---- transmission ----
                transmission.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(transmission, "cell 2 15 4 1");

                //---- label11 ----
                label11.setText("\u041a\u043e\u043b\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0441\u0442\u0443\u043f\u0435\u043d\u0435\u0439:");
                contentPanel.add(label11, "cell 0 16");

                //---- gearsNum ----
                gearsNum.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(gearsNum, "cell 2 16 4 1");

                //---- label15 ----
                label15.setText("\u041f\u0440\u0438\u0432\u043e\u0434:");
                contentPanel.add(label15, "cell 0 17");

                //---- drive ----
                drive.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(drive, "cell 2 17 4 1");

                //---- bodyType ----
                bodyType.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(bodyType, "cell 2 6 4 1");

                //---- doorNum ----
                doorNum.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(doorNum, "cell 2 6 4 1");

                //---- label20 ----
                label20.setText("(\u0434\u0432\u0435\u0440(\u0438/\u0435\u0439))");
                label20.setFont(new Font("Inter", Font.ITALIC, 13));
                contentPanel.add(label20, "cell 2 6 4 1");

                //---- wheel ----
                wheel.setFont(new Font("Inter", Font.PLAIN, 14));
                contentPanel.add(wheel, "cell 2 7 4 1");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]" +
                    "[fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u044c");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, "cell 0 0");

                //---- button1 ----
                button1.setText("\u041e\u0442\u043c\u0435\u043d\u0430");
                button1.addActionListener(e -> button1(e));
                buttonBar.add(button1, "cell 1 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel title;
    private JLabel label1;
    private JLabel brand;
    private JLabel model;
    private JLabel label3;
    private JLabel vin;
    private JLabel label6;
    private JLabel year;
    private JLabel label8;
    private JLabel color;
    private JLabel label10;
    private JLabel mileage;
    private JLabel generation;
    private JLabel label18;
    private JLabel label19;
    private JLabel label12;
    private JLabel label13;
    private JLabel engine;
    private JLabel label14;
    private JLabel capacity;
    private JLabel config;
    private JLabel type;
    private JLabel fuel;
    private JLabel label4;
    private JLabel ecoclass;
    private JLabel label2;
    private JLabel timingBeltDrive;
    private JLabel timingType;
    private JLabel label5;
    private JLabel power;
    private JLabel label7;
    private JLabel label9;
    private JLabel transmission;
    private JLabel label11;
    private JLabel gearsNum;
    private JLabel label15;
    private JLabel drive;
    private JLabel bodyType;
    private JLabel doorNum;
    private JLabel label20;
    private JLabel wheel;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private static int answer;
}
