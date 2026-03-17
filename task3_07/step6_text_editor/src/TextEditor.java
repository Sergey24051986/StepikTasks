import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Mon Mar 09 10:56:09 MSK 2026
 */



/**
 * @author Serge
 */
public class TextEditor extends JFrame {
    public TextEditor() {
        initComponents();
        setSize(1000, 500);
        setVisible(true);

    }
    /**
     * Через файловый менеджер настроенный на папку <code>input</code> <br/>
     * Загружает файлы формата <code>TXT</code> с кодировкой <code>UTF-8</code>
     */
    private void openFile(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser("task3_07/step6_text_editor/input/");
        int res = fileChooser.showOpenDialog(this);

        File file;
        if (res == 0) {
            file = fileChooser.getSelectedFile();
            try {
                String text = Files.readString(Paths.get(file.getPath()));
                textArea.setText(text);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else return;
        String name  = file.getName();
        fileName.setText(name.substring(0, name.length() - 4));
    }

    /**
     * Создается новый файл в папке <code>output</code> с именем: <code>старое имя + (Измененный).txt</code>
     */
    private void save(ActionEvent e) {
        File file = new File("task3_07/step6_text_editor/output/" + fileName.getText() + " (Измененный).txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.writeString(Paths.get(file.getPath()), textArea.getText(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(this, "Файл " + file.getName() + " сохранен по пути: " + file.getPath());
    }

    /**
     * Предлагает создать файл через файловый менеджер<br/>
     * По умолчанию имя файла - <code>старое имя + (Измененный).txt</code>
     */
    private void saveAs(ActionEvent e) {
        String path = "task3_07/step6_text_editor/output/";
        JFileChooser fileChooser = new JFileChooser(path);
        fileChooser.setSelectedFile(new File(path + fileName.getText()  + " (Измененный).txt"));
        int res = fileChooser.showSaveDialog(this);

        if (res != 0) return;

        File file = fileChooser.getSelectedFile();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.writeString(Paths.get(file.getPath()), textArea.getText(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(this, "Файл " + file.getName() + " сохранен по пути: " + file.getPath());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("TextEditor");
        scrollPane1 = new JScrollPane();
        textArea = new JTextArea();
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        openFile = new JButton();
        fileNameTitle = new JLabel();
        fileName = new JLabel();
        rightPanel = new JPanel();
        save = new JButton();
        saveAs = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {

            //---- textArea ----
            textArea.setFont(new Font("Calibri", Font.PLAIN, 16));
            textArea.setLineWrap(true);
            textArea.setMargin(new Insets(10, 10, 10, 10));
            scrollPane1.setViewportView(textArea);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== mainPanel ========
        {
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

            //======== leftPanel ========
            {
                leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

                //---- openFile ----
                openFile.setText(bundle.getString("TextEditor.openFile.text"));
                openFile.setFont(new Font("Arial", Font.PLAIN, 20));
                openFile.addActionListener(e -> openFile(e));
                leftPanel.add(openFile);

                //---- fileNameTitle ----
                fileNameTitle.setText(bundle.getString("TextEditor.fileNameTitle.text"));
                fileNameTitle.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 13));
                leftPanel.add(fileNameTitle);
                leftPanel.add(fileName);
            }
            mainPanel.add(leftPanel);

            //======== rightPanel ========
            {
                rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- save ----
                save.setText(bundle.getString("TextEditor.save.text"));
                save.setFont(new Font("Arial", Font.PLAIN, 20));
                save.addActionListener(e -> save(e));
                rightPanel.add(save);

                //---- saveAs ----
                saveAs.setText(bundle.getString("TextEditor.saveAs.text"));
                saveAs.setFont(new Font("Arial", Font.PLAIN, 20));
                saveAs.addActionListener(e -> saveAs(e));
                rightPanel.add(saveAs);
            }
            mainPanel.add(rightPanel);
        }
        contentPane.add(mainPanel, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTextArea textArea;
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JButton openFile;
    private JLabel fileNameTitle;
    private JLabel fileName;
    private JPanel rightPanel;
    private JButton save;
    private JButton saveAs;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

void main() {
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException |
             ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    SwingUtilities.invokeLater(TextEditor::new);
}
