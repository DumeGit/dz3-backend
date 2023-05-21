package dz3.tt.entities;

import dz3.tt.util.AppRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@ToString(exclude = {"password"})
@EqualsAndHashCode(exclude = {"password"})
public abstract class BaseUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private AppRole role;

    public BaseUser(String username, String password, AppRole role) {
        this.username = username;
        this. password = password;
        this.role = role;
    }
}
