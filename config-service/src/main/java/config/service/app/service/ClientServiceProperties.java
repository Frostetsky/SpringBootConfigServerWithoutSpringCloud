package config.service.app.service;

import config.service.app.config.YamlPropertySourceFactory;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "app")
@PropertySource(value = "classpath:application-CLIENT-SERVICE.yml", factory = YamlPropertySourceFactory.class)
public class ClientServiceProperties {

    private Map<String, String> config;

    private int value;
}
