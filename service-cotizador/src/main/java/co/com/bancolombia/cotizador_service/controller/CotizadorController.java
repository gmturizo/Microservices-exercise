package co.com.bancolombia.cotizador_service.controller;

import co.com.bancolombia.cotizador_service.service.CotizadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizadorController {

    private final CotizadorService cotizadorService;

    public CotizadorController(CotizadorService cotizadorService) {
        this.cotizadorService = cotizadorService;
    }

    @GetMapping("/calcular")
    public ResponseEntity<?> obtenerCotizacion(
            @RequestParam double distancia,
            @RequestParam String tipoVehiculo) {
        try {
            double costoTotal = cotizadorService.calcularCosto(distancia, tipoVehiculo);

            Map<String, Object> respuesta = Map.of(
                    "distanciaKm", distancia,
                    "tipoVehiculo", tipoVehiculo,
                    "costoTotal", costoTotal
            );

            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
