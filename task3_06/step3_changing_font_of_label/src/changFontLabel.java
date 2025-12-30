import javax.swing.*;
import java.awt.*;


public class changFontLabel extends JFrame {

    private static final int width = 500;
    private static final int height = 500;
    private final int firstPosition = 5;
    private Slider slider;
    private JSlider jSlider;

    public changFontLabel() {
        super("changFontLabel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(null);


        JLabel text = new JLabel("Text");
        text.setFont(new Font("", Font.BOLD, 5));
        add(text);
        text.setBounds(width / 2 - firstPosition - 5, height / 2 - 100, 250, 200);

        slider = new Slider(5, 100, firstPosition);
        add(slider);
        slider.setBounds(width / 2 - slider.getWidth() / 2, height - slider.getHeight() - 80, slider.getWidth(), slider.getHeight());
        slider.addSliderListener(e -> {
            text.setFont(new Font("", Font.BOLD, slider.getCurrentPosition()));
            jSlider.setValue(slider.getCurrentPosition());
            text.setBounds(width / 2 - slider.getCurrentPosition() - 5, height / 2 - 100, 250, 200);
        });


        jSlider = new JSlider(5, 100, firstPosition);
        add(jSlider);
        jSlider.setBounds(width / 2 - slider.getWidth() / 2, 80, slider.getWidth(), slider.getHeight() / 2);
        jSlider.addChangeListener(e -> {
            text.setFont(new Font("", Font.BOLD, jSlider.getValue()));
            slider.setCurrentPosition(jSlider.getValue());
            text.setBounds(width / 2 - jSlider.getValue() - 5, height / 2 - 100, 250, 200);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(changFontLabel::new);
    }
}


