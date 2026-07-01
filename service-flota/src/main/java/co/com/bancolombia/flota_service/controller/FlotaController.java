package co.com.bancolombia.flota_service.controller;

import co.com.bancolombia.flota_service.client.CotizadorClient;
import co.com.bancolombia.flota_service.client.VehiculoClient;
import co.com.bancolombia.flota_service.dto.VehiculoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flotas")
public class FlotaController {

    @Autowired
    private CotizadorClient cotizadorClient;

    @Autowired
    private VehiculoClient vehiculoClient;

    @GetMapping("/mas-economico")
    public String cotizarMejorViaje(@RequestParam double km) {
        List<VehiculoDTO> vehiculos = vehiculoClient.obtenerVehiculos();

        if (vehiculos.isEmpty()) {
            return "Error: No hay vehículos registrados en la base de datos.";
        }

        VehiculoDTO primerVehiculo = vehiculos.get(0);

        Map<String, Object> respuestaCotizador = cotizadorClient.calcularCosto("carro", km);

        Object costo = respuestaCotizador.get("costoTotal");

        return "El auto " + primerVehiculo.getMarca() + " " + primerVehiculo.getModelo() + " gana con costo: " + costo;
    }
}
