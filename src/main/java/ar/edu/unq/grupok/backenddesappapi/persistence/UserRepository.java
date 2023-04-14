package ar.edu.unq.grupok.backenddesappapi.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupok.backenddesappapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	
	User findByEmail(String email);
	
	User findByName(String username);
	
}