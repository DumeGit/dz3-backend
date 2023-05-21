package dz3.tt.dao;

import dz3.tt.entities.Article;
import dz3.tt.entities.ArticleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
@TestPropertySource(properties = "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect")
public class ArticleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void testDeleteById() {
        Article article = new Article();
        ArticleType articleType = new ArticleType("a");
        entityManager.persist(articleType);
        article.setName("Sample Article");
        article.setPrice(10.0);
        article.setStock(5);
        article.setImageUrl("a");
        article.setType(articleType);
        article.setAvailable(true);
        Article savedArticle = entityManager.persist(article);

        articleRepository.deleteById(savedArticle.getId());

        Article deletedArticle = entityManager.find(Article.class, savedArticle.getId());

        assertNull(deletedArticle);
    }

    @Test
    public void testFindArticlesByType() {
        ArticleType articleType = new ArticleType();
        articleType.setName("Sample Type");
        ArticleType savedType = entityManager.persist(articleType);

        Article article1 = new Article();
        article1.setName("Article 1");
        article1.setPrice(10.0);
        article1.setStock(5);
        article1.setType(savedType);
        entityManager.persist(article1);

        Article article2 = new Article();
        article2.setName("Article 2");
        article2.setPrice(15.0);
        article2.setStock(3);
        article2.setType(savedType);
        entityManager.persist(article2);

        List<Article> articles = articleRepository.findArticlesByType(savedType);

        assertEquals(2, articles.size());

        for (Article article : articles) {
            assertEquals(savedType, article.getType());
        }
    }
}
