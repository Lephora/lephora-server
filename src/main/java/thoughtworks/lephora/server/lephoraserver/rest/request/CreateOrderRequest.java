package thoughtworks.lephora.server.lephoraserver.rest.request;

import thoughtworks.lephora.server.lephoraserver.domain.application.command.CreateOrderCommand;
import thoughtworks.lephora.server.lephoraserver.domain.model.ShippingAddress;

public record CreateOrderRequest(
        String customerId,
        String commoditySku,
        long quantity,
        String address,
        String fullName,
        String phoneNumber
) {
    public CreateOrderCommand toCommand() {
        return new CreateOrderCommand(
                customerId,
                commoditySku,
                quantity,
                new ShippingAddress(address, fullName, phoneNumber));
    }
}
