package dz3.tt.dao;

import dz3.tt.entities.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {
    Optional<BaseUser> findBaseUserByUsernameAndPassword(String username, String password);
}
