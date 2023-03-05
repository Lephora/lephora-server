package thoughtworks.lephora.server.lephoraserver.fixture;

import thoughtworks.lephora.server.lephoraserver.domain.model.Customer;

public class CustomerFixture {

    private String customerId = "000000";

    public static CustomerFixture buildCustomer() {
        return new CustomerFixture();
    }

    public CustomerFixture withId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Customer build() {
        return new Customer(customerId);
    }

}
