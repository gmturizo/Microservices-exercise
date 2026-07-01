package co.com.bancolombia.flota_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "SERVICE-COTIZADOR")
public interface CotizadorClient {

    @GetMapping("/api/cotizaciones/calcular")
    Map<String, Object> calcularCosto(
            @RequestParam("tipoVehiculo") String tipoVehiculo,
            @RequestParam("distancia") double distancia
    );
}
