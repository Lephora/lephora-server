package thoughtworks.lephora.server.lephoraserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table
@Entity
public class Commodity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String sku;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "commodity_sku")
    private Set<CommodityImage> images;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private OffsetDateTime lastModifiedAt;

    @Column(nullable = false)
    private String lastModifiedBy;

    @Embedded
    private CommodityPrice price;

    protected Commodity() {
    }

    public void attachSku(String sku) {
        this.sku = sku;
    }

    public Commodity(
            String title,
            String description,
            Set<CommodityImage> images,
            CommodityPrice price,
            String creatorId
    ) {
        this.title = title;
        this.description = description;
        this.images = images;
        this.price = price;
        this.createdBy = creatorId;
        this.lastModifiedBy = creatorId;
        final var now = OffsetDateTime.now();
        this.createdAt = now;
        this.lastModifiedAt = now;
    }

    public String getSku() {
        return sku;
    }

    public CommodityPrice getPrice() {
        return price;
    }
}
