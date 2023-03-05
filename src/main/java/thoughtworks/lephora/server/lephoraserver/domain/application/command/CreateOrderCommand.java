package thoughtworks.lephora.server.lephoraserver.domain.application.command;

import thoughtworks.lephora.server.lephoraserver.domain.model.ShippingAddress;

public record CreateOrderCommand(
        String customerId,
        String commoditySku,
        long quantity,
        ShippingAddress address
) {
}
