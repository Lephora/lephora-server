package thoughtworks.lephora.server.lephoraserver.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import thoughtworks.lephora.server.lephoraserver.fixture.CommodityDetailViewFixture;
import thoughtworks.lephora.server.lephoraserver.query.CommodityQuery;
import thoughtworks.lephora.server.lephoraserver.query.exception.CommodityNotFoundException;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static thoughtworks.lephora.server.lephoraserver.query.constant.QueryErrorCode.COMMODITY_NOT_FOUND;

@WebMvcTest(CommodityManagementQueryApi.class)
class CommodityManagementQueryApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommodityQuery commodityQuery;

    @Test
    void should_query_commodity_detail_by_sku_success() throws Exception {
        // given
        var sku = "000001";
        final var commodityDetailView = CommodityDetailViewFixture.buildCommodityDetailView().build();

        // when
        given(commodityQuery.queryCommodityDetailBySku(sku)).willReturn(commodityDetailView);

        // then
        mockMvc
                .perform(get("/commodity/000001"))
                .andExpect(status().isOk());
    }

    @Test
    void should_throw_exception_when_query_not_exist_commodity() throws Exception {
        // given
        var sku = "000001";

        // when
        given(commodityQuery.queryCommodityDetailBySku(sku)).willThrow(new CommodityNotFoundException("sku %s not exist".formatted(sku)));

        // then
        mockMvc
                .perform(get("/commodity/000001"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(COMMODITY_NOT_FOUND)));
    }

}
