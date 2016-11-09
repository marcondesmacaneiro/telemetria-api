package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.LeituraPontoSensor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "leituraspontossensores", itemResourceRel = "leiturapontosensor", path = "leiturapontosensor")
interface LeituraPontoSensorRepository extends JpaRepository<LeituraPontoSensor, Long> {

    public List<LeituraPontoSensor> findAllByOrderByIdAsc();

}
