package hexlet.code.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config/${env}.properties",
        "classpath:config/local.properties"})
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
}
