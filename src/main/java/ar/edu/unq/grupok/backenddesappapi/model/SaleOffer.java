package ar.edu.unq.grupok.backenddesappapi.model;


public class SaleOffer {

	public void validate(Offer offer) throws PriceDifferenceException {
		
		if (offer.getCrypto().getPrice() < offer.getPriceOfCrypto()) {
			throw new PriceDifferenceException("the system price is below the price indicated by the user");
		}
		
	}

}
