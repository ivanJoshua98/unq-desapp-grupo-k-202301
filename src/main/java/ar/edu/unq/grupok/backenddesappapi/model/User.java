package ar.edu.unq.grupok.backenddesappapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user's_offers",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private List<Offer> offers;
	
	private Integer points;
	
	@ManyToMany(fetch = FetchType.EAGER)
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
	}
	
	public UUID getId() {
		return id;
	}
	
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

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public List<Offer> getSuccessfulOperations() {
		return successfulOperations;
	}

	public void setSuccessfulOperations(List<Offer> successfulOperations) {
		this.successfulOperations = successfulOperations;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCvu(String cvu) {
		this.cvu = cvu;
	}

	public void setCriptoWallet(String criptoWallet) {
		this.criptoWallet = criptoWallet;
	}
	
	@JsonIgnore	
	public Integer getReputation() {
		Integer numberOfOperations = this.successfulOperations.size(); 
		if (numberOfOperations == 0) {
			throw new UserWithoutOperationsException("User without operations");
		}
		return points / numberOfOperations;
	}

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

	public List<Offer> closedOffersBetweenDates(LocalDateTime startDate, LocalDateTime endDate){
		return this.successfulOperations.stream()
				.filter(offer -> offer.getCreationDate().isAfter(startDate)
								&& offer.getClosingDate().isBefore(endDate)).toList();
	}

	public List<CryptoVolume> cryptoVolumes(LocalDateTime startDate, LocalDateTime endDate) {
		List<Offer> closedOffers = this.closedOffersBetweenDates(startDate, endDate);
		// Cryptos symbols sin repetidos
		List<String> cryptos = new ArrayList<String>();
		closedOffers.forEach(offer -> {
			String cryptoSymbol = offer.getCrypto().getSymbol();
			if (!cryptos.contains(cryptoSymbol)) {
				cryptos.add(cryptoSymbol);
			}
		});

		// List of crypto volumes
		List<CryptoVolume> cryptoVolumesList = new ArrayList<CryptoVolume>();
		cryptos.forEach(cryptoSymbol -> {
			CryptoVolume cryptoVolume = new CryptoVolume();
			cryptoVolume.setCryptoSymbol(cryptoSymbol);
			closedOffers.forEach(offer -> {
				if (offer.getCrypto().getSymbol() == cryptoSymbol){
					cryptoVolume.incrementTotalAmountUSD(offer.getPriceOfCrypto());
					cryptoVolume.incrementTotalAmountPesos(offer.getAmountInPesos());
					cryptoVolume.incrementTotalAmountOfCrypto(offer.getAmountOfCrypto());

					cryptoVolume.setPriceOfCrypto(offer.getPriceOfCrypto());
					cryptoVolume.setPriceOfCryptoInPesos(offer.getAmountInPesos());
				}
			});
			cryptoVolumesList.add(cryptoVolume);
		});

		return cryptoVolumesList;
	}
	
}
