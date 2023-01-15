package client.service.app;

import client.service.app.config.AppConfiguration;
import client.service.app.config.UpdatedCommandBean;
import client.service.app.dto.ClientServiceConfigResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
@Order(Integer.MIN_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientServiceRunner implements ApplicationRunner {

    private final RestTemplate restTemplate;

    private final AppConfiguration appConfiguration;

    private final List<UpdatedCommandBean> updatedCommandBeanList;

    @Override
    public void run(ApplicationArguments args) {
        ClientServiceConfigResponse config;
        try {
            config = restTemplate.exchange(
                    "http://localhost:9091/config-service/client-service/config",
                    HttpMethod.GET,
                    null, new ParameterizedTypeReference<ClientServiceConfigResponse>() {
                    }).getBody();
            appConfiguration.setConfiguration(config.getConfigMap());
            appConfiguration.setValue(config.getValue());
            updatedCommandBeanList.forEach(updatedCommandBean -> {
                log.info("Создаем конфиги внутри бинов. Имя бина- {}.", updatedCommandBean.getClass().getCanonicalName());
                updatedCommandBean.updateConfigElement(appConfiguration);
            });
            log.info("Создана конфигурация приложения.");
        } catch (Exception e) {
            log.error("Сервис конфигураций не запущен.");
            throw new RuntimeException();
        }
    }
}
