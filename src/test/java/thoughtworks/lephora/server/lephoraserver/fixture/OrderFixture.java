package thoughtworks.lephora.server.lephoraserver.fixture;

import thoughtworks.lephora.server.lephoraserver.domain.model.Order;
import thoughtworks.lephora.server.lephoraserver.domain.model.Price;
import thoughtworks.lephora.server.lephoraserver.domain.model.ShippingAddress;

import java.math.BigDecimal;

import static thoughtworks.lephora.server.lephoraserver.domain.model.Price.PriceUnit.RMB;

public class OrderFixture {
    private String orderId = "1000043960001";
    private String commoditySku = "000001";
    private long quantity = 10L;
    private Price totalPrice = Price.of(new BigDecimal("45.8"), RMB);
    private ShippingAddress shippingAddress = new ShippingAddress(
            "Shanghai Thoughtworks",
            "Jincheng, Zhang",
            "15859936749"
    );
    private String customerId = "100000";

    public static OrderFixture buildOrder() {
        return new OrderFixture();
    }

    public OrderFixture withCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderFixture withCommoditySku(String commoditySku) {
        this.commoditySku = commoditySku;
        return this;
    }

    public Order build() {
        final var order = new Order(
                commoditySku,
                quantity,
                shippingAddress,
                customerId,
                customerId,
                totalPrice
        );
        order.attachId(orderId);
        return order;
    }

}
