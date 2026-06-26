package co.com.bancolombia.flota_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${servicio.vehiculo.url}")
    private String vehiculoUrl;

    @Value("${servicio.cotizador.url}")
    private String cotizadorUrl;

    @Bean
    public WebClient vehiculoWebClient() {
        return WebClient.builder()
                .baseUrl(vehiculoUrl + "/api/v1/autos")
                .build();
    }

    @Bean
    public WebClient cotizadorWebClient() {
        return WebClient.builder()
                .baseUrl(cotizadorUrl + "/api/cotizaciones")
                .build();
    }
}
