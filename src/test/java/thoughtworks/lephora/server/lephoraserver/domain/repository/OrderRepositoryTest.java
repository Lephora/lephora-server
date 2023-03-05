package thoughtworks.lephora.server.lephoraserver.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import thoughtworks.lephora.server.lephoraserver.core.DataSourceConfiguration;
import thoughtworks.lephora.server.lephoraserver.fixture.OrderFixture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Transactional
@SpringBootTest(classes = DataSourceConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void should_return_order_id_when_save_order_success() {
        // given
        final var order = OrderFixture.buildOrder().build();
        // when
        final var savedOrder = orderRepository.saveAndFlush(order);

        //then
        assertThat(savedOrder.getId(), notNullValue());
    }

    @Test
    void should_return_order_when_find_order_success() {
        // given
        final var order = OrderFixture.buildOrder().build();
        // when
        final var savedOrder = orderRepository.saveAndFlush(order);
        final var findOrder = orderRepository.findById(savedOrder.getId()).orElseThrow();

        //then
        assertThat(findOrder.getId(), equalTo(order.getId()));
        assertThat(savedOrder.getOrderStatus(), equalTo(order.getOrderStatus()));
        assertThat(savedOrder.getCreatedAt(), equalTo(order.getCreatedAt()));
        assertThat(savedOrder.getCreatedBy(), equalTo(order.getCreatedBy()));
        assertThat(savedOrder.getCustomerId(), equalTo(order.getCustomerId()));
        assertThat(savedOrder.getQuantity(), equalTo(order.getQuantity()));
        assertThat(savedOrder.getCommoditySku(), equalTo(order.getCommoditySku()));
    }
}
