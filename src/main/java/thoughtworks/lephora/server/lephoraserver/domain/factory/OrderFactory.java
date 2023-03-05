package thoughtworks.lephora.server.lephoraserver.domain.factory;

import org.springframework.stereotype.Component;
import thoughtworks.lephora.server.lephoraserver.domain.application.command.CreateOrderCommand;
import thoughtworks.lephora.server.lephoraserver.domain.model.Order;

@Component
public class OrderFactory {

    public Order create(CreateOrderCommand createOrderCommand) {
        return new Order(
                createOrderCommand.commoditySku(),
                createOrderCommand.quantity(),
                createOrderCommand.address(),
                createOrderCommand.customerId(),
                createOrderCommand.customerId()
        );
    }
}
