package client.service.app.client;

import client.service.app.config.AppConfiguration;
import client.service.app.config.UpdatedCommandBean;
import client.service.app.dto.ClientServiceConfigResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ConfigServiceTemplate {

    private final RestTemplate restTemplate;
    private final AppConfiguration appConfiguration;
    private final List<UpdatedCommandBean> updatedCommandBeanList;

    @Autowired
    public ConfigServiceTemplate(@Qualifier("basicRestTemplate") RestTemplate restTemplate,
                                 List<UpdatedCommandBean> updatedCommandBeanList,
                                 AppConfiguration appConfiguration) {
        this.restTemplate = restTemplate;
        this.appConfiguration = appConfiguration;
        this.updatedCommandBeanList = updatedCommandBeanList;
    }

    /**
     * можно переделать на Quartz для load-balanced в режиме многоподовости.
     */
    @Scheduled(initialDelay = 10_000, fixedRate = 15_000)
    public void getConfiguration() {
        ClientServiceConfigResponse config;
        try {
            config = restTemplate.exchange(
                    "http://localhost:9091/config-service/client-service/config",
                    HttpMethod.GET,
                    null, new ParameterizedTypeReference<ClientServiceConfigResponse>() {
                    }).getBody();
        } catch (Exception e) {
            log.error("Сервис конфигураций недоступен.");
            log.info("Текущие конфигурации в сервисе - {}, {}", appConfiguration.getConfiguration(), appConfiguration.getValue());
            return;
        }
        log.info("Получили ответ с конфиг-сервиса. Данные конфигурации - {}, {}", config.getConfigMap(), config.getValue());
        appConfiguration.setConfiguration(config.getConfigMap());
        appConfiguration.setValue(config.getValue());
        updateConfigurationsBeans(updatedCommandBeanList, appConfiguration);
    }

    public void updateConfigurationsBeans(List<UpdatedCommandBean> updatedCommandBeanList, AppConfiguration appConfiguration) {
        updatedCommandBeanList.forEach(updatedCommandBean -> {
            log.info("Обновляем бины из конфигураций. Имя бина- {}.", updatedCommandBean.getClass().getCanonicalName());
            updatedCommandBean.updateConfigElement(appConfiguration);
        });
        log.info("Конфиги на сервере актуализированы.");
    }
}
