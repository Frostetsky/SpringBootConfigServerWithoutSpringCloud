package config.service.app.controller;

import config.service.app.dto.rs.ClientServiceConfigResponse;
import config.service.app.service.ClientServiceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client-service")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientServiceController {

    private final ClientServiceProperties clientServiceProperties;

    @GetMapping("/config")
    public ClientServiceConfigResponse getConfiguration() {
        return new ClientServiceConfigResponse(clientServiceProperties.getConfig(), clientServiceProperties.getValue());
    }
}
