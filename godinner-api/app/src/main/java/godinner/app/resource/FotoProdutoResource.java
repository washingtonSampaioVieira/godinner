package godinner.app.resource;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.FotoProduto;
import godinner.app.repository.FotoProdutoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/fotoproduto")
public class FotoProdutoResource {
	
	@Autowired
	FotoProdutoRepository fotoProdutoRepository;
	
	@GetMapping("/todos")
	public List<FotoProduto> getFotoProdutos(){
		return fotoProdutoRepository.findAll();
	}
	
	@GetMapping("/todos/{id}")
	public List<FotoProduto> getFotoProdtosIdProduto(@PathVariable int id){
		List<FotoProduto> fotosProd = fotoProdutoRepository.findByIdProduto(id);
		return fotosProd;
	}
}
