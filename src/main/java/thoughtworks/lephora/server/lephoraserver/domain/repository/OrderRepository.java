package thoughtworks.lephora.server.lephoraserver.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thoughtworks.lephora.server.lephoraserver.domain.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
