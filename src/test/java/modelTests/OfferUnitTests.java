package modelTests;

import ar.edu.unq.grupok.backenddesappapi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mock;

import java.time.LocalDateTime;

public class OfferUnitTests {

    private User aUser;
    private Crypto aCrypto;
    private Offer aBuyOffer;

    @Mock
    private User aUserMock;

    @BeforeEach
    public void init() throws InvalidPublishedPriceException {
        this.aUser = new User("John", "Doe", "johndoe@example.com", "123 Main St", "Password!123",
                "1234567891234567891234", "12345678");

        this.aCrypto = new Crypto("ALICEUSDT", 1.000);

        this.aBuyOffer = new Offer(aCrypto, 50, 1.000, 400, aUser,
                OperationType.PURCHASE);
    }

    @Test
    public void testConstructorAndGeters() throws InvalidPublishedPriceException {
        User authorUser = new User("John", "Doe", "johndoe@example.com", "123 Main St", "Password!123",
                "1234567891234567891234", "12345678");
        Offer offer = new Offer(aCrypto, 50, 1.000, 400, authorUser,
                OperationType.PURCHASE);

        Assertions.assertEquals(offer.getCrypto().getSymbol(), aCrypto.getSymbol());
        Assertions.assertEquals(offer.getOfferState(), OfferState.OPEN);
        Assertions.assertEquals(offer.getPriceOfCrypto(), 1.000);
        Assertions.assertEquals(offer.getAmountInPesos(), 400);
        Assertions.assertEquals(offer.getAmountOfCrypto(), 50);
        Assertions.assertEquals(offer.getAuthor().getEmail(), "johndoe@example.com");
        Assertions.assertNotNull(offer.getCreationDate());
        Assertions.assertEquals(offer.getOperationType(), OperationType.PURCHASE);
        Assertions.assertNull(offer.getClient());
    }

    @Test
    public void testOfferConstructorInvalidPrice() throws InvalidPublishedPriceException {
        Assertions.assertThrows(InvalidPublishedPriceException.class,() -> new Offer(aCrypto, 50, 1.051, 400, aUser,
                OperationType.PURCHASE));
        Assertions.assertThrows(InvalidPublishedPriceException.class,() -> new Offer(aCrypto, 50, 0.949, 400, aUser,
                OperationType.PURCHASE));
    }

    @Test
    public void testOfferCryptoPriceIsValid() {
        //offer crypto price: 1.000
        Assertions.assertTrue(aBuyOffer.priceIsValid(1.049));
        Assertions.assertTrue(aBuyOffer.priceIsValid(1.050));
        Assertions.assertFalse(aBuyOffer.priceIsValid(1.051));

        Assertions.assertTrue(aBuyOffer.priceIsValid(0.951));
        Assertions.assertTrue(aBuyOffer.priceIsValid(0.950));
        Assertions.assertFalse(aBuyOffer.priceIsValid(0.949));
    }

    @Test
    public void testOfferAccepted() throws InvalidPublishedPriceException {
        LocalDateTime tradingStartDate = LocalDateTime.now();
        aBuyOffer.offerAccepted(aUserMock, tradingStartDate);
        Assertions.assertEquals(OfferState.INPROCESS, aBuyOffer.getOfferState());
    }

    @Test
    public void testOfferCancelledBySystem() {
        LocalDateTime tradingStartDate = LocalDateTime.now();
        Assertions.assertEquals(aBuyOffer.getOperationType(), OperationType.PURCHASE);
        aCrypto.setPrice(1.500, tradingStartDate);
        aBuyOffer.offerAccepted(aUserMock, tradingStartDate);
        Assertions.assertEquals(OfferState.CANCELLED, aBuyOffer.getOfferState());
    }

    @Test
    public void testOperationCancelledRetursToOpen() {
        aBuyOffer.operationCancelled(aUser);
        Assertions.assertEquals(OfferState.OPEN, aBuyOffer.getOfferState());
    }

    @Test
    public void testFinishOffer() {
        aBuyOffer.offerAccepted(aUser, LocalDateTime.now());
        aBuyOffer.finishOffer(LocalDateTime.now());
        Assertions.assertEquals(OfferState.CLOSE, aBuyOffer.getOfferState());
    }

}
