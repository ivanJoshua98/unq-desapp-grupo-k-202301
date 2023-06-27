package ar.edu.unq.grupok.backenddesappapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec;

import ar.edu.unq.grupok.backenddesappapi.model.UserModel;
import ar.edu.unq.grupok.backenddesappapi.security.JWTProvider;
import ar.edu.unq.grupok.backenddesappapi.webservice.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerEndToEndTest {

/*	@LocalServerPort
	private int port;
	
	@Autowired
	private UserController userController;
	
	@Autowired
    private WebTestClient webClient;
	
	@Autowired
	private JWTProvider jwtProvider;
	
	private UserModel anyUser;
	
	private String token;
	
	//private UserLoginDTO login;

    @BeforeEach
    public void init() {
    	this.anyUser = new UserModel("Lautaro", "Martinez", "toro@mail.com", "Bahia Blanca, Argentina", ".18Diciembre22",
				"1220280347448058375927", "43072034");
    	
    	this.token = this.jwtProvider.generateToken(
                new UsernamePasswordAuthenticationToken("dibumartinez@mail.com", "18Dic2022"));
    }
    
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(this.userController).isNotNull();
	}
	
	@Test
	public void getAllUsersRegisteredSuccessfullyTest() throws Exception {
		
		this.webClient.get().uri("/p2p/users").headers(http -> http.setBearerAuth(token)).exchange().expectStatus().isOk();

		//ResponseEntity<String> result = restTemplate.getForEntity(HTTP_LOCALHOST + port + "/p2p/users", String.class);

		//assertEquals(200, result.getStatusCode().value());
		
	}
	
	@Test
	public void getAllUsersRegisteredSuccessfullyWithTheirPropertiesTest() throws Exception {
		
		//this.webClient.get().uri("/p2p/users").exchange().expectBody().json("{\"name\":\"Jane\"}");
		
		//this.webClient.get().uri("/p2p/users").exchange().expectBody().jsonPath("$").isEqualTo("name");
		//this.webClient.get().uri("/p2p/users").exchange().expectBody().jsonPath("$[0].name");
		
		BodyContentSpec result = this.webClient.get().uri("/p2p/users").headers(http -> http.setBearerAuth(token)).exchange().expectBody();
		
		result.jsonPath("$[0].name").exists();
		result.jsonPath("$[0].lastName").exists();
		result.jsonPath("$[0].email").exists();
		result.jsonPath("$[0].address").exists();
		result.jsonPath("$[0].password").exists();
		result.jsonPath("$[0].criptoWallet").exists();
		result.jsonPath("$[0].cvu").exists();
		
	
		/*ResponseEntity<String> result = restTemplate.getForEntity(HTTP_LOCALHOST + port + "/p2p/users", String.class);

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

		this.webClient.post().uri("/p2p/register").bodyValue(this.anyUser).exchange().expectStatus().isCreated();
		
		//ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		//assertEquals(201, result.getStatusCode().value());
		
	}
	
	@Test
	public void registerAUserThatAlreadyExistsErroneouslyTest() throws Exception {
		
		this.anyUser.setEmail("dibumartinez@mail.com");
		
		this.webClient.post().uri("/p2p/register").bodyValue(this.anyUser).exchange().expectStatus().isBadRequest();
		
		//ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		//assertEquals(400, result.getStatusCode().value());
		
	}
	
	@Test
	public void registerAUserWithEmailAlreadyUsedIsNotPermittedTest() throws Exception {
		
		this.anyUser.setEmail("dibumartinez@mail.com");
		
		BodyContentSpec result = this.webClient.post().uri("/p2p/register").bodyValue(this.anyUser).exchange().expectBody();
		
		// Operator -$- : The root element to query. This starts all path expressions.
		result.jsonPath("$").isEqualTo("Email: dibumartinez@mail.com is already used");
		
		//ResponseEntity<String> result = restTemplate.postForEntity(HTTP_LOCALHOST + port + "/p2p/register", this.anyUser, String.class);

		//assertEquals("Email: dibumartinez@mail.com is already used", result.getBody());
		
	}*/
	
}
