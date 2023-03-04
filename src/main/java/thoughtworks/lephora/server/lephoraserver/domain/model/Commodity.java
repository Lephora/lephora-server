package thoughtworks.lephora.server.lephoraserver.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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

    @Embedded
    private CommodityPrice price;

    protected Commodity() {
    }
}
