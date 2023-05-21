package dz3.tt.dto;

import dz3.tt.entities.ArticleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticleTypeDto {
    private Long id;
    private String name;

    public ArticleTypeDto(ArticleType type) {
        this.id = type.getId();
        this.name = type.getName();
    }
}
