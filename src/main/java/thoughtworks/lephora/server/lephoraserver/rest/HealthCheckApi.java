package thoughtworks.lephora.server.lephoraserver.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthCheckApi {

    @GetMapping("/health")
    public String healthCheck() {
        return "healthy";
    }
}
