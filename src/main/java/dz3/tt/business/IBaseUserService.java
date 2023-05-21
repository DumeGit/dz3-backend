package dz3.tt.business;

import dz3.tt.dto.LoginDto;
import dz3.tt.exceptions.UserNotFoundException;

public interface IBaseUserService {

    LoginDto login(String username, String password) throws UserNotFoundException;
}
