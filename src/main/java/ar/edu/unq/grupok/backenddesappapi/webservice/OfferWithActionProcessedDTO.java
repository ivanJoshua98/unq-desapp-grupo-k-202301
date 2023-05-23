package ar.edu.unq.grupok.backenddesappapi.webservice;

import java.time.LocalDateTime;

import ar.edu.unq.grupok.backenddesappapi.model.OfferState;
import ar.edu.unq.grupok.backenddesappapi.model.OperationType;

public class OfferWithActionProcessedDTO {

	private LocalDateTime creationDate; 
	
	private String cryptoSymbol;

	private Integer amountOfCrypto;
	
	private Double priceOfCryptoInTheOffer;
	
	private Integer amountInPesos;
	
	private String authorEmail;

	private OperationType operationType;
	
	private OfferState offerState;
	
	private String clientEmail;

	private LocalDateTime tradingStartDate;
	
	
	public OfferWithActionProcessedDTO() {
		super();
	}
	
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
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

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public OfferState getOfferState() {
		return offerState;
	}

	public void setOfferState(OfferState offerState) {
		this.offerState = offerState;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public Double getPriceOfCryptoInTheOffer() {
		return priceOfCryptoInTheOffer;
	}

	public void setPriceOfCryptoInTheOffer(Double priceOfCryptoInTheOffer) {
		this.priceOfCryptoInTheOffer = priceOfCryptoInTheOffer;
	}

	public LocalDateTime getTradingStartDate() {
		return tradingStartDate;
	}

	public void setTradingStartDate(LocalDateTime tradingStartDate) {
		this.tradingStartDate = tradingStartDate;
	}


}
