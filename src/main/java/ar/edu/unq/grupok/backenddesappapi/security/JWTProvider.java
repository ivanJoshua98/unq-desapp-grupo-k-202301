package ar.edu.unq.grupok.backenddesappapi.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import ar.edu.unq.grupok.backenddesappapi.model.AppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTProvider {

	 // LLAVE_MUY_SECRETA => [Base64] => TExBVkVfTVVZX1NFQ1JFVEE= //
	private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEE=";
	
	private SecretKey secretKey = Keys
									.hmacShaKeyFor(Base64.getEncoder()
									.encodeToString(JWT_SECRET_KEY.getBytes())
									.getBytes(StandardCharsets.UTF_8)); 
			

	public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 8; // 8 Horas
	
	public String generateToken(Authentication authentication) {
		String userEmail = authentication.getName();
		Date now = new Date();
		Date expirationToken = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
		String token = Jwts.builder()
				.setSubject(userEmail)
				.setIssuedAt(new Date())
				.setExpiration(expirationToken)
				.signWith(this.secretKey, SignatureAlgorithm.HS256)
				.compact();
		return token;
	}
	
	public String getUserNameFromJWT(String token) {
		/*Claims claims = Jwts.parser()
				.setSigningKey(JWT_SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();*/
		Claims claims = Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public Boolean validateToken(String token) {
		try {
			//Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token);
			Jwts.parserBuilder().setSigningKey(this.secretKey).build()
            .parseClaimsJws(token);
			return true;
		} 
		catch(Exception e) {
			throw new AppException("JWT incorrect or JWT expired", HttpStatus.UNAUTHORIZED);
		}
	}
	
}
