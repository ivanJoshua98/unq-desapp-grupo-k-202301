package ar.edu.unq.grupok.backenddesappapi.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import ar.edu.unq.grupok.backenddesappapi.service.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Crypto services", description = "Manage cryptos of the application")
@RestController
@Transactional
@RequestMapping("/p2p")
public class CryptoController {
	
	@Autowired
	CryptoService cryptoService;

	@Operation(summary = "Get a crypto price")
	@GetMapping("/getCryptoValue/{symbol}")
	public ResponseEntity<Crypto> getCryptoValue(
			@Parameter(description = "The crypto symbol that needs to be fetched", required = true) @PathVariable String symbol) {
		Crypto entity = this.cryptoService.getCryptoBySymbol(symbol);
		return ResponseEntity.ok().body(entity);
	}

	@Operation(summary = "Get all crypto prices")
	@GetMapping("/getCrypoValue/all")
	public ResponseEntity<List<Crypto>> getAllCryptoPrices() {
		List<Crypto> list = this.cryptoService.getAllCryptos();		
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Get a crypto prices of last 24 hours")
	@GetMapping("/last24Hours/{symbol}")
	public ResponseEntity<List<Crypto>> getPriceOfLast24Hours(
			@Parameter(description = "The crypto symbol that needs to be fetched", required = true) @PathVariable String symbol) {
		List<Crypto> list = this.cryptoService.getPriceOfLast24Hours(symbol);
		return ResponseEntity.ok().body(list);
	}

}
