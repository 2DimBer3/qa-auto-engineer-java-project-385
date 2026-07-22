package hexlet.code.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigManager {
    private static TestConfig config;

    public static TestConfig getConfig() {
        if (config == null) {
            config = ConfigCache.getOrCreate(TestConfig.class);
        }
        return config;
    }
}
