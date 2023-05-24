package ar.edu.unq.grupok.backenddesappapi.webservice;

import java.time.LocalDateTime;

import ar.edu.unq.grupok.backenddesappapi.model.OperationType;

public class OpenOfferDTO {

	private LocalDateTime creationDate; 
	
	private OperationType operationType;

	private String cryptoSymbol;
	
	private Integer amountOfCrypto;
	
	private Double priceOfCryptoInTheOffer;
	
	private Integer amountInPesos;
	
	private String authorEmail;
	
	private Integer numberOfOperations;
	
	private String reputation;
	
	private String addressToOperate;
	

	public OpenOfferDTO() {
		super();
	}
	
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public OperationType getOperationType() {
		return operationType;
	}


	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
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


	public Double getPriceOfCryptoInTheOffer() {
		return priceOfCryptoInTheOffer;
	}


	public void setPriceOfCryptoInTheOffer(Double priceOfCryptoInTheOffer) {
		this.priceOfCryptoInTheOffer = priceOfCryptoInTheOffer;
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


	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}


	public Integer getNumberOfOperations() {
		return numberOfOperations;
	}


	public void setNumberOfOperations(Integer numberOfOperations) {
		this.numberOfOperations = numberOfOperations;
	}


	public String getReputation() {
		return reputation;
	}


	public void setReputation(String reputation) {
		this.reputation = reputation;
	}

	public String getAddressToOperate() {
		return addressToOperate;
	}


	public void setAddressToOperate(String addressToOperate) {
		this.addressToOperate = addressToOperate;
	}

	
	

}
