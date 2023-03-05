package thoughtworks.lephora.server.lephoraserver.domain.service;

import org.junit.jupiter.api.Test;
import thoughtworks.lephora.server.lephoraserver.fixture.OrderFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    void should_return_order_id_when_calculation_order_id_success() {
        // given
        var customerId = "102000";
        var commoditySku = "002016";
        final var order = OrderFixture.buildOrder().withCustomerId(customerId).withCommoditySku(commoditySku).build();

        // when
        final var orderId = orderService.calculationOrderId(order);

        // then
        assertThat(orderId.length(), equalTo(12));
    }
}
