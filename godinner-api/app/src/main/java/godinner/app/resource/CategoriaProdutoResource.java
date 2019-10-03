package godinner.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.CategoriaProduto;
import godinner.app.repository.CategoriaProdutoRepository;
import godinner.app.repository.ProdutoRepository;

@RestController
@RequestMapping("/categoriaproduto")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaProdutoResource {

	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<CategoriaProduto> getCategoriasProduto() {
		return categoriaProdutoRepository.findAll();
	}

	@PostMapping
	public List<CategoriaProduto> setCategoriaProduto(@Validated @RequestBody CategoriaProduto cp) {
		cp.setCategoria(categoriaProdutoRepository.getById(cp.getCategoria().getId()));
		cp.setProduto(produtoRepository.getProdutosById(cp.getProduto().getId()));
		cp = categoriaProdutoRepository.save(cp);

		return categoriaProdutoRepository.todosDoRestaurante(cp.getProduto().getRestaurante().getId());
	}
}
