package dz3.tt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateArticleDto {
    private Long articleId;

    private String name;

    private Double price;

    private Integer stock;

    private String imageUrl;

    private Long articleTypeId;
}
