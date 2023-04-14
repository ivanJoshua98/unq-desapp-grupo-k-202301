package modelTests;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class CryptoUnitTests {
    private Crypto aRandomCrypto = anyCrypto();

    private Crypto anyCrypto() {
        Crypto aCrypto = new Crypto("ALICEUSDT", 1.574);
        return aCrypto;
    }

//	@BeforeAll
//	public void contextLoads() {
//	}

    @Test
    void createNewCryptoAndCheckSymbol() {
        String symbol = "MATICUSDT";
        Crypto aCrypto = new Crypto(symbol, 0.0);
        Assertions.assertEquals(aCrypto.getSymbol(),symbol);
    }

    @Test
    void modifyCryptoPrice() {
        Double newCryptoPrice = 1.600;
        aRandomCrypto.setPrice(newCryptoPrice);
        Assertions.assertEquals(aRandomCrypto.getPrice(),newCryptoPrice);
    }

    @Test
    void createCryptoAndCheckPricesHistoryIncrementation() {
        Double cryptoFirstPrice = 308.5;
        Crypto aCrypto = new Crypto("AXSUSDT", cryptoFirstPrice);
        Collection<Double> last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),1);

        aCrypto.setPrice(309.0);
        last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),2);
    }

    @Test
    void createCryptoAndSetSamePriceTwoTimes() {
        Double cryptoFirstPrice = 308.5;
        Crypto aCrypto = new Crypto("AXSUSDT", cryptoFirstPrice);
        Collection<Double> last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        aCrypto.setPrice(309.0);
        aCrypto.setPrice(309.0);
        last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),3);
    }


}