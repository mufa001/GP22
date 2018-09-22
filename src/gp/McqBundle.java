package gp;
import java.util.HashMap;
public class McqBundle {
	
	public HashMap<Integer,Object> mcqlist=new HashMap();
	
	public String stmcq="\\begin{subqlist}\r\n";
	//public String table="";
	public String enmcq="\\end{subqlist}\r\n"+
                           "}\r\n";
	public String title="";
	public String utitle="";
	public  String get(){
		  return  title+stmcq+this.getq()+enmcq;
		}
	
	public String getq(){
		String s="";
		for (Object o : this.mcqlist.values()){
			   mcqo a=(mcqo)o;
			   s+=a.mcq;
			  
			} 
		return s;
	}
	
	

}
