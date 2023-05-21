package dz3.tt.dao;

import dz3.tt.entities.BuyerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerUserRepository extends JpaRepository<BuyerUser, Long>, BuyerUserDao {

}
