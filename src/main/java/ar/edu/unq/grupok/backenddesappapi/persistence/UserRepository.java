package ar.edu.unq.grupok.backenddesappapi.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupok.backenddesappapi.model.UserModel;


@Configuration
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{
	
	Optional<UserModel> findByEmail(String email);
	
	Optional<UserModel> findByName(String username);
	
}
