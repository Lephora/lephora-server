package thoughtworks.lephora.server.lephoraserver.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thoughtworks.lephora.server.lephoraserver.domain.model.Commodity;
import thoughtworks.lephora.server.lephoraserver.domain.model.Price;

import java.util.Optional;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {

    @Query(value = """
            SELECT
            c.price
            FROM Commodity c
            WHERE c.sku = ?1
            """)
    Optional<Price> findPriceBySku(String sku);
}
