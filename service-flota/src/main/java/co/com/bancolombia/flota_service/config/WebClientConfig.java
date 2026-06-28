package co.com.bancolombia.flota_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient vehiculoWebClient(WebClient.Builder builder) {
        return builder.clone().baseUrl("http://SERVICE-VEHICULO/api/v1/autos").build();
    }

    @Bean
    public WebClient cotizadorWebClient(WebClient.Builder builder) {
        return builder.clone().baseUrl("http://SERVICE-COTIZADOR/api/cotizaciones").build();
    }
}

