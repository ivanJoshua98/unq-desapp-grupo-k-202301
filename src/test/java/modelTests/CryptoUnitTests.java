package modelTests;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class CryptoUnitTests {
    private Crypto aRandomCrypto = new Crypto("ALICEUSDT", 1.574);

    @Test
    void createNewCryptoAndCheckSymbol() {
        Crypto aCrypto = new Crypto("MATICUSDT", 0.0);
        Assertions.assertEquals(aCrypto.getSymbol(),"MATICUSDT");
    }

    @Test
    void modifyCryptoPrice() {
        aRandomCrypto.setPrice(1.600);
        Assertions.assertEquals(aRandomCrypto.getPrice(),1.600);
    }

    @Test
    void createCryptoAndCheckPricesHistoryIncrementation() {
        Crypto aCrypto = new Crypto("AXSUSDT", 308.5);
        Collection<Double> last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),1);

        aCrypto.setPrice(309.0);
        last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),2);
    }

    @Test
    void createCryptoAndSetSamePriceTwoTimes() {
        Crypto aCrypto = new Crypto("AXSUSDT", 308.5);
        Collection<Double> last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        aCrypto.setPrice(309.0);
        aCrypto.setPrice(309.0);
        last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),3);
    }

}
