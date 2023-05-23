package ar.edu.unq.grupok.backenddesappapi.webservice;


import ar.edu.unq.grupok.backenddesappapi.model.OfferState;
import ar.edu.unq.grupok.backenddesappapi.model.OperationType;

public class OfferDTO {
	
	private String cryptoSymbol;

	private Integer amountOfCrypto;
	
	private Double priceOfCrypto;
	
	private Integer amountInPesos;
	
	private String authorEmail;
	
	private OfferState offerState; 

	private OperationType operationType;
	
	public OfferDTO() {
		super();
	}

	public String getCryptoSymbol() {
		return cryptoSymbol;
	}

	public void setCryptoSymbol(String cryptoSymbol) {
		this.cryptoSymbol = cryptoSymbol;
	}

	public Integer getAmountOfCrypto() {
		return amountOfCrypto;
	}

	public void setAmountOfCrypto(Integer amountOfCrypto) {
		this.amountOfCrypto = amountOfCrypto;
	}

	public Double getPriceOfCrypto() {
		return priceOfCrypto;
	}

	public void setPriceOfCrypto(Double priceOfCrypto) {
		this.priceOfCrypto = priceOfCrypto;
	}

	public Integer getAmountInPesos() {
		return amountInPesos;
	}

	public void setAmountInPesos(Integer amountInPesos) {
		this.amountInPesos = amountInPesos;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String email) {
		this.authorEmail = email;
	}

	public OperationType getOperationType() {
		return operationType;
	}
	
	public OfferState getOfferState() {
		return offerState;
	}

	public void setOfferState(OfferState offerState) {
		this.offerState = offerState;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}


}
