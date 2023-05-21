package dz3.tt.api;

import dz3.tt.business.CartServiceImpl;
import dz3.tt.business.OrderServiceImpl;
import dz3.tt.dto.CartArticleDto;
import dz3.tt.dto.EntityDto;
import dz3.tt.dto.OrderDto;
import dz3.tt.exceptions.ArticleNotFoundException;
import dz3.tt.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/")
public class MainController {
    private final CartServiceImpl cartService;
    private final OrderServiceImpl orderService;

    @PostMapping("add")
    public ResponseEntity addArticleToCart(@RequestBody EntityDto dto, @RequestHeader("Auth") Long id) throws UserNotFoundException, ArticleNotFoundException {
        cartService.addArticleToCart(dto.getId(), id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("remove")
    public ResponseEntity removeArticleFromCart(@RequestBody EntityDto dto, @RequestHeader("Auth") Long id) throws UserNotFoundException, ArticleNotFoundException {
        cartService.removeArticleFromCart(dto.getId(), id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("createOrder")
    public OrderDto createOrder(@RequestHeader("Auth") Long id) throws UserNotFoundException {
        return orderService.createOrder(id);
    }

    @GetMapping("getCart")
    public List<CartArticleDto> getCart(@RequestHeader("Auth") Long id) throws UserNotFoundException {
        return cartService.getCart(id);
    }
}
