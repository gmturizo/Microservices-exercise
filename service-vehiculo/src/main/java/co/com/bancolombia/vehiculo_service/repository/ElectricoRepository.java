package co.com.bancolombia.vehiculo_service.repository;

import co.com.bancolombia.vehiculo_service.model.Electrico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ElectricoRepository extends JpaRepository<Electrico, Long> {
    Optional<Electrico> findByPlaca(String placa);
}
