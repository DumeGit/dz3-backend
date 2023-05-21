package dz3.tt.business;

import dz3.tt.dto.ArticleTypeDto;
import dz3.tt.entities.ArticleType;
import dz3.tt.exceptions.ArticleTypeNotFoundException;
import dz3.tt.dao.ArticleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleTypeServiceImpl implements IArticleTypeService{
    private final ArticleTypeRepository articleTypeRepository;
    @Override
    public List<ArticleTypeDto> search() {
        return articleTypeRepository.findAll().stream().map(ArticleTypeDto::new).collect(Collectors.toList());
    }

    public void create(String name) {
        articleTypeRepository.save(new ArticleType(name));
    }

    @Override
    public void update(Long id, String name) throws ArticleTypeNotFoundException {
        ArticleType articleType = articleTypeRepository.findById(id).orElseThrow(ArticleTypeNotFoundException::new);

        if(name != null)
            articleType.setName(name);

        articleTypeRepository.save(articleType);
    }

    @Override
    public void delete(Long id) throws ArticleTypeNotFoundException {
        if(articleTypeRepository.existsById(id)) {
            articleTypeRepository.deleteById(id);
        } else {
            throw new ArticleTypeNotFoundException();
        }
    }
}
