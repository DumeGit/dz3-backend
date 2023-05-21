package dz3.tt.dto;

import dz3.tt.entities.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String name;

    private Double price;

    private Integer stock;

    private String imageUrl;

    private String typeName;

    private Long typeId;

    private boolean available;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.name = article.getName();
        this.price = article.getPrice();
        this.stock = article.getStock();
        this.imageUrl = article.getImageUrl();
        this.typeName = article.getType().getName();
        this.typeId = article.getType().getId();
        this.available = article.isAvailable();
    }

}
