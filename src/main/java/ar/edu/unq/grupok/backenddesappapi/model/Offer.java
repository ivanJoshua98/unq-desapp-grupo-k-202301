package ar.edu.unq.grupok.backenddesappapi.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Offer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@OneToOne
	@JoinTable(name = "offered_crypto",
	            joinColumns = @JoinColumn(name = "crypto_symbol"),
	            inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private Crypto crypto;
	
	private Integer amountOfCrypto;
	
	private Double priceOfCrypto;
	
	private Integer amountInPesos;
	
	@OneToOne
	@JoinTable(name = "offer's_author",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private User author;
	
	private OperationType operationType;
	
	private OfferState offerState;

	@OneToOne
	@JoinTable(name = "client's_author",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private User client;
	
	@Basic
	private LocalDateTime creationDate; 
	
	@Basic
	private LocalDateTime tradingStartDate;

	public Offer() {
		super();
		this.offerState = OfferState.OPEN;
		this.creationDate = LocalDateTime.now();
	}
	
	public Offer(Crypto crypto, Integer amountOfCrypto, Double priceOfCrypto, Integer amountInPesos, User author,
				OperationType operationType) {
		
		super();
		this.crypto = crypto;
		this.amountOfCrypto = amountOfCrypto;
		
		if (Boolean.TRUE.equals(priceIsValid(priceOfCrypto))) {
			this.priceOfCrypto = priceOfCrypto;
		} else {
			throw new P2PException("the published price of the crypto is invalid");
		}

		this.amountInPesos = amountInPesos;
		this.author = author;
		this.operationType = operationType;
		this.offerState = OfferState.OPEN;
		this.creationDate = LocalDateTime.now();
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


	public User getAuthor() {
		return author;
	}
	
	public User getClient() {
		return client;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
		


	public OperationType getOperationType() {
		return operationType;
	}


	public OfferState getOfferState() {
		return offerState;
	}
	
	public LocalDateTime getTradingStartDate() {
		return tradingStartDate;
	}

	public void setTradingStartDate(LocalDateTime tradingStartDate) {
		this.tradingStartDate = tradingStartDate;
	}

	public void offerAccepted(User user, LocalDateTime tradingStartDate){
		try {
			checkOffer();
			this.offerState = OfferState.INPROCESS;
			this.client = user;
			this.tradingStartDate = tradingStartDate;
		} catch (P2PException e) {
			this.offerState = OfferState.CANCELLED;
		}

	}

	private void checkOffer(){
		//BUY
		if (operationType == OperationType.BUY && crypto.getPrice() > priceOfCrypto ) {
			throw new P2PException("the price of the system is higher than the price indicated by the user");
		}
		//sale
		if (operationType == OperationType.SALE && crypto.getPrice() < priceOfCrypto) {
			throw new P2PException("the system price is below the price indicated by the user");
		}
		
	}

	public void finishOffer(LocalDateTime endDate) {
		this.offerState = OfferState.CLOSED;
		
		author.increaseReputation(tradingStartDate, endDate);
		author.addSuccessfullyOperation(this);
		
		client.increaseReputation(tradingStartDate, endDate);
		client.addSuccessfullyOperation(this);
		
	}


	public void operationCancelled(User user) {
		this.offerState = OfferState.OPEN;
		user.decreaseReputation();
	}
	
	
}
