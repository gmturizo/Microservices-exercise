package co.com.bancolombia.vehiculo_service.mapper;

import co.com.bancolombia.vehiculo_service.dto.AutoDTO;
import co.com.bancolombia.vehiculo_service.dto.ElectricoDTO;
import co.com.bancolombia.vehiculo_service.dto.MotoDTO;
import co.com.bancolombia.vehiculo_service.model.Auto;
import co.com.bancolombia.vehiculo_service.model.Electrico;
import co.com.bancolombia.vehiculo_service.model.Moto;
import org.springframework.stereotype.Component;

@Component
public class VehiculoMapper {

    // --- MAPPER DE AUTO ---
    public Auto toEntity(AutoDTO dto) {
        if (dto == null) return null;
        Auto auto = new Auto();
        auto.setId(dto.id());
        auto.setMarca(dto.marca());
        auto.setModelo(dto.modelo());
        auto.setPlaca(dto.placa());
        auto.setNumeroPuertas(dto.numeroPuertas());
        auto.setTipoCombustible(dto.tipoCombustible());
        return auto;
    }

    public AutoDTO toDto(Auto entity) {
        if (entity == null) return null;
        return new AutoDTO(
                entity.getId(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getPlaca(),
                entity.getNumeroPuertas(),
                entity.getTipoCombustible()
        );
    }

    // --- MAPPER DE MOTO ---
    public Moto toEntity(MotoDTO dto) {
        if (dto == null) return null;
        Moto moto = new Moto();
        moto.setId(dto.id());
        moto.setMarca(dto.marca());
        moto.setModelo(dto.modelo());
        moto.setPlaca(dto.placa());
        moto.setCilindraje(dto.cilindraje());
        moto.setTipoTransmision(dto.tipoTransmision());
        return moto;
    }

    public MotoDTO toDto(Moto entity) {
        if (entity == null) return null;
        return new MotoDTO(
                entity.getId(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getPlaca(),
                entity.getCilindraje(),
                entity.getTipoTransmision()
        );
    }

    // --- MAPPER DE ELÉCTRICO ---
    public Electrico toEntity(ElectricoDTO dto) {
        if (dto == null) return null;
        Electrico electrico = new Electrico();
        electrico.setId(dto.id());
        electrico.setMarca(dto.marca());
        electrico.setModelo(dto.modelo());
        electrico.setPlaca(dto.placa());
        electrico.setCapacidadBateriaKwh(dto.capacidadBateriaKwh());
        electrico.setAutonomiaKm(dto.autonomiaKm());
        return electrico;
    }

    public ElectricoDTO toDto(Electrico entity) {
        if (entity == null) return null;
        return new ElectricoDTO(
                entity.getId(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getPlaca(),
                entity.getCapacidadBateriaKwh(),
                entity.getAutonomiaKm()
        );
    }
}
