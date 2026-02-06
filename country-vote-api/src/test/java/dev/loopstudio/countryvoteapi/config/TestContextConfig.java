package dev.loopstudio.countryvoteapi.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration
@EnableCaching
@ExtendWith(SpringExtension.class)
public class TestContextConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("countries");
    }
}
