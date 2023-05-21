package dz3.tt.business;

import dz3.tt.dto.ArticleTypeDto;
import dz3.tt.entities.ArticleType;
import dz3.tt.dao.ArticleTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ArticleTypeServiceImplTest {

    @Mock
    private ArticleTypeRepository articleTypeRepository;

    @InjectMocks
    private ArticleTypeServiceImpl articleTypeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearch() {
        ArticleType articleType1 = new ArticleType(1L, "Type 1");
        ArticleType articleType2 = new ArticleType(2L, "Type 2");
        List<ArticleType> articleTypes = Arrays.asList(articleType1, articleType2);
        when(articleTypeRepository.findAll()).thenReturn(articleTypes);

        List<ArticleTypeDto> result = articleTypeService.search();

        assertEquals(articleTypes.size(), result.size());
        assertEquals(articleType1.getId(), result.get(0).getId());
        assertEquals(articleType1.getName(), result.get(0).getName());
        assertEquals(articleType2.getId(), result.get(1).getId());
        assertEquals(articleType2.getName(), result.get(1).getName());
    }
}