package dz3.tt.dto;

import dz3.tt.util.AppRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {
    private Long id;
    private AppRole role;
}
