package thoughtworks.lephora.server.lephoraserver.rest;

import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import thoughtworks.lephora.server.lephoraserver.query.CommodityQuery;
import thoughtworks.lephora.server.lephoraserver.query.view.CommodityDetailView;

import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.ILLEGAL_COMMODITY_SKU;

@Validated
@RestController
public class CommodityManagementQueryApi {

    private final CommodityQuery commodityQuery;

    public CommodityManagementQueryApi(CommodityQuery commodityQuery) {
        this.commodityQuery = commodityQuery;
    }

    @GetMapping("/commodity/{sku}")
    public CommodityDetailView queryCommodityBySku(
            @PathVariable("sku") @Pattern(regexp = "^\\d{6}$", message = ILLEGAL_COMMODITY_SKU) String sku
    ) {
        return commodityQuery.queryCommodityDetailBySku(sku);
    }
}
