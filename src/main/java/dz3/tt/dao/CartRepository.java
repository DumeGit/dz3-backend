package dz3.tt.dao;

import dz3.tt.entities.Cart;
import dz3.tt.util.CartArticleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartArticleId> {
}
