package dz3.tt.dao;

import dz3.tt.entities.Article;
import dz3.tt.entities.ArticleType;

import java.util.List;

public interface ArticleDao {
    List<Article> findArticlesByType(ArticleType type);
}
