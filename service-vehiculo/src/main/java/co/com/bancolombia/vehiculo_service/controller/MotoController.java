package co.com.bancolombia.vehiculo_service.controller;

import co.com.bancolombia.vehiculo_service.dto.MotoDTO;
import co.com.bancolombia.vehiculo_service.service.MotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @PostMapping
    public ResponseEntity<MotoDTO> crear(@Valid @RequestBody MotoDTO dto) {
        return new ResponseEntity<>(motoService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MotoDTO>> obtenerTodos() {
        return ResponseEntity.ok(motoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MotoDTO dto) {
        return ResponseEntity.ok(motoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        motoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
