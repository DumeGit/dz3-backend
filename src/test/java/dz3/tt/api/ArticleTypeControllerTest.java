package dz3.tt.api;

import dz3.tt.business.ArticleTypeServiceImpl;
import dz3.tt.dto.ArticleTypeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleTypeControllerTest {

    @Mock
    private ArticleTypeServiceImpl articleTypeService;

    @InjectMocks
    private ArticleTypeController articleTypeController;

    @Test
    public void testSearch() {
        Long userId = 1L;

        List<ArticleTypeDto> articleTypeList = new ArrayList<>();
        articleTypeList.add(new ArticleTypeDto(1L, "Example Type"));

        ResponseEntity<List<ArticleTypeDto>> expectedResponse = ResponseEntity.ok(articleTypeList);

        when(articleTypeService.search()).thenReturn(articleTypeList);

        ResponseEntity<List<ArticleTypeDto>> response = articleTypeController.search(userId);

        verify(articleTypeService, times(1)).search();

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }
}
