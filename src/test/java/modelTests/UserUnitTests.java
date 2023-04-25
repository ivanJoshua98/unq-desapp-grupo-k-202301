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

@ExtendWith(MockitoExtension.class)
class UserUnitTests {
    private User aUser;

    @Mock
    Offer aOfferMock;
    @Mock
    Offer anotherOfferMock;

    @Mock
    Crypto aCrypto;


    @BeforeEach
    void init(){
        this.aUser = new User("John", "Doe", "johndoe@example.com", "123 Main St", "Password!123",
                "1234567891234567891234", "12345678");
    }

    @Test
    void createUserAndCheckConstructor() {
        User aUser = new User("John", "Doe", "johndoe@example.com", "123 Main St", "Password!123",
                "1234567891234567891234", "12345678");

        Assertions.assertEquals("John", aUser.getName());
        Assertions.assertEquals("Doe", aUser.getLastName());
        Assertions.assertEquals("johndoe@example.com", aUser.getEmail());
        Assertions.assertEquals("123 Main St", aUser.getAddress());
        Assertions.assertEquals("Password!123", aUser.getPassword());
        Assertions.assertEquals("1234567891234567891234", aUser.getCvu());
        Assertions.assertEquals("12345678", aUser.getCriptoWallet());
    }

    @Test
    void testAddOpenOfferOperation() {
        when(aOfferMock.getOfferState()).thenReturn(OfferState.OPEN);
        aUser.addOperation(aOfferMock);
        aUser.addOperation(aOfferMock);
        Assertions.assertEquals(2, aUser.openOffers().size());
    }

    @Test
    void testGetReputationWithoutSuccessfulOperation() throws  UserWithoutOperationsException {
        Assertions.assertThrows(UserWithoutOperationsException.class,() -> aUser.getReputation());
    }

    @Test
    void testIncrease10pointsOfReputationIn30minutesRange() {
        aUser.decreaseReputation();// actual reputation = 0
        aUser.increaseReputation(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));

        aUser.addSuccessfullyOperation(aOfferMock);
        Assertions.assertEquals(10, aUser.getReputation());
    }

    @Test
    void testIncrease5pointsOfReputationInMoreOf30minutesRange() {
        aUser.decreaseReputation();// actual reputation = 0
        aUser.increaseReputation(LocalDateTime.now(), LocalDateTime.now().plusMinutes(31));
        aUser.addSuccessfullyOperation(aOfferMock);

        Assertions.assertEquals(5, aUser.getReputation());
    }

    @Test
    void testDecreaseReputationDiscount20points() {
        aUser.increaseReputation(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
        aUser.increaseReputation(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
        aUser.addSuccessfullyOperation(aOfferMock);
        aUser.addSuccessfullyOperation(anotherOfferMock);
        aUser.decreaseReputation();

        Assertions.assertEquals(5, aUser.getReputation());
    }

    @Test
    void testDecreaseReputationMinimumReturn0points() {
        aUser.addSuccessfullyOperation(aOfferMock);
        aUser.decreaseReputation();
        Assertions.assertEquals(0, aUser.getReputation());
    }
    
}
