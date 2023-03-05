package thoughtworks.lephora.server.lephoraserver.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import thoughtworks.lephora.server.lephoraserver.core.DataSourceConfiguration;
import thoughtworks.lephora.server.lephoraserver.fixture.CommodityFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Transactional
@SpringBootTest(classes = DataSourceConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommodityRepositoryTest {

    @Autowired
    private CommodityRepository commodityRepository;

    @Test
    void should_return_price_and_unit_when_find_commodity_success() {
        // given
        final var commodity = CommodityFixture.buildCommodity().build();

        // when
        final var savedCommodity = commodityRepository.saveAndFlush(commodity);
        final var priceAndUnit = commodityRepository.findFirstBySku(savedCommodity.getSku());

        //then
        assertThat(priceAndUnit.isPresent(), is(true));
        assertThat(priceAndUnit.get().getPrice().getAmount(), is(commodity.getPrice().getAmount()));
        assertThat(priceAndUnit.get().getPrice().getUnit(), is(commodity.getPrice().getUnit()));
    }
}
