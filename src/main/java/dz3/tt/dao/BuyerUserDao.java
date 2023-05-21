package dz3.tt.dao;

import dz3.tt.entities.BuyerUser;

import java.util.Optional;

public interface BuyerUserDao {
    Optional<BuyerUser> findBuyerUserByUsernameAndPassword(String username, String password);
    Optional<BuyerUser> findBuyerUserByEmail(String email);
}
