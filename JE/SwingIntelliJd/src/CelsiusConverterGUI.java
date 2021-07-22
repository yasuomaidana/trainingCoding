import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelsiusConverterGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField celsiousTextField;
    private JLabel celsiousLabel;
    private JButton convertButton;
    private JLabel farenheitLabel;

    public CelsiusConverterGUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tempFahr = (int) ((Double.parseDouble(celsiousTextField.getText()))*1.8+32);
                farenheitLabel.setText(tempFahr + " Fahrenheit");
            }
        });
    }
    public static void main(String[] args){
        JFrame frame = new CelsiusConverterGUI("My Celsius converter");
        frame.setVisible(true);
    }
}
