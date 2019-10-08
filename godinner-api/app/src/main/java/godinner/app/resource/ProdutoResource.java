package godinner.app.resource;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.processing.SupportedOptions;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.engine.transaction.internal.TransactionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.Produto;
import godinner.app.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = { "eventBusIndex", "verbose" })
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> getProduto() {
		return produtoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Produto getProdutoPorId(@PathVariable int id) {
		return produtoRepository.getProdutosById(id);
	}

	@GetMapping("/desativados/{idRestaurante}")
	public List<Produto> getProdutosDesativados(@PathVariable int idRestaurante) {
		return produtoRepository.getProdutosDesativados(idRestaurante);
	}

	@GetMapping("/exibicao/{id}")
	public List<?> getProdutosExibicaoADM(@PathVariable int id) {
		return produtoRepository.getProdutoExibicao(id);
	}

	@PutMapping
	public Produto setProdutoAtualizado(@Validated @RequestBody Produto produto) {
		produtoRepository.save(produto);
		return produto = produtoRepository.getProdutosById(produto.getId());
	}

	@PutMapping("/status/{id}")
	public Produto setProdutoAtualizado(@PathVariable int id, HttpServletResponse response) throws IOException {
		Produto p = produtoRepository.getProdutosById(id);
		Integer status = Integer.parseInt(p.getStatus());
		switch (status) {
			case 1:
				p.setStatus("0");
				produtoRepository.save(p);
				break;
		        
			case 0:
				p.setStatus("1");
				produtoRepository.save(p);
		        break;
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "categoria produto null");
				break;
		}	
		return p;
	}

	@DeleteMapping("/{id}")
	public boolean deletarProduto(@PathVariable int id) {
		Produto p = produtoRepository.getProdutosById(id);
		if (p != null) {
			produtoRepository.delete(p);
			return true;
		}
		return false;
	}

	@GetMapping("/restaurante/{id}")
	public List<Produto> getProdutoPorRestaurante(@PathVariable int id) {
		return produtoRepository.getProdutosByIdRestaurante(id);
	}

	@GetMapping("/promocao/{id}")
	public List<Produto> getProdutosPromocao(@PathVariable int id) {
		return produtoRepository.getProdutoPromocao(id);
	}

	@PostMapping
	public Produto setRestaurante(@Validated @RequestBody Produto p) {
		p.setStatus("1");
		p.setVendidos(0);
		p = produtoRepository.save(p);
		return p;
	}
}