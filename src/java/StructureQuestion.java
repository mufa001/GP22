package java;

import java.util.HashMap;

public class StructureQuestion {
    public int qno;
    public String title="";
    public HashMap<Integer,Object> strlist=new HashMap();
    public String start="\\begin{subqlist}\r\n";
    public String end="\\end{subqlist}\r\n";
    public Double tmark=0.0;
   
    public String utitle="";
    float mark;
    
    
    
    StructureQuestion(int x){
    	qno=x;
    }
    
    public  String get(){
		  return  title+"\\qmarks{"+this.tmark+"}\r\n"+start+this.getq()+end;
		}
    public String getq(){
    	String s="";
    	for (Object o : this.strlist.values()){
			   StructureObject a=(StructureObject)o;
			   s+=a.question;
			  
			} 
		return s;
    	
    }
    public  boolean CheckMark(){
    	float x=0;
    	for(Object o: this.strlist.values()){
    		StructureObject a=(StructureObject)o;
    		String mrk=a.mark;
    		x+=Float.valueOf(mark);
    	}
    	if(x<=mark){
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }
	
}
