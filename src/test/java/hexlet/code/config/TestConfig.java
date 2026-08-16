package hexlet.code.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config/${env}.properties", "classpath:config/local.properties"})
public interface TestConfig extends Config {

    String baseUrl();

    String userLogin();

    String userPassword();

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("false")
    boolean headless();

    @DefaultValue("10")
    int defaultTimeout();

    @DefaultValue("200")
    int defaultSleep();

    @DefaultValue("/users/create")
    String userCreateEndpoint();

    @DefaultValue("/users/{int}")
    String userEditEndpoint();

    @DefaultValue("/task_statuses/create")
    String statusCreateEndpoint();

    @DefaultValue("/labels/create")
    String labelCreateEndpoint();

    @DefaultValue("/tasks/create")
    String taskCreateEndpoint();

    @DefaultValue("/tasks/{int}")
    String taskEditEndpoint();
}
