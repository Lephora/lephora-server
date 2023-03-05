package thoughtworks.lephora.server.lephoraserver.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import thoughtworks.lephora.server.lephoraserver.core.DataSourceConfiguration;
import thoughtworks.lephora.server.lephoraserver.fixture.CommodityFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


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
        final var priceAndUnit = commodityRepository.findPriceBySku(savedCommodity.getSku());

        //then
        assertThat(priceAndUnit.isPresent(), is(true));
        assertThat(priceAndUnit.get().getAmount(), is(commodity.getPrice().getAmount()));
        assertThat(priceAndUnit.get().getUnit(), is(commodity.getPrice().getUnit()));
    }


    @Test
    void should_return_commodity_sku_when_save_commodity_success() {
        // given
        final var commodity = CommodityFixture.buildCommodity().build();

        // when
        final var savedCommodity = commodityRepository.saveAndFlush(commodity);

        //then
        assertThat(savedCommodity.getSku(), notNullValue());
    }

    @Test
    void should_return_commodity_when_find_id_by_id() {
        // given
        final var commodity = CommodityFixture.buildCommodity().build();

        // when
        final var savedCommodity = commodityRepository.saveAndFlush(commodity);
        final var findCommodity = commodityRepository.findById(savedCommodity.getSku()).orElseThrow();

        //then
        assertThat(findCommodity.getSku(), equalTo(savedCommodity.getSku()));
        assertThat(findCommodity.getTitle(), equalTo(savedCommodity.getTitle()));
        assertThat(findCommodity.getCreatedAt(), equalTo(savedCommodity.getCreatedAt()));
        assertThat(findCommodity.getDescription(), equalTo(savedCommodity.getDescription()));
        assertThat(findCommodity.getLastModifiedBy(), equalTo(savedCommodity.getLastModifiedBy()));
        assertThat(findCommodity.getPrice().getUnit(), equalTo(savedCommodity.getPrice().getUnit()));
        assertThat(findCommodity.getPrice().getAmount(), equalTo(savedCommodity.getPrice().getAmount()));
    }
}
