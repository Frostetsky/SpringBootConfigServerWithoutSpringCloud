package client.service.app.controller;

import client.service.app.config.AppConfiguration;
import client.service.app.custorm.TestCustomBeanFromConfigurationClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AppConfiguration appConfiguration;
    private final TestCustomBeanFromConfigurationClass testCustomBeanFromConfigurationClass;

    @Autowired
    public AdminController(AppConfiguration appConfiguration, TestCustomBeanFromConfigurationClass testCustomBeanFromConfigurationClass) {
        this.appConfiguration = appConfiguration;
        this.testCustomBeanFromConfigurationClass = testCustomBeanFromConfigurationClass;
    }

    @GetMapping("/app-config")
    public Map<String, String> getConfiguration() {
        return appConfiguration.getConfiguration();
    }

    @GetMapping("/config-bean")
    public Integer getValueFromBean() {
        return testCustomBeanFromConfigurationClass.getValue();
    }

    @GetMapping("/get-value-sub-bean")
    public Integer getSubBeanValue() {
        return testCustomBeanFromConfigurationClass.getValue();
    }
}
