package dz3.tt.dto;

import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {
    private String username;
    @Length(min = 8, message = "Password length is less than 8")
    private String password;
}
