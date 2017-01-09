
package gp;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.sql.*;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BT extends JFrame {
	
	private JPanel contentPane;
	public JComboBox comboBox_1;
	private JList list; 
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
					BT frame = new BT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	
	public void fillComboBox()
	{
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
			Statement st=connection.createStatement();
			String query="SELECT DISTINCT(Area) FROM bloom";
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
				comboBox_1.addItem(rs.getString("Area"));
			}
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public BT() {
		
		setTitle("Bloom Taxanomy Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 398);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.text);
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setForeground(SystemColor.activeCaptionText);
		lblArea.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		
		JLabel lblKeyWords = new JLabel("Key Words");
		lblKeyWords.setForeground(SystemColor.activeCaptionText);
		lblKeyWords.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		
		JLabel lblExample = new JLabel("Example");
		lblExample.setForeground(SystemColor.activeCaptionText);
		lblExample.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		
		JTextPane textPane = new JTextPane();
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		DefaultListModel model= new DefaultListModel();
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
					model.removeAllElements();
					list.setModel(model);
					String query="SELECT DISTINCT Keyword FROM bloom WHERE Area=?";
					PreparedStatement pst=myConn.prepareStatement(query);
					pst.setString(1, (String)comboBox_1.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						
						model.addElement(rs.getString("Keyword"));
						list.setModel(model);
					}
					}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
		});
		
		list = new JList();
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
					String query="SELECT DISTINCT * FROM bloom WHERE Keyword=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, (String)list.getSelectedValue());
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						textPane.setText(rs.getString("Example"));
					}
					}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		list.setVisibleRowCount(6);
		list.setValueIsAdjusting(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblKeyWords)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblArea)
							.addGap(39)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblExample)
							.addGap(18)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnClose, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArea)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKeyWords)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExample)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnClose)
					.addGap(206))
		);
		contentPane.setLayout(gl_contentPane);
		fillComboBox();
		
		
		
	}
}
