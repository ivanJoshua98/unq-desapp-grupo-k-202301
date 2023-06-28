package ar.edu.unq.grupok.backenddesappapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import ar.edu.unq.grupok.backenddesappapi.model.CryptoEnum;
import ar.edu.unq.grupok.backenddesappapi.security.JWTProvider;
import ar.edu.unq.grupok.backenddesappapi.webservice.CryptoController;
import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class CyptoControllerEndToEnd {

	private static final String URL_DEPLOY = "https://unq-desapp-grupo-k-202301-production.up.railway.app";

	@LocalServerPort
	private int port;

	@Autowired
	private CryptoController cryptoController;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private JWTProvider jwtProvider;
	
	private String token;
	
	private CryptoEnum anyCryptoSymbol;
	
	private HttpEntity<Void> request;
	
	 @BeforeEach
	    public void init() {
	    	this.anyCryptoSymbol = CryptoEnum.AAVEUSDT;
	    	
	    	this.token = this.jwtProvider.generateToken(
	                new UsernamePasswordAuthenticationToken("dibumartinez@mail.com", "18Dic2022"));
	    	
	    	HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			this.request = new HttpEntity<>(headers);
	    }
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(this.cryptoController).isNotNull();
	}
	
	@Test
	public void getAllCryptocurrenciesSuccessfullyTest() throws Exception {
		
		ResponseEntity<String> result = restTemplate.exchange(URL_DEPLOY + "/p2p/getCrypoValue/all", HttpMethod.GET, request, String.class);

		assertEquals(200, result.getStatusCode().value());
	}
	
	@Test
	public void getASpecificCryptocurrenciesSuccessfullyTest() throws Exception {
		
		ResponseEntity<String> result = restTemplate.exchange(URL_DEPLOY + "/p2p/getCryptoValue/" + this.anyCryptoSymbol, HttpMethod.GET, request, String.class);

		assertEquals(200, result.getStatusCode().value());
	}
	
	@Test
	public void getASpecificCryptocurrenciesWithInvalidSymbolTest() throws Exception {
		
		String invalidSymbol = "BADUSDT";
		
		ResponseEntity<String> result = restTemplate.exchange(URL_DEPLOY + "/p2p/getCryptoValue/" + invalidSymbol, HttpMethod.GET, request, String.class);

		assertEquals(404, result.getStatusCode().value());
	}
	
	@Test
	public void notFoundASpecificCryptocurrenciesWithInvalidSymbolTest() throws Exception {
		
		String invalidSymbol = "BADUSDT";
		
		ResponseEntity<String> result = restTemplate.exchange(URL_DEPLOY + "/p2p/getCryptoValue/" + invalidSymbol, HttpMethod.GET, request, String.class);

		assertEquals("Crypto not found by symbol: BADUSDT", result.getBody());
	}
	
	
	
	
	
}
