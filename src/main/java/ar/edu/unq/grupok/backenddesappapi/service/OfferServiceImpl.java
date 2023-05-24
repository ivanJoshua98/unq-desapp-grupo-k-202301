package ar.edu.unq.grupok.backenddesappapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupok.backenddesappapi.model.AppException;
import ar.edu.unq.grupok.backenddesappapi.model.Offer;
import ar.edu.unq.grupok.backenddesappapi.persistence.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService{
	
	@Autowired
	private OfferRepository offerRepository;

	@Override
	public List<Offer> getAllOffers() {
		return this.offerRepository.findAll();
	}

	@Override
	public Offer saveOffer(Offer offer) {
		return this.offerRepository.save(offer);
	}

	@Override
	public Offer getOfferById(UUID id) {
		return this.offerRepository.findById(id).orElseThrow(() -> new AppException("Offer not found by id: " + id, HttpStatus.NOT_FOUND));
	}

	@Override
	public void deleteOfferById(UUID id) {
		this.offerRepository.deleteById(id);
	}

}
