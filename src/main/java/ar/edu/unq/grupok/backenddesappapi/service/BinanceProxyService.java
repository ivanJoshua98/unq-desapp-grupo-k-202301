package ar.edu.unq.grupok.backenddesappapi.service;

import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import ar.edu.unq.grupok.backenddesappapi.model.CryptoEnum;

@Component
@Service
public class BinanceProxyService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private CryptoService cryptoService;
	
	@Value("${integration.binance.api.url:NONE}")
	private String binanceApiURL;
	
	
	public Crypto getCryptoValue(String symbol) {
	Crypto entity = restTemplate.getForObject(binanceApiURL + "ticker/price?symbol=" + symbol, Crypto.class);
	return entity;
	}
	
	@Scheduled(cron = "${cron.expression:NONE}")
	public void getAndSaveAllCryptos() {
		for (CryptoEnum crypto : CryptoEnum.values()) {
			Crypto entity = getCryptoValue(crypto.name());
			
			if (entity != null) {
				entity.setDateTimeOfLastPrice(LocalDateTime.now());
				this.cryptoService.saveCrypto(entity);
			}
		}
		logger.info("Save the prices of cryptos from api binance");
	}
	
}
