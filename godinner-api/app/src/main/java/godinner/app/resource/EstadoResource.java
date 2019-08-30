package godinner.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.Estado;
import godinner.app.repository.EstadoRepository;

@RestController
@RequestMapping("/estado")
public class EstadoResource {
	@Autowired
	EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> getEstado(){
		return estadoRepository.findAll();
	}
	
}
