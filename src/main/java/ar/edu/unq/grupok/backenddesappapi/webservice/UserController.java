package ar.edu.unq.grupok.backenddesappapi.webservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ar.edu.unq.grupok.backenddesappapi.model.CryptoVolume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.grupok.backenddesappapi.model.User;
import ar.edu.unq.grupok.backenddesappapi.service.UserServiceImpl;
import ar.edu.unq.grupok.backenddesappapi.webservice.dto.CryptoVolumeDTO;
import ar.edu.unq.grupok.backenddesappapi.webservice.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User services", description = "Manage users of the application")
@RestController
@Transactional
@RequestMapping("/p2p")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Operation(summary = "Get all registered users")
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> registeredUsers(){
		return ResponseEntity.ok()
							 .body(userService.registeredUsers()
									 .stream()
									 	.map(this::convertUserEntityToUserDTO).toList());
	}

	@Operation(summary = "Get volumen of crypto operated between dates (yyyy-MM-dd HH:mm)")
	@GetMapping("/cryptoVolume/{email}/{startDate}/{endDate}")
	public ResponseEntity<List<CryptoVolumeDTO>> cryptoVolumeTraded(@PathVariable String email, @PathVariable String startDate, @PathVariable String endDate){

		User user = userService.getUserByEmail(email);

		// cast Dates to LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // Definir el patrón de formato
		LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
		LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);

		List<CryptoVolume> cryptoVolumes = user.cryptoVolumes(startDateTime, endDateTime);

		return ResponseEntity.ok()
				.body(cryptoVolumes
						.stream()
						.map(this::convertCryptoVolumeEntityToCryptoVolumeDTO).toList());

	}
	
	@Operation(summary = "Register a new user")
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO newUser){
		User user = new User(newUser.getName(), 
							 newUser.getLastName(),
							 newUser.getEmail(),
							 newUser.getAddress(),
							 newUser.getPassword(), 
							 newUser.getCvu(),
							 newUser.getCriptoWallet());
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
	}

	
	private UserDTO convertUserEntityToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(user.getName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		userDTO.setAddress(user.getAddress());
		userDTO.setPassword(user.getPassword());
		userDTO.setCvu(user.getCvu());
		userDTO.setCriptoWallet(user.getCriptoWallet());
		return userDTO;
	}

	public CryptoVolumeDTO convertCryptoVolumeEntityToCryptoVolumeDTO(CryptoVolume cryptoVolume) {
		CryptoVolumeDTO aCryptoVolumeDTO = new CryptoVolumeDTO();

		aCryptoVolumeDTO.setRequestDate(cryptoVolume.getRequestDate());
		aCryptoVolumeDTO.setCryptoSymbol(cryptoVolume.getCryptoSymbol());
		aCryptoVolumeDTO.setPriceOfCrypto(cryptoVolume.getPriceOfCrypto());
		aCryptoVolumeDTO.setPriceOfCryptoInPesos(cryptoVolume.getPriceOfCryptoInPesos());
		aCryptoVolumeDTO.setTotalAmountUSD(cryptoVolume.getTotalAmountUSD());
		aCryptoVolumeDTO.setTotalAmountPesos(cryptoVolume.getTotalAmountPesos());
		aCryptoVolumeDTO.setTotalAmountOfCrypto(cryptoVolume.getTotalAmountOfCrypto());

		return aCryptoVolumeDTO;
	}
}
