package dz3.tt.dao;

import dz3.tt.entities.BuyerUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
@TestPropertySource(properties = "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect")
public class BuyerUserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BuyerUserRepository buyerUserRepository;

    @Test
    public void testFindBuyerUserByEmailAndPassword() {
        BuyerUser buyerUser = new BuyerUser();
        buyerUser.setEmail("test@example.com");
        buyerUser.setPassword("password");
        buyerUser.setUsername("example");
        buyerUser.setAddress("address");
        entityManager.persist(buyerUser);

        Optional<BuyerUser> foundBuyerUser = buyerUserRepository.findBuyerUserByUsernameAndPassword("example", "password");

        assertTrue(foundBuyerUser.isPresent());
        assertEquals("test@example.com", foundBuyerUser.get().getEmail());
        assertEquals("password", foundBuyerUser.get().getPassword());
        assertEquals("example", foundBuyerUser.get().getUsername());
        assertEquals("address", foundBuyerUser.get().getAddress());
    }

    @Test
    public void testFindBuyerUserByEmailAndPassword_NotFound() {
        Optional<BuyerUser> foundBuyerUser = buyerUserRepository.findBuyerUserByUsernameAndPassword("nonexistent", "password");

        assertFalse(foundBuyerUser.isPresent());
    }
}
