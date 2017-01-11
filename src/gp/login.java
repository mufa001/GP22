package gp;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class login extends JFrame {

	private JPanel contentPane;
	private JTextField userid;
	private JPasswordField password;
	private final JLabel auth = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		initGUI();
	}
	private void initGUI() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/gp/images/log1.png")));
		lblNewLabel.setBounds(57, 11, 225, 225);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("QseTeX");
		lblNewLabel_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 44));
		lblNewLabel_1.setBounds(100, 247, 242, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblUsername = new JLabel("Password");
		lblUsername.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblUsername.setBounds(46, 338, 97, 14);
		contentPane.add(lblUsername);
		
		JLabel label = new JLabel("Username");
		label.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		label.setBounds(46, 301, 97, 14);
		contentPane.add(label);
		
		userid = new JTextField();
		userid.setBounds(125, 300, 146, 20);
		contentPane.add(userid);
		userid.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(125, 337, 146, 20);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
					String query="select * from  user where  username='"+userid.getText()+"' and password='"+password.getText()+"'";
					Statement stm=myConn.createStatement();
					/*
					PreparedStatement myStmt=myConn.prepareStatement(query);
					ResultSet myRst =myStmt.executeQuery("select * from  user where username=? and password=?");
					myStmt.setString(0,userid.getText());
					myStmt.setString(1,password.getText());*/
					ResultSet rs=stm.executeQuery(query);
					
					
					int count=0;
					while(rs.next()){
				     count++;
					}
					
					
					if(count==1){
					 
					    main1_frame window = new main1_frame();
						window.setVisible(true);
						dispose();
						
						
					
						
					}
					else{
						auth.setText("Authentication Failed");	
				     }
					
					
					
					/*while(rs.next()){
					verify.setText(rs.getString("username"));
						
					}*/
					}catch(Exception ex){
						ex.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(131, 382, 89, 23);
		contentPane.add(btnNewButton);
		auth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		auth.setBounds(46, 416, 276, 20);
		
		contentPane.add(auth);
	}
}
