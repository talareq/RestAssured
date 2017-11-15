package CALLBACK;

import java.net.URL;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

public class callBack {
	
	private String corrID;
	private String file_path;
	

	public callBack setOrder(String randomCorrID, String file_path){
		this.corrID =randomCorrID;
		this.file_path=file_path;
		return this;

		
	}
	
	public String done(){
		String callBackMessage1="";
		
		 readXMLFile file=  new readXMLFile();
		 URL inputStreamUrl = ClassLoader.getSystemResource(file_path);
         
		 if(file.setDocument(inputStreamUrl.getFile())){
			    Document document = file.getDocument();       
		         Element classElement = document.getRootElement();
		         
		         List<Node> nodes = document.selectNodes("/soapenv:Envelope //*[contains(text(),'753951') ]" );
		         for(int i=0;i<nodes.size();i++){
		        	 nodes.get(i).setText(corrID);
		         }
		        
		         callBackMessage1=document.asXML().toString();
			 
		 }
     
		 System.out.println(callBackMessage1);
		return callBackMessage1;
	}
	
				

}
