package thoughtworks.lephora.server.lephoraserver.fixture;

import thoughtworks.lephora.server.lephoraserver.domain.model.Commodity;
import thoughtworks.lephora.server.lephoraserver.domain.model.CommodityImage;
import thoughtworks.lephora.server.lephoraserver.domain.model.CommodityPrice;

import java.math.BigDecimal;
import java.util.Set;

public class CommodityFixture {
    private String sku = "000001";
    private String title = "default fixture title";
    private String description = "default fixture description";
    private BigDecimal price = new BigDecimal("100.99");
    private String operatorId = "100000";
    private CommodityPrice.PriceUnit priceUnit = CommodityPrice.PriceUnit.RMB;
    private final Set<CommodityImage> images = Set.of();

    public static CommodityFixture buildCommodity() {
        return new CommodityFixture();
    }


    public CommodityFixture withTitle(String title) {
        this.title = title;
        return this;
    }

    public CommodityFixture withDescription(String description) {
        this.description = description;
        return this;
    }

    public CommodityFixture withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CommodityFixture withOperatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public CommodityFixture withPriceUnit(CommodityPrice.PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public CommodityFixture withImage(String url, String alternativeText) {
        this.images.add(new CommodityImage(url, alternativeText));
        return this;
    }

    public CommodityFixture withSku(String sku) {
        this.sku = sku;
        return this;
    }

    public Commodity build() {
        final var commodity = new Commodity(
                this.title,
                this.description,
                this.images,
                new CommodityPrice(this.price, this.priceUnit),
                this.operatorId
        );
        commodity.attachSku(this.sku);
        return commodity;
    }
}
