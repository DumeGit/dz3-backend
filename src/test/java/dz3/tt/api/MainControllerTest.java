package dz3.tt.api;

import dz3.tt.api.MainController;
import dz3.tt.business.CartServiceImpl;
import dz3.tt.business.OrderServiceImpl;
import dz3.tt.dto.CartArticleDto;
import dz3.tt.dto.EntityDto;
import dz3.tt.dto.OrderDto;
import dz3.tt.entities.AppOrder;
import dz3.tt.entities.Article;
import dz3.tt.entities.CartArticle;
import dz3.tt.exceptions.ArticleNotFoundException;
import dz3.tt.exceptions.UserNotFoundException;
import dz3.tt.util.OrderStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private CartServiceImpl cartService;

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private MainController mainController;

    @Test
    public void testAddArticleToCart() throws UserNotFoundException, ArticleNotFoundException {
        EntityDto dto = new EntityDto(1L);
        Long userId = 1L;

        ResponseEntity<?> response = mainController.addArticleToCart(dto, userId);

        verify(cartService, times(1)).addArticleToCart(dto.getId(), userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRemoveArticleFromCart() throws UserNotFoundException, ArticleNotFoundException {
        EntityDto dto = new EntityDto(1L);
        Long userId = 1L;

        ResponseEntity<?> response = mainController.removeArticleFromCart(dto, userId);

        verify(cartService, times(1)).removeArticleFromCart(dto.getId(), userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateOrder() throws UserNotFoundException {
        Long userId = 1L;

        OrderDto orderDto = new OrderDto(new HashSet<String>(), new Date(), new Date(), OrderStatus.PROCESSED);

        when(orderService.createOrder(userId)).thenReturn(orderDto);

        OrderDto result = mainController.createOrder(userId);

        verify(orderService, times(1)).createOrder(userId);

        assertEquals(orderDto, result);
    }

    @Test
    public void testGetCart() throws UserNotFoundException {
        Long userId = 1L;

        List<CartArticleDto> cartArticleList = new ArrayList<>();
        Article article = new Article();
        article.setId(1L);
        article.setName("Example Article");
        CartArticleDto cartArticleDto = new CartArticleDto(1L, "Example", 2D, 10D, 5, "example", "type", 2L);
        cartArticleList.add(cartArticleDto);

        when(cartService.getCart(userId)).thenReturn(cartArticleList);

        List<CartArticleDto> result = mainController.getCart(userId);

        verify(cartService, times(1)).getCart(userId);

        assertEquals(cartArticleList, result);
    }
}
