package godinner.app.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import godinner.app.model.Consumidor;
import godinner.app.model.Restaurante;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.RestauranteRepository;
import godinner.app.storage.Disco;


@RestController
@RequestMapping("/foto")
public class FotoResource {

	@Autowired
	private Disco disco;
	
	@Autowired
	private ConsumidorRepository consumidorRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@PostMapping("/restaurante")
	public Restaurante uploadRestaurante(@RequestParam MultipartFile foto, @RequestParam int id){
		Restaurante r = null;
		String localFoto = disco.salvarFoto(foto, "restaurante");
		if(localFoto != null) {
		  r = restauranteRepository.getPorId(id);
		  r.setFoto(localFoto);
		  restauranteRepository.save(r);
		}
		return r;
	}
	
	@PostMapping("/consumidor")
	public Consumidor uploadConsumidor(@RequestParam MultipartFile foto, @RequestParam int id){
		Consumidor c = null;
	
		String localFoto = disco.salvarFoto(foto, "consumidor");
		if(localFoto != null) {
			c = consumidorRepository.getPorId(id);
			c.setFotoPerfil(localFoto);
			consumidorRepository.save(c);
		}
		return c;
	}
	
	
	
}
