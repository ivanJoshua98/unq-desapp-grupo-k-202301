package ar.edu.unq.grupok.backenddesappapi.service;

import java.util.List;
import java.util.UUID;

import ar.edu.unq.grupok.backenddesappapi.model.Offer;

public interface OfferService {

	List<Offer> getAllOffers();
    
	Offer saveOffer(Offer offer);
    
	Offer getOfferById(UUID id);
    
    void deleteOfferById(UUID id);
	
}
