package CALLBACK;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class readXMLFile {
	 public Document document; 
	 
	 public Document getDocument(){
		 return document;
	 }
	 
	 public boolean setDocument(String fileName){
		 boolean result=false;
		 
	         File inputFile = new File(fileName);
	         SAXReader reader = new SAXReader();
	         try {
				this.document = reader.read( inputFile );
				result=true;
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        
	       
	  return result;    
	 }
		 
	 

}
