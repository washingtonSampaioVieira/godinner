package godinner.app.helper;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hibernate.annotations.Index;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import godinner.app.storage.Disco;

public class Template {
	
	
	public boolean criarHost(String dominio, int id)  {
		System.out.println("ou");
		String index = criarArquivo(id);
		System.out.println(index);
		Disco disco = new Disco();
		disco.escreverArquivo(index, dominio+".godinner.tk");
		
		return true;

	}

	// pega o arquivo padrao substitui o id e retorna uma string da index do
	// restaurante
	public String criarArquivo(int id) {

		// define a HTML String Builder
		StringBuilder htmlStringBuilder = new StringBuilder();
		String arquivo = "";
		  
		
			
			 
			 
			
//			 FileInputStream stream;
//			try {
//				
//				stream = new FileInputStream("index.html");
//				InputStreamReader reader = new InputStreamReader(stream);
//				 BufferedReader br = new BufferedReader(reader);
//				 String linha = br.readLine();
//				 while(linha != null) {
//					 arquivo += linha;
//					 linha = br.readLine();
//				 } 
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			  
//		  StringBuilder sb = new StringBuilder();
//
//	        try (BufferedReader br = Files.newBufferedReader(Paths.get("index.html"))) {
//
//	            // read line by line
//	            String line;
//	            while ((line = br.readLine()) != null) {
//	                sb.append(line).append("\n");
//	            }
//
//	        } catch (IOException e) {
//	            System.err.format("IOException: %s%n", e);
//	        }
//
//	        System.out.println(sb);
			 
			 
			      
	
		arquivo.replaceAll("idRestauranteSub", String.valueOf(id));
		
		return htmlStringBuilder.toString();

	}

}
