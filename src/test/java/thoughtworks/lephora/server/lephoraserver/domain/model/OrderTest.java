package thoughtworks.lephora.server.lephoraserver.domain.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import thoughtworks.lephora.server.lephoraserver.fixture.OrderFixture;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static thoughtworks.lephora.server.lephoraserver.domain.model.Price.PriceUnit.RMB;

class OrderTest {

    private static Stream<Arguments> provideTestCase() {
        return Stream.of(
                Arguments.of(12.33, 20L, RMB, 246.60, RMB),
                Arguments.of(0, 20L, RMB, 0.00, RMB),
                Arguments.of(99999.99, 0, RMB, 0.00, RMB),
                Arguments.of(123.00, 9, RMB, 1107.00, RMB)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCase")
    void should_calculation_total_price_correct(double unitPrice, long quantity, Price.PriceUnit unit, double totalPrice, Price.PriceUnit afterUnit) {
        // given
        final var order = OrderFixture.buildOrder().withQuantity(quantity).build();
        final var price = Price.of(BigDecimal.valueOf(unitPrice), unit);

        // when
        order.calculationTotalPrice(price);

        // then
        assertThat(order.getTotalPrice().getAmount(), equalTo(BigDecimal.valueOf(totalPrice).setScale(2, RoundingMode.HALF_UP)));
        assertThat(order.getTotalPrice().getUnit(), equalTo(afterUnit));
    }
}
