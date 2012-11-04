package com.makingiants.views;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import com.makingiants.controllers.FrameConnectionController;


@SuppressWarnings("serial")
public class FrameConnection extends JFrame {

	private JPanel contentPane;
	public JTextField textfieldIP;
	public JTextField textfieldPort;
	public JButton buttonStart;

	/**
	 * Create the frame.
	 */
	public FrameConnection(FrameConnectionController controller) {
		setTitle("Remote Server");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 229, 153);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager
				.getColor("CheckBoxMenuItem.selectionBackground"));
		contentPane
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIp = new JLabel("Local IP:");
		lblIp.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblIp.setForeground(UIManager.getColor("Button.highlight"));
		lblIp.setBounds(17, 17, 61, 16);
		contentPane.add(lblIp);

		textfieldIP = new JTextField();
		textfieldIP.setEditable(false);
		textfieldIP.setHorizontalAlignment(SwingConstants.CENTER);
		textfieldIP.setText("192.168.1.1");
		textfieldIP.setBounds(82, 11, 134, 28);
		contentPane.add(textfieldIP);
		textfieldIP.setColumns(10);

		JLabel lblPort = new JLabel("Port :");
		lblPort.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPort.setForeground(UIManager.getColor("Button.highlight"));
		lblPort.setBounds(17, 51, 41, 16);
		contentPane.add(lblPort);

		textfieldPort = new JTextField();
		textfieldPort.setToolTipText("Change this number only if the application is not working");
		textfieldPort.setText("1111\n");
		textfieldPort.setHorizontalAlignment(SwingConstants.CENTER);
		textfieldPort.setColumns(10);
		textfieldPort.setBounds(82, 45, 134, 28);
		contentPane.add(textfieldPort);

		buttonStart = new JButton("Start\n");
		buttonStart.setBounds(17, 79, 199, 46);
		buttonStart.addActionListener(controller);

		contentPane.add(buttonStart);
	}
}
