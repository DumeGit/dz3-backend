package dz3.tt.entities;

import dz3.tt.util.CartArticleId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@IdClass(CartArticleId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartArticle {

    @Id
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @Column(nullable = false)
    private Integer count;

    @Override
    public int hashCode() {
        return Objects.hash(cart, article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartArticle that = (CartArticle) o;
        return cart.equals(that.cart) && article.equals(that.article);
    }
}
