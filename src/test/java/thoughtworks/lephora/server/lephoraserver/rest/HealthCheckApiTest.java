package thoughtworks.lephora.server.lephoraserver.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthCheckApi.class)
class HealthCheckApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_healthy_when_health_check() throws Exception {
        mockMvc
                .perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo("healthy")));
    }
}
