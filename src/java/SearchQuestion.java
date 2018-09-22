package java;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class SearchQuestion extends JFrame {

	private JPanel contentPane;
	private JLabel lblModule;
	private JTextField year;
	private JList qlist;
    private JTextPane textPane;
    private JComboBox subject;
    private JComboBox module;
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
					SearchQuestion frame = new SearchQuestion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Connection connection=null;
	public void fillComboBox()
	{
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
			Statement st=connection.createStatement();
			String query="SELECT DISTINCT(Subject) FROM ques";
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
				subject.addItem(rs.getString("Subject"));
			}
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public SearchQuestion() {
		setBackground(SystemColor.activeCaption);
	
		setTitle("Search Questions");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 520);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.desktop);
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setForeground(SystemColor.desktop);
		lblSubject.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		
		lblModule = new JLabel("Module");
		lblModule.setForeground(SystemColor.desktop);
		lblModule.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		
		JComboBox btarea = new JComboBox();
		btarea.setModel(new DefaultComboBoxModel(new String[] {"Remembering"}));
		
		JLabel lblBloomTaxArea = new JLabel("Bloom Tax. Area");
		lblBloomTaxArea.setForeground(SystemColor.desktop);
		lblBloomTaxArea.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setForeground(SystemColor.desktop);
		lblYear.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		
		year = new JTextField();
		year.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("All Questions");
		chckbxNewCheckBox.setBackground(SystemColor.menu);
		chckbxNewCheckBox.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		
		JLabel lblQue = new JLabel("Questions");
		lblQue.setForeground(SystemColor.desktop);
		lblQue.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		
		qlist = new JList();
		qlist.setBorder(new LineBorder(new Color(0, 0, 0)));
		qlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
					String query="SELECT DISTINCT * FROM ques WHERE Year=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, (String)qlist.getSelectedValue());
	
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						textPane.setText(rs.getString("Question"));
					}
					}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblPrev = new JLabel("Preview");
		lblPrev.setForeground(SystemColor.desktop);
		lblPrev.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		
		JButton btnOk = new JButton("Close");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		DefaultListModel model= new DefaultListModel();
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
					model.removeAllElements();
					qlist.setModel(model);
					String query="SELECT Year FROM ques WHERE Bloom=? and Year=? OR Subject=? OR Module=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, (String)btarea.getSelectedItem());
					pst.setString(2, (String)year.getText());
					pst.setString(3, (String)subject.getSelectedItem());
					pst.setString(4, (String)module.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						
						model.addElement(rs.getString("Year"));
						qlist.setModel(model);
					}
					}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		textPane = new JTextPane();
		
		subject = new JComboBox();
		subject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					module.removeAllItems();
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
					String query="SELECT DISTINCT Module FROM ques WHERE Subject=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, (String)subject.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						
						module.addItem(rs.getString("Module"));
						
					}
					}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
		});
		
		module = new JComboBox();
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addComponent(lblQue, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(329, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(326, Short.MAX_VALUE)
					.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYear, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblModule, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubject, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBloomTaxArea, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(module, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(subject, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btarea, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(search, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(qlist, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
								.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPrev, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(354, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblYear, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(15))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(subject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(15))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSubject, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModule, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(module, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBloomTaxArea, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(search)
						.addComponent(btarea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblQue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(qlist, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(lblPrev, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(btnOk)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
		fillComboBox();
	}
}
