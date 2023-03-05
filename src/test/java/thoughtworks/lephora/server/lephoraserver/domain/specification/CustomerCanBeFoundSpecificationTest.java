package thoughtworks.lephora.server.lephoraserver.domain.specification;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import thoughtworks.lephora.server.lephoraserver.domain.exception.CustomerNotFoundException;
import thoughtworks.lephora.server.lephoraserver.domain.repository.CustomerRepository;
import thoughtworks.lephora.server.lephoraserver.fixture.CustomerFixture;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class CustomerCanBeFoundSpecificationTest {

    private final CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
    private final CustomerCanBeFoundSpecification customerCanBeFoundSpecification
            = new CustomerCanBeFoundSpecification(customerRepository);

    @Test
    void should_throw_exception_when_cannot_find_customer_by_id() {
        // given
        var customerId = "000001";
        given(customerRepository.findById(customerId)).willReturn(Optional.empty());

        // when then
        assertThrows(CustomerNotFoundException.class, () -> customerCanBeFoundSpecification.verify(customerId));
    }

    @Test
    void should_not_throw_exception_when_find_customer_by_id_success() {
        // given
        var customerId = "000001";
        given(customerRepository.findById(customerId)).willReturn(Optional.of(CustomerFixture.buildCustomer().withId(customerId).build()));

        // when then
        assertDoesNotThrow(() -> customerCanBeFoundSpecification.verify(customerId));
    }
}
