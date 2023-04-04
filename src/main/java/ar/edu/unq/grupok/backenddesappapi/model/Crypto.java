package ar.edu.unq.grupok.backenddesappapi.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Crypto {

	private String symbol;

	private Double price;
	
	private LocalDateTime dateTimeOfLastPrice;
	
	private Map<LocalDateTime, Double> priceHistory;
	
	public Crypto(String symbol, Double price) {
		
		super();
		this.symbol = symbol;
		this.price = price;
		this.dateTimeOfLastPrice = LocalDateTime.now();
		this.priceHistory = new HashMap<LocalDateTime, Double>();
		this.priceHistory.put(dateTimeOfLastPrice, price);
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		if (price != this.price) {
			this.price = price;
			this.priceHistory.put(LocalDateTime.now(), price);
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

