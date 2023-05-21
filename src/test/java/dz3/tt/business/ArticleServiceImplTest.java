package dz3.tt.business;

import dz3.tt.entities.Article;
import dz3.tt.entities.ArticleType;
import dz3.tt.exceptions.ArticleTypeNotFoundException;
import dz3.tt.dao.ArticleRepository;
import dz3.tt.dao.ArticleTypeRepository;
import dz3.tt.exceptions.ImageUrlNotValidException;
import dz3.tt.exceptions.PriceNotValidException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ArticleServiceImplTest {

    @Mock
    private ArticleTypeRepository articleTypeRepository;

    @Mock
    private ArticleRepository ArticleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() throws ArticleTypeNotFoundException, PriceNotValidException, ImageUrlNotValidException {
        Long articleTypeId = 1L;
        ArticleType articleType = new ArticleType();
        when(articleTypeRepository.findById(articleTypeId)).thenReturn(Optional.of(articleType));

        String name = "Example Name";
        Double price = 10.99;
        Integer stock = 100;
        String imageUrl = "http://example.com/image.jpg";

        articleService.create(name, price, stock, imageUrl, articleTypeId);

        verify(articleTypeRepository, times(1)).findById(articleTypeId);
        verify(ArticleRepository, times(1)).save(any(Article.class));
    }

    @Test(expected = ArticleTypeNotFoundException.class)
    public void testCreateWithInvalidArticleTypeId() throws ArticleTypeNotFoundException, PriceNotValidException, ImageUrlNotValidException {
        Long articleTypeId = 1L;
        when(articleTypeRepository.findById(articleTypeId)).thenReturn(Optional.empty());

        String name = "Example Name";
        Double price = 10.99;
        Integer stock = 100;
        String imageUrl = "http://example.com/image.jpg";

        articleService.create(name, price, stock, imageUrl, articleTypeId);
    }

    @Test(expected = PriceNotValidException.class)
    public void testCreateWithInvalidPrice() throws ArticleTypeNotFoundException, PriceNotValidException, ImageUrlNotValidException {
        Long articleTypeId = 1L;
        ArticleType articleType = new ArticleType();
        when(articleTypeRepository.findById(articleTypeId)).thenReturn(Optional.of(articleType));

        String name = "Example Name";
        Double price = 0D;
        Integer stock = 100;
        String imageUrl = "http://example.com/image.jpg";

        articleService.create(name, price, stock, imageUrl, articleTypeId);
    }

    @Test(expected = ImageUrlNotValidException.class)
    public void testCreateWithInvalidImageUrl() throws ArticleTypeNotFoundException, PriceNotValidException, ImageUrlNotValidException {
        Long articleTypeId = 1L;
        ArticleType articleType = new ArticleType();
        when(articleTypeRepository.findById(articleTypeId)).thenReturn(Optional.of(articleType));

        String name = "Example Name";
        Double price = 10.99;
        Integer stock = 100;
        String imageUrl = "example";

        articleService.create(name, price, stock, imageUrl, articleTypeId);
    }
}