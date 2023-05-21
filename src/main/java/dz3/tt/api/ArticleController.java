package dz3.tt.api;

import dz3.tt.business.ArticleServiceImpl;
import dz3.tt.dto.ArticleDto;
import dz3.tt.dto.CreateArticleDto;
import dz3.tt.dto.EntityDto;
import dz3.tt.dto.UpdateArticleDto;
import dz3.tt.exceptions.ArticleNotFoundException;
import dz3.tt.exceptions.ArticleTypeNotFoundException;
import dz3.tt.exceptions.ImageUrlNotValidException;
import dz3.tt.exceptions.PriceNotValidException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/article/")
public class ArticleController {
    private final ArticleServiceImpl articleServiceImpl;

    @PostMapping("create")
    public void create(@RequestBody @Valid CreateArticleDto dto) throws ArticleTypeNotFoundException, PriceNotValidException, ImageUrlNotValidException {
        articleServiceImpl.create(dto.getName(), dto.getPrice(), dto.getStock(), dto.getImageUrl(), dto.getArticleTypeId());
    }

    @PostMapping("update")
    public void update(@RequestBody UpdateArticleDto dto) throws ArticleTypeNotFoundException, ArticleNotFoundException {
        articleServiceImpl.update(dto.getArticleId(), dto.getName(), dto.getPrice(), dto.getStock(), dto.getImageUrl(), dto.getArticleTypeId());
    }

    @GetMapping("search")
    public ResponseEntity<List<ArticleDto>> search() {
        return ResponseEntity.ok().body(articleServiceImpl.search());
    }

    @PostMapping("delete")
    public void delete(@RequestBody EntityDto dto) throws ArticleNotFoundException {
        articleServiceImpl.delete(dto.getId());
    }

    @PostMapping("filter")
    public ResponseEntity<List<ArticleDto>> filter(@RequestBody EntityDto dto) throws ArticleTypeNotFoundException {
        return ResponseEntity.ok().body(articleServiceImpl.filter(dto.getId()));
    }
}
