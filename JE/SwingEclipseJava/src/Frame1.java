import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class Frame1 {

	private JFrame frame;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Yasuo Button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Holi Yasuo");
			}
		});
		btnNewButton.setBounds(185, 237, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Holi\n\nLos labels NO pueden tener multiples lineas");
		lblNewLabel.setBounds(80, 49, 342, 64);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane txtpnHola = new JTextPane();
		txtpnHola.setEnabled(false);
		txtpnHola.setEditable(false);
		txtpnHola.setText("Hola\n\nEste es un ejemplo\n\nDe Multiples lineas\n");
		txtpnHola.setBounds(80, 125, 205, 88);
		frame.getContentPane().add(txtpnHola);
	}
}
