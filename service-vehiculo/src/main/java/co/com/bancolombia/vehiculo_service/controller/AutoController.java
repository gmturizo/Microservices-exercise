package co.com.bancolombia.vehiculo_service.controller;

import co.com.bancolombia.vehiculo_service.dto.AutoDTO;
import co.com.bancolombia.vehiculo_service.service.AutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/autos")
public class AutoController {

    private final AutoService autoService;

    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @PostMapping
    public ResponseEntity<AutoDTO> crear(@Valid @RequestBody AutoDTO dto) {
        return new ResponseEntity<>(autoService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AutoDTO>> obtenerTodos() {
        return ResponseEntity.ok(autoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(autoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AutoDTO dto) {
        return ResponseEntity.ok(autoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        autoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
