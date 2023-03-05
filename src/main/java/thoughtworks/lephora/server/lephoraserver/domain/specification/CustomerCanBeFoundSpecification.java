package thoughtworks.lephora.server.lephoraserver.domain.specification;

import org.springframework.stereotype.Component;
import thoughtworks.lephora.server.lephoraserver.domain.exception.CustomerNotFoundException;
import thoughtworks.lephora.server.lephoraserver.domain.repository.CustomerRepository;

@Component
public class CustomerCanBeFoundSpecification {
    public final CustomerRepository customerRepository;

    public CustomerCanBeFoundSpecification(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void verify(String customerId) {
        if (customerRepository.findById(customerId).isEmpty()) {
            throw new CustomerNotFoundException("customer %s not found".formatted(customerId));
        }
    }
}
