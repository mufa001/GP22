package java;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Save extends JFrame {
    
	
	MainFrame g;
	private JPanel contentPane;
	private final JButton Save = new JButton("   Save");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Save frame = new Save();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Save(MainFrame a) {
		
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs=new JFileChooser("C:\\");
			    fs.setDialogTitle("Save a File");
			    fs.setFileFilter(new FileTypeFilter(".txt","Text File"));
			    fs.setFileFilter(new FileTypeFilter(".tex","latex File"));
			    int result=fs.showSaveDialog(null);
			    if(result == JFileChooser.APPROVE_OPTION){
			    	String content = g.tex.getText();
			    	File fi =fs.getSelectedFile();
			    	try{
			    		FileWriter fw=new FileWriter(fi.getPath());
			    		System.out.println(fi.getPath());
			    		fw.write(content);
			    		fw.flush();
			    		fw.close();
			    		
			    	}catch(Exception e2){
			    		
			    		JOptionPane.showMessageDialog(null,e2.getMessage());
			    	}
			    }
			    
				
				
			}
		});
		initGUI();
		g=a;
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(Save)
					.addContainerGap(349, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(Save)
					.addContainerGap(212, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
