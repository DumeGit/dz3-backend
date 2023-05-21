package dz3.tt.business;

import dz3.tt.dto.ArticleDto;
import dz3.tt.entities.Article;
import dz3.tt.entities.ArticleType;
import dz3.tt.exceptions.ArticleNotFoundException;
import dz3.tt.exceptions.ArticleTypeNotFoundException;
import dz3.tt.dao.ArticleRepository;
import dz3.tt.dao.ArticleTypeRepository;
import dz3.tt.exceptions.ImageUrlNotValidException;
import dz3.tt.exceptions.PriceNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements IArticleService{
    private final ArticleRepository articleRepository;
    private final ArticleTypeRepository articleTypeRepository;

    @Override
    public void create(String name, Double price, Integer stock, String imageUrl, Long articleTypeId) throws ArticleTypeNotFoundException, ImageUrlNotValidException, PriceNotValidException {
        ArticleType type = articleTypeRepository.findById(articleTypeId).orElseThrow(ArticleTypeNotFoundException::new);
        validateNewArticle(price, imageUrl);
        articleRepository.save(new Article(name, price, stock, imageUrl, type));
    }

    @Override
    public void update(Long articleId, String name, Double price, Integer stock, String imageUrl, Long articleTypeId) throws ArticleNotFoundException, ArticleTypeNotFoundException {
        Article article = articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);

        if(name != null)
            article.setName(name);
        if(price != null)
            article.setPrice(price);
        if (stock != null)
            article.setStock(stock);
        if (imageUrl != null)
            article.setImageUrl(imageUrl);
        if (articleTypeId != null ){
            article.setType(articleTypeRepository.findById(articleTypeId).orElseThrow(ArticleTypeNotFoundException::new));
        }

        articleRepository.save(article);
    }

    @Override
    public List<ArticleDto> search() {
        return articleRepository.findAll().stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> filter(Long typeId) throws ArticleTypeNotFoundException {
        ArticleType type = articleTypeRepository.findById(typeId).orElseThrow(ArticleTypeNotFoundException::new);
        return articleRepository.findArticlesByType(type).stream().map(ArticleDto::new).collect(Collectors.toList());
    }



    @Override
    public void delete(Long id) throws ArticleNotFoundException {
        if(articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        } else {
            throw new ArticleNotFoundException();
        }
    }

    private void validateNewArticle(Double price, String imageUrl) throws ImageUrlNotValidException, PriceNotValidException {
        try {
            URL url = new URL(imageUrl);
            url.toURI();
        } catch (Exception e) {
            throw new ImageUrlNotValidException();
        }
        if(price <= 0) {
            throw new PriceNotValidException();
        }
    }
}
