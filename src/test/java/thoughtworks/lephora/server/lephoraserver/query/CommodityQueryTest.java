package thoughtworks.lephora.server.lephoraserver.query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import thoughtworks.lephora.server.lephoraserver.core.DataSourceConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(classes = DataSourceConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "thoughtworks.lephora.server.lephoraserver.query")
class CommodityQueryTest {

    @Autowired
    private CommodityQuery commodityQuery;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void should_query_commodity_detail_by_sku_success() {
        // given
        final var title = "foo";
        final var description = "bar";
        final var price = "52.05";
        final var priceUnit = "RMB";
        final var url = "https://lephora.com";
        final var alternativeText = "zuu";

        // when
        final var sku = saveCommodity(title, description, price, priceUnit);
        saveCommodityImages(sku, url, alternativeText);
        saveCommodityImages(sku, url, alternativeText);
        final var commodityDetailViewResult = commodityQuery.queryCommodityDetailBySku(sku);

        // then
        assertThat(commodityDetailViewResult, notNullValue());
        assertThat(commodityDetailViewResult.title(), equalTo(title));
        assertThat(commodityDetailViewResult.description(), equalTo(description));
        assertThat(commodityDetailViewResult.price(), equalTo(new BigDecimal(price)));
        assertThat(commodityDetailViewResult.images(), hasSize(2));
        assertThat(commodityDetailViewResult.images().get(0).url(), equalTo(url));
        assertThat(commodityDetailViewResult.images().get(1).url(), equalTo(url));
        assertThat(commodityDetailViewResult.images().get(0).alternativeText(), equalTo(alternativeText));
        assertThat(commodityDetailViewResult.images().get(1).alternativeText(), equalTo(alternativeText));
    }

    private String saveCommodity(String title, String description, String price, String priceUnit) {
        var now = Timestamp.from(OffsetDateTime.now().toInstant());
        var insertSql = """
                INSERT INTO commodity (
                title,
                description,
                price,
                price_unit,
                created_at,
                last_modified_at,
                created_by,
                last_modified_by)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            final var preparedStatement = con.prepareStatement(insertSql, new String[]{"sku"});
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setBigDecimal(3, new BigDecimal(price));
            preparedStatement.setString(4, priceUnit);
            preparedStatement.setTimestamp(5, now);
            preparedStatement.setTimestamp(6, now);
            preparedStatement.setString(7, "Tester");
            preparedStatement.setString(8, "Tester");
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKeyAs(String.class);
    }

    public Long saveCommodityImages(String commoditySku, String url, String alternativeText) {
        var insertSql = """
                INSERT INTO commodity_image (
                alternative_text,
                url,
                commodity_sku)
                VALUES (?,?,?);
                """;

        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            final var preparedStatement = con.prepareStatement(insertSql, new String[]{"id"});
            preparedStatement.setString(1, alternativeText);
            preparedStatement.setString(2, url);
            preparedStatement.setString(3, commoditySku);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }
}




