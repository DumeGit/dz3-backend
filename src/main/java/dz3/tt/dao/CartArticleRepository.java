package dz3.tt.dao;

import dz3.tt.entities.CartArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartArticleRepository extends JpaRepository<CartArticle, Long> {
}
