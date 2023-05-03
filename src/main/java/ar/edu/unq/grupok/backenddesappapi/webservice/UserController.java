package ar.edu.unq.grupok.backenddesappapi.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupok.backenddesappapi.model.User;
import ar.edu.unq.grupok.backenddesappapi.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User services", description = "Manage users of the application")
@RestController
@Transactional
@RequestMapping("/p2p")
class UserController {

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
	
}
