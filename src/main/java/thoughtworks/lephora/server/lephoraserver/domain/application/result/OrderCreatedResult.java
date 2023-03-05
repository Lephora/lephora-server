package thoughtworks.lephora.server.lephoraserver.domain.application.result;

import thoughtworks.lephora.server.lephoraserver.domain.model.OrderStatus;

public record OrderCreatedResult(
        String orderId,
        OrderStatus orderStatus
) {
}
