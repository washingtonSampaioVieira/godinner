package godinner.app.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Template {
	
	public static void main(String[] args)
    {
        try {
            //define a HTML String Builder
            StringBuilder htmlStringBuilder=new StringBuilder();
            //append html header and title
            htmlStringBuilder.append("<html><head><title>Selenium Test </title></head>");
            //append body
            htmlStringBuilder.append("<body>");
            //append table
            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
            //append row
            htmlStringBuilder.append("<tr><td><b>TestId</b></td><td><b>TestName</b></td><td><b>TestResult</b></td></tr>");
            //append row
            htmlStringBuilder.append("<tr><td>001</td><td>Login</td><td>Passed</td></tr>");
            //append row
            htmlStringBuilder.append("<tr><td>002</td><td>Logout</td><td>Passed</td></tr>");
            //close html file
            htmlStringBuilder.append("</table></body></html>");
            //write html string content to a file
            WriteToFile(htmlStringBuilder.toString(),"marina.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public static void WriteToFile(String fileContent, String fileName) throws IOException {
    	
    	
    	
    	 String projectPath = "C:\\Users\\18175241\\pages\\" + fileName;
    	 
    	 File f = new File(projectPath);
         
         // create
         boolean pasta = f.mkdir();
    	 
    	 // pasta = projectPath + fileName;
    	 
    	 if (pasta) { 
    		 
    		 String tempFile = projectPath + File.separator+fileName;
             File file = new File(tempFile);
          
             //write to file with OutputStreamWriter
             OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
             Writer writer=new OutputStreamWriter(outputStream);
             writer.write(fileContent);
             writer.close();
    		  
           
             System.out.println("Directory is created"); 
         } 
         else { 
             // display that the directory cannot be created 
             // as the function returned false 
             System.out.println("Directory cannot be created"); 
         } 
    	 
    	 
        
    	
    	 
 
    	
    	// create an abstract pathname (File object) 
        /*File file = new File("C:\\Users\\18175241\\pages\\" + fileName); 
  
        // check if the directory can be created 
        // using the abstract path name 
        if (file.mkdir()) { 
        	
        	
        	 String projectPath = System.getProperty("user.dir");
             String tempFile = projectPath + File.separator+fileName;
             File f = new File(tempFile);
  
            
            System.out.println("Directory is created"); 
        } 
        else { 
            // display that the directory cannot be created 
            // as the function returned false 
            System.out.println("Directory cannot be created"); 
        } 
    	
       // String projectPath = "C:\\Users\\18175241";
       // String tempFile = projectPath + File.separator+fileName;
        //File file = new File(tempFile);
        // if file does exists, then delete and create a new file*/
        

    }

}

