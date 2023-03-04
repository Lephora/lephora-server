package thoughtworks.lephora.server.lephoraserver.query.view;

import java.math.BigDecimal;
import java.util.List;

public record CommodityDetailView(
        String title,
        String description,
        List<CommodityImageView> images,
        BigDecimal price,
        String priceUnit
) {
}
