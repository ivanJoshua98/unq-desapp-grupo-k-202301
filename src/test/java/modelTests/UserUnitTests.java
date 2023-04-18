package modelTests;

import org.junit.jupiter.api.Test;
import ar.edu.unq.grupok.backenddesappapi.model.User;
import org.junit.jupiter.api.Assertions;

public class UserUnitTests {

    @Test
    void createUserAndCheckData() {
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



}
