package thoughtworks.lephora.server.lephoraserver.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import thoughtworks.lephora.server.lephoraserver.domain.application.OrderServiceApplication;
import thoughtworks.lephora.server.lephoraserver.domain.application.result.OrderCreatedResult;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static thoughtworks.lephora.server.lephoraserver.domain.model.OrderStatus.WAITING_FOR_PAY;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.ILLEGAL_COMMODITY_SKU;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.ILLEGAL_CUSTOMER_ID;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_COMMODITY_QUANTITY;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_COMMODITY_SKU;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_CUSTOMER_ID;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_DELIVERY_ADDRESS;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_FULL_NAME;
import static thoughtworks.lephora.server.lephoraserver.rest.constant.ValidationErrorCode.NIL_PHONE_NUMBER;

@WebMvcTest(OrderManagementApi.class)
class OrderManagementApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceApplication orderServiceApplication;

    private static Stream<Arguments> provideTestCase() {
        return Stream.of(
                Arguments.of("request/fail-to-create-order-illegal-customer-id.json", ILLEGAL_CUSTOMER_ID),
                Arguments.of("request/fail-to-create-order-illegal-commodity-sku.json", ILLEGAL_COMMODITY_SKU),
                Arguments.of("request/fail-to-create-order-nil-commodity-sku.json", NIL_COMMODITY_SKU),
                Arguments.of("request/fail-to-create-order-nil-customer-id.json", NIL_CUSTOMER_ID),
                Arguments.of("request/fail-to-create-order-nil-commodity-quantity.json", NIL_COMMODITY_QUANTITY),
                Arguments.of("request/fail-to-create-order-nil-address.json", NIL_DELIVERY_ADDRESS),
                Arguments.of("request/fail-to-create-order-nil-full-name.json", NIL_FULL_NAME),
                Arguments.of("request/fail-to-create-order-nil-phone-number.json", NIL_PHONE_NUMBER)
        );
    }

    @Test
    void should_return_created_when_create_order_success() throws Exception {
        given(orderServiceApplication.createOrder(any())).willReturn(new OrderCreatedResult("123456789058", WAITING_FOR_PAY));
        mockMvc.perform(post("/order")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(new ClassPathResource("request/create-order-success.json").getInputStream().readAllBytes()))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @MethodSource("provideTestCase")
    void should_get_error_when_create_failed(String requestFilePath, String errorCode) throws Exception {
        mockMvc.perform(post("/order")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(new ClassPathResource(requestFilePath).getInputStream().readAllBytes()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(errorCode)));
    }
}
