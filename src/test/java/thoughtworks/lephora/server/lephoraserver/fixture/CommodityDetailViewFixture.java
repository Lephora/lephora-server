package thoughtworks.lephora.server.lephoraserver.fixture;

import thoughtworks.lephora.server.lephoraserver.query.view.CommodityDetailView;
import thoughtworks.lephora.server.lephoraserver.query.view.CommodityImageView;

import java.math.BigDecimal;
import java.util.List;

public class CommodityDetailViewFixture {
    private String title = "default fixture title";
    private String description = "default fixture description";
    private BigDecimal price = new BigDecimal("100.99");
    private String priceUnit = "RMB";
    private List<CommodityImageView> images = List.of();

    public static CommodityDetailViewFixture buildCommodityDetailView() {
        return new CommodityDetailViewFixture();
    }

    public CommodityDetailViewFixture withTitle(String title) {
        this.title = title;
        return this;
    }

    public CommodityDetailViewFixture withDescription(String description) {
        this.description = description;
        return this;
    }

    public CommodityDetailViewFixture withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CommodityDetailViewFixture withPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
        return this;
    }

    public CommodityDetailViewFixture withImage(String url, String alternativeText) {
        this.images.add(new CommodityImageView(url, alternativeText));
        return this;
    }

    public CommodityDetailView build() {
        return new CommodityDetailView(
                title,
                description,
                images,
                price,
                priceUnit
        );
    }
}
