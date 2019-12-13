package godinner.app.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import godinner.app.model.Consumidor;
import godinner.app.model.Funcionario;
import godinner.app.model.Restaurante;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtill implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60000 * 60000;

	@Value("${jwt.secret}")
	private String secret;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateTokenConsumidor(Consumidor consumidor) {
		Map<String, Object> claims = new HashMap<>();

		return doGenerateToken(claims, consumidor.getEmail(), consumidor.getId());
	}

	public String generateTokenRestaurante(Restaurante restaurante) {
		Map<String, Object> claims = new HashMap<>();

		return doGenerateToken(claims, restaurante.getEmail(), restaurante.getId());
	}

	public String generateTokenFuncionario(Funcionario funcionario) {
		Map<String, Object> claims = new HashMap<>();

		return doGenerateToken(claims, funcionario.getEmail(), funcionario.getId());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject, int id) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}