package dz3.tt.dao;

import dz3.tt.entities.Article;
import dz3.tt.entities.ArticleType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleRepositoryImpl implements ArticleDao{

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Article> findArticlesByType(ArticleType type) {
        TypedQuery<Article> query = entityManager.createQuery(
                "SELECT a FROM Article a WHERE a.type = :type",
                Article.class
        );
        query.setParameter("type", type);
        return query.getResultList();
    }
}
