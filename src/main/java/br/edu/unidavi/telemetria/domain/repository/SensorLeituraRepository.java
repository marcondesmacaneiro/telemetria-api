package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.SensorLeitura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "sensorleituras", itemResourceRel = "sensorleitura", path = "sensorleitura")
public interface SensorLeituraRepository extends JpaRepository<SensorLeitura, Long> {

    public List<SensorLeitura> findAllByOrderByIdAsc();

}
