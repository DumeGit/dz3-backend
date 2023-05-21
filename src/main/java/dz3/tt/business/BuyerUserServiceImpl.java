package dz3.tt.business;

import dz3.tt.dto.LoginDto;
import dz3.tt.entities.BuyerUser;
import dz3.tt.entities.Cart;
import dz3.tt.exceptions.RegistrationNotUniqueException;
import dz3.tt.exceptions.UserNotFoundException;
import dz3.tt.dao.BuyerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyerUserServiceImpl implements IBuyerUserService {
    private final BuyerUserRepository buyerUserRepository;
    private final CartServiceImpl cartService;
    @Override
    public void registerUser(String username, String email, String password, String address) throws RegistrationNotUniqueException {
        Cart cart = cartService.create();
        try {
            buyerUserRepository.save(new BuyerUser(username, email, password, address, cart));
        } catch (Exception e) {
            throw new RegistrationNotUniqueException();
        }

    }
}
