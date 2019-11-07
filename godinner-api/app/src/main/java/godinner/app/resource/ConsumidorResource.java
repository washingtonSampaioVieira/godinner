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
import godinner.app.model.Cidade;
import godinner.app.model.Consumidor;
import godinner.app.model.Endereco;
import godinner.app.repository.CidadeRepository;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.EnderecoRepository;

@RestController
@RequestMapping("/consumidor")
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = { "eventBusIndex", "verbose" })
public class ConsumidorResource {

	@Autowired
	private ConsumidorRepository consumidorRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private JwtTokenUtill jwtTokenUtil;
	
	@Value("aes.secret.key")
	private String secret;

	@GetMapping("/todos")
	public List<Consumidor> getConsumidor() {
		return consumidorRepository.findAll();
	}

	@PostMapping
	public Consumidor setConsumidor(@Validated @RequestBody Consumidor consumidor) {
		Endereco endereco = consumidor.getEndereco();
		Endereco enderecoSalvo = enderecoRepository.save(endereco);

		Cidade c = cidadeRepository.getCidade(enderecoSalvo.getCidade().getId());

		enderecoSalvo.setCidade(c);
		consumidor.setEndereco(enderecoSalvo);

		Consumidor consumidorSalvo = consumidorRepository.save(consumidor);

		return consumidorSalvo;
	}
	@PostMapping("/redesocial")
	public Consumidor setConsumidorViaRedeSocial(@Validated @RequestBody Consumidor consumidor) {
		consumidor.setSenha(null);
		Endereco endereco = consumidor.getEndereco();
		Endereco enderecoSalvo = enderecoRepository.save(endereco);

		Cidade c = cidadeRepository.getCidade(enderecoSalvo.getCidade().getId());

		enderecoSalvo.setCidade(c);
		consumidor.setEndereco(enderecoSalvo);

		Consumidor consumidorSalvo = consumidorRepository.save(consumidor);

		return consumidorSalvo;
	}

	@GetMapping("/valida/cpf/{cpf}")
	public boolean validarCpf(@PathVariable String cpf) {
		if (cpf.matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")) {
			ValidaCadastro validaCadastro = new ValidaCadastro();
			if (validaCadastro.isCPF(cpf)) {
				boolean resultado = (consumidorRepository.validarCpfUnico(cpf) == 0 ? true : false);
				return resultado;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	@GetMapping("/valida/email/{email}")
	public boolean validarEmail(@PathVariable String email) {
		if (email.matches("^[a-z0-9.]+@[a-z0-9]+.[a-z]+\\.([a-z]+)?$")) {
			boolean resultado = (consumidorRepository.validarEmailUnico(email) == 0 ? true : false);

			return resultado;
		} else {
			return false;
		}
	}

	@GetMapping("/este")
	public Consumidor getRestauranteByToken(@RequestHeader String token) {
		AES aes = new AES(this.secret);
		token = aes.decrypt(token);
//		token = 
		String email = jwtTokenUtil.getUsernameFromToken(token);
		Consumidor consumidorLogado = consumidorRepository.getConsumidorByEmail(email);
		return consumidorLogado;
	}
	
	@GetMapping("/teste")
	public void teste() {
		 final String secretKey = "ssshhhhhhhhhhh!!!!";
	     
		 	AES aes = new AES(this.secret);	
		    String originalString = "howtodoinjava.com";
		    String encryptedString = aes.encrypt(originalString) ;
		    String decryptedString = aes.decrypt(encryptedString ) ;

	}
}