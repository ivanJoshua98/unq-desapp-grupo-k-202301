package ar.edu.unq.grupok.backenddesappapi.service;

import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import ar.edu.unq.grupok.backenddesappapi.model.InvalidPublishedPriceException;
import ar.edu.unq.grupok.backenddesappapi.model.Offer;
import ar.edu.unq.grupok.backenddesappapi.model.OperationType;
import ar.edu.unq.grupok.backenddesappapi.model.User;
import jakarta.annotation.PostConstruct;

@Service
@Transactional
public class InitServiceInMemory {

	protected final Log logger = LogFactory.getLog(getClass());

	@Value("${spring.datasource.driverClassName}")
	private String className;
	
	@Autowired
	private UserService userService;
	
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
		User user1 = new User("Lionel", "Messi", "leomessi@mail.com", "Rosario, Argentina", "Diciembre22",
				"8205730285928123474740", "47239157");
		
		User user2 = new User("Angel", "DiMaria", "fideo@mail.com", "Rosario, Argentina", "Diciembre2022",
				"7474082052812347302859", "84126191");
		
		User user3 = new User("Emiliano", "Martinez", "dibumartinez@mail.com", "Mar del Plata, Argentina", "18Dic2022",
				"2852382057304747409281", "10478317");
		
		User user4 = new User("Rodrigo", "DePaul", "rodridepaul@mail.com", "Avellaneda, Argentina", "18Diciembre",
				"0247428123874020573598", "43495813");
		
		User user5 = new User("Nicolas", "Otamendi", "nicoota@mail.com", "Tigre, Argentina", "18Diciembre22",
				"1220585927302803474748", "34472300");
		
		userService.saveUser(user1);
		userService.saveUser(user2);
		userService.saveUser(user3);
		userService.saveUser(user4);
		userService.saveUser(user5);

	}
	
	private void createAndSaveOffers() throws InvalidPublishedPriceException {
		
		User author = this.userService.getUserByEmail("dibumartinez@mail.com");
		User client1 = this.userService.getUserByEmail("rodridepaul@mail.com");
		User client2 = this.userService.getUserByEmail("fideo@mail.com");
		User client3 = this.userService.getUserByEmail("nicoota@mail.com");
		
		Crypto crypto = this.binanceProxyService.getCryptoValue("ALICEUSDT");
		Offer offer1 = new Offer(crypto, 10, crypto.getPrice(), 3523, author, OperationType.SALE);
		Offer offer2 = new Offer(crypto, 3, crypto.getPrice(), 1056, author, OperationType.BUY);
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
		} catch (InvalidPublishedPriceException e) {
			e.printStackTrace();
		}
	}	
	
}