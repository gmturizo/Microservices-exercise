package co.com.bancolombia.vehiculo_service.service;

import co.com.bancolombia.vehiculo_service.dto.ElectricoDTO;
import co.com.bancolombia.vehiculo_service.mapper.VehiculoMapper;
import co.com.bancolombia.vehiculo_service.model.Electrico;
import co.com.bancolombia.vehiculo_service.repository.ElectricoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ElectricoService {

    private final ElectricoRepository electricoRepository;
    private final VehiculoMapper mapper;

    public ElectricoService(ElectricoRepository electricoRepository, VehiculoMapper mapper) {
        this.electricoRepository = electricoRepository;
        this.mapper = mapper;
    }

    public ElectricoDTO crear(ElectricoDTO dto) {
        if (electricoRepository.findByPlaca(dto.placa()).isPresent()) {
            throw new RuntimeException("La placa ya está registrada");
        }
        Electrico electrico = mapper.toEntity(dto);
        return mapper.toDto(electricoRepository.save(electrico));
    }

    @Transactional(readOnly = true)
    public List<ElectricoDTO> obtenerTodos() {
        return electricoRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ElectricoDTO obtenerPorId(Long id) {
        Electrico electrico = electricoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo eléctrico no encontrado"));
        return mapper.toDto(electrico);
    }

    public ElectricoDTO actualizar(Long id, ElectricoDTO dto) {
        Electrico electricoExistente = electricoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo eléctrico no encontrado"));

        electricoExistente.setMarca(dto.marca());
        electricoExistente.setModelo(dto.modelo());
        electricoExistente.setPlaca(dto.placa());
        electricoExistente.setCapacidadBateriaKwh(dto.capacidadBateriaKwh());
        electricoExistente.setAutonomiaKm(dto.autonomiaKm());

        return mapper.toDto(electricoRepository.save(electricoExistente));
    }

    public void eliminar(Long id) {
        if (!electricoRepository.existsById(id)) {
            throw new RuntimeException("Vehículo eléctrico no encontrado");
        }
        electricoRepository.deleteById(id);
    }
}
