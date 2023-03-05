package thoughtworks.lephora.server.lephoraserver.domain.factory;

import org.springframework.stereotype.Component;
import thoughtworks.lephora.server.lephoraserver.domain.application.command.CreateOrderCommand;
import thoughtworks.lephora.server.lephoraserver.domain.model.Order;
import thoughtworks.lephora.server.lephoraserver.domain.model.Price;
import thoughtworks.lephora.server.lephoraserver.domain.repository.CommodityRepository;
import thoughtworks.lephora.server.lephoraserver.query.exception.CommodityNotFoundException;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.RoundingMode.HALF_DOWN;
import static thoughtworks.lephora.server.lephoraserver.domain.model.Price.PriceUnit.RMB;

@Component
public class OrderFactory {

    private final CommodityRepository commodityRepository;

    public OrderFactory(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    public Order create(CreateOrderCommand createOrderCommand) {
        final var price = commodityRepository.findPriceBySku(createOrderCommand.commoditySku())
                        .orElseThrow(() -> new CommodityNotFoundException("sku price and unit %s not found".formatted(createOrderCommand.commoditySku())));
        return new Order(
                createOrderCommand.commoditySku(),
                createOrderCommand.quantity(),
                createOrderCommand.address(),
                createOrderCommand.customerId(),
                createOrderCommand.customerId(),
                Price.of(price.getAmount()
                                .multiply(BigDecimal.valueOf(createOrderCommand.quantity()), new MathContext(2, HALF_DOWN)), RMB)
        );
    }
}
