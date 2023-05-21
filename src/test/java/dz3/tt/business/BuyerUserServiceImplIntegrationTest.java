package dz3.tt.business;

import dz3.tt.dto.LoginDto;
import dz3.tt.entities.BuyerUser;
import dz3.tt.entities.Cart;
import dz3.tt.exceptions.RegistrationNotUniqueException;
import dz3.tt.exceptions.UserNotFoundException;
import dz3.tt.dao.BuyerUserRepository;
import dz3.tt.dao.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
@TestPropertySource(properties = "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect")
public class BuyerUserServiceImplIntegrationTest {

    @Autowired
    private BuyerUserServiceImpl buyerUserService;

    @Autowired
    private BaseUserServiceImpl baseUserService;

    @Autowired
    private BuyerUserRepository buyerUserRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testRegisterUser() throws RegistrationNotUniqueException {
        buyerUserService.registerUser("example", "example@example.com", "password", "123");

        BuyerUser savedUser = buyerUserRepository.findBuyerUserByEmail("example@example.com").get();
        assertNotNull(savedUser);
        assertEquals("example", savedUser.getUsername());
        assertEquals("password", savedUser.getPassword());
        assertEquals("123", savedUser.getAddress());
        assertNotEquals(null, savedUser.getCart());
    }

    @Test
    public void testLoginUser() throws UserNotFoundException {
        Cart cart = cartRepository.save(new Cart());
        BuyerUser user = new BuyerUser("pero", "primjer@example.com", "password", "1234", cart);
        buyerUserRepository.save(user);

        LoginDto loginDto = baseUserService.login("pero", "password");

        assertNotNull(loginDto);
        assertEquals(user.getId(), loginDto.getId());
        assertEquals(user.getRole(), loginDto.getRole());
    }

    @Test
    public void testLoginUser_UserNotFound() {
        assertThrows(UserNotFoundException.class, () -> baseUserService.login("nonexistent", "password"));
    }
}
