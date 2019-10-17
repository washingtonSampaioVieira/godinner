package godinner.app.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import godinner.app.storage.Disco;

public class Template {
	
	
	public void criarHost(String dominio, int id) throws IOException {
		String index = criarArquivo(id);
		Disco disco = new Disco();
		disco.escreverArquivo(index, dominio);
		
		System.out.println(dominio);
		
		 
	}
	
	//pega o arquivo padrao substitui o id e retorna uma string da index do restaurante
	public String criarArquivo(int id) {
		 
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
        
        return htmlStringBuilder.toString(); 
    
		
	}
	
    
}

