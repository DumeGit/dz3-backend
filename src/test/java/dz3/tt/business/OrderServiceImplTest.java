package dz3.tt.business;

import dz3.tt.dto.OrderDto;
import dz3.tt.entities.*;
import dz3.tt.exceptions.UserNotFoundException;
import dz3.tt.dao.BuyerUserRepository;
import dz3.tt.dao.CartArticleRepository;
import dz3.tt.dao.CartRepository;
import dz3.tt.dao.OrderRepository;
import dz3.tt.util.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    private BuyerUserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartArticleRepository cartArticleRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrder() throws UserNotFoundException {
        Long userId = 1L;

        BuyerUser user = new BuyerUser();
        Cart cart = new Cart();
        Set<OrderArticle> orderArticles = new HashSet<>();
        AppOrder order = new AppOrder();
        order.setStatus(OrderStatus.PROCESSED);
        order.setOrderDate(new Date());
        order.setBuyer(user);
        order.setArticles(orderArticles);
        user.setCart(cart);
        cart.setArticles(new HashSet<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(AppOrder.class))).thenReturn(order);
        doNothing().when(cartArticleRepository).deleteAll(anySet());

        OrderDto result = orderService.createOrder(userId);

        assertEquals(order.getStatus(), result.getStatus());
        assertTrue(order.getOrderDate().getTime() - result.getOrderDate().getTime() < 1000);

        verify(userRepository, times(1)).findById(userId);
        verify(cartRepository, times(1)).save(any(Cart.class));
        verify(orderRepository, times(1)).save(any(AppOrder.class));
        verify(cartArticleRepository, times(1)).deleteAll(anySet());
    }
}
