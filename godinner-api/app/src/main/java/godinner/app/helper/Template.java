package godinner.app.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
		String arquivo = "";
		
		File file = new File("/home/washington/confs/template.html");

		try {
			FileReader fr = new FileReader(file.getAbsoluteFile());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				arquivo += br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		arquivo = arquivo.replace("idRestauranteSubstituir", String.valueOf(id));
		arquivo = arquivo.replace("sobreORestauranteTexto", String.valueOf(id));
		arquivo = arquivo.replace("sloganSobreOrestaurante", String.valueOf(id));
		arquivo = arquivo.replace("fotoTempleteInicial", String.valueOf(id));
		
		return arquivo;

	}

}
