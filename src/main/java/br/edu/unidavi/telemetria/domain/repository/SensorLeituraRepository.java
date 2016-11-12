package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.SensorLeitura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bruno Pasqualini
 */
public interface SensorLeituraRepository extends JpaRepository<SensorLeitura, Long> {}
