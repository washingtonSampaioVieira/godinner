package godinner.app.resource;

import javax.annotation.processing.SupportedOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import godinner.app.helper.AES;
import godinner.app.helper.Criptografia;
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
	
	@Value("aes.secret.key")
	private String secret;


	@PostMapping("/consumidor")
	public ResponseEntity<?> createAuthenticationTokenConsumidor(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		final Consumidor consumidor = consumidorRepository.getConsumidorByEmailAndPass(authenticationRequest.getEmail(),
				Criptografia.md5(authenticationRequest.getPassword()));

		if (consumidor != null) {
			final String token = jwtTokenUtil.generateTokenConsumidor(consumidor);
			//descriptografando o token
			AES aes = new AES(this.secret);			    
		    String tokenAES = aes.encrypt(token);
		    
			return ResponseEntity.ok(new JWTResponse(tokenAES));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");
	}

	@PostMapping("/restaurante")
	public ResponseEntity<?> createAuthenticationTokenRestaurante(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		final Restaurante restaurante = restauranteRepository
				.getRestauranteByEmailAndPass(authenticationRequest.getEmail(), Criptografia.md5(authenticationRequest.getPassword()));

		if (restaurante != null) {
			final String token = jwtTokenUtil.generateTokenRestaurante(restaurante);
			//descriptografando o token
			AES aes = new AES(this.secret);			    
		    String tokenAES = aes.encrypt(token);
		    
			return ResponseEntity.ok(new JWTResponse(tokenAES));
		}
		
		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");
	}

	
	
	@PostMapping("/funcionarios")
	public ResponseEntity<?> createAuthenticationTokenFuncinario(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		
		final Funcionario funcionario = funcionarioRepository
				.getFuncionarioByEmailAndPass(authenticationRequest.getEmail(), Criptografia.md5(authenticationRequest.getPassword()));
		System.out.println(funcionario);

		if (funcionario != null) {
			final String token = jwtTokenUtil.generateTokenFuncionario(funcionario);
			//descriptografando o token
			AES aes = new AES(this.secret);			    
		    String tokenAES = aes.encrypt(token);
		
			return ResponseEntity.ok(new JWTResponse(tokenAES));
		}
		//marina que fez fora de padrao
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