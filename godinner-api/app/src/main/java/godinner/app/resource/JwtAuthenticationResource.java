package godinner.app.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import godinner.app.config.JwtTokenUtill;
import godinner.app.model.Consumidor;
import godinner.app.model.JWTRequest;
import godinner.app.model.JWTResponse;
import godinner.app.repository.ConsumidorRepository;

@RestController
@CrossOrigin
public class JwtAuthenticationResource {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	@Autowired
	ConsumidorRepository consumidorRepository;
//	@Autowired
//	private JwtUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {
		
		
		
		
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
		final Consumidor consumidor = consumidorRepository.getConsumidorByEmail(authenticationRequest.getEmail());
		System.out.println(consumidor);
		final String token = jwtTokenUtil.generateTokenConsumidor(consumidor);
		
		return ResponseEntity.ok(new JWTResponse(token));
		
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}