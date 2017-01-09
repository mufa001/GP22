package gp;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import gp.math.jahuwaldt.jatex.MainWindow;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Essay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public static JTextPane textPaneEssay = null;
	public static int curPosEssay;

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
					Essay frame = new Essay();
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
	public Essay() {
		setBackground(SystemColor.menu);
		setTitle("Add Essay Question");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 450);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblQuestionNumber = new JLabel("Question Number");
		lblQuestionNumber.setForeground(Color.BLACK);
		lblQuestionNumber.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		
		JLabel lblSubQueNumber = new JLabel("Sub Que. Number");
		lblSubQueNumber.setForeground(Color.BLACK);
		lblSubQueNumber.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		
		JLabel lblNewLabel = new JLabel("Module");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Marks");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setForeground(Color.BLACK);
		lblQuestion.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		
		JTextPane textPane = new JTextPane();
		textPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				curPosEssay=textPane.getCaretPosition();
				textPaneEssay=textPane;
			}
		});
		textPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				curPosEssay=textPane.getCaretPosition();
				textPaneEssay=textPane;
			}
		});
		
		
		JButton btnNewButton = new JButton("Add Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Add Table");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new table().setVisible(true);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Refer Taxanomy");
		btnNewButton_2.setToolTipText("Open Bloom Taxanomy Help");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BT().setVisible(true);
			}
		});
		
		JButton btnNewButton_3 = new JButton("Add");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text=textField.getText();
			   
			}
		});
		
		JButton btnNewButton_4 = new JButton("Cancel");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JLabel lblBloomsTaxanomyArea = new JLabel("Bloom's Taxanomy Area");
		lblBloomsTaxanomyArea.setForeground(Color.BLACK);
		lblBloomsTaxanomyArea.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 12));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Remembering", "Understanding", "Applying", "Analyzing", "Evaluating", "Creating"}));
		
		JButton btnAddMaths = new JButton("Add Maths");
		btnAddMaths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
        	
                	if(mcqseter.main != null){
                		mcqseter.main.textArea = null;
                		mcqseter.main.textField = null;
                		mcqseter.main.curPos = curPosEssay;
                		mcqseter.main.textPaneEssay = textPaneEssay;
                		mcqseter.main.setVisible(true);
                	}else{
                		mcqseter.main = new MainWindow(null,null,textPaneEssay,curPosEssay);
                		mcqseter.main.setVisible(true);
                	}
                	//BufferedReader b=new BufferedReader(FileReader("math.txt"));
                	
                                          

                } catch (Throwable ex) {
                    ex.printStackTrace();
                    
                }
        		
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuestionNumber)
								.addComponent(lblSubQueNumber))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
							.addGap(42))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_4)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddMaths))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblQuestion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(348))
								.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
							.addGap(6))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBloomsTaxanomyArea)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(164, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuestionNumber)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubQueNumber)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBloomsTaxanomyArea)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblQuestion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton)
							.addComponent(btnNewButton_1))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_2)
							.addComponent(btnAddMaths)))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_4))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
