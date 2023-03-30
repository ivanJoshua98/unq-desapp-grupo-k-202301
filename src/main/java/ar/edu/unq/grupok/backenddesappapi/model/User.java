package ar.edu.unq.grupok.backenddesappapi.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;
	
	private String lastName;
	
	private String email;
	
	private String address;
	
	private String password;
	
	private Integer cvu;
	
	private Integer criptoWallet;
	
	private Integer reputation;
	
	private List<Offer> offers;
	
	public User(String name, String lastName, String email, String address, String password,
				Integer cvu, Integer criptoWallet, Integer reputation) {

		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.password = password;
		this.cvu = cvu;
		this.criptoWallet = criptoWallet;
		this.reputation = reputation;
		this.offers = new ArrayList<>();
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

	public Integer getCvu() {
		return cvu;
	}

	public Integer getCriptoWallet() {
		return criptoWallet;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void addOperation(Offer offer) {
		this.offers.add(offer);
	}
	
	public List<Offer> openOffers() {
		List<Offer> opens = this.offers;
		opens.removeIf(o -> o.getOfferState() != OfferState.OPEN);
		return opens;
	}
}
