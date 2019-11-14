package godinner.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.AvaliacaoUsuario;
import godinner.app.model.RetornoFloat;
import godinner.app.model.RetornoInt;
import godinner.app.repository.AvaliacaoUsuarioRepository;

@RestController
@RequestMapping("/avaliacao")
@CrossOrigin(origins = "http://localhost:3000")
public class AvaliacaoUsuarioResource {

	@Autowired
	private AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;

	@GetMapping
	public List<AvaliacaoUsuario> getAvaliacoesUsuario() {
		return avaliacaoUsuarioRepository.findAll();
	}
	
	
	@GetMapping("/mediarestaurante/{id}")
	public RetornoFloat getMediaAvaliacaoRestaurante(@PathVariable float id) {
		float mediaRestaurante = avaliacaoUsuarioRepository.getMediaAvaliacaoRestaurante(id);
		RetornoFloat retornoFloat = new RetornoFloat(mediaRestaurante );
		return retornoFloat;
	}
	
}
