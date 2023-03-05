package thoughtworks.lephora.server.lephoraserver.rest.response;

import thoughtworks.lephora.server.lephoraserver.domain.application.result.OrderCreatedResult;
import thoughtworks.lephora.server.lephoraserver.domain.model.OrderStatus;

public record OrderCreatedResponse(
        String orderId,
        OrderStatus orderStatus
) {
    public static OrderCreatedResponse fromOrderCreatedResult(OrderCreatedResult orderCreatedResult) {
        return new OrderCreatedResponse(orderCreatedResult.orderId(), orderCreatedResult.orderStatus());
    }
}
