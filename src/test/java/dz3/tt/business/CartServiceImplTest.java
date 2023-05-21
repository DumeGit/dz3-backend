package dz3.tt.business;

import dz3.tt.dto.CartArticleDto;
import dz3.tt.entities.*;
import dz3.tt.exceptions.ArticleNotFoundException;
import dz3.tt.exceptions.UserNotFoundException;
import dz3.tt.dao.ArticleRepository;
import dz3.tt.dao.BuyerUserRepository;
import dz3.tt.dao.CartArticleRepository;
import dz3.tt.dao.CartRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CartServiceImplTest {

    @Mock
    private BuyerUserRepository buyerUserRepository;

    @Mock
    private ArticleRepository ArticleRepository;

    @Mock
    private CartArticleRepository cartArticleRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddArticleToCart() throws UserNotFoundException, ArticleNotFoundException {
        Long articleId = 1L;
        Long userId = 1L;

        BuyerUser user = new BuyerUser();
        Article article = new Article();
        article.setStock(1);
        CartArticle cartArticle = new CartArticle(null, article, 1);

        Cart cart = new Cart();
        cart.setArticles(Set.of(cartArticle));

        user.setCart(cart);

        when(buyerUserRepository.findById(userId)).thenReturn(Optional.of(user));
        when(ArticleRepository.findById(articleId)).thenReturn(Optional.of(article));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(cartArticleRepository.save(any(CartArticle.class))).thenReturn(cartArticle);

        cartService.addArticleToCart(articleId, userId);

        verify(buyerUserRepository, times(1)).findById(userId);
        verify(ArticleRepository, times(1)).findById(articleId);
        verify(cartRepository, times(1)).save(any(Cart.class));
        verify(cartArticleRepository, times(1)).save(any(CartArticle.class));
        verify(ArticleRepository, times(1)).save(any(Article.class));
    }

    @Test(expected = UserNotFoundException.class)
    public void testAddArticleToCartWithInvalidUser() throws UserNotFoundException, ArticleNotFoundException {
        Long articleId = 1L;
        Long userId = 1L;

        when(buyerUserRepository.findById(userId)).thenReturn(Optional.empty());

        cartService.addArticleToCart(articleId, userId);
    }

    @Test(expected = ArticleNotFoundException.class)
    public void testAddArticleToCartWithInvalidArticle() throws UserNotFoundException, ArticleNotFoundException {
        Long articleId = 1L;
        Long userId = 1L;

        when(buyerUserRepository.findById(userId)).thenReturn(Optional.of(new BuyerUser()));
        when(ArticleRepository.findById(articleId)).thenReturn(Optional.empty());

        cartService.addArticleToCart(articleId, userId);
    }

    @Test
    public void testRemoveArticleFromCart() throws UserNotFoundException, ArticleNotFoundException {
        Long articleId = 1L;
        Long userId = 1L;

        BuyerUser user = new BuyerUser();
        Cart cart = new Cart();
        Article article = new Article();
        CartArticle cartArticle = new CartArticle(cart, article, 1);
        List<CartArticle> articleList = new ArrayList<>();
        articleList.add(cartArticle);
        cart.setArticles(new HashSet<>(articleList));
        user.setCart(cart);

        when(buyerUserRepository.findById(userId)).thenReturn(Optional.of(user));
        when(ArticleRepository.findById(articleId)).thenReturn(Optional.of(article));
        when(cartArticleRepository.save(any(CartArticle.class))).thenReturn(cartArticle);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.removeArticleFromCart(articleId, userId);

        verify(buyerUserRepository, times(1)).findById(userId);
        verify(ArticleRepository, times(1)).findById(articleId);
        verify(cartRepository, times(1)).save(any(Cart.class));
        verify(cartArticleRepository, times(1)).delete(any(CartArticle.class));
    }

    @Test(expected = UserNotFoundException.class)
    public void testRemoveArticleFromCartWithInvalidUser() throws UserNotFoundException, ArticleNotFoundException {
        Long articleId = 1L;
        Long userId = 1L;

        when(buyerUserRepository.findById(userId)).thenReturn(Optional.empty());

        cartService.removeArticleFromCart(articleId, userId);
    }

    @Test(expected = ArticleNotFoundException.class)
    public void testRemoveArticleFromCartWithInvalidArticle() throws UserNotFoundException, ArticleNotFoundException {
        Long articleId = 1L;
        Long userId = 1L;

        when(buyerUserRepository.findById(userId)).thenReturn(Optional.of(new BuyerUser()));
        when(ArticleRepository.findById(articleId)).thenReturn(Optional.empty());

        cartService.removeArticleFromCart(articleId, userId);
    }

    @Test
    public void testGetCart() throws UserNotFoundException {
        Long userId = 1L;

        BuyerUser user = new BuyerUser();
        Cart cart = new Cart();
        CartArticle cartArticle1 = new CartArticle(cart, new Article("example", 1D, 1, "example", new ArticleType(1L, "example")), 1);
        CartArticle cartArticle2 = new CartArticle(cart, new Article("example2", 1D, 1, "example2", new ArticleType(1L, "example2")), 2);
        List<CartArticle> cartArticles = new ArrayList<>();
        cartArticles.add(cartArticle1);
        cartArticles.add(cartArticle2);
        cart.setArticles(new HashSet<>(cartArticles));
        user.setCart(cart);

        when(buyerUserRepository.findById(userId)).thenReturn(Optional.of(user));

        List<CartArticleDto> result = cartService.getCart(userId);

        assertEquals(cartArticles.size(), result.size());

        verify(buyerUserRepository, times(1)).findById(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetCartWithInvalidUser() throws UserNotFoundException {
        Long userId = 1L;

        when(buyerUserRepository.findById(userId)).thenReturn(Optional.empty());

        cartService.getCart(userId);
    }

    @Test
    public void testCreate() {
        Cart cart = new Cart();
        when(cartRepository.save(cart)).thenReturn(cart);

        Cart result = cartService.create();

        assertNotNull(result);

        verify(cartRepository, times(1)).save(cart);
    }
}