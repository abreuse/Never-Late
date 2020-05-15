package fr.breuse.never_late.remote.sncf.client;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class SNCFClientConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${sncf.client-id}") String clientId) {
        return new BasicAuthRequestInterceptor(clientId, "");
    }
}
