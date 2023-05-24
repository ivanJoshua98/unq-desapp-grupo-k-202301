package ar.edu.unq.grupok.backenddesappapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupok.backenddesappapi.model.AppException;
import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import ar.edu.unq.grupok.backenddesappapi.persistence.CryptoRepository;

@Service
public class CryptoServiceImpl implements CryptoService{

	@Autowired
	private CryptoRepository cryptoRepository;
	
	
	@Override
	public List<Crypto> getAllCryptos() {
		return this.cryptoRepository.findAll();
	}

	@Override
	public Crypto saveCrypto(Crypto crypto) {
		return this.cryptoRepository.save(crypto);
	}

	@Override
	public Crypto getCryptoBySymbol(String symbol) {
		return this.cryptoRepository.findBySymbol(symbol).orElseThrow(() -> new AppException("Crypto not found by symbol: " + symbol, 
																							 HttpStatus.NOT_FOUND));
	}

	@Override
	public void deleteCryptoBySymbol(String symbol) {
		this.cryptoRepository.deleteById(symbol);
	}

}
