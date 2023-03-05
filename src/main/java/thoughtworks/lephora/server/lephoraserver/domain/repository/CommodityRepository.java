package thoughtworks.lephora.server.lephoraserver.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thoughtworks.lephora.server.lephoraserver.domain.model.Commodity;
import thoughtworks.lephora.server.lephoraserver.domain.repository.result.FindPrice;

import java.util.Optional;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {

    Optional<FindPrice> findFirstBySku(String sku);
}
