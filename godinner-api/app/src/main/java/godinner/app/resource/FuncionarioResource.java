package godinner.app.resource;

import java.util.List;

import javax.annotation.processing.SupportedOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.config.JwtTokenUtill;
import godinner.app.helper.AES;
import godinner.app.helper.ValidaCadastro;
import godinner.app.model.Calendario;
import godinner.app.model.Cidade;
import godinner.app.model.Consumidor;
import godinner.app.model.Endereco;
import godinner.app.model.Funcionario;
import godinner.app.model.RetornoInt;
import godinner.app.repository.CidadeRepository;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.EnderecoRepository;
import godinner.app.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = { "eventBusIndex", "verbose" })
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	@Value("aes.secret.key")
	private String secret;
	
	@GetMapping
	public List<Funcionario> getFuncionario() {
		return funcionarioRepository.findAll();
	}

	
	@GetMapping("/este")
	public Funcionario getFuncionarioByToken(@RequestHeader String token) {
		AES aes = new AES(secret);
		token = aes.decrypt(token);
		String email = jwtTokenUtil.getUsernameFromToken(token);
		Funcionario funcionarioLogado = funcionarioRepository.getFuncionarioByEmail(email);
		return funcionarioLogado;
	}
	
	@GetMapping("/restaurantescadastrado")
	public String getRestauranteCadastradoPorMes() {
		
		
		Calendario calendario = new Calendario();
		
		
		return calendario.jsonMeses(funcionarioRepository.getRestauranteCadastradoPorMes());
	}
	
	
	
	
	
}