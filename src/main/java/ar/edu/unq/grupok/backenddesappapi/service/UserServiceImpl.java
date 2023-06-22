package ar.edu.unq.grupok.backenddesappapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupok.backenddesappapi.persistence.UserRepository;
import ar.edu.unq.grupok.backenddesappapi.model.AppException;
import ar.edu.unq.grupok.backenddesappapi.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserModel> registeredUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserModel saveUser(UserModel user) {
		if (Boolean.TRUE.equals(emailIsUsed(user.getEmail()))) {
			throw new AppException("Email: " + user.getEmail() + " is already used", HttpStatus.BAD_REQUEST);
		}
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	private Boolean emailIsUsed(String email) {
		try {
			UserModel user = getUserByEmail(email);
			return user != null;
		}
		catch(AppException e){
			return false;
		}
		
	}
	
	@Override
	public UserModel getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new AppException("User not found by email: " + email, HttpStatus.NOT_FOUND));
	}

	@Override
	public UserModel getUserById(UUID id) {
		return userRepository.findById(id).orElseThrow(() -> new AppException("User not found by id: " + id, HttpStatus.NOT_FOUND));
	}

	@Override
	public void deleteUserById(UUID id) {
		userRepository.deleteById(id);
	}

}
