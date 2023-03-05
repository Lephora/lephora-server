package thoughtworks.lephora.server.lephoraserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

import static jakarta.persistence.EnumType.STRING;

@Embeddable
public class Price {
    @Column(name = "price")
    private BigDecimal amount;

    @Enumerated(STRING)
    @Column(name = "price_unit")
    private PriceUnit unit;

    protected Price() {
    }

    public enum PriceUnit {
        RMB, DOLLAR
    }

    private Price(BigDecimal amount, PriceUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public static Price of(BigDecimal amount, PriceUnit unit) {
        return new Price(amount, unit);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PriceUnit getUnit() {
        return unit;
    }
}
