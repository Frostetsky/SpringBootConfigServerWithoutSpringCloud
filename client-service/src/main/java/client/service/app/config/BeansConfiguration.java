package client.service.app.config;

import client.service.app.custorm.TestComponent;
import client.service.app.custorm.TestCustomBeanFromConfigurationClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BeansConfiguration {

    @Bean
    @Primary
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(12);
    }

    @Bean("basicRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public TestCustomBeanFromConfigurationClass testCustomBean(TestComponent testComponent) {
        return new TestCustomBeanFromConfigurationClass(testComponent);
    }
}
