package thoughtworks.lephora.server.lephoraserver.rest.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import thoughtworks.lephora.server.lephoraserver.domain.application.command.CreateOrderCommand;
import thoughtworks.lephora.server.lephoraserver.domain.model.ShippingAddress;

import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.ILLEGAL_COMMODITY_SKU;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.ILLEGAL_CUSTOMER_ID;

public record CreateOrderRequest(
        @NotNull
        @Pattern(regexp = "^\\d{6}$", message = ILLEGAL_CUSTOMER_ID)
        String customerId,
        @NotNull
        @Pattern(regexp = "^\\d{6}$", message = ILLEGAL_COMMODITY_SKU)
        String commoditySku,
        @NotNull
        long quantity,
        @NotNull
        String address,
        @NotNull
        String fullName,
        @NotNull
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
