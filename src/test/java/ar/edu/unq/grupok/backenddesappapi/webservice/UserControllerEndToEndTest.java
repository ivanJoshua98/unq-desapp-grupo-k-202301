package ar.edu.unq.grupok.backenddesappapi.webservice;

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
import org.springframework.http.ResponseEntity;

import ar.edu.unq.grupok.backenddesappapi.model.User;
import jakarta.transaction.Transactional;

/*@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class UserControllerEndToEndTest {

	private static final String HTTP_LOCALHOST = "http://localhost:";

	@LocalServerPort
	private int port;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private User anyUser;
	

    @BeforeEach
    public void init() {
    	this.anyUser = new User("Lautaro", "Martinez", "toro@mail.com", "Bahia Blanca, Argentina", ".18Diciembre22",
				"1220280347448058375927", "43072034");
    }
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(this.userController).isNotNull();
	}
	
	@Test
	public void getAllUsersRegisteredSuccessfullyTest() throws Exception {

		ResponseEntity<String> result = restTemplate.getForEntity(HTTP_LOCALHOST + port + "/p2p/users", String.class);

		assertEquals(200, result.getStatusCode().value());
		
	}
	
	@Test
	public void getAllUsersRegisteredSuccessfullyWithTheirPropertiesTest() throws Exception {

		ResponseEntity<String> result = restTemplate.getForEntity(HTTP_LOCALHOST + port + "/p2p/users", String.class);

		assertTrue(result.getBody().contains("name"));
		assertTrue(result.getBody().contains("lastName"));
		assertTrue(result.getBody().contains("email"));
		assertTrue(result.getBody().contains("address"));
		assertTrue(result.getBody().contains("password"));
		assertTrue(result.getBody().contains("criptoWallet"));
		assertTrue(result.getBody().contains("cvu"));
	}
	
	@Test
	public void registerNewUserSuccessfullyTest() throws Exception {

		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertEquals(201, result.getStatusCode().value());
		
	}
	
	@Test
	public void registerAUserThatAlreadyExistsErroneouslyTest() throws Exception {
		
		this.anyUser.setEmail("dibumartinez@mail.com");
		
		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertEquals(400, result.getStatusCode().value());
		
	}
	
	@Test
	public void registerAUserWithEmailAlreadyUsedIsNotPermittedTest() throws Exception {
		
		this.anyUser.setEmail("dibumartinez@mail.com");
		
		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertEquals("Email: dibumartinez@mail.com is already used", result.getBody());
		
	}
	
	@Test
	public void registerAUserWithPasswordLessThanSixCharactersIsNotPermittedTest() throws Exception {
		
		this.anyUser.setPassword(".Au2");
		
		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("The password is less than 6 characters"));
		
	}
	
	@Test
	public void registerAUserWithPasswordThatDontHaveAUpperCaseIsNotPermittedTest() throws Exception {
		
		this.anyUser.setPassword(".anyuseru2");
		
		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Password must contain 1 or more uppercase characters."));
		
	}
	
	@Test
	public void registerAUserWithPasswordThatDontHaveALowerCaseIsNotPermittedTest() throws Exception {
		
		this.anyUser.setPassword(".ANYUSER2");
		
		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Password must contain 1 or more lowercase characters."));
		
	}
	
	
	@Test
	public void registerAUserWithPasswordThatDontHaveASpecialCharacterIsNotPermittedTest() throws Exception {
		
		this.anyUser.setPassword("anyuserAu2");
		
		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Password must contain 1 or more special characters."));
		
	}
	
	@Test
	public void registerAUserWithCvuThatDontHaveA22CharactersIsNotPermittedTest() throws Exception {
		
		this.anyUser.setCvu("12334");
		
		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Invalid length for CVU"));
		
	}
	
	@Test
	public void registerAUserWithCryptoWalletThatDontHave8CharactersIsNotPermittedTest() throws Exception {
		
		this.anyUser.setCriptoWallet("6534");
		
		ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		assertTrue(result.getBody().contains("Invalid length for wallet"));
		
	}
	
}*/
