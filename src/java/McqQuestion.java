package java;

public class McqQuestion {
	
	public String stmcq="\\begin{subqlist}\r\n";
	public String table="";
	public String enmcq="\\end{subqlist}\r\n"+
                           "}\r\n";
	public int qno;
	//public  String title="\\question {"+test.title.getText()+".\r\n";
	public String title="";
	public String mcq="";
	public String utitle="";
	
	McqQuestion(int no){
		qno=no;
	}
	
	public  String get(){
	  return  title+stmcq+mcq+enmcq;
	}
}
