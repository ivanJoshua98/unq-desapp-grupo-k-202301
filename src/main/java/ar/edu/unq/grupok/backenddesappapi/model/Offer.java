package ar.edu.unq.grupok.backenddesappapi.model;

public class Offer {

	private Crypto crypto;
	
	private Integer amountOfCrypto;
	
	private Double priceOfCrypto;
	
	private Integer amountInPesos;
	
	private String userName;
	
	private OperationType operationType;
	
	private OfferState offerState;
	
	public Offer(Crypto crypto, Integer amountOfCrypto, Double priceOfCrypto, Integer amountInPesos, String userName,
				OperationType operationType) throws InvalidPublishedPriceException {
		
		super();
		this.crypto = crypto;
		this.amountOfCrypto = amountOfCrypto;
		
		if (this.priceIsValid(priceOfCrypto)) {
			this.priceOfCrypto = priceOfCrypto;
		} else {
			throw new InvalidPublishedPriceException("the published price of the crypto is invalid");
		}
			
		this.amountInPesos = amountInPesos;
		this.userName = userName;
		this.operationType = operationType;
		this.offerState = OfferState.OPEN;
	}
	
	
	public Boolean priceIsValid(Double priceOfCrypto) {
		Double fivePercent = (double) ((this.crypto.getPrice() * 5) / 100);
		
		return ((this.crypto.getPrice() + fivePercent) >= priceOfCrypto) && ((this.crypto.getPrice() - fivePercent) <= priceOfCrypto);
	}
	
	public Crypto getCrypto() {
		return crypto;
	}


	public Integer getAmountOfCrypto() {
		return amountOfCrypto;
	}


	public Double getPriceOfCrypto() {
		return priceOfCrypto;
	}


	public Integer getAmountInPesos() {
		return amountInPesos;
	}


	public String getUserName() {
		return userName;
	}


	public OperationType getOperationType() {
		return operationType;
	}


	public OfferState getOfferState() {
		return offerState;
	}
	
	public void processTransaction() {
		
	}

}
