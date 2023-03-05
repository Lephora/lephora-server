package thoughtworks.lephora.server.lephoraserver.domain.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thoughtworks.lephora.server.lephoraserver.domain.application.command.CreateOrderCommand;
import thoughtworks.lephora.server.lephoraserver.domain.application.result.OrderCreatedResult;
import thoughtworks.lephora.server.lephoraserver.domain.factory.OrderFactory;
import thoughtworks.lephora.server.lephoraserver.domain.repository.OrderRepository;
import thoughtworks.lephora.server.lephoraserver.domain.service.CommodityService;
import thoughtworks.lephora.server.lephoraserver.domain.specification.CommodityCanBeFoundSpecification;
import thoughtworks.lephora.server.lephoraserver.domain.specification.CustomerCanBeFoundSpecification;

@Service
@Transactional
public class OrderServiceApplication {

    private final CommodityCanBeFoundSpecification commodityCanBeFoundSpecification;
    private final CustomerCanBeFoundSpecification customerCanBeFoundSpecification;
    private final CommodityService commodityService;
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;

    public OrderServiceApplication(
            CommodityCanBeFoundSpecification commodityCanBeFoundSpecification,
            CustomerCanBeFoundSpecification customerCanBeFoundSpecification,
            OrderFactory orderFactory,
            OrderRepository orderRepository,
            CommodityService commodityService
    ) {
        this.commodityCanBeFoundSpecification = commodityCanBeFoundSpecification;
        this.customerCanBeFoundSpecification = customerCanBeFoundSpecification;
        this.orderFactory = orderFactory;
        this.orderRepository = orderRepository;
        this.commodityService = commodityService;
    }

    public OrderCreatedResult createOrder(CreateOrderCommand createOrderCommand) {
        commodityCanBeFoundSpecification.verify(createOrderCommand.commoditySku());
        customerCanBeFoundSpecification.verify(createOrderCommand.customerId());
        final var unitPrice = commodityService.queryUnitPriceBySku(createOrderCommand.commoditySku());
        final var order = orderFactory.create(createOrderCommand);
        order.calculationTotalPrice(unitPrice);
        final var savedOrder = orderRepository.save(order);
        return new OrderCreatedResult(
                savedOrder.getId(),
                savedOrder.getOrderStatus()
        );
    }
}
