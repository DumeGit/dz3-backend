package dz3.tt.business;

import dz3.tt.dto.ArticleDto;
import dz3.tt.exceptions.*;

import java.util.List;

public interface IArticleService {
    void create(String name, Double price, Integer stock, String imageUrl, Long articleTypeId) throws ArticleTypeNotFoundException, ForbidenAccessException, UserNotFoundException, ImageUrlNotValidException, PriceNotValidException;

    void update(Long articleId, String name, Double price, Integer stock, String imageUrl, Long articleTypeId) throws ArticleNotFoundException, ArticleTypeNotFoundException;
    List<ArticleDto> search();

    void delete(Long id) throws ArticleNotFoundException;

    List<ArticleDto> filter(Long id) throws ArticleTypeNotFoundException;
}
