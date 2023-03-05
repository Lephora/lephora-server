package thoughtworks.lephora.server.lephoraserver.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import thoughtworks.lephora.server.lephoraserver.domain.application.OrderServiceApplication;
import thoughtworks.lephora.server.lephoraserver.domain.application.result.OrderCreatedResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static thoughtworks.lephora.server.lephoraserver.domain.model.OrderStatus.WAITING_FOR_PAY;

@WebMvcTest(OrderManagementApi.class)
class OrderManagementApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceApplication orderServiceApplication;

    @Test
    void should_return_created_when_create_order_success() throws Exception {
        given(orderServiceApplication.createOrder(any())).willReturn(new OrderCreatedResult("123456789058", WAITING_FOR_PAY));
        mockMvc.perform(post("/order")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(new ClassPathResource("request/create-order-success.json").getInputStream().readAllBytes()))
                .andExpect(status().isCreated());
    }

}
