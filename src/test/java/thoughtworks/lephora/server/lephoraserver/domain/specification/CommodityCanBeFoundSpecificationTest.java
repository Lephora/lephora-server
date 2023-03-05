package thoughtworks.lephora.server.lephoraserver.domain.specification;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import thoughtworks.lephora.server.lephoraserver.domain.repository.CommodityRepository;
import thoughtworks.lephora.server.lephoraserver.query.exception.CommodityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class CommodityCanBeFoundSpecificationTest {

    private final CommodityRepository commodityRepository = Mockito.mock(CommodityRepository.class);
    private final CommodityCanBeFoundSpecification commodityCanBeFoundSpecification
            = new CommodityCanBeFoundSpecification(commodityRepository);

    @Test
    void should_throw_exception_when_cannot_find_commodity_by_sku() {
        // given
        var sku = "000001";
        given(commodityRepository.findById(sku)).willReturn(Optional.empty());

        // when then
        assertThrows(CommodityNotFoundException.class, () -> commodityCanBeFoundSpecification.verify(sku));
    }
}
