package gp;

import java.util.HashMap;

public class essayclass{
    public int qno;
    public String title="";
    public HashMap<Integer,Object> essaylist=new HashMap();
    public String start="\\begin{subqlist}\r\n";
    public String end="\\end{subqlist}\r\n";
    public String utitle="";
    float mark;
    
    
    essayclass(int x){
    	qno=x;
    }
    
    public  String get(){
		  return  title+start+this.getq()+end;
		}
    public String getq(){
    	String s="";
    	for (Object o : this.essaylist.values()){
			   esso a=(esso)o;
			   s+=a.question;
			  
			} 
		return s;
    	
    }
    /*public  boolean CheckMark(){
    	float x=0;
    	for(Object o: this.essaylist.values()){
    		esso a=(esso)o;
    		String mrk=a.mark;
    		x+=Float.valueOf(mark);
    	}
    	if(x<=mark){
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }*/
	
}
