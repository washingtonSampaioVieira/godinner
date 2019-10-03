package godinner.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.Categoria;
import godinner.app.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public List<Categoria> getCategorias() {
		return categoriaRepository.buscar10Aleatorios();
	}

	@PostMapping
	public Categoria setCategoria(@RequestBody Categoria c) {
		Categoria categoriaSalva = categoriaRepository.save(c);
		return categoriaSalva;
	}

}
