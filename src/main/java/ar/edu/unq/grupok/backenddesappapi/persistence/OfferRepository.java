package ar.edu.unq.grupok.backenddesappapi.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupok.backenddesappapi.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID>{

}
