package modelTests;

import ar.edu.unq.grupok.backenddesappapi.model.Crypto;
import ar.edu.unq.grupok.backenddesappapi.model.Offer;
import ar.edu.unq.grupok.backenddesappapi.model.OfferState;
import ar.edu.unq.grupok.backenddesappapi.model.InvalidPublishedPriceException;
import ar.edu.unq.grupok.backenddesappapi.model.OperationType;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//public class OfferTest {
//}

class OfferTest {

    private Crypto crypto;
    private Integer amountOfCrypto;
    private Double priceOfCrypto;
    private Integer amountInPesos;
    private String userName;
    private OperationType operationType;

    @BeforeEach
    void setUp() throws Exception {
        crypto = new Crypto("BTC", 50000.00);
        amountOfCrypto = 2;
        priceOfCrypto = 51000.00;
        amountInPesos = 102000;
        userName = "Carlos";
        operationType = OperationType.PURCHASE;
    }

    @Test
    void testOfferConstructor() throws InvalidPublishedPriceException {
        Offer offer = new Offer(crypto, amountOfCrypto, priceOfCrypto, amountInPesos, userName, operationType);
        Assertions.assertEquals(crypto, offer.getCrypto());
        Assertions.assertEquals(amountOfCrypto, offer.getAmountOfCrypto());
        Assertions.assertEquals(priceOfCrypto, offer.getPriceOfCrypto());
        Assertions.assertEquals(amountInPesos, offer.getAmountInPesos());
        Assertions.assertEquals(userName, offer.getUserName());
        Assertions.assertEquals(operationType, offer.getOperationType());
        Assertions.assertEquals(OfferState.OPEN, offer.getOfferState());
    }

    @Test
    void testPriceIsValid() throws InvalidPublishedPriceException {
        Offer offer = new Offer(crypto, amountOfCrypto, priceOfCrypto, amountInPesos, userName, operationType);
        Double validPrice = 52500.00;
        Double invalidPrice = 55000.00;
        Assertions.assertTrue(offer.priceIsValid(validPrice));
        Assertions.assertFalse(offer.priceIsValid(invalidPrice));
    }

    @Test
    void testProcessTransaction() throws InvalidPublishedPriceException {
        Offer offer = new Offer(crypto, amountOfCrypto, priceOfCrypto, amountInPesos, userName, operationType);
        offer.processTransaction();
        Assertions.assertEquals(OfferState.INPROCESS, offer.getOfferState());
    }

}