package thoughtworks.lephora.server.lephoraserver.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import thoughtworks.lephora.server.lephoraserver.core.cache.RedisCache;
import thoughtworks.lephora.server.lephoraserver.domain.application.OrderServiceApplication;
import thoughtworks.lephora.server.lephoraserver.rest.request.CreateOrderRequest;
import thoughtworks.lephora.server.lephoraserver.rest.response.OrderCreatedResponse;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/order")
public class OrderManagementApi {

    private final OrderServiceApplication orderServiceApplication;

    public OrderManagementApi(OrderServiceApplication orderServiceApplication) {
        this.orderServiceApplication = orderServiceApplication;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @RedisCache
    public OrderCreatedResponse createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return OrderCreatedResponse
                .fromOrderCreatedResult(orderServiceApplication.createOrder(createOrderRequest.toCommand()));
    }
}
