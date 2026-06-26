package co.com.bancolombia.vehiculo_service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record MotoDTO(
        Long id,
        @NotBlank(message = "La marca no puede estar vacía")
        String marca,
        @NotBlank(message = "El modelo no puede estar vacío")
        String modelo,
        @NotBlank(message = "La placa no puede estar vacía")
        @Size(min = 6, max = 6, message = "La placa debe tener exactamente 6 caracteres")
        String placa,
        @NotNull(message = "El cilindraje es obligatorio")
        Integer cilindraje,
        @NotBlank(message = "El tipo de transmisión es obligatorio")
        String tipoTransmision
) {}
