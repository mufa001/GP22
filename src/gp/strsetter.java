package gp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.SpinnerNumberModel;

public class strsetter extends JFrame {

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel Question = new JPanel();
	private final JPanel Bloomtex = new JPanel();
	private final JLabel Title = new JLabel("Title");
	private final JTextField textField = new JTextField();
	private final JLabel lblQuestion = new JLabel("Question");
	private final JTextArea textArea = new JTextArea();
	private final JLabel QuestionNo = new JLabel("     Question No");
	private final JSpinner qno = new JSpinner();
	private final JButton ctitle = new JButton("Change Title");
	private final JLabel lblNewLabel = new JLabel("Sub question No");
	private final JSpinner spinner = new JSpinner();
	private final JLabel lblMarks = new JLabel(" Marks");
	private final JSpinner spinner_1 = new JSpinner();
	private final JLabel lblSpace = new JLabel("Space");
	private final JSpinner spinner_2 = new JSpinner();
	private final JList list = new JList();
	private final JLabel lblArea = new JLabel("Area");
	private final JButton amath = new JButton("Add Math");
	private final JButton pic = new JButton("Add Pic");
	private final JButton btnAdd = new JButton("Add");
	private final JLabel lblArea_1 = new JLabel("Area");
	private final JComboBox Barea = new JComboBox();
	private final JLabel key = new JLabel("Key Words");
	private final JList list_2 = new JList();
	private final JLabel lblExample = new JLabel("Example");
	private final JTextArea meaning = new JTextArea();

	/**
	 * Launch the application.
	 */
	
	public  String  tst="";
	public  String  tp ="Above";
	main1_frame g;
	
	public static JTextArea textAreaStruct = null;
	public static int curPosStruct;
	
	public String imgpath="";
	public String abimgpath="";
	
	public void strsetter(main1_frame a) {
    	setResizable(false);
    	setFont(new Font("Bell Gothic Std Light", Font.PLAIN, 12));
    	setAlwaysOnTop(true);
        initGUI();
        g=a;
    }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*try {
					strsetter frame = new strsetter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		});
	}

	/**
	 * Create the frame.
	 * @param main1_frame 
	 */
	public strsetter(main1_frame main1_frame) {
		ctitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setColumns(10);
		initGUI();
	}
	
	  public void fillComboBox()
	{
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
			Statement st=connection.createStatement();
			String query="SELECT DISTINCT(Area) FROM bloom";
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()){
				Barea.addItem(rs.getString("Area"));
			}
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private void initGUI() {
		setResizable(false);
		setTitle("Structured Question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		 String Area[]={"Remembering","Understanding","Applying","Analyzing","Evaluating","Creating"};        
		 //f.setLayout(null);    
		 //f.setSize(400,500);    
		 //f.setVisible(true);    
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("Question Setter", null, Question, null);
		JComboBox cb=new JComboBox(Area);    
		cb.setBounds(50, 50,90,20);
		GroupLayout gl_Question = new GroupLayout(Question);
		gl_Question.setHorizontalGroup(
			gl_Question.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Question.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Question.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_Question.createSequentialGroup()
							.addGroup(gl_Question.createParallelGroup(Alignment.LEADING)
								.addComponent(Title, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuestion))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_Question.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textArea, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
								.addComponent(textField, Alignment.TRAILING)))
						.addGroup(gl_Question.createSequentialGroup()
							.addComponent(lblSpace)
							.addGap(18)
							.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(lblArea)
							.addGap(18)
							.addComponent(cb, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(amath)
							.addGap(18)
							.addComponent(pic)))
					.addGap(4)
					.addGroup(gl_Question.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_Question.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(QuestionNo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblMarks, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_Question.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ctitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(qno, Alignment.LEADING)
						.addComponent(spinner, Alignment.LEADING)
						.addComponent(spinner_1, Alignment.LEADING))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		qno.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		gl_Question.setVerticalGroup(
			gl_Question.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Question.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Question.createParallelGroup(Alignment.BASELINE)
						.addComponent(Title)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(QuestionNo)
						.addComponent(qno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_Question.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Question.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_Question.createParallelGroup(Alignment.BASELINE)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuestion, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_Question.createSequentialGroup()
							.addGap(18)
							.addComponent(ctitle)
							.addGap(30)
							.addGroup(gl_Question.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(gl_Question.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMarks)
								.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_Question.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Question.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_Question.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSpace)
								.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_Question.createSequentialGroup()
							.addGap(32)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Question.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_Question.createParallelGroup(Alignment.BASELINE)
								.addComponent(cb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblArea)))
						.addGroup(gl_Question.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_Question.createParallelGroup(Alignment.BASELINE)
								.addComponent(pic)
								.addComponent(amath)
								.addComponent(btnAdd))))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		Question.setLayout(gl_Question);
		
		tabbedPane.addTab("Bloom Taxanomy ", null, Bloomtex, null);
		GroupLayout gl_Bloomtex = new GroupLayout(Bloomtex);
		gl_Bloomtex.setHorizontalGroup(
			gl_Bloomtex.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Bloomtex.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_Bloomtex.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Bloomtex.createSequentialGroup()
							.addComponent(lblArea_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(Barea, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Bloomtex.createSequentialGroup()
							.addGroup(gl_Bloomtex.createParallelGroup(Alignment.LEADING)
								.addComponent(key, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblExample))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_Bloomtex.createParallelGroup(Alignment.LEADING)
								.addComponent(meaning)
								.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(340, Short.MAX_VALUE))
		);
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
					String query="SELECT DISTINCT * FROM bloom WHERE Keyword=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, (String)list_2.getSelectedValue());
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						meaning.setText(rs.getString("Example"));
					}
					}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		list_2.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		DefaultListModel model= new DefaultListModel();
		Barea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pro_db?useSSL=true","root","mysql");
					model.removeAllElements();
					list.setModel(model);
					String query="SELECT DISTINCT Keyword FROM bloom WHERE Area=?";
					PreparedStatement pst=myConn.prepareStatement(query);
					pst.setString(1, (String)Barea.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						
						model.addElement(rs.getString("Keyword"));
						list_2.setModel(model);
					}
					}
				catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
		});
	
		Barea.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				fillComboBox();
			}
		});
		gl_Bloomtex.setVerticalGroup(
			gl_Bloomtex.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Bloomtex.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_Bloomtex.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArea_1)
						.addComponent(Barea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_Bloomtex.createParallelGroup(Alignment.BASELINE)
						.addComponent(key)
						.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_Bloomtex.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Bloomtex.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addComponent(lblExample)
							.addGap(67))
						.addGroup(gl_Bloomtex.createSequentialGroup()
							.addGap(26)
							.addComponent(meaning, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		Barea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fillComboBox();	
			}
		});
		Bloomtex.setLayout(gl_Bloomtex);
	}
}
