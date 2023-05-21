package dz3.tt.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class CreateArticleDto {

    @Length(min = 1, message = "Article name length must be larger than 1")
    @NotBlank(message = "Article name must not be blank")
    private String name;

    @NotNull(message = "Article price must not be null")
    private Double price;

    @Min(value = 1, message = "Stock must be larger than 0")
    @NotNull(message = "Article stock must not be null")
    private Integer stock;

    @NotBlank(message = "Article image url must not be null")
    private String imageUrl;

    @NotNull(message = "Article type url must not be null")
    private Long articleTypeId;
}
