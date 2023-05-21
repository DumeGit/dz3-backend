package dz3.tt.api;

import dz3.tt.api.AuthController;
import dz3.tt.business.BuyerUserServiceImpl;
import dz3.tt.dto.UserRegisterDto;
import dz3.tt.exceptions.RegistrationNotUniqueException;
import jakarta.transaction.RollbackException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private BuyerUserServiceImpl userService;

    @InjectMocks
    private AuthController authController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser_Success() throws RegistrationNotUniqueException {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("john");
        dto.setEmail("john@example.com");
        dto.setPassword("password");
        dto.setAddress("123 Main St");

        doNothing().when(userService).registerUser(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getAddress());

        ResponseEntity<?> response = authController.registerUser(dto, 123);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = RegistrationNotUniqueException.class)
    public void testRegisterUser_RegistrationNotUniqueException() throws RegistrationNotUniqueException {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("john");
        dto.setEmail("john@example.com");
        dto.setPassword("password");
        dto.setAddress("123 Main St");

        doThrow(RegistrationNotUniqueException.class).when(userService).registerUser(anyString(), anyString(), anyString(), anyString());

        authController.registerUser(dto, 123);
    }
}
