package ar.edu.unq.grupok.backenddesappapi.model;

public class PurchaseOffer {
	
	public void validate(Offer offer) throws PriceDifferenceException {
		
		if (offer.getCrypto().getPrice() > offer.getPriceOfCrypto()) {
			throw new PriceDifferenceException("the price of the system is higher than the price indicated by the user");
		}
		
	}

}
