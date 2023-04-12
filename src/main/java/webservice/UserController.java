package webservice;

import java.util.List;
import java.util.stream.Collectors;

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
import service.UserService;


@RestController
@Transactional
@RequestMapping("/p2p")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> registeredUsers(){
		return ResponseEntity.ok()
							 .body(userService.registeredUsers()
									 .stream()
									 	.map(this::convertUserEntityToUserDTO)
									 		.collect(Collectors.toList()));
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody UserDTO newUser){
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
