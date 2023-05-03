package ar.edu.unq.grupok.backenddesappapi.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@PostConstruct
	public void initialize() {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialData();
		}
	}
	
	private void fireInitialData() {
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
	
}
