package dz3.tt.dao;

import dz3.tt.entities.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleTypeRepository extends JpaRepository<ArticleType, Long> {
}
