package dz3.tt.business;

import dz3.tt.dto.OrderDto;
import dz3.tt.entities.AppOrder;
import dz3.tt.entities.BuyerUser;
import dz3.tt.entities.Cart;
import dz3.tt.entities.OrderArticle;
import dz3.tt.exceptions.UserNotFoundException;
import dz3.tt.mappers.CartToOrderArticleMapper;
import dz3.tt.dao.BuyerUserRepository;
import dz3.tt.dao.CartArticleRepository;
import dz3.tt.dao.CartRepository;
import dz3.tt.dao.OrderRepository;
import dz3.tt.util.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService{
    private final BuyerUserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartArticleRepository cartArticleRepository;
    @Override
    public OrderDto createOrder(Long userId) throws UserNotFoundException {
        BuyerUser user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Cart cart = user.getCart();
        AppOrder order = new AppOrder();
        order.setStatus(OrderStatus.PROCESSED);
        Set<OrderArticle> orderArticleSet = user.getCart().getArticles().stream().map((cartArticle -> CartToOrderArticleMapper.transform(order, cartArticle))).collect(Collectors.toSet());
        order.setArticles(orderArticleSet);
        order.setOrderDate(new Date());
        order.setBuyer(user);
        orderRepository.save(order);
        cartArticleRepository.deleteAll(cart.getArticles());
        cart.setArticles(new HashSet<>());
        cartRepository.save(cart);
        return new OrderDto(order);
    }
}
