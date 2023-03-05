package thoughtworks.lephora.server.lephoraserver.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.ILLEGAL_COMMODITY_SKU;

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

    @CsvSource({"1", "xxxxxx", "X00001", "0000000001"})
    @ParameterizedTest
    void should_return_error_response_when_validate_sku_failed(String illegalSku) throws Exception {
        mockMvc
                .perform(get("/commodity/%s".formatted(illegalSku)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(ILLEGAL_COMMODITY_SKU)))
                .andExpect(jsonPath("$.errorMessage", is("Illegal sku pattern, it should be like 000001.")));
    }
}
