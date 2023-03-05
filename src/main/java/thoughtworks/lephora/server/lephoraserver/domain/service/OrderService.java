package thoughtworks.lephora.server.lephoraserver.domain.service;

import org.springframework.stereotype.Service;
import thoughtworks.lephora.server.lephoraserver.domain.model.Order;

import java.util.Random;

@Service
public class OrderService {

    private final Random random = new Random();

    public String calculationOrderId(Order order) {
        final var customerId = order.getCustomerId();
        final var commoditySku = order.getCommoditySku();
        StringBuilder index = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            index.append(random.nextInt(10));
        }
        return "%s%s%s%s%s".formatted(
                customerId.substring(0, 2),
                commoditySku.substring(0, 2),
                index,
                commoditySku.substring(3, 5),
                customerId.substring(3, 5));
    }
}
