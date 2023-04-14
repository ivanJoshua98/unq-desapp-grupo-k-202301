package ar.edu.unq.grupok.backenddesappapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupok.backenddesappapi.model.UserNotFoundException;
import ar.edu.unq.grupok.backenddesappapi.persistence.UserRepository;
import ar.edu.unq.grupok.backenddesappapi.model.EmailAlreadyUsedException;
import ar.edu.unq.grupok.backenddesappapi.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> registeredUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		if (emailIsUsed(user.getEmail())) {
			throw new EmailAlreadyUsedException("Email: " + user.getEmail() + " is already used");
		}
		return userRepository.save(user);
	}

	private Boolean emailIsUsed(String email) {
		User user = getUserByEmail(email);
		return user != null;
	}
	
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User getUserById(UUID id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by id: " + id));
	}

	@Override
	public void deleteUserById(UUID id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByName(username);//.orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));
	}


}
