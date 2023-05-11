package ar.edu.unq.grupok.backenddesappapi.service;

import java.util.List;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;

public interface CryptoService {

    List<Crypto> getAllCryptos();
    
    Crypto saveCrypto(Crypto crypto);
    
    Crypto getCryptoBySymbol(String symbol);
    
    void deleteCryptoBySymbol(String symbol);
	
}
