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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

    @Mock
    private ArticleServiceImpl articleService;

    @InjectMocks
    private ArticleController articleController;

    @Test
    public void testCreate() throws ArticleTypeNotFoundException, PriceNotValidException, ImageUrlNotValidException {
        CreateArticleDto dto = new CreateArticleDto();
        dto.setName("Example Article");
        dto.setPrice(10.99);
        dto.setStock(100);
        dto.setImageUrl("example.com/image.jpg");
        dto.setArticleTypeId(1L);

        articleController.create(dto);

        verify(articleService, times(1)).create(dto.getName(), dto.getPrice(), dto.getStock(), dto.getImageUrl(), dto.getArticleTypeId());
    }

    @Test
    public void testUpdate() throws ArticleTypeNotFoundException, ArticleNotFoundException {
        UpdateArticleDto dto = new UpdateArticleDto();
        dto.setArticleId(1L);
        dto.setName("Updated Article");
        dto.setPrice(19.99);
        dto.setStock(50);
        dto.setImageUrl("example.com/updated-image.jpg");
        dto.setArticleTypeId(2L);

        articleController.update(dto);

        verify(articleService, times(1)).update(dto.getArticleId(), dto.getName(), dto.getPrice(), dto.getStock(), dto.getImageUrl(), dto.getArticleTypeId());
    }

    @Test
    public void testSearch() {
        List<ArticleDto> articleList = new ArrayList<>();
        articleList.add(new ArticleDto(1L, "Example Article", 10.99, 100, "example.com/image.jpg","Pamuk", 1L, true));

        ResponseEntity<List<ArticleDto>> expectedResponse = ResponseEntity.ok(articleList);

        when(articleService.search()).thenReturn(articleList);

        ResponseEntity<List<ArticleDto>> response = articleController.search();

        verify(articleService, times(1)).search();

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    public void testDelete() throws ArticleNotFoundException {
        EntityDto dto = new EntityDto();
        dto.setId(1L);

        articleController.delete(dto);

        verify(articleService, times(1)).delete(dto.getId());
    }

    @Test
    public void testFilter() throws ArticleTypeNotFoundException {
        EntityDto dto = new EntityDto();
        dto.setId(1L);

        List<ArticleDto> filteredArticleList = new ArrayList<>();
        filteredArticleList.add(new ArticleDto(1L, "Example Article", 10.99, 100, "example.com/image.jpg", "type", 1L, true));

        ResponseEntity<List<ArticleDto>> expectedResponse = ResponseEntity.ok(filteredArticleList);

        when(articleService.filter(dto.getId())).thenReturn(filteredArticleList);

        ResponseEntity<List<ArticleDto>> response = articleController.filter(dto);

        verify(articleService, times(1)).filter(dto.getId());

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }
}
