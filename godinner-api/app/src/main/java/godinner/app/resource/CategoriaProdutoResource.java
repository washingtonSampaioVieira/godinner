package godinner.app.resource;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.CategoriaProduto;
import godinner.app.model.FotoProduto;
import godinner.app.repository.CategoriaProdutoRepository;
import godinner.app.repository.ProdutoRepository;
import godinner.app.storage.Disco;

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
	
	@GetMapping("/{idProduto}")
	public List<CategoriaProduto> getCategoriaProduto(@PathVariable int idProduto){
		return categoriaProdutoRepository.todosDoRestaurante(idProduto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoriaProduto(@PathVariable int id, HttpServletResponse response) throws IOException {
		CategoriaProduto categoriaProduto = categoriaProdutoRepository.getIdCategoriaProduto(id);
		if(categoriaProduto != null) {
			categoriaProdutoRepository.delete(categoriaProduto);
			return;
		}
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "categoria produto null");
		return;
	}
}
