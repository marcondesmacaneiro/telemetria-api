package br.edu.unidavi.telemetria.domain.repository;

import br.edu.unidavi.telemetria.domain.model.LeituraSensor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bruno Pasqualini
 */
@RepositoryRestResource(collectionResourceRel = "leiturassensores", itemResourceRel = "leiturasensor", path = "leiturasensor")
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {

    public List<LeituraSensor> findAllByOrderByIdAsc();

}
