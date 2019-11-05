package godinner.app.resource;

import javax.annotation.processing.SupportedOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import godinner.app.config.JwtTokenUtill;
import godinner.app.model.Consumidor;
import godinner.app.model.Funcionario;
import godinner.app.model.JWTRequest;
import godinner.app.model.JWTResponse;
import godinner.app.model.Restaurante;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.FuncionarioRepository;
import godinner.app.repository.RestauranteRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = { "eventBusIndex", "verbose" })
@RequestMapping("/login")
public class JwtAuthenticationResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	@Autowired
	private ConsumidorRepository consumidorRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@PostMapping("/consumidor")
	public ResponseEntity<?> createAuthenticationTokenConsumidor(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		final Consumidor consumidor = consumidorRepository.getConsumidorByEmailAndPass(authenticationRequest.getEmail(),
				authenticationRequest.getPassword());

		if (consumidor != null) {
			final String token = jwtTokenUtil.generateTokenConsumidor(consumidor);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");
	}

	@PostMapping("/restaurante")
	public ResponseEntity<?> createAuthenticationTokenRestaurante(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		final Restaurante restaurante = restauranteRepository
				.getRestauranteByEmailAndPass(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		if (restaurante != null) {
			final String token = jwtTokenUtil.generateTokenRestaurante(restaurante);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");
	}

	
	
	@PostMapping("/funcionarios")
	public ResponseEntity<?> createAuthenticationTokenFuncinario(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		final Funcionario funcionario = funcionarioRepository
				.getFuncionarioByEmailAndPass(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		if (funcionario != null) {
			final String token = jwtTokenUtil.generateTokenFuncionario(funcionario);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok(false);
	}
	
	
	@PostMapping("/redesocial")
	public ResponseEntity<?> createAuthenticationTokenConsumidorViaRedeSocial(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		final Consumidor consumidor = consumidorRepository.getConsumidorLogadoPorRedeSocial(authenticationRequest.getEmail());  

		if (consumidor!= null) {
			final String token = jwtTokenUtil.generateTokenConsumidor(consumidor);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");
	}
	
	

}