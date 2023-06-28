package ar.edu.unq.grupok.backenddesappapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import ar.edu.unq.grupok.backenddesappapi.model.UserModel;
import ar.edu.unq.grupok.backenddesappapi.security.JWTProvider;
import ar.edu.unq.grupok.backenddesappapi.webservice.UserController;
import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class UserControllerEndToEndTest {
	
	private static final String URL_DEPLOY = "https://unq-desapp-grupo-k-202301-production.up.railway.app";

	@LocalServerPort
	private int port;

	@Autowired
	private UserController userController;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private JWTProvider jwtProvider;

	private UserModel anyUser;
	
	private String token;
	
	


    @BeforeEach
    public void init() {
    	this.anyUser = new UserModel("Lautaro", "Martinez", "toro@mail.com", "Bahia Blanca, Argentina", ".18Diciembre22",
				"1220280347448058375927", "43072034");
    	
    	this.token = this.jwtProvider.generateToken(
                new UsernamePasswordAuthenticationToken("dibumartinez@mail.com", "18Dic2022"));
    }

	@Test
	void contextLoads() throws Exception {
		assertThat(this.userController).isNotNull();
	}

	@Test
	void getAllUsersRegisteredSuccessfullyTest() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<Void> request = new HttpEntity<>(headers);
		
		ResponseEntity<String> result = restTemplate.exchange(URL_DEPLOY + "/p2p/users", HttpMethod.GET, request, String.class);

		assertEquals(200, result.getStatusCode().value());

	}

	@Test
	void getAllUsersRegisteredSuccessfullyWithTheirPropertiesTest() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<Void> request = new HttpEntity<>(headers);
		
		ResponseEntity<String> result = restTemplate.exchange(URL_DEPLOY + "/p2p/users", HttpMethod.GET, request, String.class);

		assertTrue(result.getBody().contains("name"));
		assertTrue(result.getBody().contains("lastName"));
		assertTrue(result.getBody().contains("email"));
		assertTrue(result.getBody().contains("address"));
		assertTrue(result.getBody().contains("password"));
		assertTrue(result.getBody().contains("criptoWallet"));
		assertTrue(result.getBody().contains("cvu"));
	}

	@Test
	void registerNewUserSuccessfullyTest() throws Exception {

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertEquals(201, result.getStatusCode().value());
		
		this.restTemplate.delete(URL_DEPLOY + "/p2p/users/delete/" + this.anyUser.getEmail());

	}

	@Test
	void registerAUserThatAlreadyExistsErroneouslyTest() throws Exception {

		this.anyUser.setEmail("dibumartinez@mail.com");

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertEquals(400, result.getStatusCode().value());

	}

	@Test
	void registerAUserWithEmailAlreadyUsedIsNotPermittedTest() throws Exception {

		this.anyUser.setEmail("dibumartinez@mail.com");

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertEquals("Email: dibumartinez@mail.com is already used", result.getBody());

	}

	@Test
	void registerAUserWithPasswordLessThanSixCharactersIsNotPermittedTest() throws Exception {

		this.anyUser.setPassword(".Au2");

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("The password is less than 6 characters"));

	}

	@Test
	void registerAUserWithPasswordThatDontHaveAUpperCaseIsNotPermittedTest() throws Exception {

		this.anyUser.setPassword(".anyuseru2");

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Password must contain 1 or more uppercase characters."));

	}

	@Test
	void registerAUserWithPasswordThatDontHaveALowerCaseIsNotPermittedTest() throws Exception {

		this.anyUser.setPassword(".ANYUSER2");

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Password must contain 1 or more lowercase characters."));

	}


	@Test
	void registerAUserWithPasswordThatDontHaveASpecialCharacterIsNotPermittedTest() throws Exception {

		this.anyUser.setPassword("anyuserAu2");

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Password must contain 1 or more special characters."));

	}

	@Test
	void registerAUserWithCvuThatDontHaveA22CharactersIsNotPermittedTest() throws Exception {

		this.anyUser.setCvu("12334");

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Invalid length for CVU"));

	}

	@Test
	void registerAUserWithCryptoWalletThatDontHave8CharactersIsNotPermittedTest() throws Exception {

		this.anyUser.setCriptoWallet("6534");

		ResponseEntity<String> result = restTemplate.postForEntity(URL_DEPLOY + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Invalid length for wallet"));

	}

}
