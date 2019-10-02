package godinner.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.Cidade;
import godinner.app.repository.CidadeRepository;

@RestController
@RequestMapping("/cidade")
@CrossOrigin(origins = "http://localhost:3000")
public class CidadeResource {
	
	@Autowired
	CidadeRepository cidadeRepository;

	@GetMapping
	public List<Cidade> getCidades() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public List<Cidade> getCidadesPorEstados(@PathVariable int id) {
		
		return cidadeRepository.getCidadesPorEstados(id);		
	}
}
