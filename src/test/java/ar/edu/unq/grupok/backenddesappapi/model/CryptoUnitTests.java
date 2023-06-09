package ar.edu.unq.grupok.backenddesappapi.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Collection;

class CryptoUnitTests {
    private Crypto aRandomCrypto = anyCrypto();
    private Crypto anyCrypto() {
        Crypto aCrypto = new Crypto("ALICEUSDT", 1.574);
        return aCrypto;
    }

    @Test
    void createNewCryptoAndCheckSymbol() {
        String symbol = "MATICUSDT";
        Crypto aCrypto = new Crypto(symbol, 0.0);
        Assertions.assertEquals(aCrypto.getSymbol(),symbol);
    }
    @Test
    void modifyCryptoPrice() {
        Double newCryptoPrice = 1.600;
        aRandomCrypto.setPrice(newCryptoPrice, LocalDateTime.now());
        Assertions.assertEquals(aRandomCrypto.getPrice(),newCryptoPrice);
    }
    @Test
    void createCryptoAndCheckPricesHistoryIncrementation() throws InterruptedException {
        Double cryptoFirstPrice = 308.5;
        Crypto aCrypto = new Crypto("AXSUSDT", cryptoFirstPrice);
        Collection<Double> last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(1, last24HoursPricesValues.size());
        aCrypto.setPrice(309.0, LocalDateTime.now().plusSeconds(1));
        last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(2,last24HoursPricesValues.size());
    }
    @Test
    void createCryptoAndSetSamePriceTwoTimes() throws InterruptedException {
        Double cryptoFirstPrice = 308.5;
        Crypto aCrypto = new Crypto("AXSUSDT", cryptoFirstPrice);
        Collection<Double> last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        aCrypto.setPrice(309.0, LocalDateTime.now());
        aCrypto.setPrice(309.0, LocalDateTime.now().plusSeconds(1));
        last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(2, last24HoursPricesValues.size());
    }
}