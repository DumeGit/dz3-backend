package dz3.tt.dao;

import dz3.tt.entities.Article;
import dz3.tt.entities.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleDao {
}
