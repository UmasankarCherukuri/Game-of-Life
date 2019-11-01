import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class winOne {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public int row = 0;
	public int col = 0;
	public boolean parametersInitialized = false;
	


	public winOne() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel l1 = new JLabel("Please enter the number of Rows and Columns");
		l1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(37, 85, 315, 28);
		frame.getContentPane().add(l1);
		
		JLabel l2 = new JLabel("Rows:");
		l2.setBounds(77, 124, 46, 14);
		frame.getContentPane().add(l2);
		
		t1 = new JTextField();
		t1.setBounds(201, 121, 86, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel l3 = new JLabel("Columns:");
		l3.setBounds(77, 155, 46, 14);
		frame.getContentPane().add(l3);
		
		t2 = new JTextField();
		t2.setBounds(201, 152, 86, 20);
		frame.getContentPane().add(t2);
		t2.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = Integer.parseInt(t1.getText());
				col = Integer.parseInt(t2.getText());
				parametersInitialized = true;
				frame.dispose();
			}
		});
		btnOk.setBounds(145, 201, 101, 23);
		frame.getContentPane().add(btnOk);
		
		JLabel lblWelcomeToGame = new JLabel("Welcome to Game Of Life !");
		lblWelcomeToGame.setForeground(Color.BLUE);
		lblWelcomeToGame.setFont(new Font("Castellar", Font.BOLD, 13));
		lblWelcomeToGame.setBounds(77, 29, 225, 28);
		frame.getContentPane().add(lblWelcomeToGame);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
