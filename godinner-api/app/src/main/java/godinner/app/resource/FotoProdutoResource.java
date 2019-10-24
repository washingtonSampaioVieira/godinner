package godinner.app.resource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.processing.SupportedOptions;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.x.protobuf.Mysqlx.Ok;

import godinner.app.model.FotoProduto;
import godinner.app.repository.FotoProdutoRepository;
import godinner.app.repository.ProdutoRepository;
import godinner.app.storage.Disco;

@RestController
@RequestMapping("/fotoproduto")
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = { "eventBusIndex", "verbose" })
public class FotoProdutoResource {

	@Autowired
	private FotoProdutoRepository fotoProdutoRepository;

	@GetMapping("/todos")
	public List<FotoProduto> getFotoProdutos() {
		return fotoProdutoRepository.findAll();

	}

	@GetMapping("/{id}")
	public List<FotoProduto> getFotoProdtosIdProduto(@PathVariable int id) {
		List<FotoProduto> fotosProd = fotoProdutoRepository.findByIdProduto(id);
		return fotosProd;
	}

	@DeleteMapping("/{id}")
	public void deleteFotoProduto(@PathVariable int id, HttpServletResponse response) throws IOException {
		FotoProduto fotoProduto = fotoProdutoRepository.getFotoProdutoPorId(id);
		if (fotoProduto == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "foto produto null");
			return;
		}
		Disco disco = new Disco();
		String localDaFoto = "/var/www/fotos.godinner.tk" + fotoProduto.getFoto();
		System.out.println(localDaFoto);
		disco.deleteFoto(localDaFoto);
		System.out.println("Apagou aqui");
		return;
			
		
		

	}
}
