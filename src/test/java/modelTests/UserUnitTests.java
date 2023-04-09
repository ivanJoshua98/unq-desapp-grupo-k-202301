package modelTests;

import ar.edu.unq.grupok.backenddesappapi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserUnitTests {

    @Test
    public void testConstructor() {
        String name = "Carlos";
        String lastName = "Perez";
        String email = "CarlosPerez@example.com";
        String address = "calle 123";
        String password = "mypassword";
        Integer cvu = 12345;
        Integer criptoWallet = 67890;

        User user = new User(name, lastName, email, address, password, cvu, criptoWallet);

        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(lastName, user.getLastName());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(address, user.getAddress());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(cvu, user.getCvu());
        Assertions.assertEquals(criptoWallet, user.getCriptoWallet());
        Assertions.assertEquals(0, user.getReputation());
        Assertions.assertNotNull(user.openOffers());
    }

    @Test
    public void testConstructorInvalidName() {
        String name = "C";
        String lastName = "Perez";
        String email = "carlosperez@example.com";
        String address = "123 Main St";
        String password = "mypassword";
        Integer cvu = 12345;
        Integer criptoWallet = 67890;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(name, lastName, email, address, password, cvu, criptoWallet));
    }

    @Test
    public void testConstructorInvalidLastName() {
        String name = "Carlos";
        String lastName = "P";
        String email = "carlosperez@example.com";
        String address = "123 Main St";
        String password = "mypassword";
        Integer cvu = 12345;
        Integer criptoWallet = 67890;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(name, lastName, email, address, password, cvu, criptoWallet));
    }

}
