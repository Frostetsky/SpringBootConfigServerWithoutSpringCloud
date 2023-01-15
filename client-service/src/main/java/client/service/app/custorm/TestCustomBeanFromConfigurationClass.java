package client.service.app.custorm;

import client.service.app.config.AppConfiguration;
import client.service.app.config.UpdatedCommandBean;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class TestCustomBeanFromConfigurationClass implements UpdatedCommandBean {

    private AppConfiguration appConfiguration;
    private int value;
    private TestComponent testComponent;

    @Autowired
    public TestCustomBeanFromConfigurationClass(TestComponent testComponent) {
        this.testComponent = testComponent;
    }

    public int getValue() {
        return testComponent.getValue();
    }

    public void updateConfigElement(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
        this.value = appConfiguration.getValue();
        testComponent.setValue(value);
    }
}
