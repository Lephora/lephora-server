package thoughtworks.lephora.server.lephoraserver.domain.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import thoughtworks.lephora.server.lephoraserver.domain.model.Price;
import thoughtworks.lephora.server.lephoraserver.domain.repository.CommodityRepository;
import thoughtworks.lephora.server.lephoraserver.query.exception.CommodityNotFoundException;

import java.util.Optional;

import static java.math.BigDecimal.ONE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static thoughtworks.lephora.server.lephoraserver.domain.model.Price.PriceUnit.RMB;

class CommodityServiceTest {

    private final CommodityRepository commodityRepository = Mockito.mock(CommodityRepository.class);

    private final CommodityService commodityService = new CommodityService(commodityRepository);

    @Test
    void should_return_unit_price_when_query_price_by_sku() {
        // given
        var commoditySku = "002016";
        given(commodityRepository.findPriceBySku(commoditySku)).willReturn(Optional.of(Price.of(ONE, RMB)));

        // when
        final var unitPrice = commodityService.queryUnitPriceBySku(commoditySku);

        // then
        assertThat(unitPrice.getAmount(), equalTo(ONE));
        assertThat(unitPrice.getUnit(), equalTo(RMB));
    }

    @Test
    void should_throw_exception_when_query_not_exist_sku() {
        // given
        var commoditySku = "002016";
        given(commodityRepository.findPriceBySku(commoditySku)).willReturn(Optional.empty());

        // when then
        assertThrows(CommodityNotFoundException.class, () -> commodityService.queryUnitPriceBySku(commoditySku));
    }
}
