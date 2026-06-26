package co.com.bancolombia.flota_service.controller;


import co.com.bancolombia.flota_service.dto.AutoDTO;
import co.com.bancolombia.flota_service.dto.CotizacionDTO;
import co.com.bancolombia.flota_service.dto.ResumenFlotaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flota")
public class FlotaOrquestadorController {

    private final WebClient vehiculoWebClient;
    private final WebClient cotizadorWebClient;

    public FlotaOrquestadorController(WebClient vehiculoWebClient, WebClient cotizadorWebClient) {
        this.vehiculoWebClient = vehiculoWebClient;
        this.cotizadorWebClient = cotizadorWebClient;
    }

    @GetMapping("/resumen-completo")
    public Mono<ResponseEntity<List<ResumenFlotaDTO>>> obtenerResumenFlota() {
        return vehiculoWebClient.get()
                .retrieve()
                .bodyToFlux(AutoDTO.class)
                .flatMap(auto -> {
                    String tipoValido = determinarTipoParaCotizador(auto);

                    return cotizadorWebClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .path("/calcular")
                                    .queryParam("distancia", 150.0)
                                    .queryParam("tipoVehiculo", tipoValido)
                                    .build())
                            .retrieve()
                            .bodyToMono(CotizacionDTO.class)
                            .map(cotizacion -> new ResumenFlotaDTO(auto, cotizacion));
                })
                .collectList()
                .map(ResponseEntity::ok);
    }

    private String determinarTipoParaCotizador(AutoDTO auto) {
        if (auto.modelo() != null && auto.modelo().toLowerCase().contains("camion")) {
            return "camion";
        }
        return "carro";
    }


}
