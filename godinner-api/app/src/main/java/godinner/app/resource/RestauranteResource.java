package godinner.app.resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import godinner.app.model.Cidade;
import godinner.app.model.Endereco;
import godinner.app.model.Restaurante;
import godinner.app.repository.CidadeRepository;
import godinner.app.repository.EnderecoRepository;
import godinner.app.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurante")
public class RestauranteResource {
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@GetMapping("/todos")
	public List<Restaurante> getRestaurantes(){
		return restauranteRepository.findAll();
	}
	
	@PostMapping("/novo")
	public Restaurante setRestaurante (@Validated @RequestBody Restaurante restaurante) {
		Endereco endereco = restaurante.getEndereco();
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		
		Cidade c = cidadeRepository.getCidade(enderecoSalvo.getCidade().getId());

		enderecoSalvo.setCidade(c);
		restaurante.setEndereco(enderecoSalvo);
		
		Restaurante restauranteSalvo = restauranteRepository.save(restaurante);	
		
		return restauranteSalvo;
	}
	
	
	@GetMapping("/valida/cnpj")
	public boolean validarCnpj(@RequestBody String cnpj) {
		return restauranteRepository.validarCnpjUnico(cnpj) == 0? false: true;
	}
	
	@GetMapping("/valida/email")
	public boolean validarEmail(@RequestBody String email) {
		return restauranteRepository.validarEmailUnico(email) == 0? false: true;
	}

}
