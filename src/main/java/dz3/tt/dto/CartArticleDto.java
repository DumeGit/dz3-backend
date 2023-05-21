package dz3.tt.dto;

import dz3.tt.entities.CartArticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartArticleDto {
    private Long articleId;
    private String name;

    private Double price;

    private Double totalPrice;

    private Integer count;

    private String imageUrl;

    private String typeName;

    private Long typeId;

    public CartArticleDto(CartArticle cartArticle) {
        this.articleId = cartArticle.getArticle().getId();
        this.name = cartArticle.getArticle().getName();
        this.price = cartArticle.getArticle().getPrice();
        this.count = cartArticle.getCount();
        this.totalPrice = this.price * this.count;
        this.imageUrl = cartArticle.getArticle().getImageUrl();
        this.typeName = cartArticle.getArticle().getType().getName();
        this.typeId = cartArticle.getArticle().getType().getId();
    }

}
