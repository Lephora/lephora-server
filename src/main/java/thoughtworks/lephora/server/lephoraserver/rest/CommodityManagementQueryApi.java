package thoughtworks.lephora.server.lephoraserver.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import thoughtworks.lephora.server.lephoraserver.query.CommodityQuery;
import thoughtworks.lephora.server.lephoraserver.query.view.CommodityDetailView;

@RestController
public class CommodityManagementQueryApi {

    private final CommodityQuery commodityQuery;

    public CommodityManagementQueryApi(CommodityQuery commodityQuery) {
        this.commodityQuery = commodityQuery;
    }

    @GetMapping("/commodity/{sku}")
    public CommodityDetailView queryCommodityBySku(@PathVariable("sku") String sku) {
        return commodityQuery.queryCommodityDetailBySku(sku);
    }

}
