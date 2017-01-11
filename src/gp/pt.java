package gp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

public class pt extends JFrame {

	private JPanel contentPane;
	private final JButton pta = new JButton("  set");
	private final JComboBox noofq = new JComboBox();
	private final JLabel lblNoOfMcqs = new JLabel("Mcqs");
	public String qu = "This is a MCQ Question";
	public String utable="";
	public String dtable="";
	public String smp="Answer";
	public String title="Select the write answer for following questions";

	/**
	 * Launch the application.
	 */
	
	public newp1 np;
	private final JComboBox marks = new JComboBox();
	private final JLabel lblMarks = new JLabel("marks");
	private final JComboBox sq = new JComboBox();
	private final JLabel lblNoOfStructure = new JLabel("     Structures");
	private final JLabel label_1 = new JLabel("marks");
	private final JComboBox sm = new JComboBox();
	public float mks;
	private final JLabel label = new JLabel("Essay");
	private final JComboBox essq = new JComboBox();
	private final JLabel label_2 = new JLabel("marks");
	private final JComboBox essmks = new JComboBox();
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*try {
					//pt frame = new pt();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public pt(newp1 a) {
		essmks.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50"}));
		essmks.setToolTipText("10\r\n20\r\n30\r\n40\r\n50");
		essmks.setEditable(true);
		essq.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		essq.setEditable(true);
		sq.setEditable(true);
		sq.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		sm.setEditable(true);
		sm.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50"}));
		noofq.setEditable(true);
		noofq.setModel(new DefaultComboBoxModel(new String[] {"15", "20", "25", "50"}));
		
		initGUI();
		np=a;
		
	}
	public pt(ActionListener actionListener) {
		// TODO Auto-generated constructor stub
		
	}


	
	
	
	private void initGUI() {
		setTitle("Paper Structure"); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(pta)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNoOfStructure, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(16)
											.addComponent(lblNoOfMcqs, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(sq, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(noofq, Alignment.TRAILING, 0, 76, Short.MAX_VALUE))
									.addGap(74)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblMarks)
											.addGap(18)
											.addComponent(marks, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(sm, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(essmks, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(essq, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
					.addGap(16))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(noofq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(marks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMarks)
						.addComponent(lblNoOfMcqs, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(sq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNoOfStructure, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(sm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(essq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(essmks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(pta)
					.addGap(22))
		);
		marks.setEditable(true);
		
		
		
		
		pta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			//total number of mcq question	
			String  noq=noofq.getSelectedItem().toString();
			//total marks for mcq 
		    String mrk=marks.getSelectedItem().toString();
		    
		    
		    //total number of structure questions
		    String  nsq=sq.getSelectedItem().toString();
		    //total marks for structure questions
		    String smrk=sm.getSelectedItem().toString();
		    
		    
		    //total number of essay questions
		    String  nesq=essq.getSelectedItem().toString();
		    String  esmks=essmks.getSelectedItem().toString();
		    
		    
			 
		     int n=Integer.parseInt(noq);
		     np.g.mcc=n;//setting  total marks
		     float m=Float.parseFloat(mrk);
		     np.g.mcm+=m;//setting mcq marks
		     int ns=Integer.parseInt(nsq);
		     float strques=Float.parseFloat(nsq);
		     np.g.sc+=ns;//setting  structure marks
		     
		     int ms=Integer.parseInt(smrk);
		     float mss=Float.parseFloat(smrk);
		     np.g.tms+=mss; 
		     np.g.tcq+=ns+n;
		     np.g.tmc+=m+mss;
		     float mks1=mss/strques; //getting  marks per questions
		     //int sfmark=Math.round(mks); //rounded marks per questions 
		     //String mkss=Float.toString(sfmark);
		     int mkss1=Math.round(mks1);
		     String mkss=Integer.toString(mkss1);
		     
			 float mk=(float)m/n;
			 mks=mk;
			 int mk1=Math.round(mk);
			 
			 
		     String mkk=Integer.toString(mk1);
		     
		     int ec=Integer.parseInt(nesq);
		     np.g.ec=ec;
		     float m1=Float.parseFloat(esmks);
		     np.g.mec+=m1;
		     
		     np.g.tcq+=ec;
		     np.g.tmc+=m1;
		     
		     
  /*  String Str="\\newpage\r\n"+"\\question{ \r\n"+

"\\begin{subqlist}\r\n"+

"\\subquestion{Insert \\nth{1} subquestion of the structure question here.\r\n"+  


"\\qmarks{"+mkss+"}\r\n"+

"\\answer{2.5in}\r\n"+

"}\r\n"+

"\\subquestion{Insert  \\nth{2} subquestion of the structure question here.\r\n"+

"\\qmarks{"+mkss+"}\r\n"+
"\\answer{1.5in}\r\n"+

"}\r\n"+


"\\subquestion{Insert  \\nth{3} subquestion of the structure question here.\r\n"+     
"\\qmarks{"+mkss+"}\r\n"+
"\\answer{2.5in}\r\n"+
"}\r\n"+

"\\subquestion{Insert \\nth{4} subquestion of the structure question here.\r\n"+
"\\vspace{5mm}\r\n"+
"\\vspace{5mm}\r\n"+   
"\\qmarks{"+mkss+"}\r\n"+
"\\answer{3.5in}\r\n"+
"}\r\n"+


"\\subquestion{ Insert \\nth{5} subquestion of the structure question here .\r\n"+
"\\qmarks{"+mkss+"}\r\n"+
"\\answer{1.5in}\r\n"+
"}\r\n"+

"\\end{subqlist}\r\n";*/
		     
  String str="\\newpage\r\n"+"\\question{Answer for following question \r\n"+

              "\\begin{subqlist}\r\n"+

              "\\subquestion{Insert \\nth{1} subquestion of the structure question here.\r\n"+  

              "\\qmarks{"+mkss+"}\r\n"+

              "\\answer{2.5in}\r\n"+

              "}\r\n"+
              
              "\\end{subqlist}\r\n";
  
  String essy="\\newpage\r\n"+"\\question{Answer for following question \r\n"+

              "\\begin{subqlist}\r\n"+

              "\\subquestion{Insert \\nth{1} subquestion of the structure question here.\r\n"+  

              "\\qmarks{"+mkss+"}\r\n"+

              "}\r\n"+
              
              "\\end{subqlist}\r\n";
	     
		     
				
				
	String MCQ= utable+"\\subquestion{"+qu+"\r\n"+dtable+
						"\\begin{enumerate}\r\n"+
						"\\item "+smp+"\r\n"+ 
						"\\item "+smp+"\r\n"+
						"\\item "+smp+"\r\n"+
						"\\item "+smp+"\r\n"+
						"\\end{enumerate}\r\n"+
						"\\qmarks{"+mkk+"}\r\n"+
						"}\r\n";
	
	String MCQ5=utable+"\\subquestion{"+qu+"\r\n"+dtable+
			"\\begin{enumerate}\r\n"+
			"\\item "+smp+"\r\n"+ 
			"\\item "+smp+"\r\n"+
			"\\item "+smp+"\r\n"+
			"\\item "+smp+"\r\n"+
			"\\item "+smp+"\r\n"+
			"\\end{enumerate}\r\n"+
			"\\qmarks{"+mkk+"}\r\n"+
			"}\r\n";
	
		String mlq="";
		mcqbundel mcqbund=new mcqbundel();
		String stitle="Select Most suitable Answer from here";
		mcqbund.title="\\question {"+stitle+".\r\n";
		mcqbund.utitle=stitle;
		for(int i=1;i<=n;i++){
				mcqo ob=new mcqo(i,MCQ);
				ob.subquestion=qu;
				ob.ans1=smp;
				ob.ans2=smp;
				ob.ans3=smp;
				ob.ans4=smp;
				ob.mark=mkk;
				np.g.mcc+=1;
				mcqbund.mcqlist.put(i,ob);
			}
		np.g.mapper.put(1,mcqbund);
		
		String msq="";
		String strtitle="Anwer the following Questions";
		String strsubq="Insert 1st subquestion of the structure question here";
		for(int i=1;i<=ns;i++){
			msq+=str;
			strclass t=new strclass(i);
		    t.mark=mks1;
			t.title="\\question {"+strtitle+".\r\n";
		    t.utitle=strtitle;
		    stro t1=new stro(1,strsubq);
		    t1.mark=mkss;
		    t.strlist.put(1,t1);
			np.g.stmapper.put(i,t);
		}
		
		
		
		
		np.g.str=msq;
		/*mcqq testa=new mcqq(1);
		testa.title="\\question {"+title+".\r\n";
		testa.utitle=title;
		testa.mcq+=mlq;
	    np.g.mapper.put(1,testa);*/
	    
	    String ess="";
	    for(int i=1;i<=ec;i++){
	    	ess+=essy;
			essayclass t=new essayclass(i);
		    t.mark=mks1;
			t.title="\\question {"+strtitle+".\r\n";
		    t.utitle=strtitle;
		    stro t1=new stro(1,strsubq);
		    t1.mark=mkss;
		    t.essaylist.put(1,t1);
			np.g.esmapper.put(i,t);
	    }
	    
	    np.g.es=ess;
	    
	    //test
	     //System.out.println(n);
		 np.g.mc.setText(String.valueOf(n));
		 np.g.mm.setText(String.valueOf(np.g.mcm));
		 np.g.tq.setText(String.valueOf(np.g.tcq));
		 np.g.tm.setText(String.valueOf(np.g.tmc));
		 np.g.sm.setText(String.valueOf(np.g.tms));
		 np.g.sn.setText(String.valueOf(np.g.sc));
		 np.g.en.setText(String.valueOf(np.g.ec));
		 np.g.em.setText(String.valueOf(np.g.mec));
	    
				 
	             /*np.g.tms+=Integer.parseInt(smrk);
	             np.g.sc=Integer.parseInt(nsq);
				 np.g.mcc+=n;
				 np.g.mcm+=m;
				 np.g.mc.setText(String.valueOf(n));
				 np.g.mm.setText(String.valueOf(m));
				 np.g.tq.setText(String.valueOf(n));
				 np.g.tm.setText(String.valueOf(m));
				 np.g.sm.setText(smrk);
				 np.g.sn.setText(nsq);*/
				 dispose();
				 
			}	
				
		});
		marks.setModel(new DefaultComboBoxModel(new String[] {"20", "30", "40", "50"}));
		contentPane.setLayout(gl_contentPane);
	}
}
