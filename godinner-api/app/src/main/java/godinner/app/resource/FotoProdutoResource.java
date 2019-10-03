package godinner.app.resource;

import java.util.List;

import javax.annotation.processing.SupportedOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.FotoProduto;
import godinner.app.repository.FotoProdutoRepository;

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
}
