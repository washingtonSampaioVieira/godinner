package godinner.app.resource;

import java.util.List;

import javax.websocket.server.PathParam;

import org.hibernate.validator.cfg.context.ReturnValueTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.Produto;
import godinner.app.model.ProdutoExibicao;
import godinner.app.model.Restaurante;
import godinner.app.repository.ProdutoRepository;
import godinner.app.repository.RestauranteRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoResource {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	
	
	
	@GetMapping("/todos")
	public List<Produto> getProduto(){
		return produtoRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public Produto getProdutoPorId(@PathVariable int id){
		return produtoRepository.getProdutosById(id);
	}
	
	@GetMapping("/exibicao/{id}")
	public List<ProdutoExibicao> getProdutoExibicao(@PathVariable int id){
		
		return produtoRepository.getProdutoExibicao();
	}
	
	@GetMapping("/todos/{id}")
	public List<Produto> getProdutoPorRestaurante(@PathVariable int id){
		return produtoRepository.getProdutosByIdRestaurante(id);
	}
	
	@PostMapping("/novo")
	public Produto setRestaurante(@Validated @RequestBody Produto p) {
		p = produtoRepository.save(p);
		return p;
	}
	
	
	
	private List<ProdutoExibicao> setDistancia(List<ProdutoExibicao> produtos){
		
		for (int i = 0; i < produtos.size(); i++) {
			
			produtos.get(i).setTempoEntrega("10mins");
			produtos.get(i).setDistacia("2km");;
			
			
		}
		
		return produtos;
		
	}
	
}
