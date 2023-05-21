package dz3.tt.mappers;

import dz3.tt.entities.AppOrder;
import dz3.tt.entities.CartArticle;
import dz3.tt.entities.OrderArticle;

public class CartToOrderArticleMapper {
    public static OrderArticle transform(AppOrder order, CartArticle cartArticle) {
        OrderArticle orderArticle = new OrderArticle();
        orderArticle.setArticle(cartArticle.getArticle());
        orderArticle.setOrder(order);
        orderArticle.setCount(cartArticle.getCount());
        return orderArticle;
    }
}
