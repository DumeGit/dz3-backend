package dz3.tt.business;

import dz3.tt.dto.EntityDto;
import dz3.tt.dto.LoginDto;
import dz3.tt.entities.Cart;
import dz3.tt.exceptions.RegistrationNotUniqueException;
import dz3.tt.exceptions.UserNotFoundException;


public interface IBuyerUserService {
    void registerUser(String username, String email, String password, String address) throws UserNotFoundException, RegistrationNotUniqueException;
}
