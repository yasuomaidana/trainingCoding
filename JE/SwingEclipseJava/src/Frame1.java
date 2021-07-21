import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame1 {

	private JFrame frame;
	private JLabel LabelToChange;
	private JButton RestoreLabel;
	private JButton FirstButton;
	private boolean seeFirstButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void toggleVisibility() {
		RestoreLabel.setVisible(!seeFirstButton);
		FirstButton.setVisible(seeFirstButton);
	}
	private void initialize() {
		
		seeFirstButton = true;
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		FirstButton = new JButton("Yasuo Button");
		FirstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Changing label");
				LabelToChange.setText("Label Changed");
				seeFirstButton = false;
				toggleVisibility();
			}
		});
		FirstButton.setBounds(185, 237, 117, 29);
		frame.getContentPane().add(FirstButton);
		
		LabelToChange = new JLabel("Holi\n\nLos labels NO pueden tener multiples lineas");
		LabelToChange.setLabelFor(frame);
		LabelToChange.setBounds(80, 49, 342, 64);
		frame.getContentPane().add(LabelToChange);
		
		JTextPane txtpnHola = new JTextPane();
		txtpnHola.setEnabled(false);
		txtpnHola.setEditable(false);
		txtpnHola.setText("Hola\n\nEste es un ejemplo\n\nDe Multiples lineas\n");
		txtpnHola.setBounds(80, 125, 205, 88);
		frame.getContentPane().add(txtpnHola);
		
		RestoreLabel = new JButton("Restore label text");
		RestoreLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LabelToChange.setText("Holi\n\nLos labels NO pueden tener multiples lineas");
				seeFirstButton=true;
				toggleVisibility();
			}
		});
		RestoreLabel.setBounds(170, 268, 144, 29);
		frame.getContentPane().add(RestoreLabel);
		RestoreLabel.setVisible(!seeFirstButton);
	}
}
