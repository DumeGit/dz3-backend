package dz3.tt.entities;

import dz3.tt.util.AppRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class BuyerUser extends BaseUser {

    @OneToMany(mappedBy = "buyer")
    private Set<AppOrder> orders;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    public BuyerUser(String username, String email, String password, String address, Cart cart) {
        super(username, password, AppRole.BUYER);
        this.email = email;
        this.address = address;
        this.cart = cart;
    }


}
