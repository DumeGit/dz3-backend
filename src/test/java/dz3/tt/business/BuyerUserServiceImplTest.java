package dz3.tt.business;

import dz3.tt.dao.BaseUserRepository;
import dz3.tt.dto.LoginDto;
import dz3.tt.entities.BuyerUser;
import dz3.tt.entities.Cart;
import dz3.tt.exceptions.RegistrationNotUniqueException;
import dz3.tt.exceptions.UserNotFoundException;
import dz3.tt.dao.BuyerUserRepository;
import dz3.tt.util.AppRole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BuyerUserServiceImplTest {

    @Mock
    private BuyerUserRepository buyerUserRepository;
    @Mock
    private BaseUserRepository baseUserRepository;

    @Mock
    private CartServiceImpl cartService;

    @InjectMocks
    private BuyerUserServiceImpl buyerUserService;

    @InjectMocks
    private BaseUserServiceImpl baseUserService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() throws RegistrationNotUniqueException {
        Cart cart = new Cart();
        when(cartService.create()).thenReturn(cart);

        String username = "testUser";
        String email = "test@example.com";
        String password = "password";
        String address = "123 Street, City";

        buyerUserService.registerUser(username, email, password, address);

        verify(cartService, times(1)).create();
        verify(buyerUserRepository, times(1)).save(any(BuyerUser.class));
    }

    @Test(expected = Exception.class)
    public void testRegisterUserWithNonUniqueRegistration() throws RegistrationNotUniqueException {
        doThrow(RegistrationNotUniqueException.class).when(buyerUserRepository).save(any(BuyerUser.class));

        String username = "testUser";
        String email = "test@example.com";
        String password = "password";
        String address = "123 Street, City";

        buyerUserService.registerUser(username, email, password, address);
    }

    @Test
    public void testLoginUser() throws UserNotFoundException {
        Long userId = 1L;
        AppRole userRole = AppRole.BUYER;
        BuyerUser user = new BuyerUser();
        user.setId(userId);
        user.setRole(userRole);
        when(baseUserRepository.findBaseUserByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(user));

        String username = "example";
        String password = "password";

        LoginDto result = baseUserService.login(username, password);

        assertEquals(userId, result.getId());
        assertEquals(userRole, result.getRole());
    }

    @Test(expected = UserNotFoundException.class)
    public void testLoginUserWithInvalidCredentials() throws UserNotFoundException {
        when(baseUserRepository.findBaseUserByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        String username = "example";
        String password = "password";

        baseUserService.login(username, password);
    }
}
