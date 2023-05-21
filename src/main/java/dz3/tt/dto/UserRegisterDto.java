package dz3.tt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {
    private String username;
    private String password;
    private String email;
    private String address;
}
