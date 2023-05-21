package dz3.tt.dto;

import dz3.tt.entities.AppOrder;
import dz3.tt.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private Set<String> articles;
    private Date orderDate;
    private Date finishDate;
    private OrderStatus status;

    public OrderDto (AppOrder order) {
        this.articles = order.getArticles().stream().map((orderArticle -> orderArticle.getArticle().getName())).collect(Collectors.toSet());
        this.orderDate = order.getOrderDate();
        this.finishDate = order.getFinishDate();
        this.status = order.getStatus();
    }
}
