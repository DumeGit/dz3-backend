package dz3.tt.entities;

import dz3.tt.entities.OrderArticle;
import dz3.tt.entities.BuyerUser;
import dz3.tt.util.OrderStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AppOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
    private Set<OrderArticle> articles;

    private Date orderDate;

    private Date finishDate;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private BuyerUser buyer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppOrder appOrder = (AppOrder) o;
        return Objects.equals(id, appOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
