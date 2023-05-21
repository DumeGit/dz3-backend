package dz3.tt.dao;

import dz3.tt.entities.AppOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<AppOrder, Long> {
}
