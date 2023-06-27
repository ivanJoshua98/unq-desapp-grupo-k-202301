package ar.edu.unq.grupok.backenddesappapi.service;

import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import ar.edu.unq.grupok.backenddesappapi.model.Offer;
import ar.edu.unq.grupok.backenddesappapi.model.OperationType;
import ar.edu.unq.grupok.backenddesappapi.model.Role;
import ar.edu.unq.grupok.backenddesappapi.model.UserModel;
import jakarta.annotation.PostConstruct;

@Service
@Transactional
public class InitInMemoryService {

	protected final Log logger = LogFactory.getLog(getClass());

	@Value("${spring.datasource.driverClassName}")
	private String className;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired 
	BinanceProxyService binanceProxyService;

	@PostConstruct
	public void initialize() {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialData();
		}
	}	
	
	private void createAndSaveUsers(){
		
		Role role1 = new Role("USER");
		this.roleService.saveRole(role1);
		Role role2 = new Role("ADMIN");
		this.roleService.saveRole(role2);
		
		UserModel user1 = new UserModel("Lionel", "Messi", "leomessi@mail.com", "Rosario, Argentina", "Diciembre22",
				"8205730285928123474740", "47239157");
		
		UserModel user2 = new UserModel("Angel", "DiMaria", "fideo@mail.com", "Rosario, Argentina", "Diciembre2022",
				"7474082052812347302859", "84126191");
		
		UserModel user3 = new UserModel("Emiliano", "Martinez", "dibumartinez@mail.com", "Mar del Plata, Argentina", "18Dic2022",
				"2852382057304747409281", "10478317");
		
		UserModel user4 = new UserModel("Rodrigo", "DePaul", "rodridepaul@mail.com", "Avellaneda, Argentina", "18Diciembre",
				"0247428123874020573598", "43495813");
		
		UserModel user5 = new UserModel("Nicolas", "Otamendi", "nicoota@mail.com", "Tigre, Argentina", "18Diciembre22",
				"1220585927302803474748", "34472300");
		
		user1.addRole(role1);
		user2.addRole(role1);
		user3.addRole(role1);
		user4.addRole(role1);
		user5.addRole(role1);
		
		userService.saveUser(user1);
		userService.saveUser(user2);
		userService.saveUser(user3);
		userService.saveUser(user4);
		userService.saveUser(user5);

	}
	
	private void createAndSaveOffers() {
		
		UserModel author = this.userService.getUserByEmail("dibumartinez@mail.com");
		UserModel client1 = this.userService.getUserByEmail("rodridepaul@mail.com");
		UserModel client2 = this.userService.getUserByEmail("fideo@mail.com");
		UserModel client3 = this.userService.getUserByEmail("nicoota@mail.com");

		Crypto crypto = this.binanceProxyService.getCryptoValue("ALICEUSDT");
		Crypto crypto1 = this.binanceProxyService.getCryptoValue("MATICUSDT");
		Offer offer1 = new Offer(crypto, 10, crypto.getPrice(), 3523, author, OperationType.SALE);
		Offer offer2 = new Offer(crypto1, 3, crypto1.getPrice(), 1056, author, OperationType.BUY);
		Offer offer3 = new Offer(crypto, 4, crypto.getPrice(), 1408, author, OperationType.SALE);
		
		author.addOperation(offer1);
		author.addOperation(offer2);
		author.addOperation(offer3);
		
		client1.reportTransaction(offer1, LocalDateTime.now().plusMinutes(5));
		client2.reportTransaction(offer2, LocalDateTime.now().plusMinutes(5));
		client3.reportTransaction(offer3, LocalDateTime.now().plusMinutes(5));
		
		offerService.saveOffer(offer1);
		offerService.saveOffer(offer2);
		offerService.saveOffer(offer3);
	}
	
	
	private void fireInitialData() {
		createAndSaveUsers();
		this.binanceProxyService.getAndSaveAllCryptos();
		try {
			createAndSaveOffers();
		} catch (RuntimeException e) {
			this.logger.warn(e.getMessage());
		}
	}	
	
}