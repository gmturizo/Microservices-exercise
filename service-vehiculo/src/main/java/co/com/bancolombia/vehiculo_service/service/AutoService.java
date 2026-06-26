package co.com.bancolombia.vehiculo_service.service;

import co.com.bancolombia.vehiculo_service.dto.AutoDTO;
import co.com.bancolombia.vehiculo_service.mapper.VehiculoMapper;
import co.com.bancolombia.vehiculo_service.model.Auto;
import co.com.bancolombia.vehiculo_service.repository.AutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AutoService {

    private final AutoRepository autoRepository;
    private final VehiculoMapper mapper;

    public AutoService(AutoRepository autoRepository, VehiculoMapper mapper) {
        this.autoRepository = autoRepository;
        this.mapper = mapper;
    }

    public AutoDTO crear(AutoDTO dto) {
        if (autoRepository.findByPlaca(dto.placa()).isPresent()) {
            throw new RuntimeException("La placa ya está registrada");
        }
        Auto auto = mapper.toEntity(dto);
        return mapper.toDto(autoRepository.save(auto));
    }

    @Transactional(readOnly = true)
    public List<AutoDTO> obtenerTodos() {
        return autoRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AutoDTO obtenerPorId(Long id) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));
        return mapper.toDto(auto);
    }

    public AutoDTO actualizar(Long id, AutoDTO dto) {
        Auto autoExistente = autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));

        autoExistente.setMarca(dto.marca());
        autoExistente.setModelo(dto.modelo());
        autoExistente.setPlaca(dto.placa());
        autoExistente.setNumeroPuertas(dto.numeroPuertas());
        autoExistente.setTipoCombustible(dto.tipoCombustible());

        return mapper.toDto(autoRepository.save(autoExistente));
    }

    public void eliminar(Long id) {
        if (!autoRepository.existsById(id)) {
            throw new RuntimeException("Auto no encontrado");
        }
        autoRepository.deleteById(id);
    }
}
