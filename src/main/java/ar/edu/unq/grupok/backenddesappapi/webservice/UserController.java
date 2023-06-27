package ar.edu.unq.grupok.backenddesappapi.webservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ar.edu.unq.grupok.backenddesappapi.model.CryptoVolume;
import ar.edu.unq.grupok.backenddesappapi.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.grupok.backenddesappapi.model.UserModel;
import ar.edu.unq.grupok.backenddesappapi.security.JWTProvider;
import ar.edu.unq.grupok.backenddesappapi.service.RoleService;
import ar.edu.unq.grupok.backenddesappapi.service.UserService;
import ar.edu.unq.grupok.backenddesappapi.dto.CryptoVolumeDTO;
import ar.edu.unq.grupok.backenddesappapi.dto.UserDTO;
import ar.edu.unq.grupok.backenddesappapi.dto.UserLoginDTO;
import ar.edu.unq.grupok.backenddesappapi.dto.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User services", description = "Manage users of the application")
@RestController
@Transactional
@RequestMapping("/p2p")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private JWTProvider jwtProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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

		UserModel user = userService.getUserByEmail(email);

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
	public ResponseEntity<UserModel> registerUser(@Valid @RequestBody UserDTO newUser){
		UserModel user = new UserModel(newUser.getName(), 
							 newUser.getLastName(),
							 newUser.getEmail(),
							 newUser.getAddress(),
							 newUser.getPassword(), 
							 newUser.getCvu(),
							 newUser.getCriptoWallet());
		Role role = this.roleService.getByName("USER");
		user.addRole(role);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
	}

	@Operation(summary = "Login a user")
	@PostMapping("/login")
	public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody UserLoginDTO request){
		
		UserModel user = userService.getUserByEmail(request.getUserEmail());
		
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUserEmail(), 
				request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = this.jwtProvider.generateToken(authentication);
		TokenDTO tokenInfo = new TokenDTO(token);
		
		return ResponseEntity.ok()
                .header(
                    HttpHeaders.AUTHORIZATION,
                    tokenInfo.getJwtToken()
                )
                .body(convertUserEntityToUserDTO(user));
	}
	
	
	private UserDTO convertUserEntityToUserDTO(UserModel user) {
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
