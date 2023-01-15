package client.service.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component("appConfiguration")
public class AppConfiguration {


    private Map<String, String> configuration;

    private int value;
}
