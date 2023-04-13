package ar.edu.unq.grupok.backenddesappapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name= "Users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	private String name;
	
	private String lastName;
	
	private String email;
	
	private String address;
	
	private String password;
	
	private String cvu;
	
	private String criptoWallet;

	@ManyToMany
	@JoinTable(name = "user's_offers",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private List<Offer> offers;
	
	private Integer points;
	
	@ManyToMany
	@JoinTable(name = "user's_succesfully_operations",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private List<Offer> successfulOperations;
	
	public User() {
		super();
	}
	
	public User(String name, String lastName, String email, String address, String password,
				String cvu, String criptoWallet) {

		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.password = password;
		this.cvu = cvu;
		this.criptoWallet = criptoWallet;
		this.points = 10;
		this.offers = new ArrayList<>();
		this.successfulOperations = new ArrayList<>();
	};
	
	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public String getCvu() {
		return cvu;
	}

	public String getCriptoWallet() {
		return criptoWallet;
	}

	/*public Integer getReputation() {
		Integer numberOfOperations = this.successfulOperations.size(); 
		if (numberOfOperations == 0) {
			throw new UserWithoutOperationsException("User without operations");
		}
		return points / numberOfOperations;
	}*/

	public void addOperation(Offer offer) {
		this.offers.add(offer);
	}
	
	public List<Offer> openOffers() {
		List<Offer> opens = this.offers;
		opens.removeIf(o -> o.getOfferState() != OfferState.OPEN);
		return opens;
	}
	
	
	public void reportTransaction(Offer offer, LocalDateTime tradingStartDate) {
		offer.offerAccepted(this, tradingStartDate);
	}
	
	public void confirmTransferReceived(Offer offer, LocalDateTime endDate) {
		offer.finishOffer(endDate);
	}

	public void increaseReputation(LocalDateTime tradingStartDate, LocalDateTime endDate) {
		LocalDateTime thirtyMinutesAgo = endDate.minusMinutes(30);
		if (thirtyMinutesAgo.isBefore(tradingStartDate)) {
			points += 10;
		} else {
			points += 5;
		}		
	}
	
	public void decreaseReputation() {
		if (this.points - 20 < 0) {
			points = 0;
		} else {
			this.points -= 20;
		}
	
	}

	public void addSuccessfullyOperation(Offer offer) {
		this.successfulOperations.add(offer);
	}
	
	public void cancelOperation(Offer offer) {
		offer.operationCancelled(this);
	}
	
	
}
