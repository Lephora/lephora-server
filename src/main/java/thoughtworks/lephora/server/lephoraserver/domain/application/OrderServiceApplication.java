package thoughtworks.lephora.server.lephoraserver.domain.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thoughtworks.lephora.server.lephoraserver.domain.application.command.CreateOrderCommand;
import thoughtworks.lephora.server.lephoraserver.domain.application.result.OrderCreatedResult;
import thoughtworks.lephora.server.lephoraserver.domain.factory.OrderFactory;
import thoughtworks.lephora.server.lephoraserver.domain.repository.OrderRepository;
import thoughtworks.lephora.server.lephoraserver.domain.service.OrderService;
import thoughtworks.lephora.server.lephoraserver.domain.specification.CommodityCanBeFoundSpecification;

@Service
@Transactional
public class OrderServiceApplication {

    private final CommodityCanBeFoundSpecification commodityCanBeFoundSpecification;
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderServiceApplication(
            CommodityCanBeFoundSpecification commodityCanBeFoundSpecification,
            OrderFactory orderFactory,
            OrderRepository orderRepository,
            OrderService orderService) {
        this.commodityCanBeFoundSpecification = commodityCanBeFoundSpecification;
        this.orderFactory = orderFactory;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    public OrderCreatedResult createOrder(CreateOrderCommand createOrderCommand) {
        commodityCanBeFoundSpecification.verify(createOrderCommand.commoditySku());
        final var order = orderFactory.create(createOrderCommand);
        order.attachId(orderService.calculationOrderId(order));
        final var savedOrder = orderRepository.save(order);
        return new OrderCreatedResult(
                savedOrder.getId(),
                savedOrder.getOrderStatus()
        );
    }
}
