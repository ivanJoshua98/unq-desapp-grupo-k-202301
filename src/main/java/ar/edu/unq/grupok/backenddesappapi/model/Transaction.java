package ar.edu.unq.grupok.backenddesappapi.model;

public class Transaction {

	private Crypto crypto;
	
	private Integer amountOfCrypto;
	
	private Double priceOfCrypto;
	
	private Integer amountOfOperation;
	
	private String userName;
	
	private Integer numberOfOperations;
	
	private Integer reputation;
	
	private Integer shippingAddress;
	
	private TransactionAction transactionAction;
	
	
	public Transaction(Crypto crypto, Integer amountOfCrypto, Double priceOfCrypto, Integer amountOfOperation,
					   String userName, Integer numberOfOperations, Integer reputation, Integer shippingAddress,
					   TransactionAction transactionAction) {
		
		super();
		this.crypto = crypto;
		this.amountOfCrypto = amountOfCrypto;
		this.priceOfCrypto = priceOfCrypto;
		this.amountOfOperation = amountOfOperation;
		this.userName = userName;
		this.numberOfOperations = numberOfOperations;
		this.reputation = reputation;
		this.shippingAddress = shippingAddress;
		this.transactionAction = transactionAction;
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


	public Integer getAmountOfOperation() {
		return amountOfOperation;
	}


	public String getUserName() {
		return userName;
	}


	public Integer getNumberOfOperations() {
		return numberOfOperations;
	}


	public Integer getReputation() {
		return reputation;
	}


	public Integer getShippingAddress() {
		return shippingAddress;
	}

	public TransactionAction getTransactionAction() {
		return transactionAction;
	}

	
}

