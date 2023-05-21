package dz3.tt.business;

import dz3.tt.dto.CartArticleDto;
import dz3.tt.entities.Article;
import dz3.tt.entities.BuyerUser;
import dz3.tt.entities.Cart;
import dz3.tt.entities.CartArticle;
import dz3.tt.exceptions.ArticleNotFoundException;
import dz3.tt.exceptions.UserNotFoundException;
import dz3.tt.dao.ArticleRepository;
import dz3.tt.dao.BuyerUserRepository;
import dz3.tt.dao.CartArticleRepository;
import dz3.tt.dao.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements ICartService {
    private final CartRepository cartRepository;
    private final ArticleRepository ArticleRepository;
    private final BuyerUserRepository buyerUserRepository;
    private final CartArticleRepository cartArticleRepository;

    @Override
    public Cart create() {
        return cartRepository.save(new Cart());
    }

    @Transactional
    public void addArticleToCart(Long articleId, Long userId) throws UserNotFoundException, ArticleNotFoundException {
        BuyerUser user = buyerUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Cart cart = user.getCart();
        Article addArticle = ArticleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
        CartArticle cartArticle = new CartArticle(cart, addArticle, 1);
        List<CartArticle> articleList = new ArrayList<>(cart.getArticles());
        int index = articleList.indexOf(cartArticle);
        if (index != -1) {
            articleList.get(index).setCount(articleList.get(index).getCount() + 1);
            cartArticleRepository.save(articleList.get(index));
        } else {
            cartArticleRepository.save(cartArticle);
            Set<CartArticle> newArticles = new HashSet<>(cart.getArticles());
            newArticles.add(cartArticle);
            cart.setArticles(newArticles);
        }
        addArticle.setStock(addArticle.getStock() - 1);
        ArticleRepository.save(addArticle);
        cartRepository.save(cart);
    }

    @Transactional
    public void removeArticleFromCart(Long articleId, Long userId) throws UserNotFoundException, ArticleNotFoundException {
        BuyerUser user = buyerUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Cart cart = user.getCart();
        Article addArticle = ArticleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
        CartArticle cartArticle = new CartArticle(cart, addArticle, 1);
        List<CartArticle> articleList = new ArrayList<>(cart.getArticles());

        int index = articleList.indexOf(cartArticle);
        if (index == -1) {
            throw new ArticleNotFoundException();
        } else {
            if(articleList.get(index).getCount() == 1) {
                cartArticleRepository.delete(articleList.get(index));
                articleList.remove(index);
            } else {
                articleList.get(index).setCount(articleList.get(index).getCount() - 1);
                cartArticleRepository.save(articleList.get(index));
            }
        }
        cart.setArticles(new HashSet<>(articleList));
        cartRepository.save(cart);
    }

    public List<CartArticleDto> getCart(Long userId) throws UserNotFoundException {
        BuyerUser user = buyerUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getCart().getArticles().stream().map(CartArticleDto::new).collect(Collectors.toList());
    }
}
