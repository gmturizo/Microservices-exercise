package co.com.bancolombia.vehiculo_service.service;

import co.com.bancolombia.vehiculo_service.dto.MotoDTO;
import co.com.bancolombia.vehiculo_service.mapper.VehiculoMapper;
import co.com.bancolombia.vehiculo_service.model.Moto;
import co.com.bancolombia.vehiculo_service.repository.MotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MotoService {

    private final MotoRepository motoRepository;
    private final VehiculoMapper mapper;

    public MotoService(MotoRepository motoRepository, VehiculoMapper mapper) {
        this.motoRepository = motoRepository;
        this.mapper = mapper;
    }

    public MotoDTO crear(MotoDTO dto) {
        if (motoRepository.findByPlaca(dto.placa()).isPresent()) {
            throw new RuntimeException("La placa ya está registrada");
        }
        Moto moto = mapper.toEntity(dto);
        return mapper.toDto(motoRepository.save(moto));
    }

    @Transactional(readOnly = true)
    public List<MotoDTO> obtenerTodos() {
        return motoRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MotoDTO obtenerPorId(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));
        return mapper.toDto(moto);
    }

    public MotoDTO actualizar(Long id, MotoDTO dto) {
        Moto motoExistente = motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto no encontrada"));

        motoExistente.setMarca(dto.marca());
        motoExistente.setModelo(dto.modelo());
        motoExistente.setPlaca(dto.placa());
        motoExistente.setCilindraje(dto.cilindraje());
        motoExistente.setTipoTransmision(dto.tipoTransmision());

        return mapper.toDto(motoRepository.save(motoExistente));
    }

    public void eliminar(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new RuntimeException("Moto no encontrada");
        }
        motoRepository.deleteById(id);
    }
}
