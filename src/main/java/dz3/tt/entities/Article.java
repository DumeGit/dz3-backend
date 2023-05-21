package dz3.tt.entities;

import dz3.tt.entities.ArticleType;
import dz3.tt.entities.CartArticle;
import dz3.tt.entities.OrderArticle;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private Integer stock;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    private ArticleType type;

    @OneToMany(mappedBy = "article")
    private Set<OrderArticle> orders;

    @OneToMany(mappedBy = "article")
    private Set<CartArticle> carts;

    public Article(String name, Double price, Integer stock, String imageUrl, ArticleType articleType) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.type = articleType;
    }

}
