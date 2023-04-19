package modelTests;
import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
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
        aRandomCrypto.setPrice(newCryptoPrice, LocalDateTime.now());
        Assertions.assertEquals(aRandomCrypto.getPrice(),newCryptoPrice);
    }
    @Test
    void createCryptoAndCheckPricesHistoryIncrementation() throws InterruptedException {
        Double cryptoFirstPrice = 308.5;
        Crypto aCrypto = new Crypto("AXSUSDT", cryptoFirstPrice);
        Collection<Double> last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),1);
        TimeUnit.SECONDS.sleep(1);
        aCrypto.setPrice(309.0, LocalDateTime.now());
        last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),2);
    }
    @Test
    void createCryptoAndSetSamePriceTwoTimes() throws InterruptedException {
        Double cryptoFirstPrice = 308.5;
        Crypto aCrypto = new Crypto("AXSUSDT", cryptoFirstPrice);
        Collection<Double> last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        TimeUnit.SECONDS.sleep(1);
        aCrypto.setPrice(309.0, LocalDateTime.now());
        TimeUnit.SECONDS.sleep(1);
        aCrypto.setPrice(309.0, LocalDateTime.now());
        last24HoursPricesValues = aCrypto.pricesOfTheLast24Hours().values();
        Assertions.assertEquals(last24HoursPricesValues.size(),2);
    }
}