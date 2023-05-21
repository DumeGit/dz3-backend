package dz3.tt.business;


import dz3.tt.dao.BaseUserRepository;
import dz3.tt.dto.LoginDto;
import dz3.tt.entities.BaseUser;
import dz3.tt.entities.BuyerUser;
import dz3.tt.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseUserServiceImpl implements IBaseUserService{
    private final BaseUserRepository  baseUserRepository;
    @Override
    public LoginDto login(String username, String password) throws UserNotFoundException {
        BaseUser user = baseUserRepository.findBaseUserByUsernameAndPassword(username, password).orElseThrow(UserNotFoundException::new);
        return new LoginDto(user.getId(), user.getRole());
    }
}
