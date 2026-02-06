package dev.loopstudio.countryvoteapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfig {

    @Value("${countryapi.countriesBaseUrl}")
    private String countriesApiBaseUrl;

    /**
     * Countries api rest client bean.
     *
     * @return the rest client
     */
    @Bean
    public RestClient countriesApiRestClient() {
        return RestClient.builder()
                .baseUrl(countriesApiBaseUrl)
                .build();
    }
}
