package godinner.app.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.helper.ValidaCadastro;
import godinner.app.model.Cidade;
import godinner.app.model.Consumidor;
import godinner.app.model.Endereco;
import godinner.app.model.ProdutoExibicao;
import godinner.app.model.Restaurante;
import godinner.app.model.RestauranteExibicao;
import godinner.app.repository.CidadeRepository;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.EnderecoRepository;
import godinner.app.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin(origins = "http://localhost:3000")
public class RestauranteResource {

	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	ConsumidorRepository consumidorRepository;

	@GetMapping("/todos")
	public List<Restaurante> getRestaurantes() {
		return restauranteRepository.findAll();
	}

	@PostMapping("/novo")
	public Restaurante setRestaurante(@Validated @RequestBody Restaurante restaurante) {
		Endereco endereco = restaurante.getEndereco();
		Endereco enderecoSalvo = enderecoRepository.save(endereco);

		Cidade c = cidadeRepository.getCidade(enderecoSalvo.getCidade().getId());

		enderecoSalvo.setCidade(c);
		restaurante.setEndereco(enderecoSalvo);

		Restaurante restauranteSalvo = restauranteRepository.save(restaurante);

		return restauranteSalvo;
	}

	@GetMapping("/valida/cnpj/{cnpj}")
	public boolean validarCnpj(@PathVariable String cnpj) {
		cnpj = cnpj.replace("@", "/");
		if (cnpj.matches("[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}")) {
			ValidaCadastro validaCadastro = new ValidaCadastro();
			if (validaCadastro.isCNPJ(cnpj)) {
				return restauranteRepository.validarCnpjUnico(cnpj) == 0 ? true : false;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	@GetMapping("/valida/email/{email}")
	public boolean validarEmail(@PathVariable String email) {
		if (email.matches("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$")) {
			return restauranteRepository.validarEmailUnico(email) == 0 ? true : false;
		} else {
			return false;
		}

	}
	

	@GetMapping("/todos/exibicao/{id}")
	public List<RestauranteExibicao> getRestaurantesExibicao(@PathVariable int id){
		List<RestauranteExibicao> r =  restauranteRepository.getRestauranteExibicao();
		Consumidor c = consumidorRepository.getPorId(id);
		List<RestauranteExibicao> restaurantesExibicao =  setDadosExibicao(r, c);
		
		return restaurantesExibicao ;
	}

	private List<RestauranteExibicao> setDadosExibicao(List<RestauranteExibicao> restaurantes, Consumidor c) {

		for (int i = 0; i < restaurantes.size(); i++) {
			restaurantes.get(i).setTempoEntrega("10mins");
			restaurantes.get(i).setDistancia("2km");
			restaurantes.get(i).setNota(5.0);

		}

//		return restaurantes;
		return null;

	}

}



















