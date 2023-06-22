package ar.edu.unq.grupok.backenddesappapi.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupok.backenddesappapi.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{
	
	Optional<Role> findByName(String username);

}
