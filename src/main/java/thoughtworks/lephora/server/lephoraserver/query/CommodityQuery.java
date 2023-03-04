package thoughtworks.lephora.server.lephoraserver.query;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thoughtworks.lephora.server.lephoraserver.query.view.CommodityDetailView;
import thoughtworks.lephora.server.lephoraserver.query.view.CommodityImageView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommodityQuery {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CommodityQuery(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CommodityDetailView queryCommodityDetailBySku(String sku) {
        final var querySql = """
                SELECT
                c.title,
                c.description,
                c.price,
                c.price_unit
                FROM commodity c
                WHERE c.sku = :sku;
                """;
        final var commodityDetailView = jdbcTemplate.queryForStream(querySql, new MapSqlParameterSource("sku", sku), this::commodityDetailViewMapper)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("not define"));

        final var commodityImageViews = queryCommodityImagesBySku(sku);
        return new CommodityDetailView(
                commodityDetailView.title(),
                commodityDetailView.description(),
                commodityImageViews,
                commodityDetailView.price(),
                commodityDetailView.priceUnit()
        );
    }

    private List<CommodityImageView> queryCommodityImagesBySku(String sku) {
        final var queryImage = """
                SELECT
                c.url,
                c.alternative_text
                FROM commodity_image c
                WHERE c.commodity_sku = :sku
                """;
        return jdbcTemplate.queryForStream(queryImage, new MapSqlParameterSource("sku", sku), this::commodityImageViewMapper).toList();
    }

    private CommodityDetailView commodityDetailViewMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        return new CommodityDetailView(
                resultSet.getString("title"),
                resultSet.getString("description"),
                null,
                resultSet.getBigDecimal("price"),
                resultSet.getString("price_unit")
        );
    }

    private CommodityImageView commodityImageViewMapper(ResultSet resultSet, int rowNumber) throws SQLException {
        return new CommodityImageView(
                resultSet.getString("url"),
                resultSet.getString("alternative_text")
        );
    }
}
