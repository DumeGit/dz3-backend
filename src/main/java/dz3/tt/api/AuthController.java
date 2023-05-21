package dz3.tt.api;

import dz3.tt.business.BaseUserServiceImpl;
import dz3.tt.business.BuyerUserServiceImpl;
import dz3.tt.business.CartServiceImpl;
import dz3.tt.dto.UserLoginDto;
import dz3.tt.dto.UserRegisterDto;
import dz3.tt.exceptions.RegistrationNotUniqueException;
import dz3.tt.exceptions.UserNotFoundException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user/")
@Validated
public class AuthController {

    private final BuyerUserServiceImpl userService;
    private final BaseUserServiceImpl baseUserService;

    @Transactional(rollbackOn = RollbackException.class)
    @PostMapping(path = "register")
    public ResponseEntity registerUser(@RequestBody UserRegisterDto dto, @RequestHeader("Auth") int id) throws RegistrationNotUniqueException {
        userService.registerUser(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getAddress());
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "login")
    public ResponseEntity loginKorisnik(@RequestBody @Valid UserLoginDto dto) throws UserNotFoundException {
        return ResponseEntity.ok().body(baseUserService.login(dto.getUsername(), dto.getPassword()));
    }

    @PostMapping(path = "logout")
    public ResponseEntity logout(@RequestHeader("Auth") int id) {
        return ResponseEntity.ok().build();
    }

}