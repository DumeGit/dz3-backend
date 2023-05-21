package dz3.tt.dao;

import dz3.tt.entities.OrderArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderArticleRepository extends JpaRepository<OrderArticle, Long> {
}
