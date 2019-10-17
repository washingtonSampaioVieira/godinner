package godinner.app.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import godinner.app.storage.Disco;

public class Template {

	public void criarHost(String dominio, int id) {
		String index = criarArquivo(id);
		Disco disco = new Disco();
		disco.escreverArquivo(index, dominio);

	}

	// pega o arquivo padrao substitui o id e retorna uma string da index do
	// restaurante
	public String criarArquivo(int id) {

		// define a HTML String Builder
		StringBuilder htmlStringBuilder = new StringBuilder();
		// append html header and title
		htmlStringBuilder.append("<html><head><title>Selenium Test </title></head>");
		// append body
		htmlStringBuilder.append("<body>");
		// append table
		htmlStringBuilder.append("<h1>ID do restaurante" + id +"<h1>");
		// append row
		htmlStringBuilder.append("<h3> Sucelso </h3>" );
		
		htmlStringBuilder.append("</body></html>");

		// write html string content to a file

		return htmlStringBuilder.toString();

	}

}
