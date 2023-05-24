package modelTests;

import ar.edu.unq.grupok.backenddesappapi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mock;

import java.time.LocalDateTime;

class OfferUnitTests {

    private User aUser;
    private Crypto aCrypto;
    private Offer aBuyOffer;

    @Mock
    private User aUserMock;

    @BeforeEach
    void init() throws P2PException {
        this.aUser = new User("John", "Doe", "johndoe@example.com", "123 Main St", "Password!123",
                "1234567891234567891234", "12345678");

        this.aCrypto = new Crypto("ALICEUSDT", 1.000);

        this.aBuyOffer = new Offer(aCrypto, 50, 1.000, 400, aUser,
                OperationType.BUY);
    }

    @Test
    void testConstructorAndGeters() throws P2PException {
        User authorUser = new User("John", "Doe", "johndoe@example.com", "123 Main St", "Password!123",
                "1234567891234567891234", "12345678");
        Offer offer = new Offer(aCrypto, 50, 1.000, 400, authorUser,
                OperationType.BUY);

        Assertions.assertEquals(offer.getCrypto().getSymbol(), aCrypto.getSymbol());
        Assertions.assertEquals(OfferState.OPEN, offer.getOfferState());
        Assertions.assertEquals(1.000, offer.getPriceOfCrypto());
        Assertions.assertEquals(400, offer.getAmountInPesos());
        Assertions.assertEquals(50, offer.getAmountOfCrypto());
        Assertions.assertEquals("johndoe@example.com", offer.getAuthor().getEmail());
        Assertions.assertNotNull(offer.getCreationDate());
        Assertions.assertEquals(OperationType.BUY, offer.getOperationType());
        Assertions.assertNull(offer.getClient());
    }

    @Test
    void testOfferConstructorInvalidPrice() throws P2PException {
        Assertions.assertThrows(P2PException.class,() -> new Offer(aCrypto, 50, 1.051, 400, aUser,
                OperationType.BUY));
        Assertions.assertThrows(P2PException.class,() -> new Offer(aCrypto, 50, 0.949, 400, aUser,
                OperationType.BUY));
    }

    @Test
    void testOfferCryptoPriceIsValid() {
        //offer crypto price: 1.000
        Assertions.assertTrue(aBuyOffer.priceIsValid(1.049));
        Assertions.assertTrue(aBuyOffer.priceIsValid(1.050));
        Assertions.assertFalse(aBuyOffer.priceIsValid(1.051));

        Assertions.assertTrue(aBuyOffer.priceIsValid(0.951));
        Assertions.assertTrue(aBuyOffer.priceIsValid(0.950));
        Assertions.assertFalse(aBuyOffer.priceIsValid(0.949));
    }

    @Test
    void testOfferAccepted() throws P2PException {
        LocalDateTime tradingStartDate = LocalDateTime.now();
        aBuyOffer.offerAccepted(aUserMock, tradingStartDate);
        Assertions.assertEquals(OfferState.INPROCESS, aBuyOffer.getOfferState());
    }

    @Test
    void testOfferCancelledBySystem(){
        LocalDateTime tradingStartDate = LocalDateTime.now();
        Assertions.assertEquals(OperationType.BUY, aBuyOffer.getOperationType());
        aCrypto.setPrice(1.500, tradingStartDate);
        aBuyOffer.offerAccepted(aUserMock, tradingStartDate);
        Assertions.assertEquals(OfferState.CANCELLED, aBuyOffer.getOfferState());
    }

    @Test
    void testOperationCancelledRetursToOpen() {
        aBuyOffer.operationCancelled(aUser);
        Assertions.assertEquals(OfferState.OPEN, aBuyOffer.getOfferState());
    }

    @Test
    void testFinishOffer() {
        aBuyOffer.offerAccepted(aUser, LocalDateTime.now());
        aBuyOffer.finishOffer(LocalDateTime.now());
        Assertions.assertEquals(OfferState.CLOSED, aBuyOffer.getOfferState());
    }

}
