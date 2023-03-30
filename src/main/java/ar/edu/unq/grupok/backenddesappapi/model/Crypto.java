package ar.edu.unq.grupok.backenddesappapi.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Crypto {

	private Double price;
	
	private String symbol;
	
	private LocalDateTime dateTimeOfLastPrice;
	
	private Map<LocalDateTime, Double> priceHistory;
	
	public Crypto(Double price, String symbol) {
		
		super();
		this.price = price;
		this.symbol = symbol;
		this.dateTimeOfLastPrice = LocalDateTime.now();
		this.priceHistory = new HashMap<LocalDateTime, Double>();
		this.priceHistory.put(dateTimeOfLastPrice, price);
	}

	public Double getPrice() {
		return price;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public void setPrice(Double price) {
		this.price = price;
		this.priceHistory.put(LocalDateTime.now(), price);
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

