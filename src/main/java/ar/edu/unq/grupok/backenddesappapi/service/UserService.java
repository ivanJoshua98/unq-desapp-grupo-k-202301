package ar.edu.unq.grupok.backenddesappapi.service;

import java.util.List;
import java.util.UUID;

import ar.edu.unq.grupok.backenddesappapi.model.User;

public interface UserService {
	
    List<User> registeredUsers();
    
    User saveUser(User user);
    
    User getUserById(UUID id);
    
    void deleteUserById(UUID id);
    
    User getUserByUsername(String username);
	
    User getUserByEmail(String email);

}
