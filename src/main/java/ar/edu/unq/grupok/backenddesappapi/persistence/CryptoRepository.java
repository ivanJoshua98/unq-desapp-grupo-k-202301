package ar.edu.unq.grupok.backenddesappapi.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;

@Repository
public interface CryptoRepository extends RevisionRepository<Crypto, String, Double>, JpaRepository<Crypto, String>{

	Optional<Crypto> findBySymbol(String symbol);
}
