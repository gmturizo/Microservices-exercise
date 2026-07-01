package co.com.bancolombia.flota_service.client;

import co.com.bancolombia.flota_service.dto.VehiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "SERVICE-VEHICULO")
public interface VehiculoClient {

    @GetMapping("/api/v1/autos")
    List<VehiculoDTO> obtenerVehiculos();
}
