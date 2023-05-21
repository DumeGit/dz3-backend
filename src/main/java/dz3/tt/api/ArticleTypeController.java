package dz3.tt.api;

import dz3.tt.business.ArticleTypeServiceImpl;
import dz3.tt.dto.*;
import dz3.tt.exceptions.ArticleTypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/type/")
public class ArticleTypeController {
    private final ArticleTypeServiceImpl articleTypeServiceImpl;
    @GetMapping("search")
    public ResponseEntity<List<ArticleTypeDto>> search(@RequestHeader("Auth") Long id) {
        return ResponseEntity.ok().body(articleTypeServiceImpl.search());
    }

    @PostMapping("create")
    public void create(@RequestBody CreateArticleTypeDto dto) throws ArticleTypeNotFoundException {
        articleTypeServiceImpl.create(dto.getName());
    }

    @PostMapping("update")
    public void update(@RequestBody UpdateArticleTypeDto dto) throws ArticleTypeNotFoundException {
        articleTypeServiceImpl.update(dto.getId(), dto.getName());
    }

    @PostMapping("delete")
    public void delete(@RequestBody EntityDto dto) throws ArticleTypeNotFoundException {
        articleTypeServiceImpl.delete(dto.getId());
    }


}
