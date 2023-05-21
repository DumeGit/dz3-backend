package dz3.tt.entities;

import dz3.tt.util.OrderArticleId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@IdClass(OrderArticleId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderArticle {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private AppOrder order;

    @Id
    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    private Integer count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderArticle that = (OrderArticle) o;
        return Objects.equals(order, that.order) && Objects.equals(article, that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, article);
    }
}
