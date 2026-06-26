package co.com.bancolombia.vehiculo_service.controller;

import co.com.bancolombia.vehiculo_service.dto.ElectricoDTO;
import co.com.bancolombia.vehiculo_service.service.ElectricoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/electricos")
public class ElectricoController {

    private final ElectricoService electricoService;

    public ElectricoController(ElectricoService electricoService) {
        this.electricoService = electricoService;
    }

    @PostMapping
    public ResponseEntity<ElectricoDTO> crear(@Valid @RequestBody ElectricoDTO dto) {
        return new ResponseEntity<>(electricoService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ElectricoDTO>> obtenerTodos() {
        return ResponseEntity.ok(electricoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectricoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(electricoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElectricoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ElectricoDTO dto) {
        return ResponseEntity.ok(electricoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        electricoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
