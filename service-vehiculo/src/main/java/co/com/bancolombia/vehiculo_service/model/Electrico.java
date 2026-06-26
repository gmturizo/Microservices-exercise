package co.com.bancolombia.vehiculo_service.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ELECTRICO")
public class Electrico extends Vehiculo {
    private Integer capacidadBateriaKwh;
    private Integer autonomiaKm;

    public Integer getCapacidadBateriaKwh() {
        return capacidadBateriaKwh;
    }

    public void setCapacidadBateriaKwh(Integer capacidadBateriaKwh) {
        this.capacidadBateriaKwh = capacidadBateriaKwh;
    }

    public Integer getAutonomiaKm() {
        return autonomiaKm;
    }

    public void setAutonomiaKm(Integer autonomiaKm) {
        this.autonomiaKm = autonomiaKm;
    }
}