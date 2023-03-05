package thoughtworks.lephora.server.lephoraserver.domain.specification;

import org.springframework.stereotype.Component;
import thoughtworks.lephora.server.lephoraserver.domain.repository.CommodityRepository;
import thoughtworks.lephora.server.lephoraserver.query.exception.CommodityNotFoundException;

@Component
public class CommodityCanBeFoundSpecification {
    public final CommodityRepository commodityRepository;

    public CommodityCanBeFoundSpecification(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    public void verify(String sku) {
        if (commodityRepository.findById(sku).isEmpty()) {
            throw new CommodityNotFoundException("commodity sku %s not found".formatted(sku));
        }
    }
}
