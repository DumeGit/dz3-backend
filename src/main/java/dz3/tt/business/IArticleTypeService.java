package dz3.tt.business;

import dz3.tt.dto.ArticleTypeDto;
import dz3.tt.entities.ArticleType;
import dz3.tt.exceptions.ArticleNotFoundException;
import dz3.tt.exceptions.ArticleTypeNotFoundException;

import java.util.List;

public interface IArticleTypeService {
    List<ArticleTypeDto> search();

    void create(String name) throws ArticleTypeNotFoundException;

    void update(Long id, String name) throws ArticleTypeNotFoundException;

    void delete(Long id) throws ArticleTypeNotFoundException;
}
