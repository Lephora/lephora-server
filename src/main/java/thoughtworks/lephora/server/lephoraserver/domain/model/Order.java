package thoughtworks.lephora.server.lephoraserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

import static jakarta.persistence.EnumType.STRING;
import static java.time.OffsetDateTime.now;
import static thoughtworks.lephora.server.lephoraserver.domain.model.OrderStatus.WAITING_FOR_PAY;

@Entity
@Table(name = "lephora_order")
public class Order {

    @Id
    private String id;

    @Column(nullable = false)
    private String commoditySku;

    @Column(nullable = false)
    private long quantity;

    @Embedded
    private Price totalPrice;

    @Column(nullable = false)
    @Enumerated(STRING)
    private OrderStatus orderStatus;

    @Embedded
    private ShippingAddress shippingAddress;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private OffsetDateTime lastModifiedAt;

    @Column(nullable = false)
    private String lastModifiedBy;

    protected Order() {
    }

    public Order(
            String orderId,
            String commoditySku,
            long quantity,
            ShippingAddress shippingAddress,
            String customerId,
            String creatorId,
            Price totalPrice
    ) {
        this.id = orderId;
        this.commoditySku = commoditySku;
        this.quantity = quantity;
        this.shippingAddress = shippingAddress;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.orderStatus = WAITING_FOR_PAY;
        this.createdBy = creatorId;
        this.lastModifiedBy = creatorId;
        final var now = now();
        this.createdAt = now;
        this.lastModifiedAt = now;
    }

    public String getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getCommoditySku() {
        return commoditySku;
    }

    public long getQuantity() {
        return quantity;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OffsetDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
}

