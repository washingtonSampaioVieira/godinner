package godinner.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.CategoriaProduto;
import godinner.app.repository.CategoriaProdutoRepository;
import godinner.app.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoriaproduto")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaProdutoResource {
	
	@Autowired
	CategoriaProdutoRepository categoriaProdutoRepository;
	
	@GetMapping
	public List<CategoriaProduto> getCategoriasProduto() {
		return categoriaProdutoRepository.findAll();
	}

}
