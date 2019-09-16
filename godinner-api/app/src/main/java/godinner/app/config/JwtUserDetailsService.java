package godinner.app.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import godinner.app.model.Consumidor;
import godinner.app.model.Restaurante;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.RestauranteRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	ConsumidorRepository consumidorRepository;

	@Autowired
	RestauranteRepository restauranteRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Consumidor c = consumidorRepository.getConsumidorByEmail(email);
		Restaurante r = restauranteRepository.getRestauranteByEmail(email);
		
		if (c != null) {
			User u = new User(c.getEmail(), c.getId().toString(), new ArrayList<>());

			return u;
		} else if (r != null) {
			User u = new User(r.getEmail(), r.getId().toString(), new ArrayList<>());

			return u;
		}
		throw new UsernameNotFoundException("User not found with username: " + email);

	}
}