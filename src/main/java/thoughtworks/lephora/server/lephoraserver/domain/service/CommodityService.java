package thoughtworks.lephora.server.lephoraserver.domain.service;

import org.springframework.stereotype.Service;
import thoughtworks.lephora.server.lephoraserver.domain.model.Price;
import thoughtworks.lephora.server.lephoraserver.domain.repository.CommodityRepository;
import thoughtworks.lephora.server.lephoraserver.query.exception.CommodityNotFoundException;

@Service
public class CommodityService {

    private final CommodityRepository commodityRepository;

    public CommodityService(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    public Price queryUnitPriceBySku(String sku) {
        return commodityRepository.findPriceBySku(sku)
                .orElseThrow(() -> new CommodityNotFoundException("sku price and unit %s not found".formatted(sku)));
    }
}
