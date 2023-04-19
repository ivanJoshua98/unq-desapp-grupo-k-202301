package modelTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import ar.edu.unq.grupok.backenddesappapi.model.User;

public class UserUnitTests {
    private User aUser = new User("John", "Doe", "johndoe@example.com", "123 Main St", "Password!123",
            "1234567891234567891234", "12345678");

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

//    @Test
//    public void testAddOffer() {
//
//    }
//
//    @Test
//    public void testCancelLastOffer() {
//
//    }
//
//    @Test // (expected = UserWithoutOperationsException.class)
//    public void testCancelLastOfferWithNoOffers() {
//
//    }
//
//    @Test
//    public void testUpdateReputation() {
//
//    }
//
//    @Test
//    public void testUpdateReputationWithNegativeValue() {
//
//    }
//
//    @Test
//    public void testAcceptOffer() {
//
//    }
//
//    @Test
//    public void testCancelOffer() {
//
//    }
}
