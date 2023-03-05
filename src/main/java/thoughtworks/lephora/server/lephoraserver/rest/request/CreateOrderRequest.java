package thoughtworks.lephora.server.lephoraserver.rest.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import thoughtworks.lephora.server.lephoraserver.domain.application.command.CreateOrderCommand;
import thoughtworks.lephora.server.lephoraserver.domain.model.ShippingAddress;

import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.ILLEGAL_COMMODITY_SKU;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.ILLEGAL_CUSTOMER_ID;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_COMMODITY_QUANTITY;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_COMMODITY_SKU;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_CUSTOMER_ID;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_DELIVERY_ADDRESS;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_FULL_NAME;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_PHONE_NUMBER;

public record CreateOrderRequest(
        @NotNull(message = NIL_CUSTOMER_ID)
        @Pattern(regexp = "^\\d{6}$", message = ILLEGAL_CUSTOMER_ID)
        String customerId,
        @NotNull(message = NIL_COMMODITY_SKU)
        @Pattern(regexp = "^\\d{6}$", message = ILLEGAL_COMMODITY_SKU)
        String commoditySku,
        @NotNull(message = NIL_COMMODITY_QUANTITY)
        Long quantity,
        @NotNull(message = NIL_DELIVERY_ADDRESS)
        String address,
        @NotNull(message = NIL_FULL_NAME)
        String fullName,
        @NotNull(message = NIL_PHONE_NUMBER)
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
