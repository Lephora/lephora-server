package thoughtworks.lephora.server.lephoraserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

import static jakarta.persistence.EnumType.STRING;

@Embeddable
public class CommodityPrice {
    @Column(name = "price")
    private BigDecimal amount;

    @Enumerated(STRING)
    @Column(name = "price_unit")
    private PriceUnit unit;

    protected CommodityPrice() {
    }

    public enum PriceUnit {
        RMB, DOLLAR
    }

    public CommodityPrice(BigDecimal account, PriceUnit unit) {
        this.amount = account;
        this.unit = unit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PriceUnit getUnit() {
        return unit;
    }
}
