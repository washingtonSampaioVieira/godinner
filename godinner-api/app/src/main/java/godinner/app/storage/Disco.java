package godinner.app.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import godinner.app.helper.Date;

@Component
public class Disco {

	@Value("${contato.disco.raiz}")
	private String raiz;

	@Value("${contato.disco.diretorio-fotos}")
	private String diretorioFotos;

	public String salvarFoto(MultipartFile foto, String pasta) {
		return this.salvar(this.diretorioFotos + "/" + pasta, foto);
	}

	public String salvar(String diretorio, MultipartFile arquivo) {
		String local = null;
		String caminhoCompleto = null;
		Path diretorioPath = Paths.get(this.raiz, diretorio);

		Date data = new Date();
		local = (String) (data.getData() + "-" + arquivo.getOriginalFilename());

		Path arquivoPath = diretorioPath.resolve(local);
		try {

			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			caminhoCompleto = (raiz + "/" + diretorio + "/" + local);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return caminhoCompleto;
	}
}
