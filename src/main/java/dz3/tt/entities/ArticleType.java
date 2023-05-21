package dz3.tt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ArticleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="type")
    private Set<Article> articles;

    public ArticleType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArticleType(String name) {
        this.name = name;
    }
}
