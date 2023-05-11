package ar.edu.unq.grupok.backenddesappapi.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.envers.Audited;

import jakarta.persistence.*;

@Audited
@Entity
public class Crypto {
	
	@Id
	private String symbol;

	private Double price;

	@Basic
	private LocalDateTime dateTimeOfLastPrice;

	@Transient
	private Map<LocalDateTime, Double> priceHistory = new HashMap<>();

	public Crypto() {
		super();
	}
	
	public Crypto(String symbol, Double price) {

		super();
		this.symbol = symbol;
		this.price = price;
		this.dateTimeOfLastPrice = LocalDateTime.now();
		this.priceHistory.put(dateTimeOfLastPrice, price);
	}

	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
		this.dateTimeOfLastPrice = LocalDateTime.now();
		this.priceHistory.put(dateTimeOfLastPrice, price);
	}
	
	public LocalDateTime getDateTimeOfLastPrice() {
		return dateTimeOfLastPrice;
	}

	public void setDateTimeOfLastPrice(LocalDateTime dateTimeOfLastPrice) {
		this.dateTimeOfLastPrice = dateTimeOfLastPrice;
	}

	public void setPrice(Double price, LocalDateTime date) {
		if (!(price.equals(this.price))) {
			this.price = price;
			this.priceHistory.put(date, price);
		}
	}

	public Map<LocalDateTime, Double> pricesOfTheLast24Hours(){
		HashMap<LocalDateTime, Double> last24Hours = new HashMap<>();
		LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);

		for (var entry : this.priceHistory.entrySet()) {
			if(entry.getKey().isAfter(twentyFourHoursAgo)) {
				last24Hours.put(entry.getKey(), entry.getValue());
			}
		}
		return last24Hours;
	}
}