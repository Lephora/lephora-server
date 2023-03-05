package thoughtworks.lephora.server.lephoraserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShippingAddress {
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    protected ShippingAddress() {
    }

    public ShippingAddress(String address, String fullName, String phoneNumber) {
        this.address = address;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
}
