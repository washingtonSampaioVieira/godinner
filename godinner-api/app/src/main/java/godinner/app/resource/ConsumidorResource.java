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
import godinner.app.model.Consumidor;
import godinner.app.model.Endereco;
import godinner.app.model.Restaurante;
import godinner.app.repository.CidadeRepository;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.EnderecoRepository;

@RestController
@RequestMapping("/consumidor")
public class ConsumidorResource {
	@Autowired
	ConsumidorRepository consumidorRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	
	@GetMapping("/todos")
	public List<Consumidor> getConsumidor(){
		return consumidorRepository.findAll();
	}
	
	@PostMapping("/novo")
	public Consumidor setConsumidor (@Validated @RequestBody Consumidor consumidor) {
		Endereco endereco = consumidor.getEndereco();
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		
		Cidade c = cidadeRepository.getCidade(enderecoSalvo.getCidade().getId());

		enderecoSalvo.setCidade(c);
		consumidor.setEndereco(enderecoSalvo);
		
		Consumidor consumidorSalvo = consumidorRepository.save(consumidor);	
		
		return consumidorSalvo;
	}
	
	@GetMapping("/valida/cpf")
	public boolean validarCpf(@RequestBody String cnpj) {
		return consumidorRepository.validarCpfUnico(cnpj) == 0? false: true;
	}
	
	@GetMapping("/valida/email")
	public boolean validarEmail(@RequestBody String email) {
		return consumidorRepository.validarEmailUnico(email) == 0? false: true;
	}
	
}
