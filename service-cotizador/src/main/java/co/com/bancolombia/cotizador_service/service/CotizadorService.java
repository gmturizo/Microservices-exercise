package co.com.bancolombia.cotizador_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CotizadorService {

    @Value("${tarifa.moto}")
    private double tarifaMoto;

    @Value("${tarifa.carro}")
    private double tarifaCarro;

    @Value("${tarifa.camion}")
    private double tarifaCamion;

    public double calcularCosto(double distancia, String tipoVehiculo) {
        if (distancia < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa");
        }

        double tarifaPorKm = switch (tipoVehiculo.toLowerCase()) {
            case "moto" -> tarifaMoto;
            case "carro" -> tarifaCarro;
            case "camion", "camión" -> tarifaCamion;
            default -> throw new IllegalArgumentException("Tipo de vehículo no soportado: " + tipoVehiculo);
        };

        return distancia * tarifaPorKm;
    }
}
