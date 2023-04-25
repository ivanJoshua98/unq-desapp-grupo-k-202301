package modelTests;

import ar.edu.unq.grupok.backenddesappapi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

public class OfferUnitTests {

    private User aUser;

    private Crypto aCrypto;
    private Offer aOffer;

    @BeforeEach
    public void init() throws InvalidPublishedPriceException {
        this.aUser = new User("John", "Doe", "johndoe@example.com", "123 Main St", "Password!123",
                "1234567891234567891234", "12345678");

        this.aCrypto = new Crypto("ALICEUSDT", 1.000);

        this.aOffer = new Offer(aCrypto, 50, 1.000, 400, aUser,
                OperationType.PURCHASE);
    }

    @Test
    public void testOfferCryptoPriceIsValidIn5porcentOfPriceRange() {
        //offer crypto price: 1.000
        Assertions.assertTrue(aOffer.priceIsValid(1.049));
        Assertions.assertTrue(aOffer.priceIsValid(1.050));
        Assertions.assertFalse(aOffer.priceIsValid(1.051));

        Assertions.assertTrue(aOffer.priceIsValid(0.951));
        Assertions.assertTrue(aOffer.priceIsValid(0.950));
        Assertions.assertFalse(aOffer.priceIsValid(0.949));
    }

    @Test
    public void testConstructorFailedOfferPriceOfCryptoOutOf5porcentRange() throws InvalidPublishedPriceException {
        Assertions.assertThrows(InvalidPublishedPriceException.class,() -> new Offer(aCrypto, 50, 1.051, 400, aUser,
                OperationType.PURCHASE));
        Assertions.assertThrows(InvalidPublishedPriceException.class,() -> new Offer(aCrypto, 50, 0.949, 400, aUser,
                OperationType.PURCHASE));
    }
}
