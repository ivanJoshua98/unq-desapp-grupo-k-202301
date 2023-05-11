package ar.edu.unq.grupok.backenddesappapi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;

public interface CryptoRepository extends RevisionRepository<Crypto, String, Double>, JpaRepository<Crypto, String>{

	Crypto findBySymbol(String symbol);
}
